package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Membro;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Membro;

public class MembroConnect {
	
	Connection connection;
	String nomeTB = "membro";
	Statement statement;
	ResultSet res;
	
	public void create () {
		String sql = "CREATE TABLE membro (
			+ "cpf NUMBER,"
			+ "nome VARCHAR2(10) NOT NULL,"
			+ "sobrenome VARCHAR2(10),"
			+ "sexo CHAR(1) NOT NULL,"
			+ "id_endereco NUMBER NOT NULL,"
			+ "estado_civil VARCHAR2(10) NOT NULL,"
			+ "email VARCHAR2(40),"
			+ "data_nascimento DATE NOT NULL,"
			+ "CONSTRAINT membro_pkey PRIMARY KEY (cpf),"
			+ "CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep),"
			+ "CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))"
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		connection.commit();
		connection.rollback();
		connection.close();
	}
	
	public void select (Membro membro) throws SQLException {
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
	
	public void insert (Membro membro) throws SQLException {
		String sql2 = "INSERT INTO " + nomeTB + "VALUES " + "(" + membro.getCpf()+ ", " + membro.getNome() + ", " + membro.getSobrenome() + ", " + membro.getSexo() + "," + membro.getId_endereco() + ", " + membro.getEstado_civil() + ", " + membro.getEmail() + ", " + membro.getData_nascimento() + ")";
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.executeUpdate(sql2);
		connection.commit();
		connection.rollback();
		connection.close();
	}
	
	public void update (Membro membro) throws SQLException {
		String sql3 = "UPDATE " + nomeTB + "SET " + "nome = " + membro.getNome() + "sobrenome = " + membro.getSobrenome() + "sexo = " + membro.getSexo() + "id_endereco = " + membro.getId_endereco() + "estado_civil = " + membro.getEstado_civil() + "email = " + membro.getEmail() + "data_nascimento = " + membro.getData_nascimento() + "WHERE cpf = " + membro.getCpf() ;
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.executeUpdate(sql3);
		connection.commit();
		connection.rollback();
		connection.close();
	}
	
	public void delete (Membro membro) throws SQLException {
		String sql4 = "DELETE FROM " + nomeTB + "WHERE cpf = " + membro.getCpf();
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.executeUpdate(sql4);
		connection.commit();
		connection.rollback();
		connection.close();
		
	}
	
}
