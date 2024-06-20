import java.sql.*;
public class Product {
    Connection con;
    Statement stmt;
    ResultSet rs;
    public Product() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3333/midterm","root", "dinedinedine");
            System.out.println("Success");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllProductInfo() {
        try{
            String query = "SELECT * FROM product";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price_per_unit");
                Boolean active = rs.getBoolean("active_for_sell");
                System.out.println("ID: " + id + ", name: " + name + ", price: " + price + ", active: " + active);
            }
        }catch (SQLException e){
            System.out.println("Error retrieving product info: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection" + e.getMessage());
        }
    }
}
