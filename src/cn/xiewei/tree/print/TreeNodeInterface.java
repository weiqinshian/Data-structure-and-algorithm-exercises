package cn.xiewei.tree.print;



public interface TreeNodeInterface {
    /**
     * 需要打印的信息
     * @return
     */
    String getPrintInfo();

    TreeNodeInterface getLeftChild();

    TreeNodeInterface getRightChild();
}