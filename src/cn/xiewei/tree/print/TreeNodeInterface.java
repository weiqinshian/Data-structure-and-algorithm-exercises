package cn.xiewei.tree.print;



public interface TreeNodeInterface {
    /**
     * ��Ҫ��ӡ����Ϣ
     * @return
     */
    String getPrintInfo();

    TreeNodeInterface getLeftChild();

    TreeNodeInterface getRightChild();
}