package cn.xiewei.tree.huffmanstring;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import cn.xiewei.tree.print3.TreePrinter;
/**
 * Huffman编码算法主要用到的数据结构是完全二叉树(full binary tree)和优先级队列。后者用的是java.util.PriorityQueue，前者自己实现(都为内部类)
 * 
 * @author XW
 * @create_date 2019年8月30日
 */
public class TestHuffman {

    public static void main(String[] args) {
        String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. 中华崛起";
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());//统计数据
        String encodedBinariStr = encode(oriStr, statistics);//编码
        String decodedStr = decode(encodedBinariStr, statistics);//解码
    
        System.out.println("Original sstring: " + oriStr);
        System.out.println("Huffman encoed length="+encodedBinariStr.length()+" ,binary string: " + encodedBinariStr);
        System.out.println("decoded string from binariy string: " + decodedStr);
    
        System.out.println("UTF-8 encoed length="+getStringOfByte(oriStr, Charset.forName("UTF-8")).length()+" ,binary string of UTF-8: "
                + getStringOfByte(oriStr, Charset.forName("UTF-8")));
        System.out.println("UTF-16 encoed length="+getStringOfByte(oriStr, Charset.forName("UTF-16")).length()+" ,binary string of UTF-16: "
                + getStringOfByte(oriStr, Charset.forName("UTF-16")));
        System.out.println("US-ASCII encoed length="+getStringOfByte(oriStr, Charset.forName("US-ASCII")).length()+" ,binary string of US-ASCII: "
                + getStringOfByte(oriStr, Charset.forName("US-ASCII")));
        System.out.println("GB2312 encoed length="+getStringOfByte(oriStr, Charset.forName("GB2312")).length()+" ,binary string of GB2312: "
                + getStringOfByte(oriStr, Charset.forName("GB2312")));
    }

    public static String getStringOfByte(String str, Charset charset) {
        if (str == null || str.equals("")) {
            return "";
        }
 
        byte[] byteArray = str.getBytes(charset);
        int size = byteArray.length;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            byte temp = byteArray[i];
            buffer.append(getStringOfByte(temp));
        }
 
        return buffer.toString();
    }
 
    public static String getStringOfByte(byte b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 7; i >= 0; i--) {
            byte temp = (byte) ((b >> i) & 0x1);
            buffer.append(String.valueOf(temp));
        }
 
        return buffer.toString();
    }
    
    /**
     * 统计数据
     * 既然要按频率来安排编码表，那么首先当然得获得频率的统计信息。
     * 我实现了一个方法处理这样的问题。如果已经有统计信息，那么转为Map<Character,Integer>即可。
     * 如果你得到的信息是百分比，乘以100或1000，或10000。总是可以转为整数。比如12.702%乘以1000为12702，Huffman编码只关心大小问题。统计方法实现如下：
     *   
     * @author XW
     * @create_date 2019年8月30日
     * @return Map<Character,Integer>
     */
    public static Map<Character, Integer> statistics(char[] charArray) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : charArray) {
            Character character = new Character(c);
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            } else {
                map.put(character, 1);
            }
        }
 
        return map;
    }

    /**
     * 编码
     *   
     * @author XW
     * @create_date 2019年8月31日
     * @return String
     */
    public static String encode(String originalStr,
            Map<Character, Integer> statistics) {
        if (originalStr == null || originalStr.equals("")) {
            return "";
        }
 
        char[] charArray = originalStr.toCharArray();
        List<Node> leafNodes = new ArrayList<Node>();
        buildTree(statistics, leafNodes);//构建二叉树
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);
 
        StringBuffer buffer = new StringBuffer();
        for (char c : charArray) {
            Character character = new Character(c);
            buffer.append(encodInfo.get(character));//循环拼接获取，“赫夫曼”编码之后生成的二进制序列
        }
 
        return buffer.toString();
    }
 

    /**
     * leafNodes:集合中存储的是所有叶子节点
     * 
     * 某个字符对应的编码为，从该字符所在的叶子节点向上搜索，
     * 如果该字符节点是父节点的左节点，编码字符之前加0，反之如果是右节点，加1，直到根节点。
     * 只要获取了字符和二进制码之间的mapping关系，编码就非常简单。代码如下:
     *   
     * @author XW
     * @create_date 2019年8月31日
     * @return Map<Character,String>
     */
    private static Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {
        Map<Character, String> codewords = new HashMap<Character, String>();
        for (Node leafNode : leafNodes) {
            Character character = new Character(leafNode.getChars().charAt(0));
            String codeword = "";
            Node currentNode = leafNode;
 
            do {
                if (currentNode.isLeftChild()) {
                    codeword = "0" + codeword;
                } else {
                    codeword = "1" + codeword;
                }
 
                currentNode = currentNode.parent;//第一次循环，获取叶子节点的父节点
            } while (currentNode.parent != null);
 
            codewords.put(character, codeword);//将字符和编码保存的集合中
        }
 
        return codewords;
    }

    /**
     * 解码
     * 因为Huffman编码算法能够保证任何的二进制码都不会是另外一个码的前缀，
     * 解码非常简单，依次取出二进制的每一位，从树根向下搜索，1向右，0向左，到了叶子节点(命中)，
     * 退回根节点继续重复以上动作。代码如下:
     *   
     * @author XW
     * @create_date 2019年8月30日
     * @return String
     */
    public static String decode(String binaryStr,
            Map<Character, Integer> statistics) {
        if (binaryStr == null || binaryStr.equals("")) {
            return "";
        }
 
        char[] binaryCharArray = binaryStr.toCharArray();
        LinkedList<Character> binaryList = new LinkedList<Character>();
        int size = binaryCharArray.length;
        for (int i = 0; i < size; i++) {
            binaryList.addLast(new Character(binaryCharArray[i]));
        }

        StringBuffer buffer = new StringBuffer();
 
        while (binaryList.size() > 0) {
            Node node = Tree.getRoot();//编码的时候构建了一次二叉树，这里直接拿来用就可以了
 
            do {
                Character c = binaryList.removeFirst();
                if (c.charValue() == '0') {
                    node = node.leftNode;
                } else {
                    node = node.rightNode;
                }
            } while (!node.isLeaf());//节点是叶子节点就退出循环
 
            buffer.append(node.chars);
        }
 
        return buffer.toString();
    }

    /**
     * 构建树
     * 构建树是Huffman编码算法的核心步骤。
     * 思想是把所有的字符挂到一颗完全二叉树的叶子节点，任何一个非页子节点的左节点出现频率不大于右节点。
     * 算法为把统计信息转为Node存放到一个优先级队列里面，每一次从队列里面弹出两个最小频率的节点，构建一个新的父Node(非叶子节点), 
     * 字符内容刚弹出来的两个节点字符内容之和，频率也是它们的和，最开始的弹出来的作为左子节点，后面一个作为右子节点，
     * 并且把刚构建的父节点放到队列里面。重复以上的动作N-1次，N为不同字符的个数(每一次队列里面个数减1)。
     * 结束以上步骤，队列里面剩一个节点，弹出作为树的根节点。代码如下:
     *   
     * @author XW
     * @create_date 2019年8月30日
     * @return Tree
     */
    private static Tree buildTree(Map<Character, Integer> statistics,
            List<Node> leafs) {
        Character[] keys = statistics.keySet().toArray(new Character[0]);
 
        /*1. 创建一个队列，存储，初始化的所有叶子节点，按照概率由小到大的顺序。*/
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        for (Character character : keys) {
            Node node = new Node();
            node.chars = character.toString();
            node.frequence = statistics.get(character);
            priorityQueue.add(node);
            leafs.add(node);
        }
 
        int size = priorityQueue.size();
        /*2. 每次从队列，取出两个节点，并创建一个新节点，新节点概率为取出的两个子节点概率之和。*/
        for (int i = 1; i <= size - 1; i++) {
            Node node1 = priorityQueue.poll();
            Node node2 = priorityQueue.poll();
 
            Node sumNode = new Node();//
            sumNode.chars = node1.chars + node2.chars;
            sumNode.frequence = node1.frequence + node2.frequence;
 
            sumNode.leftNode = node1;
            sumNode.rightNode = node2;
 
            node1.parent = sumNode;
            node2.parent = sumNode;
 
            priorityQueue.add(sumNode);
        }
 
        Tree tree = new Tree();
        tree.root = priorityQueue.poll();
        treePrinter(tree.root);
        return tree;
    }
    
    public static void treePrinter(Node t) {
        
        TreePrinter<Node> printer = new TreePrinter<>(n -> ""+n.getFrequence(), n -> n.getLeftNode(), n -> n.getRightNode());
        
        printer.setHspace(3);
        printer.setSquareBranches(true);
        printer.printTree(t);
        System.out.println();
        
        System.out.println("打印二叉树，方式2：");
        printer.setSquareBranches(false);
        printer.printTree(t);
        
    }

    

    



}


