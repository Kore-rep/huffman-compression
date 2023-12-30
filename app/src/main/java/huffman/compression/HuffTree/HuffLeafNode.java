package huffman.compression.HuffTree;

import huffman.compression.interfaces.HuffBaseNode;

public class HuffLeafNode implements HuffBaseNode {
    private char element;
    private int weight;

    public HuffLeafNode(char el, int wt) {
        element = el;
        weight = wt;
    }

    public char value() {
        return element;
    }

    public int weight() {
        return weight;
    }

    public boolean isLeaf() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HuffLeafNode other = (HuffLeafNode) obj;
        if (element != other.element)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

}
