package cn.xiewei.tree.huffmanstring;
public   class Node implements Comparable<Node> {
    public String chars = "";
    public int frequence = 0;
    public Node parent;
    public Node leftNode;
    public Node rightNode;
    @Override
    public int compareTo(Node n) {
        return frequence - n.frequence;
    }
    public boolean isLeaf() {
        return chars.length() == 1;
    }
    public boolean isRoot() {
        return parent == null;
    }
    public boolean isLeftChild() {
        return parent != null && this == parent.leftNode;
    }
    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
    public String getChars() {
        return chars;
    }
    public void setChars(String chars) {
        this.chars = chars;
    }

    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
    public Node getRightNode() {
        return rightNode;
    }
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}