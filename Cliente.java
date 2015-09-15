import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.io.BufferedReader;

import javax.swing.DropMode;
import javax.swing.JScrollPane;


public class Cliente extends javax.swing.JFrame {
	private JTextField textField;
    private int count;
    private String IP;
    private TCP_Chat client;
    private Server servidor;
    public BufferedReader gambi;
    public String cacilds;
    
    
	public Cliente() {
    	count = 0;    	
    	IP = "";
    	
    	client = new TCP_Chat();
    	servidor = new Server(8080);
    	
    	
    	
    	setTitle("Chama JAVAC");///nome da tela.
    	
    	this.setSize(500, 300);///tamanho tela;
    	this.setResizable(false);///tela n muda de tamanho
    	
    	setBackground(Color.WHITE);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getContentPane().setLayout(null);
    	
    	//CHAT
    	JLabel lblChat = new JLabel("Chat");
    	lblChat.setBounds(29, 12, 39, 45);
    	getContentPane().add(lblChat);
    	
    	final JTextArea textArea = new JTextArea();
    	textArea.setWrapStyleWord(true);
    	textArea.setBounds(64, 32, 381, 110);
    	textArea.setEditable(false);
    	textArea.setWrapStyleWord(true);
    	getContentPane().add(textArea);  
   
    	//- -----
    	
    	
    	//TEXTO
    	JLabel lblNewLabel = new JLabel("Texto para enviar.");
    	lblNewLabel.setBounds(173, 154, 149, 15);
    	getContentPane().add(lblNewLabel);
    	
    	textField = new JTextField();
    	textField.setBounds(32, 179, 388, 24);
    	getContentPane().add(textField);
    	textField.setColumns(10);
    	
    	    	
    	//IP ------
    	JLabel lblIp = new JLabel("IP");
    	lblIp.setBounds(213, 238, 60, 15);
    	getContentPane().add(lblIp);
    	
    	final JTextPane textPane = new JTextPane();
    	textPane.setBounds(240, 230, 135, 23);
    	getContentPane().add(textPane);

    	//---------
    
    	
    	/**
    	 *  Aqui:
    	 * 		Ele pega a info e envia ao servidor.
    	 * */
    	JButton btnNewButton = new JButton("Enviar");
    	btnNewButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {	
    			if(IP.equalsIgnoreCase(""))
    			{
    				verif(textArea, "Erro, IP não informado.");
    			}else
    			{
    				//envia a msg
    				client.envia(IP, textField.getText());
    				
    				verif(textArea, textField.getText());
    			}
       		}
    	});
    	btnNewButton.setBounds(42, 215, 135, 45);
    	getContentPane().add(btnNewButton);
    	
    	/**
    	 * Pega o endereço de ip a ser enviado.
    	 * */
    	JButton btnSetIp = new JButton("Add Ip");
    	btnSetIp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			IP = textPane.getText();
    		}
    	});
    	btnSetIp.setBounds(387, 226, 100, 27);
    	getContentPane().add(btnSetIp);
    	
    	/**
    	 * Acaba com a conexão.
    	 * */
    	JButton btnFecharConexo = new JButton("Fechar Conexão");
    	btnFecharConexo.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			client.envia(IP, "\n\n");
    			IP = "";
    		}
    	});
    	btnFecharConexo.setBounds(246, 200, 149, 27);
    	getContentPane().add(btnFecharConexo);  	
    
    	//Recebe as msgs do cliente. Aqui quem roda é o servidor.
    	cacilds = "";
    	
    	if(cacilds.equalsIgnoreCase(servidor.getLine()) && IP.equalsIgnoreCase("")){
    		cacilds = servidor.getLine();

    		verif(textArea, textField.getText());
    	}
    }
    
	/*
	 * 	Verifica contador para atualizar a janela com msgs.
	 * */
    private void verif(JTextArea textArea, String text)
    {
		if(count < 7) 
		{
			textArea.append(text+"\n");
			count ++;
		}else
		{
			count = 1;
			textArea.setText("");
			textArea.append(text +"\n");
		}
    }
    
    public static void main (String args[]) {
        new Cliente().setVisible(true);
    }
}
