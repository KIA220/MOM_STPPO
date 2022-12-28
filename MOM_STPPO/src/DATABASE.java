import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.JDBC;

public class DATABASE {


    DATABASE() throws SQLException {
        DriverManager.registerDriver(new JDBC());
    }

    public String pass() {
        try {
            Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
            String url1 = "jdbc:sqlite:src/DB/StorageDB.db";
            try (Connection conn = DriverManager.getConnection(url1)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Storage");
                StringBuilder data= new StringBuilder();
                while (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String numberItems = resultSet.getString(3);
                    data.append(id).append(" ").append(name).append(" ").append(numberItems).append("\n");

                }
                System.out.println("DOING WORK...");
                conn.close();
                return data.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        }

    }
}
