package practice.work;

import java.util.ArrayList;
import java.util.List;

public class BWT {
    private static final String STX = "\u0002";
    private static final String ETX = "\u0003";

    public String encode(String text) {
        String ss = STX + text + ETX;
        List<String> table = new ArrayList<>();
        for (int i = 0; i < ss.length(); i++) {
            String before = ss.substring(i);
            String after = ss.substring(0, i);
            table.add(before + after);
        }
        table.sort(String::compareTo);

        StringBuilder sb = new StringBuilder();
        for (String str : table) {
            sb.append(str.charAt(str.length() - 1));
        }
        return sb.toString();
    }

    public String decode(String text) {
        int len = text.length();
        List<String> table = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            table.add("");
        }
        for (int j = 0; j < len; ++j) {
            for (int i = 0; i < len; ++i) {
                table.set(i, text.charAt(i) + table.get(i));
            }
            table.sort(String::compareTo);
        }
        for (String row : table) {
            if (row.endsWith(ETX)) {
                return row.substring(1, len - 1);
            }
        }
        return "";
    }
}
