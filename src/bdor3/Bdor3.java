package bdor3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

/**
 *
 * @author oracle
 */
public class Bdor3 {

    public static Connection conexion = null;
    
    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost"; // tambien puede ser una ip como "192.168.1.14"
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;
        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }
    public void datosEmpregados() throws SQLException{
        
        String datos = "SELECT * FROM empregado";
        Statement st = conexion.createStatement();
	ResultSet rs = st.executeQuery(datos);
        while(rs.next()){
            Struct x = (Struct) rs.getObject(1);
            int edad = rs.getInt(2);
            
            Object[] campos = x.getAttributes();
            
            String nome = (String)campos[0];
            java.math.BigDecimal numero = (java.math.BigDecimal)campos[1];
            System.out.println("Nombre: " + nome + " Numero: " + numero + " Idade: " + edad + "\n");
        }
    }

    public static void main(String[] args) throws SQLException {
        Bdor3 b = new Bdor3();
        b.getConexion();
        b.datosEmpregados();
        b.closeConexion();
    }

}
