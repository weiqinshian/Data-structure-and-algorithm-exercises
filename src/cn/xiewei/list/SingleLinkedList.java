package cn.xiewei.list;

/**
 * java 实现单链表
 * 
 * @author XW
 * @create_date 2019年5月23日
 */
public class SingleLinkedList {
    private int size;//用于保存链表节点个数
    private Node head;//链表的头节点，Node类型
    
    /**
     * 构造函数初始化链表成员属性
     */
    public SingleLinkedList() {
        size=0;
        head=null;
    }
    
    /**
     * 通过内部类来封装，链表的每个节点的信息
     * @author XW
     * @create_date 2019年5月23日
     */
    private class Node{
        private Object data;//链表节点值
        private Node next;//相当于链表节点指针，指向下一个链表节点
        /**
         * 有参构造函数，为链表节点赋值
         * @param data
         */
        public Node(Object data) {
            this.data=data ;
        }
    }
    
    /**
     * 向链表头添加节点
     *   
     * @author XW
     * @create_date 2019年5月23日
     * @return Object
     */
    public Object addHead(Object data) {
        Node newhead=new Node(data);//创建一个名为newhead的节点
        if (size==0) {
            head=newhead;
        } else {
            newhead.next=head;//新链表头的指针指向旧表头
            head=newhead;
        }
        size++;
        return data;
    }
    
    /**
     * 从链表头删除节点
     *   
     * @author XW
     * @create_date 2019年5月23日
     * @return Object
     */
    public Object deleteHead() {
        if (size==0) {
            return null;
        }
        Object object =head.data;
        head=head.next;//原来表头指针指向的节点，成为新表头。
        size--;
    
        return object;    
    }
    
  /**
   * 查找指定元素，找到了返回节点Node，找不到返回null
   *   
   * @author XW
   * @create_date 2019年5月23日
   * @return Node
   */
    public Node find(Object obj){
        Node current = head;
        int tempSize = size;
        while(tempSize > 0){
            if(obj.equals(current.data)){
                return current;
            }
            current = current.next;
            tempSize--;
        }
        return null;
    }
    
    /**
     * 删除指定的元素，删除成功返回true
     *  
     * 删除节点之后，有一个指针切换的过程
     * @author XW
     * @create_date 2019年5月23日
     * @return boolean
     */
    public boolean delete(Object value){
        if(size == 0){
            return false;
        }
        Node deleteNode = head;//deleteNode表示将要删除的节点
        Node previous = head;//previous将要删除节点的前一个节点
        //通过while循环找到将要删除的节点
        while(deleteNode.data != value){
            if(deleteNode.next == null){
                return false;
            }
            previous = deleteNode;
            deleteNode = deleteNode.next;            
        }
        //如果删除的节点是第一个节点
        if(deleteNode == head){
            head = deleteNode.next;
            size--;
        }else{//删除的节点不是第一个节点
            previous.next = deleteNode.next;
            size--;
        }
        return true;
    }
    
    /**
     * 显示节点信息
     *   
     * @author XW
     * @create_date 2019年5月23日
     * @return void
     */
    public void display(){
        if(size >0){
            Node node = head;
            int tempSize = size;
            if(tempSize == 1){//当前链表只有一个节点
                System.out.println("["+node.data+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.data+"->");
                }else if(node.next == null){
                    System.out.print(node.data+"]");
                }else{
                    System.out.print(node.data+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }
        
    }
    
    public static void  main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //打印当前链表信息
        singleList.display();
        //删除C
        System.out.println("删除C之后：");
        singleList.delete("C");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
        
    }

}
