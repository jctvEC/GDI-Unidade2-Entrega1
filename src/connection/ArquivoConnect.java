package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ArquivoConnect {
	public static Connection connection = null;
			
	public void create () throws SQLException {
		
			this.connection = Conexao.connectDriver();
			PreparedStatement psmt1 = connection.prepareStatement("CREATE TABLE midia (name VAR_CHAR(20), byte BLOB");
			psmt1.executeUpdate();	
			connection.close();
			
	}
		
			public void enviar(File file) {
				
			try {
				connection = Conexao.connectDriver();
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				connection.setAutoCommit(false);

				String sql = "INSERT INTO arquivos (name, byte) VALUES (?, ?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, file.getName());
				//stmt.setString(2, "Descricao");

				//File file = new File(path);
				FileInputStream   fis = new FileInputStream(file);
				stmt.setBinaryStream(2, fis, (int) file.length());
				stmt.execute();

				connection.commit();
				fis.close();
				connection.close();
			} catch (SQLException | IOException /*| ClassNotFoundException*/ e) {
				e.printStackTrace();
			}
			}
	  }
