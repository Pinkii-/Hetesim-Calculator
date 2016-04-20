package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GenericTest {
	private final static String pt = "\033[0;0m";
    private final static String bt = "\033[0;1m";
	
	enum Command {
		help, exit, varType, var
	}
	
	enum VariableTypes {
		integer, matrix, graf, node, hetesanic
	}
	
	static Map<String,Object> variables;
	static Map<String,String> helpMessages;
	
	static String testClassName = HeteSanic.class.toString();
	
	public static void main(String[] args) throws IOException {
		variables = new HashMap<String,Object>();
		helpMessages = new HashMap<String,String>();
		helpMessages.put(Command.help.toString(), "Shows this message");
		helpMessages.put(Command.exit.toString(), "Close the program");
		helpMessages.put(Command.varType.toString(), "Shows the type of the data that can be instanciated");
		helpMessages.put(Command.var.toString(), "var varType nameOfVariable - Instantiate a variable varType with name nameOfVariable");
		System.out.println("Welcome to the test of " + testClassName+":");
		help();
		while (true) {
			System.out.print("> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			String s[] = in.split("\\s+");
			in = s[0];
			try{
				Command current = Command.valueOf(in);
				switch(current) {
				case help:
					help();
					break;
				case exit:
					System.exit(0);
					break;
				case varType:
					variables();
					break;
				case var:
					if (s.length < 3) {
						System.out.println("This command need at least two arguments");
						continue;
					}
					try {
						VariableTypes vt = VariableTypes.valueOf(s[1]);
						switch (vt) {
							case integer:
								variables.put(s[2],new Integer(s[3]));
								break;
							default:
								break;
						}
					}
					catch (IllegalArgumentException e) {
						System.out.println("Wrong variable type");
						variables();
						continue;
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Wrong number of parametes pass to the constructor");
						// Shows the parameters that need a variable
						continue;
					}
					
				}
			}
			catch (IllegalArgumentException e) {

				if (in.isEmpty()) continue;
				else if (variables.containsKey(in)) {
					System.out.println(in + " = " + variables.get(in) + " " + variables.get(in).getClass());
				}
				else System.out.println("The command " + bt + in + pt +" doesn't exist.");
				continue;
			}
		}
	}
	
	public static void help() {
		System.out.println("You have the nexts commands availables:");
		for (Command c : Command.values()) {
			System.out.println("+ " + c.toString() + ": "+ helpMessages.get(c.toString()));
		}
	}
	
	public static void variables() {
		System.out.println("Types of variables?");	
		for (VariableTypes v : VariableTypes.values()) {
			System.out.println("+ "+v.toString());
		}
	}
}
