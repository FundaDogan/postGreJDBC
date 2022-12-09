import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "fundam");
        Statement st = con.createStatement();

        //1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table

        //1. Way: By using OFFSET and FETCH NEXT

        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";

        ResultSet resultSet1 = st.executeQuery(sql1);

        while(resultSet1.next()){

            System.out.println(resultSet1.getString("company")+"->"+resultSet1.getInt("number_of_employees"));

        }


        //2. Way: BY using Subquery
        String sql2 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "                                FROM companies\n" +
                "                                WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "                                                             FROM companies))";


        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){

            System.out.println(resultSet2.getString("company")+"->"+resultSet2.getInt("number_of_employees"));
        }

    //2.Example: Find the company names and number of employees whose number of employees is less than the
        // average number of employees

        String sql3 = "select company ,number_of_employees\n" +
                "from companies\n" +
                "where number_of_employees < (select avg(number_of_employees) from companies)";

        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()){

            System.out.println(resultSet3.getString("company")+"->"+resultSet3.getInt("number_of_employees"));

        }

        con.close();
        st.close();

    }
}
