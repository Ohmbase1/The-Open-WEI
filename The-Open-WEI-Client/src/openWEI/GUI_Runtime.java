package openWEI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI_Runtime {
	
	/**
	 * Main method run at startup. checks if arguments for host and port were passed. 
	 * Asks for them if not given or if given values fail.
	 * @param args Optional run-time arguments for host and port location of the database.
	 */
	public static void main(String[] args) {
		Client_Frame myFrame = new Client_Frame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(1024, 768);
		Boolean isHost = false; 
		String hostPort = "";
		Boolean argsCheck = false;
		try{						/** try to use run-time arguments. **/
			if(args.length == 2){
				argsCheck = true;
				hostPort = hostPort.concat(args[0]);
				hostPort = hostPort.concat(":" +args[1]);
			}
		}
		catch(NullPointerException ex){
			System.out.println("null pointer error...");
			return;
		}

		while(!isHost)		/** While we haven't connected to a database, don't display the Client Frame. **/
		{
			if(!argsCheck)
			{
				String host = JOptionPane.showInputDialog(null, "Enter Host", "Enter Host", JOptionPane.QUESTION_MESSAGE);
				if(host.isEmpty()){ return; }
				String port = JOptionPane.showInputDialog(null, "Enter Port", "Enter Port", JOptionPane.QUESTION_MESSAGE);
				hostPort = hostPort.concat(host);
				if(port.length() > 0){
					hostPort = hostPort.concat(":");
					hostPort = hostPort.concat(port);					
				}
			}
			isHost = myFrame.dbCheck(hostPort);			
			if(!isHost)
			{
				JOptionPane.showMessageDialog(null, "Could not Contact DB at: " + hostPort, "Error", JOptionPane.WARNING_MESSAGE);
				argsCheck = false;
				args = null;
				hostPort = "";
			}
		}
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
}
