import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "fundam");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees
        String sql1 ="update companies\n" +
                "set number_of_employees = 16000\n" +
                "where number_of_employees < (select avg(number_of_employees) from companies)\n";

        int numOfRecords = st.executeUpdate(sql1);

        System.out.println(numOfRecords + " rows updated");

        String sql2 = "select * from companies";
        ResultSet resultSet = st.executeQuery(sql2);

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+"-"+ resultSet.getString(2)+"-" + resultSet.getInt(3));
        }

        con.close();
        st.close();
    }
}
