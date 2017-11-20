package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.*;

import connection.*;
import app.*;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainWindow {
	private JFrame mainFrame;
	private JLabel filePanel;
	private JLabel tableFrame;
	private JTextField filePath;
	private JLabel debug1;
	private JLabel debug2;
	private JLabel debug3;
	private JLabel debug4;
	private JLabel debug5;
	private JLabel debug6;
	private JLabel debug7;
	private JLabel debug8;
	private File selected;
	private String path;

	private Conexao conexao;
	private MembroConnect membroConexao;
	
	private Membro membro;
	

	private JTextField cpfField;
	private JTextField nameField;
	private JTextField sobrenomeField;
	private JTextField sexoField;
	private JTextField idEnderecoField;
	private JTextField civilField;
	private JTextField emailField;
	private JTextField nascimentoField;
	private JTable sqlTable;

	public MainWindow(){
		prepareGUI();
	}
	public static void main(String[] args){
		MainWindow swingControlDemo = new MainWindow();  
		swingControlDemo.showEventDemo();       
	}
	private void prepareGUI(){
		Date dat = new Date();
		conexao = new Conexao();
		membroConexao = new MembroConnect();
		
		//membro = new Membro(0, "", "", '0', 0, "", "", "");


		//setando os frames
		mainFrame = new JFrame("GDI");
		mainFrame.setSize(600,600);

		filePanel = new JLabel("",JLabel.CENTER );
		filePanel.setBounds(10, 0, 141, 40);
		tableFrame = new JLabel("",JLabel.CENTER);
		tableFrame.setBounds(72, 134, 247, 25);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		mainFrame.getContentPane().setLayout(null);

		mainFrame.getContentPane().add(filePanel);
		mainFrame.getContentPane().add(tableFrame);

		//
		//buttons
		//

		//botao de abrir selector de arquivos
		JButton buttonAbrir = new JButton("Abrir");
		buttonAbrir.setBounds(10, 33, 77, 25);
		mainFrame.getContentPane().add(buttonAbrir);

		buttonAbrir.setActionCommand("abrir");

		//botao para adicionar arquivo no banco de dados
		JButton buttonAdicionar = new JButton("Adicionar Arquivo");
		buttonAdicionar.setBounds(10, 100, 153, 23);
		mainFrame.getContentPane().add(buttonAdicionar);

		buttonAdicionar.setActionCommand("add");

		//botao de adicionar membro
		JButton buttonMembro = new JButton("Adicionar membro");
		buttonMembro.setBounds(345, 117, 141, 23);
		mainFrame.getContentPane().add(buttonMembro);

		buttonMembro.setActionCommand("membro");

		//setando listeners
		buttonAbrir.addActionListener(new ButtonClickListener());
		buttonAdicionar.addActionListener(new ButtonClickListener());
		buttonMembro.addActionListener(new ButtonClickListener());

		//setando campo onde vai ficar o path do arquivo
		filePath = new JTextField();
		filePath.setBounds(20, 69, 99, 20);
		mainFrame.getContentPane().add(filePath);
		filePath.setColumns(10);

		//
		// fields
		//
		cpfField = new JTextField();
		cpfField.setBounds(222, 11, 86, 20);
		mainFrame.getContentPane().add(cpfField);
		cpfField.setColumns(10);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(222, 35, 86, 20);
		mainFrame.getContentPane().add(nameField);

		sobrenomeField = new JTextField();
		sobrenomeField.setColumns(10);
		sobrenomeField.setBounds(222, 66, 86, 20);
		mainFrame.getContentPane().add(sobrenomeField);

		sexoField = new JTextField();
		sexoField.setColumns(10);
		sexoField.setBounds(222, 89, 86, 20);
		mainFrame.getContentPane().add(sexoField);

		idEnderecoField = new JTextField();
		idEnderecoField.setColumns(10);
		idEnderecoField.setBounds(470, 10, 86, 20);
		mainFrame.getContentPane().add(idEnderecoField);

		civilField = new JTextField();
		civilField.setColumns(10);
		civilField.setBounds(470, 35, 86, 20);
		mainFrame.getContentPane().add(civilField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(470, 66, 86, 20);
		mainFrame.getContentPane().add(emailField);

		nascimentoField = new JTextField();
		nascimentoField.setColumns(10);
		nascimentoField.setBounds(470, 89, 86, 20);
		mainFrame.getContentPane().add(nascimentoField);

		//
		//labels
		//
		JLabel idEnderecoLabel = new JLabel("Endere\u00E7oID");
		idEnderecoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		idEnderecoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idEnderecoLabel.setBounds(372, 13, 77, 14);
		mainFrame.getContentPane().add(idEnderecoLabel);

		JLabel lblEstadoCivil = new JLabel("Estado civil");
		lblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoCivil.setBounds(392, 38, 65, 14);
		mainFrame.getContentPane().add(lblEstadoCivil);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(414, 72, 38, 14);
		mainFrame.getContentPane().add(lblEmail);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeNascimento.setBounds(335, 92, 112, 14);
		mainFrame.getContentPane().add(lblDataDeNascimento);

		JLabel cpfLabel = new JLabel("C.P.F.");
		cpfLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		cpfLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cpfLabel.setBounds(180, 13, 38, 14);
		mainFrame.getContentPane().add(cpfLabel);

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(180, 38, 38, 14);
		mainFrame.getContentPane().add(nameLabel);

		JLabel sobrenomeLabel = new JLabel("Sobrenome");
		sobrenomeLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		sobrenomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sobrenomeLabel.setBounds(161, 69, 57, 14);
		mainFrame.getContentPane().add(sobrenomeLabel);

		JLabel sexoLabel = new JLabel("Sexo");
		sexoLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		sexoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sexoLabel.setBounds(192, 92, 31, 14);
		mainFrame.getContentPane().add(sexoLabel);

		sqlTable = new JTable();
		sqlTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sqlTable.setBounds(10, 295, 564, 231);
		sqlTable.setModel(new DefaultTableModel(
				new Object[][] {
					{"CPF", "Nome", "Sobrenome", "Sexo", "ID Endereço", "Estado civil", "Email", "Data de nascimento"},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
						"Sender", "Subject", "Body", "New column", "New column", "New column", "New column", "New column"
				}
				));
		mainFrame.getContentPane().add(sqlTable);
		
		JLabel debug1 = new JLabel("", SwingConstants.CENTER);
		debug1.setBounds(45, 170, 153, 20);
		mainFrame.getContentPane().add(debug1);
		
		JLabel debug2 = new JLabel("", SwingConstants.CENTER);
		debug2.setBounds(45, 194, 153, 20);
		mainFrame.getContentPane().add(debug2);
		
		JLabel debug3 = new JLabel("", SwingConstants.CENTER);
		debug3.setBounds(45, 225, 153, 20);
		mainFrame.getContentPane().add(debug3);
		
		JLabel debug4 = new JLabel("", SwingConstants.CENTER);
		debug4.setBounds(45, 256, 153, 20);
		mainFrame.getContentPane().add(debug4);
		
		JLabel debug8 = new JLabel("", SwingConstants.CENTER);
		debug8.setBounds(320, 256, 153, 20);
		mainFrame.getContentPane().add(debug8);
		
		JLabel debug7 = new JLabel("", SwingConstants.CENTER);
		debug7.setBounds(320, 225, 153, 20);
		mainFrame.getContentPane().add(debug7);
		
		JLabel debug6 = new JLabel("", SwingConstants.CENTER);
		debug6.setBounds(308, 194, 153, 20);
		mainFrame.getContentPane().add(debug6);
		
		JLabel debug5 = new JLabel("", SwingConstants.CENTER);
		debug5.setBounds(320, 170, 153, 20);
		mainFrame.getContentPane().add(debug5);



		//setando listeners
		buttonAbrir.addActionListener(new ButtonClickListener());
		buttonAdicionar.addActionListener(new ButtonClickListener());
		buttonMembro.addActionListener(new ButtonClickListener());
		mainFrame.setVisible(true);  
	}
	private void showEventDemo(){
		filePanel.setText("Escolha o arquivo"); 
		mainFrame.setVisible(true);  
	}


	//
	// listener dos botoes, serve pra fazer eventos de acordo com o botao apertado
	//
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  


			// evento de abrir seletor de arquivos
			if("abrir".equals(command)){
				tableFrame.setText("abrir Button clicked.");

				//inicializando file chooser
				JFileChooser file = new JFileChooser(); 
				file.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int selecting= file.showSaveDialog(null);

				//escolhendo arquivo e salvando o path dele
				if( selecting == JFileChooser.APPROVE_OPTION) {
					selected = file.getSelectedFile();
					path = selected.getAbsolutePath();
					filePath.setText(path);
				}
				// evento de adicionar arquivo comum
			}else if("add".equals(command)) {
				tableFrame.setText("ad Button clicked.");
				// evento de adicionar membro
				
				
			}else if("membro".equals(command)) {
				//cpf nome sobrenome sexo endereco estadocivil email nascimento
				String data = nascimentoField.getText();
				Date dat = null;
				try {
					dat = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				tableFrame.setText("aaa");
				java.sql.Date dataSql = new java.sql.Date(dat.getTime());
				tableFrame.setText("membro Button clicked.");
				Membro memb = new Membro(Integer.parseInt(cpfField.getText()),
						nameField.getText(),
						sobrenomeField.getText(), 
						sexoField.getText(), 
						Integer.parseInt(idEnderecoField.getText()),
						civilField.getText(), 
						emailField.getText(), nascimentoField.getText()
						);
				
				debug1.setText(Integer.toString(membro.getCpf()));
				debug2.setText(membro.getNome());
				debug3.setText(membro.getSobrenome());
				debug4.setText(membro.getSexo());
				debug5.setText(Integer.toString(membro.getId_endereco()));
				debug6.setText(membro.getEstado_civil());
				debug6.setText(membro.getEmail());
				debug8.setText(membro.getData_nascimento());
				
				try {
					membroConexao.insert(memb);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				System.out.println("kkk eae men");
			}
		}

	}
}