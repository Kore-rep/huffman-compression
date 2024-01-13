/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package huffman.compression;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import huffman.compression.HuffTree.HuffInternalNode;
import huffman.compression.HuffTree.HuffLeafNode;
import huffman.compression.HuffTree.HuffTree;
import huffman.compression.interfaces.HuffBaseNode;

public class Compressor {

    public static void main(String[] args) {
    }

    public static HashMap<Character, Integer> calculateCharacterFrequencies(String filePath) throws IOException {
        if (!filePath.endsWith(".txt"))
            throw new UnsupportedOperationException("Only supported text files.");
        HashMap<Character, Integer> map = new HashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                line.chars().forEach(c -> {
                    map.put((char) c, map.getOrDefault((char) c, 0) + 1);
                });

            });
        }
        return map;
    }

    public static PriorityQueue<HuffTree> buildPriorityQueue(Map<Character, Integer> map) {
        PriorityQueue<HuffTree> heap = new PriorityQueue<>();
        map.entrySet().forEach(entry -> {
            heap.add(new HuffTree(entry.getKey(), entry.getValue()));
        });
        return heap;
    }

    public static HuffTree buildBinaryTree(PriorityQueue<HuffTree> heap) {
        HuffTree tmp1, tmp2, tree = null;

        while (heap.size() > 1) {
            tmp1 = heap.poll();
            tmp2 = heap.poll();
            tree = new HuffTree(tmp1.root(), tmp2.root(), tmp1.weight() + tmp2.weight());
            heap.add(tree);
        }
        return tree;
    }

    public static HashMap<Character, String> buildPrefixCodeTable(HuffTree tree) {
        HashMap<Character, String> prefixTable = new HashMap<>();
        preOrderTraversal(tree.root(), prefixTable, "");
        return prefixTable;
    }

    public static void preOrderTraversal(HuffBaseNode root, HashMap<Character, String> map, String code) {

        HuffBaseNode node = root;
        if (root.isLeaf()) {
            map.put(((HuffLeafNode) node).value(), code);
            return;
        }
        preOrderTraversal(((HuffInternalNode) root).left(), map, code + "0");
        preOrderTraversal(((HuffInternalNode) root).right(), map, code + "1");
    }
}
