package com.junit;

import com.dbconn.DBConnection;
import java.sql.Connection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SingletonJUnit {

    @Test
    void testSingletonConnection() throws Exception {
        // Obtain two connections
        Connection conn1 = DBConnection.getConnection();
        Connection conn2 = DBConnection.getConnection();

        // Assert that the connections are the same instance
        assertNotNull(conn1, "First connection should not be null");
        assertNotNull(conn2, "Second connection should not be null");
        assertSame(conn1, conn2, "Both connections should be the same instance (Singleton)");

        // Assert that the connection is open
        assertFalse(conn1.isClosed(), "Connection should be open");
    }
}

