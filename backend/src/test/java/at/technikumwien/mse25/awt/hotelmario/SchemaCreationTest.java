package at.technikumwien.mse25.awt.hotelmario;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class SchemaCreationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void tables_areCreated() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();
            assertTableExists(meta, "room");
            assertTableExists(meta, "extra");
            assertTableExists(meta, "room_extra");
            assertTableExists(meta, "booking");
        }
    }

    @Test
    void room_hasExpectedColumns() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();
            assertColumnExists(meta, "room", "id");
            assertColumnExists(meta, "room", "title");
            assertColumnExists(meta, "room", "description");
            assertColumnExists(meta, "room", "image_url");
            assertColumnExists(meta, "room", "price_per_night");
        }
    }

    @Test
    void extra_hasExpectedColumns() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();
            assertColumnExists(meta, "extra", "id");
            assertColumnExists(meta, "extra", "name");
            assertColumnExists(meta, "extra", "icon");
            assertColumnExists(meta, "extra", "description");
        }
    }

    @Test
    void roomExtra_hasForeignKeyColumns() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();
            assertColumnExists(meta, "room_extra", "room_id");
            assertColumnExists(meta, "room_extra", "extra_id");
        }
    }

    @Test
    void booking_hasExpectedColumns() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();
            assertColumnExists(meta, "booking", "id");
            assertColumnExists(meta, "booking", "room_id");
            assertColumnExists(meta, "booking", "first_name");
            assertColumnExists(meta, "booking", "last_name");
            assertColumnExists(meta, "booking", "email");
            assertColumnExists(meta, "booking", "check_in");
            assertColumnExists(meta, "booking", "check_out");
            assertColumnExists(meta, "booking", "breakfast");
            assertColumnExists(meta, "booking", "created_at");
        }
    }

    private void assertTableExists(DatabaseMetaData meta, String table) throws Exception {
        try (ResultSet rs = meta.getTables(null, null, table.toUpperCase(), new String[]{"TABLE"})) {
            assertTrue(rs.next(), "Expected table to exist: " + table);
        }
    }

    private void assertColumnExists(DatabaseMetaData meta, String table, String column) throws Exception {
        try (ResultSet rs = meta.getColumns(null, null, table.toUpperCase(), column.toUpperCase())) {
            assertTrue(rs.next(), "Expected column '%s' on table '%s'".formatted(column, table));
        }
    }
}
