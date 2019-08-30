package cn.xiewei.tree.print;

/**
 * ����������
 * 
 * @author zhuguohui
 *
 */
public class BST<K extends Comparable<K>, V> {
    private int count;
    private Node<K, V> root;
    private int maxLevel;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(K k, V v) {
        Node<K, V> node = new Node<>(k, v);
        if (insertToNode(root, node, 0)) {
            // ����ǲ�����count+1
            count++;
        }
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 
     * @param parint
     * @param node
     * @return �����������true�����ֻ�Ǹ��·���false
     */
    private boolean insertToNode(Node<K, V> parent, Node<K, V> node, int level) {
        if (root == null) {
            root = node;
            maxLevel = 1;
            return true;
        }
        if (parent.k.compareTo(node.k) == 0) {
            // key��ͬ�����
            parent.v = node.v;
            return false;
        } else if (parent.k.compareTo(node.k) < 0) {
            // ���node��parent������뵽������
            if (parent.right == null) {
                parent.right = node;
                if (level + 1 > maxLevel) {
                    maxLevel = level + 1;
                }
                return true;
            }
            return insertToNode(parent.right, node, level + 1);
        } else {
            // ���node��parentС�������������
            if (parent.left == null) {
                parent.left = node;
                if (level + 1 > maxLevel) {
                    maxLevel = level + 1;
                }
                return true;
            }
            return insertToNode(parent.left, node, level + 1);
        }

    }

    private static class Node<K extends Comparable<K>, V> implements TreeNodeInterface {
        K k;
        V v;
        Node left, right;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "[" + k + "]";
        }

        @Override
        public String getPrintInfo() {

            return toString();
        }

        @Override
        public TreeNodeInterface getLeftChild() {
            // TODO Auto-generated method stub
            return left;
        }

        @Override
        public TreeNodeInterface getRightChild() {
            // TODO Auto-generated method stub
            return right;
        }

    }
    




 

}
