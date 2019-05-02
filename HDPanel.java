import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class HDPanel extends JPanel
{
	private static final int PANEL_WIDTH = 500;
	private static final int PANEL_HEIGHT = 800;
	
	private ArrayList<String> list;//ArrayList that will contain all the Station ID's from "Mesonet.txt"
	
	
	/* This method will be used to initial the ArrayList<String> list
	 * 
	 */
	public static ArrayList<String> myList() throws IOException
	{
		ArrayList<String> stidList = new ArrayList<String>();
		
		// Use a buffered Reader on the file:
    	BufferedReader br = new BufferedReader(new FileReader(new File("Mesonet.txt")));
        String strg = null;
 
     
        // Loop over the lines in the file and place them into the ArrayList<String> named 'stidList'
        while((strg = br.readLine()) != null) 
        {
        	//strg.substring(0,4) is used to pull only the 4 variables of the Station ID's.
        	stidList.add(strg.substring(0, 4)); 
        }      
        //Close the buffered reader
        br.close();
        
        return stidList;
		
	}
	
	/*
	 * This method will calculate the Hamming distance between two Strings.
	 */
	
	public int HammingDistCalc(String stid1, String stid2) {
		int index; //used to locate and check each letter of the string
		int distance = 0; //this will be the value of the Hamming Distance between the two Strings
		for (index = 0; index < stid1.length(); index++) 
		{ 
		    if (stid1.charAt(index) != stid2.charAt(index)) 
		    {	
		       distance++;      
		    }
		} 
		return distance;
	}
	
	public HDPanel() throws IOException 
	{
		
		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		
		JPanel panel = new JPanel(new GridLayout(0,2));//This panel will contain the required parts of the project on the left side and the creative part on the right side.
		
		JPanel panel0 = new JPanel(new GridLayout(12,1));//This panel will contain all the components for the required parts of the project.
				
		JPanel panel1 = new JPanel(new GridLayout(0,2));//This panel will contain a JLabel and JTextField
		
		JLabel enterHDInfo = new JLabel("Enter Hamming Dist:");
		JTextField enterHD = new JTextField();
		enterHD.setEditable(false);//This is so the user cannot change the vaule in the JTextField
		enterHD.setText("1");//Initialize the JTextField to read "1".
		panel1.add(enterHDInfo);
		panel1.add(enterHD);
		panel1.setSize(100, 10);
		
		JSlider HDSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);//The JSlider is the slider that will be used to set the Hamming Distance to be calculated
		HDSlider.setMajorTickSpacing(1);
		//Setting the slider up to show the ticks and labels
		HDSlider.setPaintTicks(true);
		HDSlider.setPaintLabels(true);
		HDSlider.setPaintTrack(true);
		
		//Add ChangeListener to change the text of the JTextField "enterHD"
		HDSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enterHD.setText("" + ((JSlider)e.getSource()).getValue());
			}
		});
		//This panel will be created to hold a button and an empty space for formatting
		JPanel panel2 = new JPanel(new GridLayout(0, 2));
		//This button will output the list of Station ID's a certain Hamming Distance away
		JButton showStation = new JButton("Show Station");
		
		panel2.add(showStation);
		
		//First, created a JTextArea for the output list
		JTextArea output = new JTextArea();
		//Second, add the JTextArea to a JScrollPane so the user can scroll through the list
		JScrollPane outputsp = new JScrollPane(output);
		
		//This is a label for the dropdown box
		JLabel compareInfo = new JLabel("Compare with:");
		//This creates a dropdown box filled with strings
		JComboBox<String> stationComboBox = new JComboBox<String>();
		//This calls upon the myList() method and places that in the list for the program
		this.list = myList();
		
		//The list is then looped through and added to the dropdown box
		for(int index=0; index < list.size(); index++) {
			stationComboBox.addItem(list.get(index));
		}
		
		//ActionListener was added to the showStation button so when the button is pressed, a list is output in the JTextArea.
		showStation.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//new arrayList that will hold the station ID's that are the selected Hamming Distance
				ArrayList<String> outputList = new ArrayList<String>();
				
				//Reads value from the JTextField enterHD and checks if it is equal to 1, 2, 3, or 4.
				//Once the Hamming Distance is chosen the list will then be looped through to check 
				//if the Hamming Distance is equal to the selected value and if it is, then the string from the list is added to the new outputList
				if (enterHD.getText().equals("1")) 
				{
					for(int index = 0; index < list.size(); index++) {
						if(HammingDistCalc(stationComboBox.getSelectedItem().toString(), 
								list.get(index)) == 1) 
						{
							outputList.add(list.get(index));
						}
					}
					
				}
				else if (HDSlider.getValue() == 2) 
				{
					for(int index = 0; index < list.size(); index++) {
						if(HammingDistCalc(stationComboBox.getSelectedItem().toString(), 
								list.get(index)) == 2) 
						{
							outputList.add(list.get(index));
						}
					}
				}
				else if (enterHD.getText().equals("3"))
				{
					for(int index = 0; index < list.size(); index++) {
						if(HammingDistCalc(stationComboBox.getSelectedItem().toString(), 
								list.get(index)) == 3) 
						{
							outputList.add(list.get(index));
						}
					}
				}
				else 
				{
					for(int index = 0; index < list.size(); index++) {
						if(HammingDistCalc(stationComboBox.getSelectedItem().toString(), 
								list.get(index)) == 4) 
						{
							outputList.add(list.get(index));
						}
					}
				}
				//I used this for loop and String in order to create the desired output for the list. (adds a new line after each string)
				String strg = "";
				for(int index = 0; index < outputList.size(); index++)
				{
					strg += outputList.get(index) + "\n";
				}
				//Sets the output JTextField to the String created
				output.setText(strg);
			}	
			
		});
		
		//order matters so panel1 then Slider bar then panel 2 and lastly the JScrollPane
		panel0.add(panel1);//order matters so panel1 then Slider bar then panel 2 and lastly the JScrollPane
		panel0.add(HDSlider);
		panel0.add(panel2);
		panel0.add(outputsp);
		//panel3 will contain the dropdown box and its JLabel
		JPanel panel3 = new JPanel(new GridLayout(0,2));
		
		panel3.add(compareInfo);
		panel3.add(stationComboBox);
		
		panel0.add(panel3);
		
		//panel 4 will hold only the calculateHD button 
		JPanel panel4 = new JPanel(new GridLayout(0,2));
		
		
		JButton calculateHD = new JButton("Calculate HD");
		
		
		//Initialize JLabels, JTextFields, and JButtons to be used
		JLabel distance0 = new JLabel("Distance 0");
		JTextField distance0out = new JTextField();
		distance0out.setEditable(false);
		JLabel distance1 = new JLabel("Distance 1");
		JTextField distance1out = new JTextField();
		distance1out.setEditable(false);
		JLabel distance2 = new JLabel("Distance 2");
		JTextField distance2out = new JTextField();
		distance2out.setEditable(false);
		JLabel distance3 = new JLabel("Distance 3");
		JTextField distance3out = new JTextField();
		distance3out.setEditable(false);
		JLabel distance4 = new JLabel("Distance 4");
		JTextField distance4out = new JTextField();
		distance4out.setEditable(false);
		JButton addStation = new JButton("Add Station");
		JTextField addStationInput = new JTextField();
		
		//Add an ActionListener that will calculate the sums of each Hamming Distance
		calculateHD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int distance0 = 0;
				int distance1 = 0;
				int distance2 = 0;
				int distance3 = 0;
				int distance4 = 0;
				
				for(int index = 0; index < list.size(); index++) {
					if(HammingDistCalc(stationComboBox.getSelectedItem().toString(), 
							list.get(index)) == 0) 
					{
						distance0 ++;
					}
					else if (HammingDistCalc(stationComboBox.getSelectedItem().toString(),
							list.get(index)) == 1) {
						distance1 ++;
					}
					else if (HammingDistCalc(stationComboBox.getSelectedItem().toString(),
							list.get(index)) == 2) {
						distance2 ++;
					}
					else if (HammingDistCalc(stationComboBox.getSelectedItem().toString(),
							list.get(index)) == 3) {
						distance3 ++;
					}
					else {
						distance4 ++;
					}
				}
				
				distance0out.setText("" + distance0);
				distance1out.setText("" + distance1);
				distance2out.setText("" + distance2);
				distance3out.setText("" + distance3);
				distance4out.setText("" + distance4);
			}
			
		});
		
		//add an ActionListener to addStation JButton in order to write out to the file and add the new ID to the list and dropdown box
		addStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try (FileWriter input = new FileWriter("Mesonet.txt"); 
						BufferedWriter bw = new BufferedWriter(input)){
					list.add(addStationInput.getText());
					Collections.sort(list);
					for (int i = 0; i < list.size(); i++) 
					{
						bw.write(list.get(i) + "\n");
					}
					stationComboBox.removeAllItems();
					for (int j = 0; j < list.size(); j++) 
					{
						stationComboBox.addItem(list.get(j));
					}
					bw.close();
				}catch (IOException e1) {
					System.err.format("IOException : %s%n", e1);
				}
			}
		});
		panel4.add(calculateHD);
		panel0.add(panel4);
		//panel to hold Distance 0 outputs
		JPanel dist0p = new JPanel(new GridLayout(1,2));
		dist0p.add(distance0);
		dist0p.add(distance0out);
		panel0.add(dist0p);
		//panel to hold Distance 1 outputs
		JPanel dist1p = new JPanel(new GridLayout(1,2));
		dist1p.add(distance1);
		dist1p.add(distance1out);
		panel0.add(dist1p);
		//panel to hold Distance 2 outputs
		JPanel dist2p = new JPanel(new GridLayout(1,2));
		dist2p.add(distance2);
		dist2p.add(distance2out);
		panel0.add(dist2p);
		//panel to hold Distance 3 outputs
		JPanel dist3p = new JPanel(new GridLayout(1,2));
		dist3p.add(distance3);
		dist3p.add(distance3out);
		panel0.add(dist3p);
		//panel to hold Distance 4 outputs
		JPanel dist4p = new JPanel(new GridLayout(1,2));
		dist4p.add(distance4);
		dist4p.add(distance4out);
		panel0.add(dist4p);
		//panel to hold the addstation button and input JTextField
		JPanel addStationPanel = new JPanel(new GridLayout(1,2));
		addStationPanel.add(addStation);
		addStationPanel.add(addStationInput);
		panel0.add(addStationPanel);
		//add the panel to the class panel
		panel.add(panel0);
		//add the panel to the class panel
		this.add(panel);
		
		//This contains the creative idea panel
		JPanel panel00 = new JPanel(new GridLayout(0,1));
		//this panel will contain just a single button that says "Calculate" that when pressed
		//will calculate the total number of station ID that have the same first letter, second letter, third letter, and fourth letter
		JPanel panel01 = new JPanel(new GridLayout(0,2));
		JButton calculate = new JButton("Calculate");
		
		//creates a label to explain what the button does
		JLabel firstLetterInfo = new JLabel("The Number of IDs that start with:");
		//panel to hold a label that will hold the selected station IDs letters and the output sum.
		JPanel panel02 = new JPanel(new GridLayout(0,2));
		JLabel firstLetter = new JLabel();
		JTextField firstLetterSum = new JTextField();
		firstLetterSum.setEditable(false);
		panel02.add(firstLetter);
		panel02.add(firstLetterSum);
		
		JLabel secondLetterInfo = new JLabel("The Number of IDs whoms's second letter is:");
		
		JPanel panel03 = new JPanel(new GridLayout(0,2));
		JLabel secondLetter = new JLabel();
		JTextField secondLetterSum = new JTextField();
		secondLetterSum.setEditable(false);
		panel03.add(secondLetter);
		panel03.add(secondLetterSum);
		
		JLabel thirdLetterInfo = new JLabel("The Number of IDs whom's third letter is:");
		
		JPanel panel04 = new JPanel(new GridLayout(0,2));
		JLabel thirdLetter = new JLabel();
		JTextField thirdLetterSum = new JTextField();
		thirdLetterSum.setEditable(false);
		panel04.add(thirdLetter);
		panel04.add(thirdLetterSum);
		
		JLabel fourthLetterInfo = new JLabel("The Number of IDs whom's fourth letter is:");
		
		JPanel panel05 = new JPanel(new GridLayout(0,2));
		JLabel fourthLetter = new JLabel();
		JTextField fourthLetterSum = new JTextField();
		fourthLetterSum.setEditable(false);
		panel05.add(fourthLetter);
		panel05.add(fourthLetterSum);
		
		//add an ActionListener to calculate the sums of letter
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				firstLetter.setText("" + stationComboBox.getSelectedItem().toString().charAt(0));
				secondLetter.setText("" + stationComboBox.getSelectedItem().toString().charAt(1));
				thirdLetter.setText("" + stationComboBox.getSelectedItem().toString().charAt(2));
				fourthLetter.setText("" + stationComboBox.getSelectedItem().toString().charAt(3));
				
				int firstSum = 0, secondSum = 0, thirdSum = 0, fourthSum = 0;
				
				for (int i = 0; i < list.size(); i++) {
					if(("" + stationComboBox.getSelectedItem().toString().charAt(0)).equals("" + list.get(i).charAt(0))) {
						firstSum++;
					}
					else if(("" + stationComboBox.getSelectedItem().toString().charAt(1)).equals("" + list.get(i).charAt(1))) {
						secondSum++;
					}
					else if(("" + stationComboBox.getSelectedItem().toString().charAt(2)).equals("" + list.get(i).charAt(2))) {
						thirdSum++;
					}
					else if(("" + stationComboBox.getSelectedItem().toString().charAt(3)).equals("" + list.get(i).charAt(3))) {
						fourthSum++;
					}
				}
				
				firstLetterSum.setText("" + firstSum);
				secondLetterSum.setText("" + secondSum);
				thirdLetterSum.setText("" + thirdSum);
				fourthLetterSum.setText("" + fourthSum);
			}
			
		});
		panel01.add(calculate);
		panel00.add(panel01);
		panel00.add(firstLetterInfo);
		panel00.add(panel02);
		panel00.add(secondLetterInfo);
		panel00.add(panel03);
		panel00.add(thirdLetterInfo);
		panel00.add(panel04);
		panel00.add(fourthLetterInfo);
		panel00.add(panel05);
		
		this.add(panel00);
		
		
		
		setVisible(true);
	}
	
	
}
