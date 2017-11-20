package app;
import java.util.Date;

//CREATE TABLE membro (
//  cpf NUMBER,
//  nome VARCHAR2(10) NOT NULL,
//  sobrenome VARCHAR2(10),
//  sexo CHAR(1) NOT NULL,
//  id_endereco NUMBER NOT NULL,
//  estado_civil VARCHAR2(10) NOT NULL,
//  email VARCHAR2(40),
//  data_nascimento DATE NOT NULL,
//  CONSTRAINT membro_pkey PRIMARY KEY (cpf),
//  CONSTRAINT membro_fkey FOREIGN KEY (id_endereco) REFERENCES endereco(cep),
//  CONSTRAINT membro_check CHECK (sexo ='M' OR sexo ='F')
//);

public class Membro {
	
	private int cpf;
	private String nome;
	private String sobrenome;
	private String sexo;
	private int id_endereco;
	private String estado_civil;
	private String email;
	private String data_nascimento;
	
	public Membro (int cpf, String nome, String sobrenome, String sexo, int id_endereco, String estado_civil, String email, String data_nascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.id_endereco = id_endereco;
		this.estado_civil = estado_civil;
		this.email = email;
		this.data_nascimento = data_nascimento;
	}
	
	public Membro() {
		this.cpf = 0;
		this.nome = "";
		this.sobrenome = "";
		this.sexo = "0";
		this.id_endereco = 0;
		this.estado_civil = "";
		this.email = "";
		this.data_nascimento = "";
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	
}

