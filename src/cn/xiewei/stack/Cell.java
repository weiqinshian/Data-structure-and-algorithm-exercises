package cn.xiewei.stack;

public class Cell {
    int x = 0; // ��Ԫ������
    int y = 0; // ��Ԫ������
    boolean visited = false; // �Ƿ���ʹ�
    char c = ' '; // ��ǽ('1')����ͨ·('0')����㵽�յ��·��('*')
    char t = ' '; // ���������ԣ���¼��һ���Ѷ���ջ��Ԫ��

    public Cell(int x, int y, char c, boolean visited) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.visited = visited;
    }
}
