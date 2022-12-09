import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","fundam");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        System.out.println("Connection Success");

        //4. Step: Execute the query

        /*
            execute() method can be used in DDL(Table creation, drop table, alter table) and DQL(Select)
            1)If you use execute() method in DDL you will get false everytime.
            2)If you use execute() method in DQL you will get false or true
            When you use execute() method in DQL, if you get ResultSet Object as return you will get true
            otherwise you will get false.
        */


        //1.Example: Create a workers table with the columns worker_id,worker_name, worker_salary

        String sql1 = "Create table workers(worker_id varchar(50),worker_name varchar(20),worker_salary integer)";
        st.execute(sql1); // database'e create table codunu gonderdim.
        Boolean result1 = st.execute(sql1); // gelen cevapta record yok sadece table var. result1 containerin'a koyacak record yok
        System.out.println(result1);// returns false, means no record returned


        //2.Example: Alter table by adding worker_address column into the workers table
        String sql2 = "Alter table workers Add worker_address Varchar(50)";
        Boolean result2 = st.execute(sql2);
        System.out.println(result2); // returns false


        //3. Example: Drop the table
        String sql3 = "drop table workers";
        Boolean result3 = st.execute(sql3);
        System.out.println(result3); // false


        //5. Step: Close the connection and statement
        con.close();
        st.close();

    }
}
