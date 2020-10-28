package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import backend.client.MiniEditorClient;

/**
 * Front-end
 * @author mukrram
 *
 */
public class MiniEditorTextInterfaceWithCommand {
	
	public static void main(String[] args) {
	
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));        // To read input from the console terminal.
		
		String userCommand = "";
		MiniEditorClient client = new MiniEditorClient();                                      // Client of Command design Pattern
		System.out.println("********************************************************");
		System.out.println("*Welcome to Mini-Editor 1.0 (c) 2016 EIT Digital Rennes*");
		System.out.println("********************************************************");
		do {
			System.out.println("Enter command.[I/S/C/P/X/D/H/Q]");                             // Available commands.
			try {
				userCommand = keyboard.readLine();                                             // Input from console
				while(userCommand.matches("([Ii][ :].*)|([Ss][ :][\\d]+,[\\d]+)|([CPDXQHcpdxqh])") == false) {        // Regex to parse user command
					System.out.println("ERROR! Please select valid option.(H for help.)");
					userCommand = keyboard.readLine();	                                                              // In case of invalid regex, read again
				}
				switch(userCommand.substring(0,1).toUpperCase()) {                             // Switch on the bases of user command.
					case "I" :  {
						client.insert(userCommand.substring(2));                               // Call insert method of client.
						System.out.println("Text : " + client.getBufferText());                // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());        // Display selection after executing command.
					}
					break;
					case "S" : {
						String[] userCommandArgs = userCommand.substring(2).split(",");
						client.select(Integer.parseInt(userCommandArgs[0]), Integer.parseInt(userCommandArgs[1]));     // Call select method of client.
						System.out.println("Text : " + client.getBufferText());                                        // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());                                // Display selection after executing command.
					}
					break;
					case "C" : {
						client.copy();                                                         // Call cop method of client.
						System.out.println("Text : " + client.getBufferText());                // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());        // Display selection after executing command.
					}
					break;
					case "P" : {
						client.paste();                                                        // Call paste method of client.
						System.out.println("Text : " + client.getBufferText());                // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());        // Display selection after executing command.
					}
					break;
					case "X" : {
						client.cut();                                                          // Call cut method of client.
						System.out.println("Text : " + client.getBufferText());                // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());        // Display selection after executing command.
					}
					break;
					case "D" : {
						client.delete();                                                      // Call delete method of client.
						System.out.println("Text : " + client.getBufferText());               // Display buffer text after executing command.
						System.out.println("Selection : " + client.getSelectionText());       // Display selection after executing command.
					}
					break;
					case "H" : {                                                              // User help.
						System.out.println("********************************************************");
						System.out.println("*                         HELP                         *");
						System.out.println("********************************************************");
						System.out.println("(I/i) : Insert text.  [Ii][ :]<<Your text>>");
						System.out.println("(S/s) : Select text.  [Ss][ :]<<Start position>>:<<End position>>");
						System.out.println("(C/c) : Copy text.    [Cc]");
						System.out.println("(P/p) : Paste text.   [Pp]");
						System.out.println("(X/x) : Cut text.     [Xx]");
						System.out.println("(D/d) : Delete text.  [Dd]");
						System.out.println("(H/h) : Help.         [Hh]");
						System.out.println("(Q/q) : Quit.         [Qq]");
						break;
					}
					case "Q" : System.exit(1);                                               // Quit when user enters [q/Q] command.
					break;
					default : System.out.println("ERROR! Please select a valid option.");
				}
				System.out.println("----------------------------------");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} while(true);

	}

}
