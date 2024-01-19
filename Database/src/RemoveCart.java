package Database.src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveCart {
    public RemoveCart(String email) throws SQLException{
        Scanner in=new Scanner(System.in);
        UserDatabase db=new UserDatabase();
            System.out.println("ARE YOU WISH TO REMOVE A VEHICLE FROM CART TYPE YES OR NO");
            if(in.nextLine().equalsIgnoreCase("yes")){
                System.out.println("ENTER THE VEHICLE NUMBER");
                String str_num=in.nextLine();
                ResultSet remove_count_outer=db.count_remove_vehicle(str_num);
                if(remove_count_outer.next()){
                    int count_remove_value=remove_count_outer.getInt("count_remove");
                    if(count_remove_value==0){
                        System.out.println("YOU RENTED THE VEHICLE DOES NOT REMOVE IT====");
                    }
                    else{
                        int rows=db.remove_vehicle(str_num,email);
                        System.out.println(rows==1?"REMOVED SUCCESSFULLY":"CANNOT REMOVED IT I THINK ITS OTHERS");
                    }
                }
            }
    }
}
