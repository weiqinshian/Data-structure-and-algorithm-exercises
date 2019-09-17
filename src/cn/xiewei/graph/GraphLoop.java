package cn.xiewei.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphLoop {
    private Map<String, List<String>> graph = new HashMap<String, List<String>>();

    /**
     * ��ʼ��ͼ���ݣ�ʹ���ھӱ�����ʾͼ���ݡ�
     */
    public void initGraphData() {
//        ͼ�ṹ����
//          1
//        /   \
//       2     3
//      / \   / \
//     4  5  6  7
//      \ | / \ /
//        8    9
        graph.put("1", Arrays.asList("2", "3"));
        graph.put("2", Arrays.asList("1", "4", "5"));
        graph.put("3", Arrays.asList("1", "6", "7"));
        graph.put("4", Arrays.asList("2", "8"));
        graph.put("5", Arrays.asList("2", "8"));
        graph.put("6", Arrays.asList("3", "8", "9"));
        graph.put("7", Arrays.asList("3", "9"));
        graph.put("8", Arrays.asList("4", "5", "6"));
        graph.put("9", Arrays.asList("6", "7"));
    }

    /**
     * �����������(BFS, Breadth First Search)
     * BFSʹ�ö���(queue)��ʵʩ�㷨����
     */
    private Queue<String> queue = new LinkedList<String>();
    private Map<String, Boolean> status = new HashMap<String, Boolean>();

    /**
     * ��ʼ��
     *
     * @param startPoint
     */
    public void BFSSearch(String startPoint) {
        //1.����ʼ�����queue��
        queue.add(startPoint);
        status.put(startPoint, false);
        bfsLoop();
    }

    private void bfsLoop() {
        //  1) ��queue��ȡ������ͷ�ĵ㣻����״̬Ϊ�Ѿ�������
        String currentQueueHeader = queue.poll(); //����
        status.put(currentQueueHeader, true);
        System.out.println(currentQueueHeader);
        //  2) �ҳ���˵��ڽӵ�����δ�����ĵ㣬���б�ǣ�Ȼ��ȫ������queue�С�
        List<String> neighborPoints = graph.get(currentQueueHeader);
        for (String poinit : neighborPoints) {
            if (!status.getOrDefault(poinit, false)) { //δ������
                if (queue.contains(poinit)) continue;
                queue.add(poinit);
                status.put(poinit, false);
            }
        }
        if (!queue.isEmpty()) {  //������в�Ϊ�ռ�������
            bfsLoop();
        }
    }


    /**
     * �����������(DFS, Depth First Search)
     * DFSʹ�ö���(queue)��ʵʩ�㷨����
     * stack���к���ȳ�LIFO(Last Input First Output)�����ԣ�DFS�Ĳ����������£�
     */
//     1������ʼ�����stack��
//     2���ظ�����3���裬ֱ��stackΪ��Ϊֹ��
//    ��stack�з���ջ���ĵ㣻
//    �ҳ���˵��ڽӵ�����δ�����ĵ㣬���б�ǣ�Ȼ��ȫ������stack�У�
//    ����˵�û����δ�������ڽӵ㣬�򽫴˵��stack�е�����

    private Stack<String> stack = new Stack<String>();
    public void DFSSearch(String startPoint) {
        stack.push(startPoint);
        status.put(startPoint, true);
        dfsLoop();
    }

    private void dfsLoop() {
        if(stack.empty()){
            return;
        }
        //�鿴ջ��Ԫ�أ���������ջ
        String stackTopPoint = stack.peek();
        //  2) �ҳ���˵��ڽӵ�����δ�����ĵ㣬���б�ǣ�Ȼ��ȫ������queue�С�
        List<String> neighborPoints = graph.get(stackTopPoint);
        for (String point : neighborPoints) {
            if (!status.getOrDefault(point, false)) { //δ������
                stack.push(point);
                status.put(point, true);
                dfsLoop();
            }
        }
        String popPoint =  stack.pop();
        System.out.print(" "+popPoint);
    }

    public static void main(String[] args) {
        GraphLoop test = new GraphLoop();
        test.initGraphData();
//        test.BFSSearch("1");
        test.DFSSearch("1");
    }
}
