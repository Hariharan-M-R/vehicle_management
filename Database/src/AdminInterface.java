package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminInterface {
    public AdminInterface() throws SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("    SELECT OPTION");
        System.out.println("1)ADD VEHICLE \n2)MODIFY DETAILS \n3)DISPLAY VEHICLE \n4)DELETE VEHICLE \n5)CHANGE SECURITY DEPOSIT: \n6)SEARCH VEHICLE: \n7)EXIT");
        System.out.println("ENTER NUMBER");
        int op=in.nextInt();
        switch (op){
            case 1:
                new InsertVehicle();
                break;
            case 2:
                    new ModifyVehicle();
                break;
            case 3:
                    new DisplayVehicle();
                break;
            case 4:
                    new DeleteVehicle();
                break;
            case 5:
                    new SecurityDeposit();
                break;
            case 6:
                    new Search();
                break;
            case 7:
                        new Main();
                break;
            default:
                System.out.println("ENTER THE VALID NUMBER");
                new AdminInterface();
        }
    }
}
