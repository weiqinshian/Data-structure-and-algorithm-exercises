package cn.xiewei.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import cn.xiewei.tree.print.TreePrinter;

/**
 * ��������ֵ&����
 * 
 * @author XW
 * @create_date 2019��8��25��
 */
public class BTreeTraversal {


    /**
     * ���������������ȣ���ʽ������һ��������
     *
     * @param inputList
     * @return
     */
    public  TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        // �õݹ����ʽ����������ʵ�����������˳����һ����
        if (data != null) {
            node = new TreeNode(data);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * ��α�����������ȷ�ʽ��������һ��������
     * ��������ʱ�򣬹�����ȣ��������������һ��
     * @param inputList
     * @return
     */
    public static void createBinaryTreeByBreadth(Integer[] inputList, List<TreeNode> nodelist) {

        /*1.�������������нڵ㡿�������е�ÿ��ֵ��������һ��TreeNode�ڵ���󣬴洢��nodelist������*/
        for (int nodeindex = 0; nodeindex < inputList.length; nodeindex++) {
            TreeNode treeNode = new TreeNode(inputList[nodeindex]);
            nodelist.add(treeNode);
        }
        /*2.�����ĸ��ڵ�֮�佨�����ù�ϵ����Ϊnodelist�����У���TreeNode�ڵ��е������ӽڵ㹹�������ù�ϵ*/
        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
           //�����и��ڵ��趨�ӽڵ�  
            //���Ϊn�Ľڵ��������ӽڵ���Ϊ2*n ���ӽڵ���Ϊ2*n+1 ������Ϊlist��0��ʼ��ţ����Ի�Ҫ+1  
            //�����������1-9����
            //���︸�ڵ���1��2,3��,2��4,5��,3��6,7��,4��8,9�� �������һ�����ڵ��ǽڵ�4���п���û�����ӽڵ���Ҫ��������  
            //���������������1-8�� ���ܲ��Գ�������⡣
            nodelist.get(index).setLeft(nodelist.get(index * 2 + 1));
            nodelist.get(index).setRight(nodelist.get(index * 2 + 2));
        }
        int index = nodelist.size() / 2 - 1;
        nodelist.get(index).setLeft(nodelist.get(index * 2 + 1));
        if (nodelist.size() % 2 == 1) {
            nodelist.get(index).setRight(nodelist.get(index * 2 + 2));
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

        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
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
                System.out.print(node.value);
                // ��¼�·��ʹ��Ľڵ�
                stack.push(node);
                node = node.left;
            }
            // ����߶�������
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
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

        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
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
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.value);
                node = node.right;
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
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
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
                    System.out.println("��node�ŵ�push:" + node.value);
                    stack.push(node);
                }
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println("��node Pop:" + node.value);
                if (lastNode == node.right) {
                    System.out.print("node��ڵ�Ϊ��:" + node.value);
                    lastNode = node;
                } else {
                    node = node.right;
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
            System.out.print(node.value + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);

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
        int level=0;
        while (!queue.isEmpty()) {

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.value + " ");
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println("��"+level+"�����");
            level++;
            n = queue.size();
        }

    }

    public static void main(String[] args) {
        TreeNode t=null;
        /*��ʼ����ʽ1���������ȷ���ʼ�������ֳ�ʼ�����ķ�ʽ��������⣩*/
        List<TreeNode> nodelist = new LinkedList<>();  
        //   createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6,null, null, null, 7, 8 },nodelist) ;
                  createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13 },nodelist) ;
        t = nodelist.get(0);        
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
        
        //�Ӹ���ʼ��ӡ

//        BTreePrinter bTreePrinter = new BTreePrinter();
//       bTreePrinter.printNode(t);
        
        
        System.out.println();
        System.out.println("��ӡ����������ʽ1��");

        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> ""+n.getValue(), n -> n.getLeft(), n -> n.getRight());
        
        printer.setHspace(3);
        printer.setSquareBranches(true);
        printer.printTree(t);
        System.out.println();
        
        System.out.println("��ӡ����������ʽ2��");
        printer.setSquareBranches(false);
        printer.printTree(t);
    }
    


}
