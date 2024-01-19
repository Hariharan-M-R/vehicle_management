package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
    public User(String email, String password) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("-----SIGHT-----");
            System.out.println("1)VIEW ALL VEHICLES: \n2)RENT A VEHICLE: \n3)REMOVE A VEHICLE FROM CART: \n4)PAYMENT: \n5)VIEW ALL YOUR DETAILS:  \n6)CLOSE ACCOUNT:  \n7)EXIT:");
            int USER_NO = sc.nextInt();
            switch (USER_NO) {
                case 1:
                    new ViewVehicles();
                    break;
                case 2:
                    new RentVehicle(email);
                    break;
                case 3:
                    new RemoveCart(email);
                    break;
                case 4:
                    new PayFine(email);
                    break;
                 case 5:
                     new DisplayDetails(email);
                     break;
                 case 6:
                     new CloseAccount(email);
                     break;
                 case 7:
                     System.out.println("OUTED");
                     flag = false;
                     new Main();
                     break;
            }
        }
    }

}
