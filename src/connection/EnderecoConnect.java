package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Endereco;

public class EnderecoConnect {
  
  Connection connection;
  String nomeTB = "endereco";
  Statement statement;
  ResultSet res;
  
  public EnderecoConnect() {
	  try {
		this.create();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void create () throws SQLException {
    String sql = "CREATE TABLE endereco (" + 
        "  cep NUMBER," + 
        "  rua VARCHAR2(50) NOT NULL," + 
        "  complemento VARCHAR2(10)," + 
        "  bairro VARCHAR2(20) NOT NULL," + 
        "  cidade VARCHAR2(50) NOT NULL," + 
        "  estado VARCHAR2(20) NOT NULL," + 
        "  CONSTRAINT endereco_pkey PRIMARY KEY (cep))";
    statement = connection.createStatement();
    statement.executeUpdate(sql);
    connection.commit();
    connection.rollback();
    connection.close();
  }
  
  public void select (Endereco endereco) throws SQLException {
    String sql1 = "SELECT * FROM " + nomeTB;
    this.connection = Conexao.connectDriver();
    statement = connection.createStatement();
    res = statement.executeQuery(sql1);
    while(res.next()) {
      //coloca na tabela pra display
    }
    connection.commit();
    connection.rollback();
    connection.close();
  }
  
  public void insert (Endereco endereco) throws SQLException {
    String sql2 = "INSERT INTO " + nomeTB + "VALUES " + "(" + endereco.getCep() + ", " + endereco.getRua() + ", " + endereco.getComplemento() + ", " + endereco.getBairro() + "," + endereco.getCidade() + ", " + endereco.getEstado() + ")";
    this.connection = Conexao.connectDriver();
    statement = connection.createStatement();
    statement.executeUpdate(sql2);
    connection.commit();
    connection.rollback();
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