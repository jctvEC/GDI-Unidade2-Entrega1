package connection;


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

	public MembroConnect()  {
		try {
			this.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void create () throws SQLException {
		String sql = "CREATE TABLE membro ("
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
				+ "CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))";

		try {
			statement = connection.createStatement();

			statement.executeUpdate(sql);
		} catch (SQLException e) {
			//connection.commit();
			//connection.rollback();
			e.printStackTrace();
		} finally {
			if(statement != null) {
				connection.close();
			}
		}
	}

	public void select (Membro membro) throws SQLException {
		String sql = "SELECT * FROM " + nomeTB;
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		res = statement.executeQuery(sql);
		while(res.next()) {
			//coloca na tabela pra display
		}
		connection.commit();
		connection.rollback();
		connection.close();
	}
	/*String sql = "INSERT INTO " + nomeTB + "(cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) " + " VALUES " +
			"(" + membro.getCpf() + ", " + "'" + membro.getNome() + "', " + "'" + membro.getSobrenome() + "', " + "'" + membro.getSexo() + "', " +
		    membro.getId_endereco() + ", " + "'" + membro.getEstado_civil() + "', " + "'" + membro.getEmail() + "', " + membro.getData_nascimento() + ")"; */
	public void insert (Membro membro) throws SQLException {
		String sql = "INSERT INTO membro (cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) VALUES(1,'a','a',1,1,'a','a','11/11/1111')";

		/*
		 * String sql = "INSERT INTO " + "membro" + "(cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) " + " VALUES " +
				"(" + membro.getCpf() + ", " + "'" + membro.getNome() + "', " + "'" + membro.getSobrenome() + "', " + "'" + membro.getSexo() + "', " +
			    membro.getId_endereco() + ", " + "'" + membro.getEstado_civil() + "', " + "'" + membro.getEmail() + "', " + membro.getData_nascimento() + "))";
		 * 
		 * */

		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.execute(sql);
		connection.commit();
		//connection.rollback();
		connection.close();
	}

	public void update (Membro membro) throws SQLException {
		String sql = "UPDATE " + nomeTB + "SET " + "nome = " + membro.getNome() + "sobrenome = " + membro.getSobrenome() + "sexo = " + membro.getSexo() + "id_endereco = " + membro.getId_endereco() + "estado_civil = " + membro.getEstado_civil() + "email = " + membro.getEmail() + "data_nascimento = " + membro.getData_nascimento() + "WHERE cpf = " + membro.getCpf() ;
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		connection.commit();
		connection.rollback();
		connection.close();
	}

	public void delete (Membro membro) throws SQLException {
		String sql = "DELETE FROM " + nomeTB + "WHERE cpf = " + membro.getCpf();
		this.connection = Conexao.connectDriver();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		connection.commit();
		connection.rollback();
		connection.close();

	}

}
