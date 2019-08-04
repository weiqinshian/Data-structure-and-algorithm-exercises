package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

/**
 * 迷宫
 * 
 * @author XW
 * @create_date 2019年7月31日
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
        Cell[][] cells = createMaze(maze); // 创建化迷宫
        System.out.println("===========打印迷宫=============");
        printMaze(cells); // 打印迷宫
        Stack s = new StackSLinked(); // 构造堆栈
        Cell startCell = cells[sx][sy]; // 起点
        Cell endCell = cells[ex][ey]; // 终点
        s.push(startCell); // 起点入栈,堆栈中存入的是，每一个迷宫节点对象
        startCell.visited = true; // 标记起点已被访问
        startCell.t = '-';
        while (!s.isEmpty()) {
            Cell current = (Cell) s.peek();
            if (current == endCell) { // 路径找到
                System.out.println("===========打印第一步，尝试过，存储在栈中的所有节点============");
                printMazeTest(cells);

                //集合遍历方式 
//                while (!s.isEmpty()) { 
//                    Cell tem = (Cell)s.pop();
//                    System.out.println(s.getSize()+ " ("+tem.x+","+tem.y+") -->"); 
//                } 
                while (!s.isEmpty()) {
                    Cell cell = (Cell) s.pop();// 沿路返回将路径上的单元设为*
                    cell.c = '*';
                    // 堆栈中与 cell 相邻的单元才是路径的组成部分，除此之外，
                    // 堆栈中还有记录下来但是未继续向下探索的单元，
                    // 这些单元直接出栈
                    if (cell.x==5&&cell.y==8) {
                        System.out.println();
                    }
                    while (!s.isEmpty() && !isAdjoinCell((Cell) s.peek(), cell))
                        s.pop();
                }
                System.out.println("====找到从起点到终点的路径===== ");
                printMaze(cells);
                return;
            } else { // 如果当前位置不是终点
                int x = current.x;
                int y = current.y;
                int count = 0;
                if (isValidWayCell(cells[x + 1][y])) { // 向下
                    s.push(cells[x + 1][y]);
                    cells[x + 1][y].visited = true;
                    cells[x + 1][y].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x][y + 1])) { // 向右
                    s.push(cells[x][y + 1]);
                    cells[x][y + 1].visited = true;
                    cells[x][y + 1].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x - 1][y])) { // 向上
                    s.push(cells[x - 1][y]);
                    cells[x - 1][y].visited = true;
                    cells[x - 1][y].t = '-';
                    count++;
                }
                if (isValidWayCell(cells[x][y - 1])) { // 向左
                    s.push(cells[x][y - 1]);
                    cells[x][y - 1].visited = true;
                    cells[x][y - 1].t = '-';
                    count++;
                }
                if (count == 0){
                    s.pop();// 如果是死点，出栈
                    cells[x][y].t = ' ';//出栈的点也不要记了
                }
                   
            } // end of if
        } // end of while

        System.out.println("没有从起点到终点的路径。 ");
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
