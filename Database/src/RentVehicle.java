    package Database.src;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.Scanner;

    public class RentVehicle {
        Scanner in=new Scanner(System.in);
        UserDatabase db=new UserDatabase();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = currentDateTime.format(formatter);
        static String vehicleType=null;
        public RentVehicle(String email) throws SQLException{
            int vehicleRentalPrice=0;

            System.out.println("RENTING A VEHICLE.............");
                ResultSet rentCount = db.rentCheck(email,todayString);
                if (rentCount.next()) {
                    int email_count = rentCount.getInt("start_count");
                    if (email_count >= 2) {
                        System.out.println("You have done rent for a day :" + todayString);
                    } else {
                        System.out.println("ENTER THE VEHICLE NUMBER");
                        String vehicleNumber = in.nextLine();
                        ResultSet rs = db.select_all(vehicleNumber,"bike");
                        if (rs.next()) {
                            int count = rs.getInt("demo");
                            if (count == 0) {
                                ResultSet rs1 =  db.select_all(vehicleNumber,"car");
                                if (rs1.next()) {
                                    int count1 = rs1.getInt("demo");
                                    if (count1 == 0)
                                        System.out.println("VEHICLE NOT FOUND....");
                                    else {
                                        vehicleType = "car";
                                    }
                                }
                            } else {
                                vehicleType = "bike";
                            }
                            System.out.println("START");
                            ResultSet availabilityCheck = db.vehicleAvailable(vehicleType,vehicleNumber);
                            if (availabilityCheck.next()) {
                                int availabilityCheckCount = availabilityCheck.getInt("checking");
                                if (availabilityCheckCount == 0)
                                    System.out.println("YOU CANNOT RENT A VEHICLE BECAUSE ITS NOT AVAILABLE");
                                else {
                                    System.out.println("Hello");
                                    ResultSet der = db.price(vehicleType,vehicleNumber);
                                    while (der.next())
                                        vehicleRentalPrice = der.getInt(1);
                                    System.out.println("DO YOU WISH TO RENT A VEHICLE OR ADD TO CART TYPE 'YES' OR 'NO' :::::::::");
                                    if (in.nextLine().equalsIgnoreCase("yes")) {
                                        ResultSet fds = db.already_find(email);
                                        if (fds.next()) {
                                            int czz = fds.getInt("count_user");
                                            if (czz == 0) {
                                                db.insert_query(email);
                                            }
                                        }
                                        ResultSet mismatch_query = db.non_rent(email,vehicleNumber);
                                        if (mismatch_query.next()) {
                                            int mismatch_value = mismatch_query.getInt("non_rents");
                                            if (mismatch_value == 1) {
                                                System.out.println("YOU ALREADY ADDED IT TO THE CART SO IT DOESN'T GET ADDED AGAIN");
                                                System.out.println("DO YOU WISH TO RENT THE VEHICLE TYPE YES OR NO");
                                                if (in.nextLine().equalsIgnoreCase("yes")) {
                                                    db.update_vehicle_true(vehicleNumber,email,todayString);
                                                }
                                            }
                                            else db.insert_cart_details(vehicleType,vehicleNumber,vehicleRentalPrice,email,todayString);

                                            db.non_available(vehicleType,vehicleNumber);
                                            System.out.println("VEHICLE DETAILS:::::::::::");
                                            ResultSet vehicle_rented_details_select= db.vehicle_details(vehicleType,vehicleNumber);
                                            while (vehicle_rented_details_select.next()) {
                                                System.out.println("VEHICLE MODEL :" + vehicle_rented_details_select.getString(1) +
                                                        "\nVEHICLE NUMBER :" + vehicle_rented_details_select.getString(2) +
                                                        "\nVEHICLE RUINED KILOMETER :" + vehicle_rented_details_select.getString(3) +
                                                        "\nVEHICLE RENTAL PRICE :" + vehicle_rented_details_select.getString(4));
                                            }
                                        }
                                    }
                                    else {
                                        ResultSet non_ren=db.rent_false_check(email,vehicleNumber);
                                        if(non_ren.next()){
                                            int count_non_rent=non_ren.getInt("non_rent");
                                            if(count_non_rent==1){
                                                System.out.println("YOU ALREADY ADDED IT TO THE CART SO IT DOESN'T GET ADDED AGAIN:::::");
                                            }
                                            else {
                                                System.out.println("YOU ARE ADDING INTO A CART::");
                                                db.insert_false(vehicleType,vehicleNumber,vehicleRentalPrice,email);
                                            }
                                        }
                                    }
                                    System.out.println("======HAVE A SAFE JOURNEY ======");
                            }
                        }
                    }
                }
            }
        }
    }
