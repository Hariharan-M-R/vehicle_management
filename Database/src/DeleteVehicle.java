package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteVehicle  {
    public DeleteVehicle() throws SQLException {
        Scanner in = new Scanner(System.in);
        Database db=new Database();
        System.out.println("ARE YOU WISH TO DELETE VEHICLE FROM TABLE \n 1)BIKE \n 2)CAR ");
        int vehicle_num=in.nextInt();
        String type=vehicle_num==1?"bike":"car";
        in.nextLine();
        System.out.println("ENTER THE "+type+" NUMBER:");
        String deletenum=in.nextLine();
        int rows=db.deleteVehicle(type,deletenum);
        System.out.println(rows==1?type+" VEHICLE DELETED SUCCESSFULLY FROM DATABASE":"VEHICLE NOT FOUND");
        new AdminInterface();
    }
}
