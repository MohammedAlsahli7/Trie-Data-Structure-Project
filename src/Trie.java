import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
		System.out.println("\nEmpty trie created.\n");
	}

	public Trie(String s) {
		root = new TrieNode();
		insertAll(s);
		System.out.printf("\nTrie with initial letters of size %d created.\n\n", size());
	}

	private void insertAll(String s) {
		s = s.replace(" ", "").strip();
		Scanner input = null;
		try {
			input = new Scanner(new File("dictionary.txt"));
			while (input.hasNext()) {
				var tmp = input.nextLine();
				if (isPerm(tmp, s))
//				if(tmp.matches(String.format("[%s]{0,%d}",s ,s.length())))
					insert(tmp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Internal error, file (dictionary.txt) not found.");
		}
	}

	private boolean isPerm(String tmp, String s) {
		var flag = true;
		for (char c : tmp.toCharArray())
			if (s.chars().filter(ch -> ch == c).count() < tmp.chars().filter(ch -> ch == c).count())
				flag = false;
		return flag;
	}

	
	public void insert(String s) {

		s = s.replace(" ", "").strip();
//		if(!s.matches("[a-zA-Z]+"))
//			return;
		char index;
		TrieNode p = root;

		for (int i = 0; i < s.length(); i++) {
			index = s.charAt(i);
			if (!p.getChildren().containsKey(index))
				p.getChildren().put(index, new TrieNode(index));
			p = p.getChildren().get(index);
		}
		p.setLeaf(true);
	}

	public void delete(String s) {
		delete(s, 0 , root);
	}

	private TrieNode delete(String s, int i, TrieNode r) {
		if (r == null)
			return null;
		if (i == s.length()) {
			if (r.isWord())
				r.setLeaf(false);
			if (isEmpty(r))
				r = null;
			return r;
		}
		r.getChildren().replace(s.charAt(i), delete(s, i + 1, r.getChildren().get(s.charAt(i))));
		
		if (isEmpty(r) && !r.isWord())
			r = null;
		return r;
	}

	private TrieNode find(String s) {
		TrieNode p = root;
		for (int i = 0; i < s.length(); i++) {
			char index = s.charAt(i);
			var tmp = p.getChildren().get(index);
			if (tmp == null)
				return null;
			p = tmp;
		}
		return p;
	}

	public boolean isEmpty() {
		return root.getChildren().isEmpty();
	}

	private boolean isEmpty(TrieNode p) {
		return p.getChildren().isEmpty();
	}

	public boolean contains(String s) {
		TrieNode node = find(s);
		if (node != null && node.isWord())
			return true;
		return false;
	}

	public boolean isPrefix(String p) {
		TrieNode node = find(p);
		if (node != null)
			return true;
		return false;
	}

	public String[] allWordsPrefix(String p) {
		if (find(p) == null)
			return new String[0];
		return allLetters(p.length() > 1 ? p.substring(0, p.length() - 1) : "", find(p)).split(";");
	}

	private String allLetters(String s, TrieNode r) {
		if (r == null)
			return "";
		ArrayList<String> x = new ArrayList<>();
		String tmp = (int) r.getC() == 0? "" : r.getC()+"";
		if (r.isWord())
			x.add(s + tmp);
		for (char c : r.getChildren().keySet())
			x.add(allLetters(s + tmp, r.getChildren().get(c)));
		return String.join(";", x);
	}

	public void clear() {
		root.getChildren().clear();
	}

	public int size() {
		return size(root);
	}

	private int size(TrieNode r) {
		if (r == null)
			return 0;
		int x = 0;
		for (char c : r.getChildren().keySet())
			x += 1 + size(r.getChildren().get(c));
		return x;

	}
	public String[] all() {
		return allWordsPrefix("");
	}

}