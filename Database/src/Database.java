package Database.src;
import java.sql.*;
import java.util.ArrayList;

public class Database {
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

    public ResultSet table_check(String type) throws SQLException {
        String query = "SELECT COUNT(*) AS row_count FROM " + type;
        return statement.executeQuery(query);
    }

    public int insertBike(String bike_model, String bike_number, String bike_service_status, int bike_rental_price, int bike_kilometer, String bike_avail_status) throws SQLException {
        String query = "INSERT INTO bike (bike_model, bike_number, bike_service_status, bike_rental_price, bike_kilometer,availability) VALUES ('" + bike_model + "', '" + bike_number + "', '" + bike_service_status + "', '" + bike_rental_price + "', '" + bike_kilometer + "', '" + bike_avail_status + "')";
        return statement.executeUpdate(query);
    }

    public int insertCar(String car_model, String car_number, String car_service_status, int car_rental_price, int car_kilometer, String car_avail_status) throws SQLException {
        String query = "INSERT INTO car (car_model, car_number, car_service_status, car_rental_price, car_kilometer,availability) VALUES ('" + car_model + "', '" + car_number + "', '" + car_service_status + "', '" + car_rental_price + "', '" + car_kilometer + "','"+car_avail_status+"')";
        return statement.executeUpdate(query);
    }

    public void service_check() throws SQLException {
        String query_check1 = "UPDATE bike SET bike_service_status='NOT-SERVICED' WHERE bike_kilometer > 1500";
        statement.executeUpdate(query_check1);
        String query_check2 = "UPDATE car SET car_service_status='NOT-SERVICED' WHERE car_kilometer > 1500";
        statement.executeUpdate(query_check2);
    }

    public int updateVehicleService(String update_status, String dbnum, String db) throws SQLException {
        if (db.equals("bike")) {
            String query = "UPDATE " + db + " SET bike_service_status='" + update_status + "' WHERE bike_number='" + dbnum + "'";
            return statement.executeUpdate(query);
        } else {
            String query = "UPDATE " + db + " SET car_service_status='" + update_status + "' WHERE car_number='" + dbnum + "'";
            return statement.executeUpdate(query);
        }
    }

    public int updateVehicleRent(int update_prize, String dbnum, String db) throws SQLException {
        if (db.equals("bike")) {
            String query = "UPDATE " + db + " SET bike_rental_price='" + update_prize + "' WHERE bike_number='" + dbnum + "'";
            return statement.executeUpdate(query);
        } else {
            String query = "UPDATE " + db + " SET bike_rental_price='" + update_prize + "' WHERE car_number='" + dbnum + "'";
            return statement.executeUpdate(query);
        }
    }

    public int updateVehicleKilometer(int update_kilo, String dbnum, String db) throws SQLException {
        String query;
        if (db.equals("bike")) {
            query = "UPDATE " + db + " SET bike_kilometer='" + update_kilo + "' WHERE bike_number='" + dbnum + "'";
        } else {
            query = "UPDATE " + db + " SET car_kilometer='" + update_kilo + "' WHERE car_number='" + dbnum + "'";
        }
        return statement.executeUpdate(query);
    }
    public int updateVehicleAvailability(String update_avail, String dbnum, String db) throws SQLException {
        String query;
        if (db.equals("bike")) {
            query = "UPDATE " + db + " SET availability='" + update_avail + "' WHERE bike_number='" + dbnum + "'";
        } else {
            query = "UPDATE " + db + " SET availability='" + update_avail + "' WHERE car_number='" + dbnum + "'";
        }
        return statement.executeUpdate(query);
    }

    public int deleteVehicle(String type, String deletenum) throws SQLException {
        if (type.equals("bike")) {
            String delete_bike = "delete from " + type + " where bike_number='" + deletenum + "';";
            return statement.executeUpdate(delete_bike);
        } else {
            String delete_bike = "delete from " + type + " where car_number='" + deletenum + "';";
            return statement.executeUpdate(delete_bike);
        }
    }

    public ArrayList<String> visualizeAllVehicleView(String type) throws SQLException {
        ArrayList<String> dataList = new ArrayList<>();

        String display = null;
        if (type.equals("bike")) {
            display = "SELECT bike_model, bike_number, bike_service_status, bike_rental_price, bike_kilometer, availability FROM bike ORDER BY bike_rental_price ASC";
        } else {
            display = "SELECT car_model, car_number, car_service_status, car_rental_price, car_kilometer,availability FROM car ORDER BY car_rental_price ASC";
        }

        ResultSet res = statement.executeQuery(display);

        while (res.next()) {
            String data = res.getString(1) + " || " + res.getString(2) + " || " + res.getString(3) + " || " + res.getInt(4) + " || " + res.getInt(5)+ " || " + res.getString(6);
            dataList.add(data);
        }
        return dataList;
    }
    public ArrayList<String> visualizeAllDueVehicleView(String type) throws SQLException {
        ArrayList<String> dataList = new ArrayList<>();

        String display = null;
        if (type.equals("bike")) {
            display="SELECT bike_model, bike_number, bike_service_status, bike_rental_price, bike_kilometer, availability FROM "+type+" where  bike_service_status='NOT-SERVICED'";
        } else {
            display="SELECT car_model, car_number, car_service_status, car_rental_price, car_kilometer,availability FROM "+type+" where  car_service_status='NOT-SERVICED'";
        }
        ResultSet res = statement.executeQuery(display);

        while (res.next()) {
            String data = res.getString(1) + " || " + res.getString(2) + " || " + res.getString(3) + " || " + res.getInt(4) + " || " + res.getInt(5)+ " || " + res.getString(6);
            dataList.add(data);
        }
        return dataList;
    }


    public ResultSet tableCheck(String type) throws SQLException {
        String query = "SELECT COUNT(*) AS row_count FROM " + type;
        return statement.executeQuery(query);
    }

    public int getInitialDepositOfBike() throws SQLException {
        String query="select amount from deposit where vehicle_type='bike'";
        ResultSet res=statement.executeQuery(query);
        while(res.next())
            return res.getInt(1);
        return 0;
    }
    public int getInitialDepositOfCar() throws SQLException {
        String query="select amount from deposit where vehicle_type='car'";
        ResultSet res=statement.executeQuery(query);
        while(res.next())
            return res.getInt(1);
        return 0;
    }
    public boolean setInitialDepositOfBike(int changeDeposit) throws SQLException {
        connection = getConnect();
        statement = connection.createStatement();
        String setDeposit = "UPDATE deposit set amount =" + changeDeposit + " where vehicle_type='bike'";
        statement.executeUpdate(setDeposit);
        return true;
    }

    public boolean setInitialDepositOfCar(int changeDeposit) throws SQLException {
        connection = getConnect();
        statement = connection.createStatement();
        String setDeposit = "UPDATE deposit set amount =" + changeDeposit + " where vehicle_type='car'";
        statement.executeUpdate(setDeposit);
        return true;
    }

    public ResultSet searchType(String type,String search_number) throws SQLException {
        if (type.equals("bike")) {
            String search = "select count(*) as demo from " + type + " where bike_number='" + search_number + "';";
            return statement.executeQuery(search);
        }else{
            String search = "select count(*) as demo from " + type + " where car_number='" + search_number + "';";
            return statement.executeQuery(search);
        }
    }

    public ResultSet searchCount(String type,String search_number) throws SQLException {
        if (type.equals("bike")) {
            String search1 = "select count(*) as dem from " + type + " where bike_number='" + search_number + "';";
            return statement.executeQuery(search1);
        }else{
            String search1 = "select count(*) as dem from " + type + " where car_number='" + search_number + "';";
            return statement.executeQuery(search1);
        }
    }
    public String vehicleFinding(String type, String search_number) throws SQLException {
        if (type.equals("bike")) {
            String search12 = "select * from " + type + " where bike_number='" + search_number + "';";
            try (ResultSet rs12 = statement.executeQuery(search12)) {
                StringBuilder data4 = new StringBuilder();
                while (rs12.next()) {
                    data4.append("  ").append(rs12.getString("bike_model"))
                            .append("  ").append(rs12.getString("bike_number"))
                            .append("  ").append(rs12.getString("bike_service_status"))
                            .append("  ").append(rs12.getInt("bike_rental_price"))
                            .append("  ").append(rs12.getInt("bike_kilometer"))
                            .append("  ").append(rs12.getString("availability"));
                }
                return data4.toString();
            }
        } else {
            String search12 = "select * from " + type + " where car_number='" + search_number + "';";
            try (ResultSet rs12 = statement.executeQuery(search12)) {
                StringBuilder data4 = new StringBuilder();
                while (rs12.next()) {
                    data4.append("  ").append(rs12.getString("car_model"))
                            .append("  ").append(rs12.getString("car_number"))
                            .append("  ").append(rs12.getString("car_service_status"))
                            .append("  ").append(rs12.getInt("car_rental_price"))
                            .append("  ").append(rs12.getInt("car_kilometer"))
                            .append("  ").append(rs12.getString("availability"));
                }
                return data4.toString();
            }
        }
    }

}
