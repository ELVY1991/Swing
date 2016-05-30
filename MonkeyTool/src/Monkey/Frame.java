package Monkey;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class Frame extends JFrame {

	private JPanel contentPane;
	public static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(120, 12, 81, 23);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox(Util.getDeviceId());
		comboBox.setBounds(10, 13, 92, 21);
		comboBox.setSelectedItem(0);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(211, 11, 362, 243);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JComboBox comboBox_package = new JComboBox(Util.listPackages());
		comboBox_package.setBounds(211, 265, 153, 23);
		comboBox_package.setSelectedItem(0);
		contentPane.add(comboBox_package);
		
		JComboBox comboBox_cmd = new JComboBox(Util.cmdList());
		comboBox_cmd.setBounds(10, 44, 92, 21);
		contentPane.add(comboBox_cmd);
		

		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					public void run() {
						String device = null;
						String commandKey;
						String command = null;
						if ( comboBox.getSelectedIndex()!= -1) {
							 device = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
						}
						if ( comboBox_cmd.getSelectedIndex()!= -1) {
							 commandKey = (String) comboBox_cmd.getItemAt(comboBox_cmd.getSelectedIndex());
							 command = commandMap.getCommandMap().get(commandKey);
						}
						Util.doCommand(device,command);	
						
						
					}
				}).start();
				
			}
		});
	}
}
