package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	
	public static String status = "desconectado";
	public static Connection con = null;
	
	public static Connection connectDriver() {
		
		String serverName = "oracle11g.cin.ufpe.br";
		String port = "1521";
		String database = "Instance01";
		String url  = "jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:Instance01";
		String user = "g172if686cc_eq03";
		String password = "kjtialwu";

		//try catch para registrar o driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		}
		
		// try catch para gerar a conexão
		try {
			con = DriverManager.getConnection(url, user, password);
			if(con != null) {
				status = ("conectado");
			} else {
				status = ("desconectado");
			} 
			return con;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	// retorna o status da conexão
	public static String getStatus() {
		return status;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}