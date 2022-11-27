package practice.work;

import java.util.*;
import java.util.stream.Collectors;

public class MTF {

    public String encode(String text, String symTable) {
        StringBuilder output = new StringBuilder();
        StringBuilder s = new StringBuilder(symTable);
        for (char c : text.toCharArray()) {
            int idx = s.indexOf("" + c);
            output = output.append(idx).append(" ");
            s = s.deleteCharAt(idx).insert(0, c);
        }
        return output.deleteCharAt(output.length() - 1).toString();
    }

    public String getAlphabet(String string) {
        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            charSet.add(string.charAt(i));
        }

        List<Character> charList = new ArrayList<>(charSet);

        Collections.sort(charList);

        StringBuilder sb = new StringBuilder();

        for (Character c : charList) {
            sb.append(c);
        }

        return sb.toString();
    }

    public String decode(String text, String symTable) {
        List<Integer> idxs = Arrays.stream(text.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        StringBuilder output = new StringBuilder();
        StringBuilder s = new StringBuilder(symTable);
        for (int idx : idxs) {
            char c = s.charAt(idx);
            output = output.append(c);
            s = s.deleteCharAt(idx).insert(0, c);
        }
        return output.toString();
    }
}
