package Database.src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Search {
    public  Search() throws SQLException {
        Scanner in = new Scanner(System.in);
        Database db = new Database();
        System.out.println("SEARCH BY VEHICLE NUMBER");
        String search_number = in.next();
        ResultSet rs = db.searchType("bike", search_number);
        if (rs.next()) {
            int count = rs.getInt("demo");
            if (count == 0) {
                ResultSet rs1 = db.searchCount("car", search_number);
                if (rs1.next()) {
                    int count1 = rs1.getInt("dem");
                    if (count1 == 0) {
                        System.out.println("VEHICLE NOT FOUND....");
                    }else {
                        System.out.println("VEHICLE TYPE = 'CAR'");
                        System.out.println();
                        System.out.println("CAR_MODEL || CAR_NUMBER || CAR_SERVICE_STATUS || CAR_RENTAL_PRICE || CAR_KILOMETER || AVAILABILITY");
                        System.out.println(db.vehicleFinding("car", search_number));
                        System.out.println();
                    }
                }
            } else {
                System.out.println("VEHICLE TYPE = 'BIKE'");
                System.out.println();
                System.out.println("BIKE_MODEL || BIKE_NUMBER || BIKE_SERVICE_STATUS || BIKE_RENTAL_PRICE || BIKE_KILOMETER || AVAILABILITY");
                System.out.println(db.vehicleFinding("bike", search_number));
                System.out.println();

            }
        }
        new AdminInterface();
    }
}
