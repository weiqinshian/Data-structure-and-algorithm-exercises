package cn.xiewei.tree.print;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreePrintUtil {
    public static void pirnt(TreeNodeInterface root) {
        // 找到左边的最大偏移量
        int maxLeftOffset = findMaxOffset(root, 0, true);//一根节点为中心，画一条竖线，最左边的节点的偏移量
        int maxRightOffset = findMaxOffset(root, 0, false);
        int offset = Math.max(maxLeftOffset, maxRightOffset);
        // 计算最大偏移量
        Map<Integer, PrintLine> lineMap = new HashMap();
        //核心代码，递归生成
        calculateLines(root, offset, lineMap, 0, true);
        Iterator<Integer> lineNumbers = lineMap.keySet().iterator();
        int maxLine = 0;
        while (lineNumbers.hasNext()) {
            int lineNumber = lineNumbers.next();
            if (lineNumber > maxLine) {
                maxLine = lineNumber;
            }
        }
        for (int i = 0; i <= maxLine; i++) {
            PrintLine line = lineMap.get(i);
            if (line != null) {
                System.out.println(line.getLineString());
            }
        }

    }

    private static void calculateLines(TreeNodeInterface parent, int offset, Map<Integer, PrintLine> lineMap, int level,
            boolean right) {
        if (parent == null) {
            return;
        }
        int nameoffset = parent.toString().length() / 2;
        PrintLine line = lineMap.get(level);//第level层打印的，字符串封装为一个对象。
        if (line == null) {
            line = new PrintLine();
            lineMap.put(level, line);
        }
        line.putString(right ? offset : (offset - nameoffset), parent.toString());//设置，第level层，节点位置，要打印的和字符串
        
        System.out.println("level="+level+ " ,PrintLine  : "+line.toString() +" ,right="+right +" ,parent.toString()="+parent.toString()+" ,offset="+offset+" ,nameoffset="+nameoffset);
        // 判断有没有下一级
        if (parent.getLeftChild() == null && parent.getRightChild() == null) {
            return;
        }
        // 如果有，添加分割线即/\
        PrintLine separateLine = lineMap.get(level + 1);
        if (separateLine == null) {
            separateLine = new PrintLine();
            lineMap.put(level + 1, separateLine);
        }
        if (parent.getLeftChild() != null) {
            separateLine.putString(offset - 1, "/");//设置，第level+1层，要打印的树节点线
            System.out.println("level="+(level+1)+ " ,PrintLine left : "+separateLine.toString() );
            calculateLines(parent.getLeftChild(), offset - nameoffset - 1, lineMap, level + 2, false);
        }
        if (parent.getRightChild() != null) {
            separateLine.putString(offset + nameoffset + 1, "\\");
            System.out.println("level="+(level+1)+ " ,PrintLine right : "+separateLine.toString() );
            calculateLines(parent.getRightChild(), offset + nameoffset + 1, lineMap, level + 2, true);
        }

    }
    /*
     * 广度优先法
     */
    private static void calculateLinesGd(TreeNodeInterface parent, int offset, Map<Integer, PrintLine> lineMap, int level,
            boolean right) {
        if (parent == null) {
            return;
        }
        int nameoffset = parent.toString().length() / 2;
        PrintLine line = lineMap.get(level);//第level层打印的，字符串封装为一个对象。
        if (line == null) {
            line = new PrintLine();
            lineMap.put(level, line);
        }
        line.putString(right ? offset : (offset - nameoffset), parent.toString());//设置，第level层，节点位置，要打印的和字符串
        
        System.out.println("level="+level+ " ,PrintLine  : "+line.toString() +" ,right="+right +" ,parent.toString()="+parent.toString()+" ,offset="+offset+" ,nameoffset="+nameoffset);
        // 判断有没有下一级
        if (parent.getLeftChild() == null && parent.getRightChild() == null) {
            return;
        }
        // 如果有，添加分割线即/\
        PrintLine separateLine = lineMap.get(level + 1);
        if (separateLine == null) {
            separateLine = new PrintLine();
            lineMap.put(level + 1, separateLine);
        }
        if (parent.getLeftChild() != null) {
            separateLine.putString(offset - 1, "/");//设置，第level+1层，要打印的树节点线
            System.out.println("level="+(level+1)+ " ,PrintLine left : "+separateLine.toString() );
            calculateLines(parent.getLeftChild(), offset - nameoffset - 1, lineMap, level + 2, false);
        }
        if (parent.getRightChild() != null) {
            separateLine.putString(offset + nameoffset + 1, "\\");
            System.out.println("level="+(level+1)+ " ,PrintLine right : "+separateLine.toString() );
            calculateLines(parent.getRightChild(), offset + nameoffset + 1, lineMap, level + 2, true);
        }
        
        
        Queue<TreeNodeInterface> queue=new LinkedList<>();
        
    }

    /**
     * 需要打印的某一行
     * 
     * @author zhuguohui
     *
     */
    private static class PrintLine {
        /**
         * 记录了offset和String的map
         */
        Map<Integer, String> printItemsMap = new HashMap<>();
        int maxOffset = 0;

        public String toString() {
            return "maxOffset="+maxOffset+" ,printItemsMap="+printItemsMap.toString();
            
        }
        
        public void putString(int offset, String info) {
            printItemsMap.put(offset, info);
            if (offset > maxOffset) {
                maxOffset = offset;
            }
        }

        public String getLineString() {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i <= maxOffset; i++) {
                String info = printItemsMap.get(i);
                if (info == null) {
                    buffer.append(" ");
                } else {
                    buffer.append(info);
                    i += info.length();
                }
            }
            return buffer.toString();
        }
    }

    private static int findMaxOffset(TreeNodeInterface parent, int offset, boolean findLeft) {
        if (parent != null) {
            offset += parent.toString().length();
        }
        if (findLeft && parent.getLeftChild() != null) {
            offset += 1;
            return findMaxOffset(parent.getLeftChild(), offset, findLeft);
        }
        if (!findLeft && parent.getRightChild() != null) {
            return findMaxOffset(parent.getRightChild(), offset, findLeft);
        }
        return offset;
    }



}
