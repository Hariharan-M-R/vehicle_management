package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    public Admin(String email,String password) throws SQLException {
        if(email.equals("hari@gmail.com") && password.equals("hari")){
            System.out.println("    WELCOME ADMIN");
            new AdminInterface();
        }else{
            System.out.println("Invalid Login");
            new Main();
        }
    }
}
