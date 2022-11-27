package practice.work;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {
    private class Node implements Comparable<Node> {
        private char symbol;
        private int count;

        private Node left;
        private Node right;

        public Node(char symbol) {
            this.symbol = symbol;
        }

        public Node(Node left, Node right) {
            this.symbol = '+';
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int getFrequency() {
            if (isLeaf())
                return count;
            return left.getFrequency() + right.getFrequency();
        }

        public char getSymbol() {
            return symbol;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void add() {
            count++;
        }

        @Override
        public int compareTo(Node o) {
            return getFrequency() - o.getFrequency();
        }

        @Override
        public String toString() {
            String ch = symbol == '\n' ? "\\n" : "" + symbol;

            return String.format("'%s': %d", ch, getFrequency());
        }

        public void fillCodeMap(Map<Character, String> codeMap, String work) {
            if (isLeaf()) {
                codeMap.put(getSymbol(), work);
                return;
            }

            left.fillCodeMap(codeMap, work + "0");
            right.fillCodeMap(codeMap, work + "1");
        }
    }

    private Node root;

    private char[] getChars(String text) {
        char[] letters = new char[text.length()];
        text.getChars(0, text.length(), letters, 0);
        return letters;
    }

    private PriorityQueue<Node> countFrequencies(char[] letters) {
        Map<Character, Node> count = new HashMap<>();
        for (char ch : letters) {
            if (!count.containsKey(ch)) {
                count.put(ch, new Node(ch));
            }
            count.get(ch).add();
        }

        return new PriorityQueue<>(count.values());
    }

    private Node createTree(PriorityQueue<Node> nodes) {
        while (true) {
            Node node1 = nodes.poll();
            Node node2 = nodes.poll();

            Node parent = new Node(node1, node2);

            if (nodes.isEmpty()) {
                return parent;
            }

            nodes.add(parent);
        }
    }

    private Map<Character, String> createCodeMap() {
        Map<Character, String> result = new TreeMap<>();
        root.fillCodeMap(result, "");
        return result;
    }

    public String encode(String text) {
        char[] letters = getChars(text);
        root = createTree(countFrequencies(letters));
        Map<Character, String> codemap = createCodeMap();

        StringBuilder data = new StringBuilder();
        for (char ch : letters) {
            data.append(codemap.get(ch));
        }
        return data.toString();
    }

    public String decode(String data) {
        Node current = root;

        StringBuilder result = new StringBuilder();
        for (char ch : getChars(data)) {
            if (ch == '0') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }

            if (current.isLeaf()) {
                result.append(current.getSymbol());
                current = root;
            }
        }
        return result.toString();
    }
}
