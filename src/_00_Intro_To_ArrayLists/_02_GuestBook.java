package _00_Intro_To_ArrayLists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_GuestBook implements ActionListener {
    /*
     * Create a GUI with two buttons. One button reads "Add Name" and the other
     * button reads "View Names". When the add name button is clicked, display
     * an input dialog that asks the user to enter a name. Add that name to an
     * ArrayList. When the "View Names" button is clicked, display a message
     * dialog that displays all the names added to the list. Format the list as
     * follows:
     * Guest #1: Bob Banders
     * Guest #2: Sandy Summers
     * Guest #3: Greg Ganders
     * Guest #4: Donny Doners
     */
	ArrayList<String> namesList = new ArrayList<String>();
	JButton button1 = new JButton("Add a Name");
	JButton button2 = new JButton("View Names");
	JPanel panel = new JPanel();
	JFrame frame = new JFrame();
	
	_02_GuestBook(){
		button1.addActionListener(this);
		button2.addActionListener(this);
		panel.add(button1);
		panel.add(button2);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		_02_GuestBook gb = new _02_GuestBook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button1) {
			String newName = JOptionPane.showInputDialog("Enter a Name:");
			namesList.add(newName);
		}
		if(e.getSource()==button2) {
			for(int i = 0; i < namesList.size(); i++) {
				int guestCounter = i+1;
				JOptionPane.showMessageDialog(null, "Guest " + guestCounter + ": " + namesList.get(i));
			}
		}
	}
}