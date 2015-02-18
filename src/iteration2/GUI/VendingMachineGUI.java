package iteration2.GUI;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;

//Test change for commit

public class VendingMachineGUI{//implements IndicatorLightSimulatorListener {

	private JFrame myFrame;
	//private JTextField myLCDscreen;
	private JButton Pop_1;
	private JButton coinReturn, Exit;
	private JButton takeItems;
	private JTextArea textPane, textPane2;
	private JCheckBox outOfOrderLight, exactChangeLight;
	// Used to redirect stdout to GUI
	private OutputStream out;

	public VendingMachineGUI() {
		out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				updateTextPane(String.valueOf((char) b), 0);
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				if(new String(b,off,len).contains("Notice")|| new String(b, off,len).contains("|"))
					updateTextPane(new String(b, off, len), 0);
				else{
					updateTextPane(new String(b, off, len), 1);
				}

			}

			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
		};
		myFrame = new JFrame();
		// Set the size of our window, and center it in the screen
		myFrame.setSize(550, 610);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Use the GridBag layout manager
		myFrame.getContentPane().setLayout(new GridBagLayout());
		Insets myInsets = new Insets(0, 0, 0, 0);
		int centerInt = GridBagConstraints.CENTER;
		int northeastInt = GridBagConstraints.NORTHEAST;
		int fillBothInt = GridBagConstraints.BOTH;
		int fillNoneInt = GridBagConstraints.NONE;

		//Updateable GUI display
		textPane = new JTextArea();
		textPane2= new JTextArea();
		textPane.setVisible(true);
		textPane.setVisible(true);
		myFrame.getContentPane().add(textPane, getNewConstraints(0,0,2,1,1.0,1.0, centerInt, fillBothInt, myInsets, 0, 0));
		myFrame.getContentPane().add(textPane2, getNewConstraints(2,0,1,1,1.0,1.0, centerInt, fillBothInt, myInsets,0,0));
		// Coin entry label text
		JLabel lblEnterCoin = new JLabel("Enter coin:");
		myFrame.getContentPane().add(
				lblEnterCoin,
				getNewConstraints(0, 2, 1, 3, 1.0, 1.0, northeastInt,
						fillNoneInt, myInsets, 0, 0));

		// $0.05 button
		JButton fiveCents = new JButton("$0.05");
		myFrame.getContentPane().add(
				fiveCents,
				getNewConstraints(1, 2, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
						myInsets, 0, 0));
		/*
		fiveCents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Coin c = new Coin(5);
				try {
					myMachine.getCoinSlot().addCoin(c);
				} catch (DisabledException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
		});*/
		
		// Pop selection label text
		JLabel lblSelectPop = new JLabel("Select pop:");
		myFrame.getContentPane().add(
				lblSelectPop,
				getNewConstraints(0, 5, 1, 3, 1.0, 1.0, northeastInt,
						fillNoneInt, myInsets, 0, 0));

		// First pop button
		// Pop_1 = new JButton(myMachine.getPopNameAt(0));
		Pop_1 = new JButton(new ImageIcon(
				getClass().getResource("Icons/1.png"),
				"Temporary Name"));
		myFrame.getContentPane().add(
				Pop_1,
				getNewConstraints(1, 5, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
						myInsets, 0, 0));

		// Coin return button
		coinReturn = new JButton("Coin Return");
		myFrame.getContentPane().add(
				coinReturn,
				getNewConstraints(0, 10, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
						myInsets, 0, 0));

		// Take items button
		takeItems = new JButton("Take Items");
		myFrame.getContentPane().add(
				takeItems,
				getNewConstraints(1, 10, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
						myInsets, 0, 0));

		// Exit button
		Exit = new JButton("Exit");
		// Exit.setBounds(179, 199, 78, 52);
		myFrame.getContentPane().add(
				Exit,
				getNewConstraints(2, 10, 1, 1, 1.0, 1.0, centerInt, fillBothInt,
						myInsets, 0, 0));

		exactChangeLight = new JCheckBox("ExactChange");
		exactChangeLight.setEnabled(false);
		exactChangeLight.setVisible(true);
		myFrame.getContentPane().add(exactChangeLight, getNewConstraints(0,1,2,1,1.0,1.0, centerInt, fillBothInt, myInsets, 0,0));
		outOfOrderLight	= new JCheckBox("OutOfOrder");
		outOfOrderLight.setEnabled(false);
		outOfOrderLight.setVisible(true);
		myFrame.getContentPane().add(outOfOrderLight, getNewConstraints(2,1,1,1,1.0,1.0, centerInt, fillBothInt, myInsets, 0,0));
		
		myFrame.setVisible(true);
	}

	private void updateTextPane(final String text, int paneNumber) {
		if(paneNumber==0){
			textPane.setText(text);
		}else{
			textPane2.setText(text);
		}
	}

	public OutputStream getOutputStream(){
		return out;
	}
	// This function lets us avoid having to make a new GridBagConstraints
	// object every time
	public GridBagConstraints getNewConstraints(int cellX, int cellY,
			int cellHeight, int cellWidth, double weightX, double weightY,
			int anchor, int fill, Insets ins, int padX, int padY) {
		return new GridBagConstraints(cellX, cellY, cellHeight, cellWidth,
				weightX, weightY, anchor, fill, ins, padX, padY);
	}

	public static void main(String [] args){
		VendingMachineGUI myGUI = new VendingMachineGUI();
	}
}
