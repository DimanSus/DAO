import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public final class GetConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/mybase";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "root";

        public static Connection open() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD );
        }
    }
