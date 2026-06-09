package at.technikumwien.mse25.awt.hotelmario.common.exception;

public class RoomNotAvailableException extends RuntimeException {

    private final Long roomId;

    public RoomNotAvailableException(Long roomId) {
        super("Room " + roomId + " is not available for the requested period");
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
