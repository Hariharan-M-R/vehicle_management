package Database.src;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PayFine {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayString = currentDateTime.format(formatter);
    public PayFine(String email) throws SQLException {
        Scanner in = new Scanner(System.in);
        UserDatabase db = new UserDatabase();
        double payfines = 0;
        boolean flag = false;
        String vehicle_return_no;
        System.out.println("ARE YOU WISH TO RETURN THE VEHICLE ");
        if (in.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("VEHICLE MODEL LIKE(BIKE OR CAR):");
            String vehicle_type = in.nextLine().toLowerCase();
            System.out.println("ENTER THE VEHICLE NUMBER::");
            vehicle_return_no = in.nextLine().toLowerCase();
            System.out.println("ENTER YES TO CONTINUE THE PROCESS....");
            if (in.nextLine().equalsIgnoreCase("yes")) {
                ResultSet str_chh = db.payfines_outer(vehicle_return_no);
                if (str_chh.next()) {
                    int ccc = str_chh.getInt("str_check_return");
                    if (ccc == 0) {
                        System.out.println("THE VEHICLE NOT FOUND OR YOU MAY RETURN BACK IT RE-CHECK AGAIN!!!!!!!!!!");
                    } else {
                        db.payfines_update(vehicle_type, vehicle_return_no);
                        db.return_date(todayString, email, vehicle_return_no);
                        ResultSet der3 = db.query1(vehicle_return_no);
                        String vechile_rental_date_return = null;
                        while (der3.next())
                            vechile_rental_date_return = der3.getString(1);

                        ResultSet der2 = db.query2(vehicle_type, vehicle_return_no);
                        int vechile_rental_price_return = 0;
                        while (der2.next())
                            vechile_rental_price_return = der2.getInt(1);
                        int num1 = Integer.parseInt(vechile_rental_date_return.substring(Math.max(vechile_rental_date_return.length() - 2, 0)));
                        int num2 = Integer.parseInt(todayString.substring(Math.max(todayString.length() - 2, 0)));
                        int sub = (int) Math.abs(num2 - num1);
                        System.out.println((sub == 0) ? "YOU RETURN WITHIN A DAY" : ("YOU RENTED MORE DAYS" + sub));
                        if (sub > 0) {
                            System.out.println("DO YOU RUN MORE THAN 500kms A DAY:");
                            if (in.nextLine().equalsIgnoreCase("yes")) {
                                double sum = 0;
                                for (int i = 1; i <= sub; i++)
                                    sum += vechile_rental_price_return + (vechile_rental_price_return * 0.15);
                                payfines = sum;
                                flag = true;
                            } else {
                                double sum = 0;
                                for (int i = 1; i <= sub; i++)
                                    sum += vechile_rental_price_return;
                                payfines = sum;
                            }
                        } else {
                            System.out.println("YOU NEED TO PAY RENT FOR VEHICLE " + vehicle_return_no + " Model " + vehicle_type + " THE AMOUNT " + vechile_rental_price_return);
                        }
                        System.out.println("ARE YOU DAMAGE THE VEHICLE:::");
                        if (in.nextLine().equalsIgnoreCase("yes")) {
                            flag = true;
                            System.out.println("ENTER THE TYPE OF DAMAGE YOU CAUSE LIKE(LOW OR MEDIUM OR HIGH)");
                            if (in.nextLine().equalsIgnoreCase("low"))
                                payfines = vechile_rental_price_return + (vechile_rental_price_return * 0.20);
                            else if (in.nextLine().equalsIgnoreCase("medium"))
                                payfines = vechile_rental_price_return + (vechile_rental_price_return * 0.50);
                            else if (in.nextLine().equalsIgnoreCase("high"))
                                payfines = vechile_rental_price_return + (vechile_rental_price_return * 0.75);
                        }
                        if (flag) {
                            System.out.println("WE HAVE REDUCED THE FINES IN YOU SECURITY DEPOSIT AMOUNT");
                            ResultSet money = db.query3(email);
                            int depo_money = 0;
                            while (money.next())
                                depo_money = money.getInt(1);
                            double reduce = depo_money - payfines;
                            reduce = Math.abs(reduce);
                            db.query4(reduce, email);
                            System.out.println(reduce);
                        }
                    }
                }
            }
        }
    }
}
