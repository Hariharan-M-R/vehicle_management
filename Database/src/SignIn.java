package Database.src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignIn {
    public SignIn(String email,String password){
        try {
            Database obj = new Database();
            Connection con = obj.getConnect();
            Statement st = con.createStatement();
            String check_email_password = "select user_email,user_password from users where user_email='" + email + "'AND user_password='" + password + "'";
            ResultSet rows = st.executeQuery(check_email_password);
            boolean flag = false;
            while (rows.next()) {
                if (rows.getString(1).equals(email) && rows.getString(2).equals(password)) {
                    System.out.println("WELCOME USER");
                    flag = true;
                    new User(email,password);
                }
            }
            if (!flag) {
                System.out.println("You need to create an account: ");
                new Main();
            }
        } catch (SQLException e) {
            System.out.println("Syntax error");
        }
    }
}
