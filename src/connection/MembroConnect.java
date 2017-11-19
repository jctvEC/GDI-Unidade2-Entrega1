package connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Membro;

public class MembroConnect {

	public static Connection connection = null;

	private Connection connection;
	
	public MembroConnect() {
		this.createTable();
	}

	public void create () throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("CREATE TABLE membro (cpf NUMBER, nome VARCHAR2(10) NOT NULL, sobrenome VARCHAR2(10), sexo CHAR(1) NOT NULL, id_endereco NUMBER NOT NULL, estado_civil VARCHAR2(10) NOT NULL, email VARCHAR2(40), data_nascimento DATE NOT NULL, CONSTRAINT membro_pkey PRIMARY KEY (cpf), CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep), CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))");
		psmt.executeUpdate();
		connecion.close();
	
	}

	public void select (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("SELECT * FROM membro");
		psmt.execute();
		res = spsmt.executeQuery();
		while(res.next()) {
			//coloca na tabela pra display
		}
		connection.close();
	}
	
	public void insert (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("INSERT INTO membro (cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		psmt.setInt(1, cpf);
		psmt.setString(2, nome);
		psmt.setString(3, sobrenome);
		psmt.setChar(4, sexo);
		psmt.setInt(5, id_endereco);
		psmt.setString(6, estado_civil);
		psmt.setString(7, email);
		psmt.setDate(8, data_nascimento);
		psmt.executeUpdate();
		
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

	public void createTable() {
		String sql = "CREATE TABLE membro (cpf NUMBER, "
				+ "nome VARCHAR2(10) NOT NULL,"
				+ " sobrenome VARCHAR2(10), "
				+ "sexo CHAR(1) NOT NULL,"
				+ " id_endereco NUMBER NOT NULL,"
				+ " estado_civil VARCHAR2(10) NOT NULL,"
				+ " email VARCHAR2(40),"
				+ " data_nascimento DATE NOT NULL,"
				+ " CONSTRAINT membro_pkey PRIMARY KEY (cpf), CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep),"
				+ "CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))";
	}
}
