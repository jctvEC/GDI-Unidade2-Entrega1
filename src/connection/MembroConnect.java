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


	public void create () throws SQLException {
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "membro", null);
		String retorno = NULL;
		if (tables.next()) {
			retorno = "A tabela já existe";
			return retorno
		} else {
			this.connection = Conexao.connectDriver();
			PreparedStatement psmt1 = connection.prepareStatement("CREATE TABLE membro (cpf NUMBER, nome VARCHAR2(10) NOT NULL, sobrenome VARCHAR2(10), sexo CHAR(1) NOT NULL, id_endereco NUMBER NOT NULL, estado_civil VARCHAR2(10) NOT NULL, email VARCHAR2(40), data_nascimento DATE NOT NULL, CONSTRAINT membro_pkey PRIMARY KEY (cpf), CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep), CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))");
			psmt1.executeUpdate();	
			connection.close();
			return retorno
		}
	}

	public void select () throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("SELECT * FROM membro");
		psmt.execute();
		ResultSet res = psmt.executeQuery();
		while(res.next()) {
			membro.setCpf(result.getInt("cpf"));
			membro.setNome(result.getString("nome"));
			membro.setSobrenome(result.getString("sobrenome"));
			membro.setSexo(result.getChar("sexo"));
			membro.setId_endereco(result.getInt("id_endereco"));
			membro.setEstado_civil(result.getString("estado_civil"));
			membro.setEmail(result.getString("email"));
			membro.setData_nascimento(result.getString("data_nascimento"));			
		}
		connection.close();
	}
	
	public void insert (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = sdf.parse(membro.getData_nascimento);
		PreparedStatement psmt = connection.prepareStatement("INSERT INTO membro (cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		psmt.setInt(1, membro.getCpf());
		psmt.setString(2, membro.getNome());
		psmt.setString(3, membro.getSobrenome());
		psmt.setString(4, String.valueOf(membro.getSexo()));
		psmt.setInt(5, membro.getId_endereco());
		psmt.setString(6, membro.getEstado_civil());
		psmt.setString(7, membro.getEmail());
		psmt.setString(8, new Date(d.getTime()));
		psmt.executeUpdate();
		
		connection.close();
	}

	public void update (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = sdf.parse(membro.getData_nascimento);
		PreparedStatement psmt = connection.prepareStatement("UPDATE " + " membro " + " SET " + "cpf = ?, nome = ?, sobrenome = ?, sexo = ?, id_endereco = ?, estado_civil = ?, email = ?, data_nascimento = ? WHERE cpf = ?") ;
		psmt.setInt(1, membro.getCpf());
		psmt.setString(2, membro.getNome());
		psmt.setString(3, membro.getSobrenome());
		psmt.setString(4, String.valueOf(membro.getSexo()));
		psmt.setInt(5, membro.getId_endereco());
		psmt.setString(6, membro.getEstado_civil());
		psmt.setString(7, membro.getEmail());
		psmt.setString(8, new Date(d.getTime()));
		psmt.executeUpdate();
		connection.commit();
		connection.rollback();
		connection.close();
	}

	public void delete (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		PreparedStatement psmt = connection.prepareStatement("DELETE FROM " + " membro " + " WHERE cpf = ?");
		psmt.setInt(1, membro.getCpf());
		psmt.executeUpdate();
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