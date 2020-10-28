package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import backend.Clipboard;
import backend.MiniEditorClient;
import backend.Observer;
import backend.State;

/**
 * Main CLI (Command line Interface) class 
 * @author mukrram
 *
 */
public class MiniEditorTextInterfaceWithCommand implements Observer{
	
	private MiniEditorClient client;
	
	public MiniEditorTextInterfaceWithCommand() {
		client = new MiniEditorClient();
		client.register(this);
	}
	
	@Override
	public void update(String bufferText, String selectionText) {
		System.out.println("Text : " + bufferText);
		System.out.println("Selection : " + selectionText);
	}
	
	public void launch() {
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		String userCommand = "";
		
		System.out.println("********************************************************");
		System.out.println("*Welcome to Mini-Editor 3.0 (c) 2016 EIT Digital Rennes*");
		System.out.println("********************************************************");
		do {
			System.out.println("Enter command.[I/S/C/P/X/D/H/Q/F/L/G/U/R/O/W]");
			try {
				userCommand = keyboard.readLine();
				while(userCommand.matches("([Ii][ :].*)|([Ss][ :][\\d]+,[\\d]+)|([CPDXQHFLGURcpdxqhflgur])|([Oo][ :].*)|([Ww][ :].*)") == false) {
					System.out.println("ERROR! Please select valid option.(H for help.)");
					userCommand = keyboard.readLine();	
				}
				switch(userCommand.substring(0,1).toUpperCase()) {
					case "I" : client.insert(userCommand.substring(2));
					break;
					case "S" : {
						String[] userCommandArgs = userCommand.substring(2).split(",");
						client.select(Integer.parseInt(userCommandArgs[0]), Integer.parseInt(userCommandArgs[1]));
					}
					break;
					case "C" : client.copy();
					break;
					case "P" : client.paste();
					break;
					case "X" : client.cut();
					break;
					case "D" : client.delete();
					break;
					case "F" : client.startRecord();
					break;
					case "L" : client.stopRecord();
					break;
					case "G" : client.replay();
					break;
					case "U" : client.undo();
					break;
					case "R" : client.redo();
					break;
					case "O" : client.open(userCommand.substring(2));
					break;
					case "W" : client.write(userCommand.substring(2));
					break;
					case "H" : {
						System.out.println("********************************************************");
						System.out.println("*                         HELP                         *");
						System.out.println("********************************************************");
						System.out.println("(I/i) : Insert text.      [Ii][ :]<<Your text>>");
						System.out.println("(S/s) : Select text.      [Ss][ :]<<Start position>>,<<End position>>");
						System.out.println("(C/c) : Copy text.        [Cc]");
						System.out.println("(P/p) : Paste text.       [Pp]");
						System.out.println("(X/x) : Cut text.         [Xx]");
						System.out.println("(D/d) : Delete text.      [Dd]");
						System.out.println("(F/f) : Start recording.  [Ff]");
						System.out.println("(L/l) : Stop recording.   [Ll]");
						System.out.println("(G/g) : Replay recording. [Gg]");
						System.out.println("(U/u) : Undo.             [Uu]");
						System.out.println("(R/r) : Redo.             [Rr]");
						System.out.println("(O/o) : Open.             [Oo][ :]<<File path>>");
						System.out.println("(W/w) : Write.            [Ww][ :]<<File path>>");
						System.out.println("(H/h) : Help.             [Hh]");
						System.out.println("(Q/q) : Quit.             [Qq]");
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
	
	public static void main(String[] args) {
	
		new MiniEditorTextInterfaceWithCommand().launch();

	}

}
