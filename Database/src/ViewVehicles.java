package Database.src;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewVehicles {
    public ViewVehicles() throws SQLException{
        Scanner in=new Scanner(System.in);
        System.out.println("SELECT ONE \n 1)BIKE \n 2)CAR");
        int vehicleType=in.nextInt();
        String type=vehicleType==1?"bike":"car";
            Database db=new Database();
            System.out.println("VEHICLE MODEL: "+type);
            System.out.println(type+" DETAILS............");
            System.out.println("VEHICLE_MODEL || VEHICLE_NUMBER || VEHICLE_SERVICE_STATUS || VEHICLE_RENTAL_PRICE || VEHICLE_KILOMETER || AVAILABILITY");
            System.out.println();
            ArrayList<String> list=  (ArrayList<String>) db.visualizeAllVehicleView(type);
            int c=1;
            for(String m:list) {
                System.out.println(c + ". " + m);
                c++;
            }
            System.out.println();
//            new User();
        }
}
