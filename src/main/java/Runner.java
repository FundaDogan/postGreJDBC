import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {

        //1. Step: Registration to the driver
        //2. Step: Create connection with database

        JdbcUtils.connectToDatabase();


        //3. Step: Create statement
        Statement statement = JdbcUtils.createStatement();

        //4. Step: Execute the query
        JdbcUtils.execute("CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(20), worker_salary INT )");

        //5. Step: Close the connection and statement
        JdbcUtils.closeConnectionAndStatement();



    }
}
