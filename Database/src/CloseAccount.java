package Database.src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CloseAccount {
    public CloseAccount(String email) throws SQLException{
        Scanner in=new Scanner(System.in);
        UserDatabase db=new UserDatabase();
        System.out.println("ARE YOU WISH TO CLOSE THE ACCOUNT::::::");
        if(in.nextLine().equalsIgnoreCase("yes")) {
            ResultSet ssr = db.query6(email);
            int amount_user = 0;
            while (ssr.next())
                amount_user = ssr.getInt(1);
            System.out.println("COLLECT YOUR REMAINING BALANCE:  " + amount_user);
            db.query7(email);
        }
    }
}
