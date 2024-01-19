package Database.src;

import java.sql.*;

public class UserDatabase {
    private static Connection connection;
    private static Statement statement;

    static {
        try {
            connection = getConnect();
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle", "root", "");
    }
    public ResultSet rentCheck(String email, String todayString) throws SQLException {
        String rentCount = "select count(*) as start_count from cart where user_email='"+ email +"' AND rented_count=true AND rented_date='"+todayString+"';";
        return statement.executeQuery(rentCount);
    }
    public  ResultSet select_all(String VehicleNumber,String type) throws SQLException {
        String search = "SELECT COUNT(*) AS demo FROM " + type + " WHERE " + type + "_number='" + VehicleNumber + "';";
        return  statement.executeQuery(search);
    }
    public ResultSet vehicleAvailable(String vehicleType,String vehicleNumber) throws SQLException {
        String availabilityCheck = "SELECT COUNT(*) AS checking FROM " + vehicleType + " WHERE availability = 'AVAILABLE' AND " + vehicleType + "_number='" + vehicleNumber + "';";
        return  statement.executeQuery(availabilityCheck);
    }
    public ResultSet price(String vehicleType, String vehicleNumber) throws SQLException {
        String price = "SELECT "+vehicleType+"_rental_price FROM " + vehicleType + " WHERE " + vehicleType + "_number='" + vehicleNumber + "';";
        return statement.executeQuery(price);
    }

    public ResultSet already_find(String email) throws SQLException {
        String already = "select count(*) as count_user from user_deposit where user_email='"+ email +"';";
        return  statement.executeQuery(already);
    }
    public void insert_query(String email) throws SQLException {
        String rented_details = "insert into user_deposit(user_email,security_deposit) values ('" + email + "'," + 30000 +");";
        int user_security = statement.executeUpdate(rented_details);
    }
    public ResultSet non_rent(String email,String vehicleNumber) throws SQLException {
        String mismatch = "select count(*) as non_rents from cart where user_email='" + email + "'AND  rented_count=false AND vehicle_number='"+ vehicleNumber +"';";
        return statement.executeQuery(mismatch);
    }
    public void update_vehicle_true(String vehicleNumber,String email,String todayDate) throws SQLException {
        String update_exit = "update cart set (rented_count="+true+",rented_date="+todayDate+",return_date="+todayDate+") where vehicle_number='"+vehicleNumber+"' AND user_email='"+email+"';";
        int rows_end = statement.executeUpdate(update_exit);
    }
    public void insert_cart_details(String vehicleType,String vehicleNumber,int price,String userEmail,String todayDate) throws SQLException {
        String cart_details = "insert into cart(user_email, vehicle_model, vehicle_number, rent_amount, rented_count,rented_date,return_date) values('" + userEmail + "','" + vehicleType + "','" + vehicleNumber + "','" + price + "', 1,'"+todayDate+"','"+todayDate+"')";
        int cart_affected = statement.executeUpdate(cart_details);
    }
    public void non_available(String vehicleType,String vehicleNumber) throws SQLException {
        String update_bikes_query = "UPDATE " + vehicleType + " SET availability='NOT-AVAILABLE' WHERE " + vehicleType + "_number='" + vehicleNumber + "';";
        int cart_update_bikes = statement.executeUpdate(update_bikes_query);
    }
    public ResultSet vehicle_details(String vehicleType,String vehicleNumber) throws SQLException {
        String vehicle_rented_details = "SELECT " + vehicleType + "_model, " + vehicleType + "_number, " + vehicleType + "_kilometer," + vehicleType + "_rental_price FROM " + vehicleType + " WHERE " + vehicleType + "_number='" + vehicleNumber + "';";
        return statement.executeQuery(vehicle_rented_details);
    }
    public ResultSet rent_false_check(String email,String vehicleNumber) throws SQLException {
        String non_rent_check="select count(*) as non_rent from cart where user_email='"+email+"' AND rented_count=false AND vehicle_number='"+vehicleNumber+"';";
        return statement.executeQuery(non_rent_check);
    }
    public void insert_false(String vehicleType,String vehicleNumber,int price,String email) throws SQLException {
        String cart_details = "insert into cart(vehicle_model,vehicle_number,rent_amount,user_email,rented_count) values('" + vehicleType + "','" + vehicleNumber + "','" + price + "','" + email + "'," + false + ")";
        int cart_affected = statement.executeUpdate(cart_details);
    }
    public ResultSet  count_remove_vehicle(String str_num) throws SQLException {
        String remove_count="select count(*) as count_remove from cart where vehicle_number='"+str_num+"' AND rented_count=false;";
        return statement.executeQuery(remove_count);
    }

    public int  remove_vehicle(String str_num,String email) throws SQLException {
        String query1="delete from cart where vehicle_number='"+str_num+"' AND user_email='"+email+"';";
        return statement.executeUpdate(query1);
    }
    public ResultSet payfines_outer(String vehicle_return_no) throws SQLException {
        String str_check="select count(*) as str_check_return from cart where vehicle_number='"+vehicle_return_no+"' AND rented_count=true;";
        return statement.executeQuery(str_check);
    }

    public void payfines_update(String vehicle_type,String vehicle_return_no) throws SQLException {
        String str1_update_vehicle_table="update "+vehicle_type+" set availability='AVAILABLE' where " +vehicle_type+"_number='"+vehicle_return_no+"';";
        int rows_affected_update=statement.executeUpdate(str1_update_vehicle_table);
    }

    public void return_date(String todayString,String email,String vehicle_return_no) throws SQLException {
        String str_return = "update cart set return_date='" + todayString + "' , rented_count=false where user_email='" + email + "' AND vehicle_number='" + vehicle_return_no + "';";
        int rows_affected=statement.executeUpdate(str_return);
    }
    public ResultSet query1(String vehicle_return_no) throws SQLException {
        String date_vehicle_return = "select rented_date from cart where vehicle_number='"+vehicle_return_no+"';";
        return statement.executeQuery(date_vehicle_return);
    }
    public ResultSet query2(String vehicle_type,String vehicle_return_no) throws SQLException {
        String price_vehicle_return = "select "+vehicle_type+"_rental_price from "+vehicle_type+" where "+vehicle_type+"_number='"+vehicle_return_no+"';";
        return statement.executeQuery(price_vehicle_return);
    }
    public ResultSet query3(String email) throws SQLException {
        String depo_mo = "select security_deposit from user_deposit where user_email='"+email+"';";
        return statement.executeQuery(depo_mo);
    }
    public void query4(double reduce, String email) throws SQLException {
        String update_security="update user_deposit set security_deposit="+reduce+" where user_email='"+email+"';";
        int security_update_money=statement.executeUpdate(update_security);
    }
    public ResultSet query5(String email) throws SQLException {
        String select ="select user_email,vehicle_model,vehicle_number,rented_date,return_date from cart where user_email='"+email+"';";
        return statement.executeQuery(select);
    }
    public ResultSet query6(String email) throws SQLException {
        String out_acc="select security_deposit from user_deposit where user_email='"+email+"';";
        return  statement.executeQuery(out_acc);
    }
    public void query7(String email) throws SQLException {
        String del="delete from user_deposit where user_email='"+email+"';";
        int del_fin=statement.executeUpdate(del);
    }

}
