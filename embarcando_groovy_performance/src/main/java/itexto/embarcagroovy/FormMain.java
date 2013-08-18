package itexto.embarcagroovy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormMain extends JFrame {
	
	
	private JComboBox comboOperacoes;
	private JComboBox comboExecutores;
	
	private JButton btnExecutar;
	private JTextArea txtResultado;
	
	
	
	private Executor executor = new ExecutorRapido();
	
	public FormMain() {
		setTitle("Embarcando Groovy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLayout(new BorderLayout());
		JPanel panelAtributos = new JPanel();
		panelAtributos.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAtributos.add(new JLabel("Script:"));
		comboOperacoes = new JComboBox();
		for (Operacao operacao : Operacao.values()) {
			comboOperacoes.addItem(operacao);
		}
		panelAtributos.add(comboOperacoes);
		panelAtributos.add(new JLabel("Executores:"));
		comboExecutores = new JComboBox();
		comboExecutores.addItem(new ExecutorLento());
		comboExecutores.addItem(new ExecutorRapido());
		panelAtributos.add(comboExecutores);
		
		System.exit(12);
		
		
		btnExecutar = new JButton("Executar");
		panelAtributos.add(btnExecutar);
		
		add(panelAtributos, BorderLayout.NORTH);
		txtResultado = new JTextArea();
		JScrollPane scroller = new JScrollPane(txtResultado);
		
		
		add(scroller, BorderLayout.CENTER);
		
		btnExecutar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Security manager = " + System.getSecurityManager());
				Executor executor = (Executor) comboExecutores.getSelectedItem();
				// Há maneiras melhores de medir tempo, mas isto é só pra ilustrar :)
				long tempoInicialNano = System.currentTimeMillis();
				Object result = executor.execute( (Operacao) comboOperacoes.getSelectedItem());
				long tempoFinal = System.currentTimeMillis() - tempoInicialNano;
				txtResultado.append("\nResultado de " + comboOperacoes.getSelectedItem() + " = " + result + "\nTempo:" + tempoFinal + " ms");
			}});
	}
	
	public static void main(String args[]) {
		FormMain form = new FormMain();
		form.setVisible(true);
	}
	
}
