import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;

public class gui{
	
	//Window elements
	JFrame frame;
	JLabel passwordLabel = new JLabel();
	JButton genButton = new JButton("Generate");
	JButton copyButton = new JButton("Copy");
	JCheckBox cbxN = new JCheckBox("Numbers");
	JCheckBox cbxU = new JCheckBox("Capital letters");
	JCheckBox cbxL = new JCheckBox("Small letters");
	JCheckBox cbxS = new JCheckBox("Special");
	JSpinner spinner = new JSpinner();
	JLabel spinnerLabel = new JLabel("Number of characters");
	
	//code elements
	String number = "0,1,2,3,4,5,6,7,8,9";
	String lower = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
	String special = "!,?,.,_,-,+,;,:,";
	String aux = "";
	
	gui(){
        windowGUI();
    }

	public void windowGUI() {
		frame=new JFrame();
		
		try {
			frame.setIconImage(ImageIO.read(new File("img\\icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setTitle("Password Generator");
		frame.getContentPane().setLayout(null);
		frame.setSize(440,168);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        copyButton.setBounds(314,93,100,30);
        frame.getContentPane().add(copyButton);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        passwordLabel.setBounds(10,11,404,30);
        frame.getContentPane().add(passwordLabel);
        
        genButton.setBounds(204,93,100,30);
        frame.getContentPane().add(genButton);
        cbxN.setEnabled(false);
        cbxN.setSelected(true);
        
        cbxN.setBounds(6, 59, 82, 23);
        frame.getContentPane().add(cbxN);
        
        cbxU.setBounds(90, 59, 112, 23);
        frame.getContentPane().add(cbxU);
        
        cbxL.setBounds(204, 59, 112, 23);
        frame.getContentPane().add(cbxL);
        
        cbxS.setBounds(314, 59, 97, 23);
        frame.getContentPane().add(cbxS);
        spinner.setModel(new SpinnerNumberModel(6, 6, 28, 2));
        
        spinner.setBounds(10, 98, 37, 20);
        frame.getContentPane().add(spinner);
        
        spinnerLabel.setBounds(57, 101, 137, 14);
        frame.getContentPane().add(spinnerLabel);
      
        //Add Listeners
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copy(evt);
            }
        });
        genButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate(evt);
            }
        });
	}
	
	//Events
	private void copy(java.awt.event.ActionEvent evt) {
		String myString = passwordLabel.getText();
		StringSelection stringSelection = new StringSelection (myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
        clpbrd.setContents (stringSelection, null);
	}
	
	private void generate(java.awt.event.ActionEvent evt) {
		
		String charactersToBeUsed = number; //Always
		
		int num = (int) spinner.getValue();
		
		if (cbxL.isSelected()) {
            charactersToBeUsed += lower;
        }
		if (cbxU.isSelected()) {
            charactersToBeUsed += lower.toUpperCase();
        }
		if (cbxS.isSelected()) {
            charactersToBeUsed += special;
        }
		String[] pass = charactersToBeUsed.split(",");
 		String pFinal="";

		for (int x=0; x<num; x++){
    		int j = (int) (Math.random()*pass.length);
    		pFinal += pass[j];
    	}
		passwordLabel.setText(pFinal);
	}
}
