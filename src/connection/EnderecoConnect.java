package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import app.Endereco;

public class EnderecoConnect {
  
  private Connection connection;
    
  public EnderecoConnect() {
	  try {
		this.create();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void create () throws SQLException {
	  this.connection = Conexao.connectDriver();
	  PreparedStatement psmt = connection.prepareStatement("CREATE TABLE endereco (" + 
        "  cep NUMBER," + 
        "  rua VARCHAR2(50) NOT NULL," + 
        "  complemento VARCHAR2(10)," + 
        "  bairro VARCHAR2(20) NOT NULL," + 
        "  cidade VARCHAR2(50) NOT NULL," + 
        "  estado VARCHAR2(20) NOT NULL," + 
        "  CONSTRAINT endereco_pkey PRIMARY KEY (cep))");
	  psmt.executeUpdate();
	  connecion.close();
  }
  
  public void select (Endereco endereco) throws SQLException {
	  	this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("SELECT * FROM endereco");
		psmt.execute();
		ResultSet res = spsmt.executeQuery();
		while(res.next()) {
			//coloca na tabela pra display
		}
		connection.close();
  }
  
  public void insert (Endereco endereco) throws SQLException {
	  	this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("INSERT INTO endereco (cep, rua, complemento, bairro, cidade, estado) VALUES(?, ?, ?, ?, ?, ?)");
		psmt.setInt(1, cep);
		psmt.setString(2, rua);
		psmt.setString(3, complemento);
		psmt.setInt(4, bairro);
		psmt.setString(5, cidade);
		psmt.setString(6, estado);
		psmt.executeUpdate();
		
		connection.close();
  }
  
  public void update (Endereco endereco) throws SQLException {
    String sql3 = "UPDATE " + nomeTB + "SET " + "rua = " + endereco.getRua() + "complemento = " + endereco.getComplemento() + "bairro = " + endereco.getBairro() + "cidade = " + endereco.getCidade() + "estado = " + endereco.getEstado() + "WHERE cpf = " + endereco.getCep();
    this.connection = Conexao.connectDriver();
    statement = connection.createStatement();
    statement.executeUpdate(sql3);
    connection.commit();
    connection.rollback();
    connection.close();
  }
  
  public void delete (Endereco endereco) throws SQLException {
    String sql4 = "DELETE FROM " + nomeTB + "WHERE cpf = " + endereco.getCep();
    this.connection = Conexao.connectDriver();
    statement = connection.createStatement();
    statement.executeUpdate(sql4);
    connection.commit();
    connection.rollback();
    connection.close();
    
  }
}