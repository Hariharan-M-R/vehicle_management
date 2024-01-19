package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class ModifyVehicle {
    public ModifyVehicle() throws SQLException {
        Database st=new Database();
        Scanner in=new Scanner(System.in);
        System.out.println("WHAT DO YOU WISH TO MODIFY \n 1)BIKE \n 2)CAR ");
        int vehicle_num=in.nextInt();
        String type=vehicle_num==1?"bike":"car";
        System.out.println("    SELECT OPTION");
        System.out.println("WHICH DATA DO YOU NEED TO CHANGE \n1)VEHICLE_SERVICE_STATUS \n2)VEHICLE_RENTAL_PRIZE \n3)VEHICLE_KILOMETER \n4)AVAILABILITY");
        int a=in.nextInt();
        in.nextLine();
        System.out.println("ENTER THE VEHICLE_NUMBER");
        String dbnum=in.nextLine();
        switch (a){
            case 1:
                System.out.println("ENTER THE VEHICLE_SERVICE_STATUS");
                String update_status=in.nextLine();
                int rows2=st.updateVehicleService(update_status,dbnum,type);
                System.out.println(rows2==1?"SERVICE UPDATE SUCCESSFULLY":" VEHICLE NOT FOUND ");
                new AdminInterface();
                break;
            case 2:
                System.out.println("ENTER THE VEHICLE_RENTAL_PRIZE");
                int update_prize=in.nextInt();
                int rows3=st.updateVehicleRent(update_prize,dbnum,type);
                System.out.println(rows3==1?"RENTAL PRICE UPDATE SUCCESSFULLY":" VEHICLE NOT FOUND ");
                new AdminInterface();
                break;
            case 3:
                System.out.println("ENTER THE VEHICLE_KILOMETER");
                int update_kilo=in.nextInt();
                int rows4=st.updateVehicleKilometer(update_kilo,dbnum,type);
                System.out.println(rows4==1?"KILOMETER UPDATE SUCCESSFULLY":" VEHICLE NOT FOUND  ");
                new AdminInterface();
                break;
            case 4:
                System.out.println("ENTER THE AVAILABILITY");
                String update_avail=in.next();
                int rows5=st.updateVehicleAvailability(update_avail,dbnum,type);
                System.out.println(rows5==1?"AVAILABILITY UPDATE SUCCESSFULLY":" VEHICLE NOT FOUND  ");
                new AdminInterface();
                break;
            default:
                System.out.println("ENTER THE VALID NUMBER");
                new AdminInterface();
        }
    }
}
