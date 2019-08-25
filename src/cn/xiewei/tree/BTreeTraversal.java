package cn.xiewei.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * ����������
 * 
 * @author XW
 * @create_date 2019��8��25��
 */
public class BTreeTraversal {
    static class TreeNode {
        private Integer data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }
        
    }

    /**
     * ����һ��������
     *
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        // �õݹ����ʽ����������ʵ�����������˳����һ����
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * ����һ��������
     *
     * @param inputList
     * @return
     */
    public static void createBinaryTreeByBreadth(Integer[] inputList, List<TreeNode> nodelist) {

        for (int nodeindex = 0; nodeindex < inputList.length; nodeindex++) {
            TreeNode treeNode = new TreeNode(inputList[nodeindex]);
            nodelist.add(treeNode);
        }

        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
            nodelist.get(index).setLeftChild(nodelist.get(index * 2 + 1));
            nodelist.get(index).setRightChild(nodelist.get(index * 2 + 2));
        }
        int index = nodelist.size() / 2 - 1;
        nodelist.get(index).setLeftChild(nodelist.get(index * 2 + 1));
        if (nodelist.size() % 2 == 1) {
            nodelist.get(index).setRightChild(nodelist.get(index * 2 + 2));
        }
    }

    /**
     * �������
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {            
            return;
        }

        System.out.print(node.data + " ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    /**
     * ������� ʹ��ջʵ��
     */
    public static void preStackOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // ������
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.data);
                // ��¼�·��ʹ��Ľڵ�
                stack.push(node);
                node = node.leftChild;
            }
            // ����߶�������
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.rightChild;
            }
        }

    }

    /**
     * �������
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.leftChild);
        System.out.print(node.data + " ");
        inOrder(node.rightChild);
    }

    /**
     * �������ʹ��ջʵ��
     *
     * @param node
     */
    public static void inStackOrder(TreeNode node) {
        // �� �� ��
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.data);
                node = node.rightChild;
            }
        }

    }

    /**
     * �������
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.print(node.data + " ");
    }

    /**
     * todo ����ջ�������
     *
     * @param node
     */
    public static void postStackOrder(TreeNode node) {
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            // ������
            while (node != null) {
                if (node != lastNode) {
                    System.out.println("��node�ŵ�push:" + node.data);
                    stack.push(node);
                }
                node = node.leftChild;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println("��node Pop:" + node.data);
                if (lastNode == node.rightChild) {
                    System.out.print("node��ڵ�Ϊ��:" + node.data);
                    lastNode = node;
                } else {
                    node = node.rightChild;
                }
            }
        }
    }

    /**
     * ������ ��α������������
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.offer(node.rightChild);

            }
        }

    }

    /**
     * ������ ��α��� ֪���Լ������Ĳ���
     * <p>
     * ˼·����ȡʹ��һ����¼ÿ�������һ��ֵ���ж�ʲôʱ����ɱ����ġ�
     * �������Ⱦ�Ӧ����ѭ������Ѹ��ڵ������У�Ȼ���ô�ʱ���еĴ�С��ѭ��ȡ���������ӽڵ�ŵ���������
     * ѭ����֮��һ��Ҳ�ͽ����ˣ���ʱ�ò������ӽڵ�Ҳ������ˣ�������һ���ÿ�����
     *
     *
     * @param root
     */
    public static void levelOrderCount(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);// �����β������Ԫ��
        int n = queue.size();
        while (!queue.isEmpty()) {

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.data + " ");
                if (node.leftChild != null) {
                    queue.offer(node.leftChild);
                }

                if (node.rightChild != null) {
                    queue.offer(node.rightChild);
                }
            }
            System.out.println("һ�����");
            n = queue.size();
        }

    }

    public static void main(String[] args) {

        TreeNode t=null;
        /*��ʼ����ʽ1��*/
        LinkedList<Integer> linkedList = new LinkedList<Integer>(
                Arrays.asList(new Integer[] { 3, 2, 9, null, null, 10, null, null, 8, null, 4 }));
        // LinkedList<Integer> linkedList = new
        // LinkedList<Integer>(Arrays.asList(new Integer[]{1, 2, null, null,
        // 3}));

        // 1,2,3��1,2,null,3�����Ķ����� ��һ����һ��ֻ����ڵ����
        // 1, 2,null,null, 3 �����Ĳ��� ���ڵ���1����ڵ���2 �ҽڵ���3
        // ע�⣡�������ĺ����鲻һ��������Ļ� ���ڵ���1����ڵ���2 �ҽڵ���3 �淨��123 ������1, 2,null,null, 3
        t = BTreeTraversal.createBinaryTree(linkedList);
        /*��ʼ����ʽ2���������ȷ���ʼ�������ֳ�ʼ�����ķ�ʽ��������⣩*/

        List<TreeNode> nodelist = new LinkedList<>();  
//        createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },nodelist) ;
        createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6,null, null, null, 7, 8 },nodelist) ;
        t = nodelist.get(0);;
        
        BTreeTraversal.preOrder(t);
        System.out.println("�������");
        preStackOrder(t);
        System.out.println("ջ�������");
        BTreeTraversal.inOrder(t);
        System.out.println("�������");
        inStackOrder(t);
        System.out.println("ջ�������");
        BTreeTraversal.postOrder(t);
        System.out.println("�������");
        postStackOrder(t);
        levelOrder(t);
        System.out.println("��α���");
        levelOrderCount(t);
        System.out.println("��α���֪���Լ��Ĳ���");
        // todo ������ System.out.println("ջ�������");
    }

}
