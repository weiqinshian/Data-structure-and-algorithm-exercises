package cn.xiewei.stack;

public class Cell {
    int x = 0; // 单元所在行
    int y = 0; // 单元所在列
    boolean visited = false; // 是否访问过
    char c = ' '; // 是墙('1')、可通路('0')或起点到终点的路径('*')
    char t = ' '; // 是用来测试，记录第一步已读入栈的元素

    public Cell(int x, int y, char c, boolean visited) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.visited = visited;
    }
}
