import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MyGui {

	private JFrame frm;

	private JTextField init_textField;
	private JTextField input_textField;

	/**
	 * Launch the application.
	 */
	public static void createForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyGui window = new MyGui();
					window.frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		frm.setTitle("Disk Scheduler");
		frm.setBounds(100, 100, 598, 383);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(null);
		
		JLabel lblInitial = new JLabel("Initial Position");
		lblInitial.setFont(new Font("Arial", Font.BOLD, 20));
		lblInitial.setBounds(10, 23, 155, 47);
		frm.getContentPane().add(lblInitial);
		
		JButton btnEnterValue = new JButton("Enter Value");
		btnEnterValue.setEnabled(false);
		btnEnterValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value;
				value = input_textField.getText();
				
				if(value.length() < 1)
				{
					JOptionPane.showMessageDialog(input_textField, "Please Enter valid Data");
				}
				else
				{
						Disk_scheduler.requests.add(Integer.parseInt(value));
				}
				input_textField.setText("");
			}
		});
		btnEnterValue.setFont(new Font("Arial", Font.BOLD, 15));
		btnEnterValue.setBounds(401, 93, 143, 27);
		frm.getContentPane().add(btnEnterValue);
		
		JButton btnInitPos = new JButton("Enter");
		btnInitPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String initTxt;
				initTxt = init_textField.getText();
				
				if(initTxt.length() < 1)
				{
					JOptionPane.showMessageDialog(init_textField, "Please Enter valid Data");
				}
				else
				{
						Disk_scheduler.initialPosition = Integer.parseInt(initTxt);
				}
				init_textField.setText("");
				btnInitPos.setEnabled(false);
				btnEnterValue.setEnabled(true);
		
			}
		});
		btnInitPos.setFont(new Font("Arial", Font.BOLD, 15));
		btnInitPos.setBounds(401, 35, 143, 27);
		frm.getContentPane().add(btnInitPos);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disk_scheduler.execute();
			}
		});
		btnRun.setFont(new Font("Arial", Font.BOLD, 15));
		btnRun.setBounds(208, 199, 143, 76);
		frm.getContentPane().add(btnRun);
		
		init_textField = new JTextField();
		init_textField.setBounds(173, 39, 190, 20);
		frm.getContentPane().add(init_textField);
		init_textField.setColumns(10);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Arial", Font.BOLD, 20));
		lblInput.setBounds(10, 81, 155, 47);
		frm.getContentPane().add(lblInput);
		
		input_textField = new JTextField();
		input_textField.setColumns(10);
		input_textField.setBounds(173, 97, 190, 20);
		frm.getContentPane().add(input_textField);
		
		
	}
}
