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
	
	private JTextField txtInput;
	
	private JComboBox comboOperacoes;
	private JButton btnExecutar;
	private JTextArea txtResultado;
	
	private Executor executor = new ExecutorThreadSecure();
	
	public FormMain() {
		setTitle("Embarcando Groovy - melhorando a segurança");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLayout(new BorderLayout());
		JPanel panelAtributos = new JPanel();
		panelAtributos.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAtributos.add(new JLabel("Input = "));
		txtInput = new JTextField(16);
		panelAtributos.add(txtInput);
		panelAtributos.add(new JLabel("Script:"));
		comboOperacoes = new JComboBox();
		for (Operacao operacao : Operacao.values()) {
			comboOperacoes.addItem(operacao);
		}
		panelAtributos.add(comboOperacoes);
		btnExecutar = new JButton("Executar");
		panelAtributos.add(btnExecutar);
		
		add(panelAtributos, BorderLayout.NORTH);
		txtResultado = new JTextArea();
		JScrollPane scroller = new JScrollPane(txtResultado);
		
		
		add(scroller, BorderLayout.CENTER);
		
		btnExecutar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Object result = executor.execute(txtInput.getText(), (Operacao) comboOperacoes.getSelectedItem());
				txtResultado.append("\nResultado de " + comboOperacoes.getSelectedItem() + " = " + result);
			}});
	}
	
	public static void main(String args[]) {
		FormMain form = new FormMain();
		form.setVisible(true);
	}
	
}
