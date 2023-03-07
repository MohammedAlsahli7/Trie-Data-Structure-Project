import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your list of letters> ");
		Trie tr = new Trie(scan.nextLine());
		
		
		System.out.print("Enter a word to search for> ");
		var s1 = scan.next();
		System.out.println(tr.contains(s1)? "Word Found: " + s1: "Word not Found: "+s1);
		System.out.print("Enter a prefix> ");
		var s2 = scan.next();
		System.out.println("Found the following words: " + String.join(", ", (tr.allWordsPrefix(s2))));
		System.out.print("Enter a word to insert> ");
		var s3 = scan.next();
		tr.insert(s3);
		System.out.println("Word inserted.");
		System.out.print("Enter a word to delete> ");
		var s4 = scan.next();
		tr.delete(s4);
		System.out.println("Word deleted.");
//		System.out.println("Found all following words:\n " + String.join(",\n ", (tr.all())));
//		System.out.println(tr.size());
		
		scan.close();
		
	
 
    }

}
