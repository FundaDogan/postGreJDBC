import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private static Connection con;
    private static Statement statement;

    //1. Step: Registration to the driver
    //2. Step: Create connection with database

    public static Connection connectToDatabase(){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "fundam");
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return con;
    }

    // 3. step create statement

        public static Statement createStatement(){

            try {
                statement = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return statement;
        }


    //4. Step: Execute the query
    public static boolean execute(String query){
        boolean isExecute;

        try {
            isExecute = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Query Executed");
        return isExecute;
    }

    //5. Step: Close the connection and statement
    public static void closeConnectionAndStatement(){
        try {
            con.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(con.isClosed() && statement.isClosed()){
                System.out.println("connection and statement are closed");
            }else{
                System.out.println("connection and statement are not closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
