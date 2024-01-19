package Database.src;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayDetails {
    public DisplayDetails(String email) throws SQLException{
        UserDatabase db=new UserDatabase();
            System.out.println("USER DETAILS........");
            ResultSet di=db.query5(email);
            while(di.next())
                System.out.println(" USER EMAIL :" + email + " VEHICLE MODEL :" + di.getString(2) + " VEHICLE NUMBER :" + di.getString(3) + "  RENTED DATE :" + di.getDate(4) + "  RETURN DATE :" + di.getDate(5) );
    }
}
