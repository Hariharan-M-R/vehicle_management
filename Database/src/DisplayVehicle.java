package Database.src;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayVehicle {
    String type="";
    public DisplayVehicle() throws SQLException {
        Scanner in = new Scanner(System.in);
        Database db = new Database();

            System.out.println("ARE YOU WISH TO VISUALIZE \n 1)BIKE \n 2)CAR");
            int vehicleNum=in.nextInt();
            type=vehicleNum==1?"bike":"car";
            ResultSet resultSet = db.tableCheck(type);
            if (resultSet.next()) {
                int rowCount = resultSet.getInt("row_count");
                if (rowCount == 0) {
                    System.out.println("The table is empty in vehicle table.");
                }
                else {
                    System.out.println("SELECT \n 1)DISPLAY ALL VEHICLES \n 2)DISPLAY ALL NOT-SERVICED VEHICLES \n 3)RENTED DETAILS");
                    int dis_op=in.nextInt();
                    if(dis_op==1){
                        visualizeAllVehicle(type);
                    }else if(dis_op==2){
                        visualizeAllDueVehicle(type);
                    }
//                    visualizeAllRentalNonrentalVehicles(type);
                }
            }
        }
    public void visualizeAllVehicle(String type) throws SQLException {
        Database db=new Database();
            System.out.println("VEHICLE MODEL: "+type);
            System.out.println(type+" DETAILS............");
            System.out.println("VEHICLE_MODEL || VEHICLE_NUMBER || VEHICLE_SERVICE_STATUS || VEHICLE_RENTAL_PRICE || VEHICLE_KILOMETER || AVAILABILITY");
            System.out.println();
            ArrayList<String> list=  (ArrayList<String>) db.visualizeAllVehicleView(type);
            for(String m:list)
                System.out.println(m);
            System.out.println();
        new AdminInterface();
    }
    public void visualizeAllDueVehicle(String type) throws SQLException {
        Database db=new Database();
            System.out.println("VEHICLE MODEL: "+type);
            System.out.println("VEHICLE_NUMBER|| VEHICLE_KILOMETER  || VEHICLE_SERVICE_STATUS");
            System.out.println();
            ArrayList<String>list1= (ArrayList<String>) db.visualizeAllDueVehicleView(type);
            for(String m:list1)
                System.out.println(m);
            System.out.println();
        new AdminInterface();

    }
}
