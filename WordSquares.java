/**
 * Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:

There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 */
import java.util.ArrayList;
import java.util.List;

public class WordSquares {

    class Trie {
        Trie[] children;
        List<String> words;
        Trie() {
            children = new Trie[26];
            words = new ArrayList<>();
        }
        private void insert(String word) {
            Trie cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Trie(); 
                    
                }
                cur.children[c - 'a'].words.add(word);
                cur = cur.children[c - 'a'];
            }
        }

        private List<String> getPrefixList(String prefix) {
            Trie cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    return new ArrayList<>();
                }
                cur = cur.children[c - 'a'];
            }
            return cur.words;
        }
    }

    Trie root;
    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        result = new ArrayList<>();
        root = new Trie();

        for (String word : words) {
            root.insert(word);
        }

        for (String word : words) {
            List<String> ans = new ArrayList<>();
            ans.add(word);
            backtrack(ans);
        }

        return result;
        
    }
    private void backtrack(List<String> answer) {

        if (answer.size() == answer.get(0).length()) {
            result.add(new ArrayList<>(answer));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); ++i) {
            sb.append(answer.get(i).charAt(answer.size()));
        }
        String prefix = sb.toString();

        for (String s : root.getPrefixList(prefix)) {
            answer.add(s);
            backtrack(answer);
            answer.remove(answer.size() - 1);
        }
    }
    public static void main(String[] args) {
        WordSquares ws = new WordSquares();
        // String[] input = new String[] {"area","lead","wall","lady","ball"};
        String[] input = new String[] {"abat","baba","atan","atal"};
        System.out.println(ws.wordSquares(input));
    }

}