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
 * Huffman�����㷨��Ҫ�õ������ݽṹ����ȫ������(full binary tree)�����ȼ����С������õ���java.util.PriorityQueue��ǰ���Լ�ʵ��(��Ϊ�ڲ���)
 * 
 * @author XW
 * @create_date 2019��8��30��
 */
public class TestHuffman {

    public static void main(String[] args) {
        String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. �л�����";
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());//ͳ������
        String encodedBinariStr = encode(oriStr, statistics);//����
        String decodedStr = decode(encodedBinariStr, statistics);//����
    
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
     * ͳ������
     * ��ȻҪ��Ƶ�������ű������ô���ȵ�Ȼ�û��Ƶ�ʵ�ͳ����Ϣ��
     * ��ʵ����һ�������������������⡣����Ѿ���ͳ����Ϣ����ôתΪMap<Character,Integer>���ɡ�
     * �����õ�����Ϣ�ǰٷֱȣ�����100��1000����10000�����ǿ���תΪ����������12.702%����1000Ϊ12702��Huffman����ֻ���Ĵ�С���⡣ͳ�Ʒ���ʵ�����£�
     *   
     * @author XW
     * @create_date 2019��8��30��
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
     * ����
     *   
     * @author XW
     * @create_date 2019��8��31��
     * @return String
     */
    public static String encode(String originalStr,
            Map<Character, Integer> statistics) {
        if (originalStr == null || originalStr.equals("")) {
            return "";
        }
 
        char[] charArray = originalStr.toCharArray();
        List<Node> leafNodes = new ArrayList<Node>();
        buildTree(statistics, leafNodes);//����������
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);
 
        StringBuffer buffer = new StringBuffer();
        for (char c : charArray) {
            Character character = new Character(c);
            buffer.append(encodInfo.get(character));//ѭ��ƴ�ӻ�ȡ�����շ���������֮�����ɵĶ���������
        }
 
        return buffer.toString();
    }
 

    /**
     * leafNodes:�����д洢��������Ҷ�ӽڵ�
     * 
     * ĳ���ַ���Ӧ�ı���Ϊ���Ӹ��ַ����ڵ�Ҷ�ӽڵ�����������
     * ������ַ��ڵ��Ǹ��ڵ����ڵ㣬�����ַ�֮ǰ��0����֮������ҽڵ㣬��1��ֱ�����ڵ㡣
     * ֻҪ��ȡ���ַ��Ͷ�������֮���mapping��ϵ������ͷǳ��򵥡���������:
     *   
     * @author XW
     * @create_date 2019��8��31��
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
 
                currentNode = currentNode.parent;//��һ��ѭ������ȡҶ�ӽڵ�ĸ��ڵ�
            } while (currentNode.parent != null);
 
            codewords.put(character, codeword);//���ַ��ͱ��뱣��ļ�����
        }
 
        return codewords;
    }

    /**
     * ����
     * ��ΪHuffman�����㷨�ܹ���֤�κεĶ������붼����������һ�����ǰ׺��
     * ����ǳ��򵥣�����ȡ�������Ƶ�ÿһλ������������������1���ң�0���󣬵���Ҷ�ӽڵ�(����)��
     * �˻ظ��ڵ�����ظ����϶�������������:
     *   
     * @author XW
     * @create_date 2019��8��30��
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
            Node node = Tree.getRoot();//�����ʱ�򹹽���һ�ζ�����������ֱ�������þͿ�����
 
            do {
                Character c = binaryList.removeFirst();
                if (c.charValue() == '0') {
                    node = node.leftNode;
                } else {
                    node = node.rightNode;
                }
            } while (!node.isLeaf());//�ڵ���Ҷ�ӽڵ���˳�ѭ��
 
            buffer.append(node.chars);
        }
 
        return buffer.toString();
    }

    /**
     * ������
     * ��������Huffman�����㷨�ĺ��Ĳ��衣
     * ˼���ǰ����е��ַ��ҵ�һ����ȫ��������Ҷ�ӽڵ㣬�κ�һ����ҳ�ӽڵ����ڵ����Ƶ�ʲ������ҽڵ㡣
     * �㷨Ϊ��ͳ����ϢתΪNode��ŵ�һ�����ȼ��������棬ÿһ�δӶ������浯��������СƵ�ʵĽڵ㣬����һ���µĸ�Node(��Ҷ�ӽڵ�), 
     * �ַ����ݸյ������������ڵ��ַ�����֮�ͣ�Ƶ��Ҳ�����ǵĺͣ��ʼ�ĵ���������Ϊ���ӽڵ㣬����һ����Ϊ���ӽڵ㣬
     * ���ҰѸչ����ĸ��ڵ�ŵ��������档�ظ����ϵĶ���N-1�Σ�NΪ��ͬ�ַ��ĸ���(ÿһ�ζ������������1)��
     * �������ϲ��裬��������ʣһ���ڵ㣬������Ϊ���ĸ��ڵ㡣��������:
     *   
     * @author XW
     * @create_date 2019��8��30��
     * @return Tree
     */
    private static Tree buildTree(Map<Character, Integer> statistics,
            List<Node> leafs) {
        Character[] keys = statistics.keySet().toArray(new Character[0]);
 
        /*1. ����һ�����У��洢����ʼ��������Ҷ�ӽڵ㣬���ո�����С�����˳��*/
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        for (Character character : keys) {
            Node node = new Node();
            node.chars = character.toString();
            node.frequence = statistics.get(character);
            priorityQueue.add(node);
            leafs.add(node);
        }
 
        int size = priorityQueue.size();
        /*2. ÿ�δӶ��У�ȡ�������ڵ㣬������һ���½ڵ㣬�½ڵ����Ϊȡ���������ӽڵ����֮�͡�*/
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
        
        System.out.println("��ӡ����������ʽ2��");
        printer.setSquareBranches(false);
        printer.printTree(t);
        
    }

    

    



}


