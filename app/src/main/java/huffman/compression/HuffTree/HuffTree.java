package huffman.compression.HuffTree;

import huffman.compression.interfaces.HuffBaseNode;

public class HuffTree implements Comparable<HuffTree> {
    private HuffBaseNode root;

    public HuffTree(char el, int wt) {
        root = new HuffLeafNode(el, wt);
    }

    public HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) {
        root = new HuffInternalNode(l, r, wt);
    }

    public HuffTree(HuffBaseNode n) {
        root = n;
    }

    public HuffBaseNode root() {
        return root;
    }

    public int weight() // Weight of tree is weight of root
    {
        return root.weight();
    }

    public int compareTo(HuffTree t) {
        HuffTree that = (HuffTree) t;
        if (root.weight() < that.weight()) {
            return -1;
        } else if (root.weight() == that.weight()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HuffTree other = (HuffTree) obj;
        if (root == null) {
            if (other.root != null)
                return false;
        } else if (!root.equals(other.root))
            return false;
        return true;
    }
}
