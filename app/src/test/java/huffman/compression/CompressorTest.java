/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package huffman.compression;

import org.junit.jupiter.api.Test;

import huffman.compression.HuffTree.HuffInternalNode;
import huffman.compression.HuffTree.HuffLeafNode;
import huffman.compression.HuffTree.HuffTree;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class CompressorTest {

    @Test
    void compressorGeneratesCharacterFrequencies() {
        try {
            HashMap<Character, Integer> map = Compressor
                    .calculateCharacterFrequencies("./src/test/resources/135-0.txt");
            assertEquals(333, map.get('X'));
            assertEquals(223000, map.get('t'));
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    void compressorGeneratesCharacterFrequencies2() {
        try {
            HashMap<Character, Integer> map = Compressor
                    .calculateCharacterFrequencies("./src/test/resources/fixed-frequencies.txt");

            assertEquals(10, map.get('a'));
            assertEquals(4, map.get('b'));
            assertEquals(11, map.get('c'));
            assertEquals(9, map.get('p'));
            assertEquals(1, map.get('l'));
            assertEquals(false, map.containsKey('d'));
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    void compressorErrorsOnMissingTextFile() {
        try {
            Compressor.calculateCharacterFrequencies("./src/test/resources/nothere.txt");
        } catch (IOException e) {
            assert (true);
        }
    }

    @Test
    void compressorErrorsOnIncorrectFileFormat() {
        try {
            Compressor.calculateCharacterFrequencies("./src/test/resources/otherFormat.bin");
        } catch (IOException e) {
            assert (false);
        } catch (UnsupportedOperationException e) {
            assert (true);
        }
    }

    @Test
    void compressorBuildsMinQueue() {
        try {
            Map<Character, Integer> map = new HashMap<>();
            map.put('a', 10);
            map.put('b', 2);
            map.put('c', 1);
            map.put('d', 2000);
            PriorityQueue<HuffTree> queue = Compressor.buildPriorityQueue(map);
            int min = queue.poll().weight();
            assertEquals(1, min);
            min = queue.poll().weight();
            assertEquals(2, min);
            min = queue.poll().weight();
            assertEquals(10, min);
            min = queue.poll().weight();
            assertEquals(2000, min);
        } catch (UnsupportedOperationException e) {
            assert (false);
        }
    }

    // https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/Huffman.html#freqexamp
    @Test
    void compressorBuildsBinaryTree() {
        try {
            Map<Character, Integer> map = new HashMap<>();
            map.put('C', 32);
            map.put('D', 41);
            map.put('E', 120);
            map.put('K', 7);
            map.put('L', 42);
            map.put('M', 24);
            map.put('U', 37);
            map.put('Z', 2);
            PriorityQueue<HuffTree> queue = Compressor.buildPriorityQueue(map);
            HuffTree tree = Compressor.buildBinaryTree(queue);
            HuffLeafNode e = new HuffLeafNode('E', 120);
            HuffLeafNode u = new HuffLeafNode('U', 37);
            HuffLeafNode d = new HuffLeafNode('D', 41);
            HuffLeafNode l = new HuffLeafNode('L', 42);
            HuffLeafNode c = new HuffLeafNode('C', 32);
            HuffLeafNode z = new HuffLeafNode('Z', 2);
            HuffLeafNode k = new HuffLeafNode('K', 7);
            HuffLeafNode m = new HuffLeafNode('M', 24);
            HuffInternalNode i1 = new HuffInternalNode(z, k, 9);
            HuffInternalNode i2 = new HuffInternalNode(i1, m, 33);
            HuffInternalNode i3 = new HuffInternalNode(c, i2, 65);
            HuffInternalNode i4 = new HuffInternalNode(l, i3, 107);
            HuffInternalNode i5 = new HuffInternalNode(u, d, 78);
            HuffInternalNode i6 = new HuffInternalNode(i5, i4, 185);
            HuffInternalNode i7 = new HuffInternalNode(e, i6, 305);
            HuffTree expectedTree = new HuffTree(i7);
            assert (expectedTree.equals(tree));
        } catch (UnsupportedOperationException e) {
            assert (false);
        }
    }

    // https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/Huffman.html#freqexamp
    @Test
    void compressorBuildsPrefixTable() {
        try {
            HuffLeafNode e = new HuffLeafNode('E', 120);
            HuffLeafNode u = new HuffLeafNode('U', 37);
            HuffLeafNode d = new HuffLeafNode('D', 41);
            HuffLeafNode l = new HuffLeafNode('L', 42);
            HuffLeafNode c = new HuffLeafNode('C', 32);
            HuffLeafNode z = new HuffLeafNode('Z', 2);
            HuffLeafNode k = new HuffLeafNode('K', 7);
            HuffLeafNode m = new HuffLeafNode('M', 24);
            HuffInternalNode i1 = new HuffInternalNode(z, k, 9);
            HuffInternalNode i2 = new HuffInternalNode(i1, m, 33);
            HuffInternalNode i3 = new HuffInternalNode(c, i2, 65);
            HuffInternalNode i4 = new HuffInternalNode(l, i3, 107);
            HuffInternalNode i5 = new HuffInternalNode(u, d, 78);
            HuffInternalNode i6 = new HuffInternalNode(i5, i4, 185);
            HuffInternalNode i7 = new HuffInternalNode(e, i6, 305);
            HuffTree tree = new HuffTree(i7);

            HashMap<Character, String> prefixTable = Compressor.buildPrefixCodeTable(tree);
            HashMap<Character, String> expectedTable = new HashMap<>();
            expectedTable.put('E', "0");
            expectedTable.put('U', "100");
            expectedTable.put('D', "101");
            expectedTable.put('L', "110");
            expectedTable.put('C', "1110");
            expectedTable.put('Z', "111100");
            expectedTable.put('K', "111101");
            expectedTable.put('M', "11111");
            assert (expectedTable.equals(prefixTable));
        } catch (UnsupportedOperationException e) {
            assert (false);
        }
    }

    @Test
    void compressorInvertsMap() {
        HashMap<Character, String> mapToSwap = new HashMap<>();
        mapToSwap.put('a', "A string");
        mapToSwap.put('b', "B string");

        Map<String, Character> swappedMap = Compressor.invertMap(mapToSwap);
        HashMap<String, Character> expectedMap = new HashMap<>();
        expectedMap.put("A string", 'a');
        expectedMap.put("B string", 'b');

        assertEquals(expectedMap, swappedMap);
    }

    @Test
    void CompressorWritesPrefixTableToFile() {
        HashMap<Character, String> charTable = new HashMap<>();
        charTable.put('E', "0");
        charTable.put('U', "100");
        charTable.put('D', "101");
        charTable.put('L', "110");
        charTable.put('C', "1110");
        charTable.put('Z', "111100");
        charTable.put('K', "111101");
        charTable.put('M', "11111");
        Map<String, Character> expectedMap = Compressor.invertMap(charTable);
        try {
            File file = new File("src/test/resources/example");
            file.delete();
            Compressor.writeHeaderToFile(charTable, file);
            try (Scanner s = new Scanner(file)) {
                String openingDelim = s.nextLine();
                assertEquals(Compressor.HEADER_DELIMITER.trim(), openingDelim);
                String mapString = s.nextLine();
                assertEquals(expectedMap.toString(), mapString);
                String closingDelim = s.nextLine();
                assertEquals(Compressor.HEADER_DELIMITER.trim(), closingDelim);
            }
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    void CompressorWritesContentToFile() {
        try {
            String inFile = "src/test/resources/fullExample.txt";
            String outFile = "src/test/resources/fullOut";
            File outFilef = new File(outFile);
            outFilef.delete();
            Compressor.encode(inFile, outFile);
            try (Scanner s = new Scanner(outFilef)) {
                s.nextLine();
                s.nextLine();
                s.nextLine();
                String actualCompression = s.nextLine();
                assertEquals("000000011010010001111111111111101101010", actualCompression);
            }
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    void CompressorDecompressesFile() {
        try {
            String outFile = "src/test/resources/outExample.txt";
            String inFile = "src/test/resources/fullOut";
            File outFilef = new File(outFile);
            outFilef.delete();
            Compressor.decode(inFile, outFile);
            try (Scanner s = new Scanner(outFilef)) {
                String actualString = s.nextLine();
                String expectedString = "aaabccdeeeeeffg";
                assertEquals(expectedString, actualString);
            }
        } catch (IOException e) {
            assert (false);
        }
    }
}