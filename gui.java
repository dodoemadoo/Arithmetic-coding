//class gui
//andrew emad aziz

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*; 
public class gui extends JFrame implements ActionListener{
	 private static JLabel label;
	 private static JLabel label1;
	 private static JLabel label2;

	 private static JFrame frame1;
	 private static JButton button1;
	 private static JButton button2;
	 private static JButton button3;
	 private static JButton button4;
	 private static JButton button5;
	 private static JTextField textbox;
	 private static JTextField textbox1;
	 private static JTextField textbox2;
	 private static char[] uniqueChar;
	 private static int[] freq;
	 private static arithmeticCoding[] lh ;
	 private static int number;

	 private static Main m;
	 gui()
	 {
		
	 }
	 public static void main  (String[] args) 
	    { 
		 		 
		 	// label to diaplay text 
		    label = new JLabel("Enter the text to compress: "); 
		 
		    // label to diaplay text 
		    label1 = new JLabel("Enter the text to decompress: "); 
		    
		    // label to diaplay text 
		    label2 = new JLabel("Enter the file name: ");
		    
		    
	        // creates instance of JFrame 
	        frame1 = new JFrame("Arithmetic_Huffman"); 
	  
	        // creates instance of JButton 
	        button1 = new JButton("Compress"); 
	          
	        // "button2" appears on the button 
	        button2 = new JButton("Decompress");
	        
	        // "button3" appears on the button 
	        button3 = new JButton("Read from file");
	        
	        // "button4" appears on the button 
	        button4 = new JButton("Exit");
	        
	        // "button5" appears on the button 
	        button5 = new JButton("New");
	  
	        // JTextField 
	        textbox= new JTextField();
	        
	        // JTextField
	        textbox1= new JTextField(); 
	        
	        // JTextField
	        textbox2= new JTextField(); 
	        
	        
	        // x axis, y axis, width, height 
	        button1.setBounds(50, 230, 150, 80); 
	        button2.setBounds(260, 230, 150, 80); 
	        button3.setBounds(470, 230, 150, 80);
	        button4.setBounds(680, 230, 150, 80);
	        button5.setBounds(890, 230, 150, 80);
	        
	        textbox.setBounds(220, 20,820, 30);
	        textbox1.setBounds(220, 70, 820, 30);
	        textbox2.setBounds(220, 120,820, 30);

	        
	        label.setBounds(50, 20, 200, 30);
	        label1.setBounds(50, 70, 200, 30);
	        label2.setBounds(50, 120, 200, 30);

	  
	        //adds button1 in Frame1 
	        frame1.add(button1); 
	          
	        //adds button2 in Frame1 
	        frame1.add(button2); 
	        
	        //adds button3 in Frame1 
	        frame1.add(button3); 
	        
	        //adds button4 in Frame1 
	        frame1.add(button4); 
	        
	        //adds button4 in Frame1 
	        frame1.add(button5); 
	        
	        //adds textbox in Frame1
	        frame1.add(textbox);
	        
	        //adds textbox1 in Frame1
	        frame1.add(textbox1);
	      
	        //adds textbox in Frame1
	        frame1.add(textbox2);
	        
	        
	        //adds label in Frame1
	        frame1.add(label);

	        //adds label1 in Frame1
	        frame1.add(label1);
	     
	        //adds label in Frame1
	        frame1.add(label2);


	        
	        //400 width and 500 height of frame1 
	        frame1.setSize(1100, 390) ; 
	          
	        //uses no layout managers 
	        frame1.setLayout(null); 
	          
	        //makes the frame visible 
	        frame1.setVisible(true); 
	        gui g = new gui();
		 	button1.addActionListener(g);
		 	button2.addActionListener(g);
		 	button3.addActionListener(g);
		 	button4.addActionListener(g);
		 	button5.addActionListener(g);
	    } 
	 	
	 	public void actionPerformed(ActionEvent e) 
	    { 
	        String s = e.getActionCommand();
			
	        if (s.equals("Compress")) 
	        { 
	        	uniqueChar = Main.getUniqueChar(textbox.getText());
	    		freq = Main.getFrequencies(textbox.getText(), uniqueChar);
	    		lh = Main.calculateRanges(uniqueChar, freq, textbox.getText());
	        	textbox1.setText(Main.compression(textbox.getText(), lh).toString());
	        	number = textbox.getText().length();
	        } 
	        else if (s.equals("Decompress")) 
	        { 
	        	textbox.setText(Main.decompression(textbox1.getText(),number, lh).toString());
	        } 
	        else if (s.equals("Read from file")) 
	        { 
	        	 File file = new File(textbox2.getText()); 
	        	 BufferedReader br = null;
	        	 String st="";
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 	  
	        	
	        	  try {
					st = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	if(st.contains("."))
	        	{
	        		//decompression(String bin,int length,arithmeticCoding[] lh)
	        		textbox.setText(Main.decompression(st,number, lh).toString());
	        		textbox1.setText(st);
	        	}
	        	else
	        	{
	        		uniqueChar = Main.getUniqueChar(st);
		    		freq = Main.getFrequencies(st, uniqueChar);
		    		lh = Main.calculateRanges(uniqueChar, freq, st);
		        	number = st.length();
	        		textbox.setText(st);
	        		textbox1.setText(Main.compression(st, lh).toString());
	        	}
	        }
	        else if(s.equals("New"))
	        {
	        	System.out.print("and");
	        	textbox.setText("");
	        	textbox1.setText("");
	        	textbox2.setText("");

	        }
	        else if (s.equals("Exit")) 
	        { 
	        	 System.exit(0); 
	        } 
	        
	    }
}

