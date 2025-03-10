
package pe.company.dbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {
    public static Connection MySQL(){
            Connection con = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://localhost/bd_example?useSSL=false&useTimezone=true&serverTimezone=UTC";
                String usr = "root";
                String psw = "123456";
                con = DriverManager.getConnection(url, usr,psw);

            } catch (ClassNotFoundException e) {
                System.out.println("Error >> Driver no instalado !!" + e.getMessage());
            } catch (SQLException e){
                System.out.println("Error >> de conexion a la base de datos" + e.getMessage());
            } catch(Exception e){
                System.out.println("Error >> general: "+ e.getMessage());
            }
            return con;
        }
}
