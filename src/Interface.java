import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		Trie tr = null;
		String word = null;
		String s ="TRIE PROJECT: Enter your choice?\n"
				+ "1) Create an empty trie\n"
				+ "2) Create a trie with initial letters\n"
				+ "3) Insert a word\n"
				+ "4) Delete a word\n"
				+ "5) List all words that begin with a prefix\n"
				+ "6) Size of the trie\n"
				+ "7) End\n";
		System.out.println(s);
		Scanner scan = new Scanner(System.in);
		var flag = true;
		while(flag) {
			try {
				System.out.print(">/ Enter a number (1-7)> ");
				
				var choice = Integer.parseInt(scan.next());
				System.out.println("........");
				switch(choice) {
				case 1:
					tr = new Trie();
					break;
				case 2:
					System.out.print(">/ Enter the letters (without spaces)> ");
					var initial = scan.next();
					tr = new Trie(initial);
					break;
				case 3:
					if(tr == null)
						throw new NullPointerException();
					System.out.print(">/ Enter a word> ");
					word = scan.next();
					if(tr.contains(word))
						System.out.println("Word is already in the trie.");
					else {
						tr.insert(word);
						if(tr.contains(word))
							System.out.println("\nWord inserted.\n");
						else
							System.out.println("Invalid word.");
					}
					break;
				case 4:
					if(tr == null)
						throw new NullPointerException();
					System.out.print(">/ Enter a word> ");
					word = scan.next();
					if(tr.contains(word)) {
						tr.delete(word);
						System.out.println("\nWord deleted.\n");
					} else
						System.out.println("Word not found.");
					break;
				case 5:
					if(tr == null)
						throw new NullPointerException();
					System.out.print(">/ Enter a prefix> ");
					word = scan.next();
					System.out.println("Found the following words: " + String.join(", ", (tr.allWordsPrefix(""))));
					break;
				case 6:
					if(tr == null)
						throw new NullPointerException();
					System.out.println("The size of the Trie is> " + tr.size());
					break;
				case 7:
					System.out.println("Program stoppend.");
					flag = false;
					break;
				default:
					throw new Exception();
				}
			} catch(NullPointerException e1) {
				System.out.println("Invalid order of commands (create a tree first), please try again.");
			} catch(Exception e) {
				System.out.println("Invalid input, please try again.");
			}
			
		}
		scan.close();
		
		
		}

}
