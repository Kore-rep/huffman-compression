package huffman.compression.HuffTree;

import huffman.compression.interfaces.HuffBaseNode;

public class HuffInternalNode implements HuffBaseNode {
    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    public HuffInternalNode(HuffBaseNode l,
            HuffBaseNode r, int wt) {
        left = l;
        right = r;
        weight = wt;
    }

    HuffBaseNode left() {
        return left;
    }

    HuffBaseNode right() {
        return right;
    }

    public int weight() {
        return weight;
    }

    public boolean isLeaf() {
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + weight;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HuffInternalNode other = (HuffInternalNode) obj;
        if (weight != other.weight)
            return false;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        return true;
    }
}
