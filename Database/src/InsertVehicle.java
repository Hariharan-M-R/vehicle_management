package Database.src;

import java.sql.SQLException;
import java.util.Scanner;

public class InsertVehicle  {
    public InsertVehicle() throws SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("ARE YOU WISH TO ADD\n 1)BIKE \n 2)CAR ");
        int vehicle_op=in.nextInt();
        Database st=new Database();
        if(vehicle_op==1){
            in.nextLine();
            System.out.println("BIKE MODEL NAME:");
            String bike_model=in.nextLine();
            System.out.println("BIKE NUMBER:");
            String bike_number=in.nextLine();
            System.out.println("BIKE RENTAL PRICE:");
            int bike_rental_price=in.nextInt();
            System.out.println("BIKE TRAVELLED KILOMETER TILL DATE");
            int bike_kilometer = in.nextInt();
            System.out.println("BIKE SERVICE STATUS");
            String bike_service_status = in.next();
            System.out.println("BIKE AVAILABILITY STATUS");
            String bike_avail_status = in.next();
            int rows=st.insertBike(bike_model,bike_number,bike_service_status,bike_rental_price,bike_kilometer,bike_avail_status);
            System.out.println(rows==1?"BIKE INSERTED SUCCESSFULLY":"ERROR OCCURS SOME MISMATCH DATATYPE GIVEN:");
            new AdminInterface();
        }
        else if(vehicle_op==2){
            in.nextLine();
            System.out.println("CAR MODEL NAME:");
            String car_model=in.nextLine();
            System.out.println("CAR NUMBER:");
            String car_number=in.nextLine();
            System.out.println("CAR RENTAL PRICE:");
            int car_rental_price=in.nextInt();
            System.out.println("CAR TRAVELLED KILOMETER TILL DATE");
            int car_kilometer = in.nextInt();
            System.out.println("CAR SERVICE STATUS");
            String car_service_status = in.next();
            System.out.println("CAR AVAILABILITY STATUS");
            String car_avail_status = in.next();
            int rows=st.insertCar(car_model,car_number,car_service_status,car_rental_price,car_kilometer,car_avail_status);
            System.out.println(rows==1?"CAR Inserted SUCCESSFULLY":"ERROR OCCURS SOME MISMATCH DATATYPE GIVEN:");
            new AdminInterface();
        }else{
            System.out.println("ENTER VALID NUMBER");
            new InsertVehicle();
        }
        st.service_check();
    }
}
