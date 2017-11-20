package connection;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Membro;

public class MembroConnect {

	public static Connection connection = null;


	
	public MembroConnect() {
		try {
			this.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void create () throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt1 = connection.prepareStatement("CREATE TABLE membro (cpf NUMBER, nome VARCHAR2(10) NOT NULL, sobrenome VARCHAR2(10), sexo CHAR(1) NOT NULL, id_endereco NUMBER NOT NULL, estado_civil VARCHAR2(10) NOT NULL, email VARCHAR2(40), data_nascimento DATE NOT NULL, CONSTRAINT membro_pkey PRIMARY KEY (cpf), CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep), CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))");
		psmt1.executeUpdate();
		connection.close();
	
	}

	public void select (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("SELECT * FROM membro");
		psmt.execute();
		ResultSet res = psmt.executeQuery();
		while(res.next()) {
			//coloca na tabela pra display
		}
		connection.close();
	}
	
	public void insert (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("INSERT INTO membro (cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		psmt.setInt(1, membro.getCpf());
		psmt.setString(2, membro.getNome());
		psmt.setString(3, membro.getSobrenome());
		psmt.setString(4, String.valueOf(membro.getSexo()));
		psmt.setInt(5, membro.getId_endereco());
		psmt.setString(6, membro.getEstado_civil());
		psmt.setString(7, membro.getEmail());
		psmt.setDate(8, membro.getData_nascimento());
		psmt.executeUpdate();
		
		connection.close();
	}

	public void update (Membro membro) throws SQLException {
		String sql = "UPDATE " + " membro " + " SET " + "nome = " + membro.getNome() + "sobrenome = " + membro.getSobrenome() + "sexo = " + membro.getSexo() + "id_endereco = " + membro.getId_endereco() + "estado_civil = " + membro.getEstado_civil() + "email = " + membro.getEmail() + "data_nascimento = " + membro.getData_nascimento() + "WHERE cpf = " + membro.getCpf() ;
		this.connection = Conexao.connectDriver();
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		connection.commit();
		connection.rollback();
		connection.close();
	}

	public void delete (Membro membro) throws SQLException {
		String sql = "DELETE FROM " + " membro " + " WHERE cpf = " + membro.getCpf();
		this.connection = Conexao.connectDriver();
		Statement statement = connection.createStatement();
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
				+ " data_nascimento DATE NOT NULL				)";
	}
}