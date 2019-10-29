package cn.xiewei.graph.mstw;

public class PriorityQ {
    private final int SIZE = 20;
    private Edge[] edges;
    private int size;// 当前边数量
    public PriorityQ() {
        edges = new Edge[SIZE];
        size = 0;
    }
    public void insert(Edge item) {
        int j;
        for (j = 0; j < size; j++) {
            if (item.distance >= edges[j].distance)
                break;
        }
        for (int k = size - 1; k >= j; k--) {
            edges[k + 1] = edges[k];
        }
        edges[j] = item;
        size++;
    }
    public Edge removeMin() {
        return edges[--size];
    }
    public void removeM(int n) {
        for (int j = n; j < size - 1; j++) {
            edges[j] = edges[j + 1];
        }
        size--;
    }
    public Edge peekMin() {
        return edges[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Edge peekN(int n) {
        return edges[n];
    }

    public int find(int findDex) {
        for (int j = 0; j < size; j++) {
            if (edges[j].destVert == findDex)
                return j;
        }
        return -1;
    }

}
