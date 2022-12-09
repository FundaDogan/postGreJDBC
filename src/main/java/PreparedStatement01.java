import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "fundam");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement

        //1. Step: Create Prepared Statement Query
        String sql1 = "update companies set number_of_employees=? where company = ?";

        //2. Step: Create prepared statement object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Step: Assign the values by using 'set...()' methods.-- setInt,setString etc.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4. Step: Execute the query
        int numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println(numOfRecordsUpdated);

        // 5.step: see the result- records
        String sql2 = "select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while (resultSet1.next()){

            System.out.println(resultSet1.getInt(1)+"-"+
                               resultSet1.getString(2)+"-"+
                               resultSet1.getInt(3));
        }

        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println(numOfRecordsUpdated2);

        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){

            System.out.println(resultSet2.getInt(1)+"-"+
                                resultSet1.getString(2)+"-"+
                                resultSet1.getInt(3));

            con.close();
            st.close();
            pst1.close();

        }
    }
}
