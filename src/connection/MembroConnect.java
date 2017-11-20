package connection;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import app.Membro;

public class MembroConnect {

	public static Connection connection = null;


	public String create () throws SQLException {
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "membro", null);
		String retorno = null;
		if (tables.next()) {
			retorno = "A tabela já existe";
			return retorno;
		} else {
			this.connection = Conexao.connectDriver();
			PreparedStatement psmt1 = connection.prepareStatement("CREATE TABLE membro (cpf NUMBER, nome VARCHAR2(10) NOT NULL, sobrenome VARCHAR2(10), sexo CHAR(1) NOT NULL, id_endereco NUMBER NOT NULL, estado_civil VARCHAR2(10) NOT NULL, email VARCHAR2(40), data_nascimento DATE NOT NULL, CONSTRAINT membro_pkey PRIMARY KEY (cpf), CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep), CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F'))");
			psmt1.executeUpdate();	
			connection.close();
			return retorno;
		}
	}

	public void select () throws SQLException {
		this.connection = Conexao.connectDriver();
		Membro membro = new Membro();
		PreparedStatement psmt = connection.prepareStatement("SELECT * FROM membro");
		psmt.execute();
		ResultSet res = psmt.executeQuery();
		while(res.next()) {
			membro.setCpf(res.getInt("cpf"));
			membro.setNome(res.getString("nome"));
			membro.setSobrenome(res.getString("sobrenome"));
			membro.setSexo(res.getString("sexo"));
			membro.setId_endereco(res.getInt("id_endereco"));
			membro.setEstado_civil(res.getString("estado_civil"));
			membro.setEmail(res.getString("email"));
			membro.setData_nascimento(res.getString("data_nascimento"));			
		}
		connection.close();
	}
	
	public void insert (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = null;
		try {
			d = sdf.parse(membro.getData_nascimento());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement psmt = connection.prepareStatement("INSERT INTO membro (cpf, nome, sobrenome, sexo, id_endereco, estado_civil, email, data_nascimento) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		psmt.setInt(1, membro.getCpf());
		psmt.setString(2, membro.getNome());
		psmt.setString(3, membro.getSobrenome());
		psmt.setString(4, String.valueOf(membro.getSexo()));
		psmt.setInt(5, membro.getId_endereco());
		psmt.setString(6, membro.getEstado_civil());
		psmt.setString(7, membro.getEmail());
		psmt.setDate(8, new Date(d.getTime()));
		psmt.executeUpdate();
		
		connection.close();
	}

	public void update (Membro membro) throws SQLException {
		this.connection = Conexao.connectDriver();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = null;
		try {
			d = sdf.parse(membro.getData_nascimento());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement psmt = connection.prepareStatement("UPDATE " + " membro " + " SET " + "cpf = ?, nome = ?, sobrenome = ?, sexo = ?, id_endereco = ?, estado_civil = ?, email = ?, data_nascimento = ? WHERE cpf = ?") ;
		psmt.setInt(1, membro.getCpf());
		psmt.setString(2, membro.getNome());
		psmt.setString(3, membro.getSobrenome());
		psmt.setString(4, String.valueOf(membro.getSexo()));
		psmt.setInt(5, membro.getId_endereco());
		psmt.setString(6, membro.getEstado_civil());
		psmt.setString(7, membro.getEmail());
		psmt.setDate(8, new Date(d.getTime()));
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