package at.technikumwien.mse25.awt.hotelmario.components.rooms.api.v1;

import at.technikumwien.mse25.awt.hotelmario.common.PageResult;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.ExtraDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.dtos.v1.RoomDto;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.api.mapper.v1.RoomMapper;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.model.RoomEntity;
import at.technikumwien.mse25.awt.hotelmario.components.rooms.service.RoomService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoomService roomService;

    @MockitoBean
    private RoomMapper roomMapper;

    @Test
    void getRooms_returnsPagedResult() throws Exception {
        RoomEntity entity = new RoomEntity();
        RoomDto dto = sampleRoomDto(1L);
        var pageResult = new PageResult<>(List.of(entity), 0, 5, 1L, 1);

        when(roomService.findAll(0, 5)).thenReturn(pageResult);
        when(roomMapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/v1/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Deluxe Suite"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));
    }

    @Test
    void getRooms_customPageAndSize_passesParamsToService() throws Exception {
        RoomEntity entity = new RoomEntity();
        RoomDto dto = sampleRoomDto(2L);
        var pageResult = new PageResult<>(List.of(entity), 1, 3, 4L, 2);

        when(roomService.findAll(1, 3)).thenReturn(pageResult);
        when(roomMapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/v1/rooms").param("page", "1").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(3))
                .andExpect(jsonPath("$.totalElements").value(4));
    }

    @Test
    void getRoomById_exists_returnsRoom() throws Exception {
        RoomEntity entity = new RoomEntity();
        RoomDto dto = sampleRoomDto(1L);

        when(roomService.findById(1L)).thenReturn(Optional.of(entity));
        when(roomMapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/v1/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Deluxe Suite"))
                .andExpect(jsonPath("$.pricePerNight").value(149.99));
    }

    @Test
    void getRoomById_notFound_returns404() throws Exception {
        when(roomService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/v1/rooms/99"))
                .andExpect(status().isNotFound());
    }

    private RoomDto sampleRoomDto(Long id) {
        return RoomDto.builder()
                .id(id)
                .title("Deluxe Suite")
                .description("Spacious suite with a king-size bed and city view.")
                .imageUrl("/images/rooms/" + id + ".jpg")
                .pricePerNight(BigDecimal.valueOf(149.99))
                .extras(List.of(
                        ExtraDto.builder().id(1L).name("Wi-Fi").icon("wifi")
                                .description("Free high-speed wireless internet").build(),
                        ExtraDto.builder().id(2L).name("Parking").icon("p-square")
                                .description("Underground parking spot included").build()))
                .build();
    }
}
