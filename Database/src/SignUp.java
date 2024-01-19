package Database.src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp {
    public SignUp(String email, String password) {
        boolean flag = false;
        try {
            Database obj=new Database();
            Connection con =obj.getConnect();
            Statement st = con.createStatement();
            String checkExistingUser = "SELECT user_email FROM users WHERE user_email='" + email + "'";
            ResultSet existingUserRows = st.executeQuery(checkExistingUser);
            if (existingUserRows.next()) {
                System.out.println("User already exists. Please sign in or use a different email for sign up.");
            } else {
                String insertSignUp = "INSERT INTO users(user_email, user_password) VALUES ('" + email + "','" + password + "')";
                int rows = st.executeUpdate(insertSignUp);
                System.out.println("SIGN_UP DETAILS INSERTED SUCCESSFULLY");

            }
            new Main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


