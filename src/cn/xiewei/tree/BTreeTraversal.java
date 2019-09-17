package cn.xiewei.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import cn.xiewei.tree.print.TreePrinter;

/**
 * 二叉树赋值&遍历
 * 
 * @author XW
 * @create_date 2019年8月25日
 */
public class BTreeTraversal {


    /**
     * 先序遍历（深度优先）方式，构建一个二叉树
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
        // 用递归的形式构建树，其实和先序遍历的顺序是一样的
        if (data != null) {
            node = new TreeNode(data);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 层次遍历（广度优先方式），构建一个二叉树
     * 传参数的时候，广度优先，更容易让人理解一点
     * @param inputList
     * @return
     */
    public static void createBinaryTreeByBreadth(Integer[] inputList, List<TreeNode> nodelist) {

        /*1.【生成树的所有节点】：数组中的每个值，都构建一个TreeNode节点对象，存储到nodelist集合中*/
        for (int nodeindex = 0; nodeindex < inputList.length; nodeindex++) {
            TreeNode treeNode = new TreeNode(inputList[nodeindex]);
            nodelist.add(treeNode);
        }
        /*2.【树的各节点之间建立引用关系】：为nodelist集合中，各TreeNode节点中的左右子节点构建，引用关系*/
        for (int index = 0; index < nodelist.size() / 2 - 1; index++) {
           //给所有父节点设定子节点  
            //编号为n的节点他的左子节点编号为2*n 右子节点编号为2*n+1 但是因为list从0开始编号，所以还要+1  
            //如果，数组是1-9数字
            //这里父节点有1（2,3）,2（4,5）,3（6,7）,4（8,9） 但是最后一个父节点是节点4，有可能没有右子节点需要单独处理  
            //如果是输入数组是1-8， 就能测试出这个问题。
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
     * 先序遍历
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
     * 先序遍历 使用栈实现
     */
    public static void preStackOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 根左右
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value);
                // 记录下访问过的节点
                stack.push(node);
                node = node.left;
            }
            // 当左边都遍历完
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }

    }

    /**
     * 中序遍历
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
     * 中序遍历使用栈实现
     *
     * @param node
     */
    public static void inStackOrder(TreeNode node) {
        // 左 中 右
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
     * 后序遍历
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
     * todo 待定栈后序遍历
     *
     * @param node
     */
    public static void postStackOrder(TreeNode node) {
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            // 左右中
            while (node != null) {
                if (node != lastNode) {
                    System.out.println("将node放到push:" + node.value);
                    stack.push(node);
                }
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println("将node Pop:" + node.value);
                if (lastNode == node.right) {
                    System.out.print("node左节点为空:" + node.value);
                    lastNode = node;
                } else {
                    node = node.right;
                }
            }
        }
    }

    /**
     * 二叉树 层次遍历，广度优先
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
     * 二叉树 层次遍历 知道自己遍历的层数
     * <p>
     * 思路：采取使用一个记录每层个数的一个值来判断什么时候完成遍历的。
     * 我们首先就应该在循环外面把根节点放入队列，然后用此时队列的大小做循环取数，并将子节点放到队列里面
     * 循环完之后，一层也就结束了，此时该层所有子节点也都入队了，更新下一层的每层个数
     *
     *
     * @param root
     */
    public static void levelOrderCount(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);// 向队列尾部插入元素
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
            System.out.println("第"+level+"层结束");
            level++;
            n = queue.size();
        }

    }

    public static void main(String[] args) {
        TreeNode t=null;
        /*初始化方式1：广义优先法初始化（这种初始化树的方式更容易理解）*/
        List<TreeNode> nodelist = new LinkedList<>();  
        //   createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6,null, null, null, 7, 8 },nodelist) ;
                  createBinaryTreeByBreadth(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13 },nodelist) ;
        t = nodelist.get(0);        
        BTreeTraversal.preOrder(t);
        System.out.println("先序遍历");
        preStackOrder(t);
        System.out.println("栈先序遍历");
        BTreeTraversal.inOrder(t);
        System.out.println("中序遍历");
        inStackOrder(t);
        System.out.println("栈中序遍历");
        BTreeTraversal.postOrder(t);
        System.out.println("后序遍历");
        postStackOrder(t);
        levelOrder(t);
        System.out.println("层次遍历");
        levelOrderCount(t);
        System.out.println("层次遍历知道自己的层数");
        // todo 有问题 System.out.println("栈后序遍历");
        
        //从根开始打印

//        BTreePrinter bTreePrinter = new BTreePrinter();
//       bTreePrinter.printNode(t);
        
        
        System.out.println();
        System.out.println("打印二叉树，方式1：");

        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> ""+n.getValue(), n -> n.getLeft(), n -> n.getRight());
        
        printer.setHspace(3);
        printer.setSquareBranches(true);
        printer.printTree(t);
        System.out.println();
        
        System.out.println("打印二叉树，方式2：");
        printer.setSquareBranches(false);
        printer.printTree(t);
    }
    


}
