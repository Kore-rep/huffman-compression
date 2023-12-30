package huffman.compression.interfaces;

public interface HuffBaseNode {

    boolean isLeaf();

    int weight();

    @Override
    boolean equals(Object obj);
}