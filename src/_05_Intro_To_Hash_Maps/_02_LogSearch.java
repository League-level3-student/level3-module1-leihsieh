package _05_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
    /* 
     * Crate a HashMap of Integers for the keys and Strings for the values.
     * Create a GUI with three buttons. 
     * Button 1: Add Entry
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      After an ID is entered, use another input dialog to ask the user
     *      to enter a name. Add this information as a new entry to your
     *      HashMap.
     * 
     * Button 2: Search by ID
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      If that ID exists, display that name to the user.
     *      Otherwise, tell the user that that entry does not exist.
     * 
     * Button 3: View List
     *      When this button is clicked, display the entire list in a message
     *      dialog in the following format:
     *      ID: 123  Name: Harry Howard
     *      ID: 245  Name: Polly Powers
     *      ID: 433  Name: Oliver Ortega
     *      etc...
     * 
     * When this is complete, add a fourth button to your window.
     * Button 4: Remove Entry
     *      When this button is clicked, prompt the user to enter an ID using
     *      an input dialog.
     *      If this ID exists in the HashMap, remove it. Otherwise, notify the
     *      user that the ID is not in the list. 
     */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton addButton = new JButton("Add Entry");
	JButton searchButton = new JButton("Search By ID");
	JButton viewButton = new JButton("View List");
	JButton removeButton = new JButton("Remove Entry");
	HashMap<Integer,String> names = new HashMap<Integer,String>();
	
		_02_LogSearch(){
			names.put(123, "Harry Howard");
			names.put(245, "Polly Powers");
			names.put(433, "Oliver Ortega");
			addButton.addActionListener(this);
			searchButton.addActionListener(this);
			viewButton.addActionListener(this);
			removeButton.addActionListener(this);
			panel.add(addButton);
			panel.add(searchButton);
			panel.add(viewButton);
			panel.add(removeButton);
			frame.add(panel);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public static void main(String[] args) {
			_02_LogSearch log = new _02_LogSearch();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==addButton) {
				String IDInput = JOptionPane.showInputDialog("Input an ID:");
				int ID = Integer.parseInt(IDInput);
				String name = JOptionPane.showInputDialog("Input a Name:");
				names.put(ID, name);
				JOptionPane.showMessageDialog(null, "The entry has been added.");
			}
			if(e.getSource()==searchButton) {
				String IDInput = JOptionPane.showInputDialog("Enter an ID to search: ");
				int intput = Integer.parseInt(IDInput);
				if(names.containsKey(intput)==true) {
					JOptionPane.showMessageDialog(null, "The name with that ID is " + names.get(intput));
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no name associated with the ID " + intput);
				}
			}
			
			if(e.getSource()==viewButton) {
				
				String printNames = "";
				for (Integer key : names.keySet()) {
					String value = names.get(key);
					printNames+="ID: " + key + "    Name: " +value +  "\n";
				}
				JOptionPane.showMessageDialog(null, printNames);
			}
			
			if(e.getSource()==removeButton) {
				String IDInput = JOptionPane.showInputDialog("Enter an ID to remove: ");
				int removeID = Integer.parseInt(IDInput);
				if(names.containsKey(removeID)==true) {
					JOptionPane.showMessageDialog(null, "The name associated with the ID " + removeID + " has been removed");
					names.remove(removeID);
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no name associated with the ID " + removeID);
			}
			
		}
	}
}
