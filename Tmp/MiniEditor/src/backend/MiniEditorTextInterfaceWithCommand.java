package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import backend.client.MiniEditorClient;

public class MiniEditorTextInterfaceWithCommand {
	
	public static void main(String[] args) {
	
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		String userCommand = "";
		MiniEditorClient client = new MiniEditorClient();
		System.out.println("********************************************************");
		System.out.println("*Welcome to Mini-Editor 2.0 (c) 2016 EIT Digital Rennes*");
		System.out.println("********************************************************");
		do {
			System.out.println("Enter command.[I/S/C/P/X/D/H/Q/U/R]");
			try {
				userCommand = keyboard.readLine();
				while(userCommand.matches("([Ii][ :].*)|([Ss][ :][\\d]+,[\\d]+)|([CPDXQHURcpdxqhur])") == false) {
					System.out.println("ERROR! Please select valid option.(H for help.)");
					userCommand = keyboard.readLine();	
				}
				switch(userCommand.substring(0,1).toUpperCase()) {
					case "I" :  {
						client.insert(userCommand.substring(2));
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "S" : {
						String[] userCommandArgs = userCommand.substring(2).split(",");
						client.select(Integer.parseInt(userCommandArgs[0]), Integer.parseInt(userCommandArgs[1]));
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "C" : {
						client.copy();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "P" : {
						client.paste();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "X" : {
						client.cut();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "D" : {
						client.delete();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
					}
					break;
					case "U" : {
						client.undo();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
						
					}
					break;
					case "R" : {
						client.redo();
						System.out.println("Text : " + client.getBufferText());
						System.out.println("Selection : " + client.getSelectionText());
						
					}
					break;
					case "H" : {
						System.out.println("********************************************************");
						System.out.println("*                         HELP                         *");
						System.out.println("********************************************************");
						System.out.println("(I/i) : Insert text.  [Ii][ :]<<Your text>>");
						System.out.println("(S/s) : Select text.  [Ss][ :]<<Start position>>,<<End position>>");
						System.out.println("(C/c) : Copy text.    [Cc]");
						System.out.println("(P/p) : Paste text.   [Pp]");
						System.out.println("(X/x) : Cut text.     [Xx]");
						System.out.println("(D/d) : Delete text.  [Dd]");
						System.out.println("(U/u) : Undo.         [Uu]");
						System.out.println("(R/r) : Redo.         [Rr]");
						System.out.println("(H/h) : Help.         [Hh]");
						System.out.println("(Q/q) : Quit.         [Qq]");
						break;
					}
					case "Q" : System.exit(1);
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
