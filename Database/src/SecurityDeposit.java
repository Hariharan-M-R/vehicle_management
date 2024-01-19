package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class SecurityDeposit {
    public SecurityDeposit() throws SQLException{
        Scanner in=new Scanner(System.in);
        Database db=new Database();
        System.out.println("SELECT \n 1)VISUALIZE DEPOSIT \n2) CHANGE DEPOSIT");
        if (in.nextInt()==1) {
            System.out.println("1) BIKE \n2) CAR ");
            if (in.nextInt() == 1) {
                System.out.println();
                System.out.println("THE SECURITY DEPOSIT TO BIKE : " + db.getInitialDepositOfBike());
                System.out.println();
            } else {
                System.out.println();
                System.out.println("THE SECURITY DEPOSIT TO CAR : " + db.getInitialDepositOfCar());
                System.out.println();
            }
        } else {
                System.out.println("1) BIKE \n2) CAR ");
                if (in.nextInt() == 1) {
                    System.out.println("ENTER THE AMOUNT TO CHANGE BIKE DEPOSIT");
                    int changeDeposit = in.nextInt();
                    System.out.println(db.setInitialDepositOfBike(changeDeposit)?"UPDATE SUCCESSFULLY":"UPDATE UNSUCCESSFULLY");
                } else {
                    System.out.println("ENTER THE AMOUNT TO CHANGE CAR DEPOSIT");
                    int change_car_deposit = in.nextInt();
                    System.out.println(db.setInitialDepositOfCar(change_car_deposit) ? "UPDATE SUCCESSFULLY" : "UPDATE UNSUCCESSFULLY");
                }

        }
        new AdminInterface();
    }
}
