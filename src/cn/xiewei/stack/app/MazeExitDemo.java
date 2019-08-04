package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

/**
 * �Թ�
 * 
 * @author XW
 * @create_date 2019��7��31��
 */
public class MazeExitDemo {
    public static void main(String[] args) {
       
        char[][] maze=  
            {
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}, 
            {'1', '0', '0', '1', '1', '1', '0', '0', '1', '1'}, 
            {'1', '0', '0', '1', '1', '0', '0', '1', '0', '1'}, 
            {'1', '0', '0', '0', '0', '0', '0', '1', '0', '1'}, 
            {'1', '0', '0', '0', '0', '1', '1', '0', '0', '1'}, 
            {'1', '0', '0', '1', '1', '1', '0', '0', '0', '1'}, 
            {'1', '0', '0', '0', '0', '1', '0', '1', '0', '1'}, 
            {'1', '0', '1', '1', '0', '0', '0', '1', '0', '1'}, 
            {'1', '1', '0', '0', '0', '0', '1', '0', '0', '1'}, 
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};
        mazeExit(maze, 8, 8, 1, 7);
    }
    
    public static void mazeExit(char[][] maze, int sx, int sy, int ex, int ey) {
        Cell[][] cells = createMaze(maze); // �������Թ�
        System.out.println("===========��ӡ�Թ�=============");
        printMaze(cells); // ��ӡ�Թ�
        Stack s = new StackSLinked(); // �����ջ
        Cell startCell = cells[sx][sy]; // ���
        Cell endCell = cells[ex][ey]; // �յ�
        s.push(startCell); // �����ջ,��ջ�д�����ǣ�ÿһ���Թ��ڵ����
        startCell.visited = true; // �������ѱ�����
        startCell.t = '-';
        while (!s.isEmpty()) {
            Cell current = (Cell) s.peek();
            if (current == endCell) { // ·���ҵ�
                System.out.println("===========��ӡ��һ�������Թ����洢��ջ�е����нڵ�============");
                printMazeTest(cells);

                //���ϱ�����ʽ 
//                while (!s.isEmpty()) { 
//                    Cell tem = (Cell)s.pop();
//                    System.out.println(s.getSize()+ " ("+tem.x+","+tem.y+") -->"); 
//                } 
                while (!s.isEmpty()) {
                    Cell cell = (Cell) s.pop();// ��·���ؽ�·���ϵĵ�Ԫ��Ϊ*
                    cell.c = '*';
                    // ��ջ���� cell ���ڵĵ�Ԫ����·������ɲ��֣�����֮�⣬
                    // ��ջ�л��м�¼��������δ��������̽���ĵ�Ԫ��
                    // ��Щ��Ԫֱ�ӳ�ջ
                    if (cell.x==5&&cell.y==8) {
                        System.out.println();
                    }
                    while (!s.isEmpty() && !isAdjoinCell((Cell) s.peek(), cell))
                        s.pop();
                }
                System.out.println("====�ҵ�����㵽�յ��·��===== ");
                printMaze(cells);
                return;
            } else { // �����ǰλ�ò����յ�
                int x = current.x;
                int y = current.y;
                int count = 0;
                if (isValidWayCell(cells[x + 1][y])) { // ����
                    s.push(cells[x + 1][y]);
                    cells[x + 1][y].visited = true;
                    cells[x + 1][y].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x][y + 1])) { // ����
                    s.push(cells[x][y + 1]);
                    cells[x][y + 1].visited = true;
                    cells[x][y + 1].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x - 1][y])) { // ����
                    s.push(cells[x - 1][y]);
                    cells[x - 1][y].visited = true;
                    cells[x - 1][y].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x][y - 1])) { // ����
                    s.push(cells[x][y - 1]);
                    cells[x][y - 1].visited = true;
                    cells[x][y - 1].t = '-';
                    count++;
                }
                if (count == 0){
                    s.pop();// ��������㣬��ջ
                    cells[x][y].t = ' ';//��ջ�ĵ�Ҳ��Ҫ����
                }
                   
            } // end of if
        } // end of while

        System.out.println("û�д���㵽�յ��·���� ");
    }

    private static void printMaze(Cell[][] cells) {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++)
                System.out.print(cells[x][y].c+" ");
            System.out.println();
        }
    }
    private static void printMazeTest(Cell[][] cells) {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++)
                System.out.print(cells[x][y].t+" ");
            System.out.println();
        }
    }

    private static boolean isAdjoinCell(Cell cell1, Cell cell2) {
        if (cell1.x == cell2.x && Math.abs(cell1.y - cell2.y) < 2)
            return true;
        if (cell1.y == cell2.y && Math.abs(cell1.x - cell2.x) < 2)
            return true;
        return false;
    }

    private static boolean isValidWayCell(Cell cell) {
        return cell.c == '0' && !cell.visited;
    }

    private static Cell[][] createMaze(char[][] maze) {
        Cell[][] cells = new Cell[maze.length][];
        for (int x = 0; x < maze.length; x++) {
            char[] row = maze[x];
            cells[x] = new Cell[row.length];
            for (int y = 0; y < row.length; y++)
                cells[x][y] = new Cell(x, y, maze[x][y], false);
        }
        return cells;
    }
}
