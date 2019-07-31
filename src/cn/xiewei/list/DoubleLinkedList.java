package cn.xiewei.list;

public class DoubleLinkedList {
    
    public static void  main(String[] args) {
        DoubleLinkedList doubleLinked = new DoubleLinkedList();
        doubleLinked.insert(0,11);
        doubleLinked.appendLast("A");
        doubleLinked.appendLast("B");
        doubleLinked.appendLast("C");
        doubleLinked.appendLast("D");
        //打印当前链表信息
//        singleList.display();
        //删除C
        System.out.println("删除C之后：");
//        singleList.delete("C");
        doubleLinked.display();
        //查找B
//        System.out.println(singleList.find("B"));
        
    }
    private int size;//用于保存链表节点个数
    private Node head;//链表的头节点，Node类型
    
    /**
     * 构造函数初始化链表成员属性
     * 构造函数要创建表头
     */
    public DoubleLinkedList() {
        // 创建“表头”。注意：表头没有存储数据！
        head = new Node(null, null, null);
        head.prev = head.next = head;
        // 初始化“节点个数”为0
        size=0;
    }
    
        /**
         * 通过内部类来封装，链表的每个节点的信息
         * 双向链表，有两个指针
         * @author XW
         * @create_date 2019年5月23日
         */
        private class Node{
            private Object data;//链表节点值
            private Node prev;//相当于链表节点指针，指向上一个链表节点
            private Node next;//相当于链表节点指针，指向下一个链表节点
            /**
             * 有参构造函数，为链表节点赋值
             * @param data
             */
            public Node(Object data,Node prev, Node next) {
                this.data=data ;
                this.prev=prev;
                this.next=next;
            }
        }
    
        // 返回节点数目
         public int size() {
             return size;
         }
     
         // 返回链表是否为空
         public boolean isEmpty() {
             return size==0;
         }
         
         // 获取第index位置的节点
         private Node getNode(int index) {
             if (index<0 || index>=size)
                 throw new IndexOutOfBoundsException();
             // 正向查找
             if (index <= size/2) {
                 Node node = head.next;//head.next表示第二个节点
                 for (int i=0; i<index; i++)
                     node = node.next;
                 return node;
             }

             // 反向查找
             Node rnode = head.prev;//head.prev表示最后一个节点
             int rindex = size - index -1;
             for (int j=0; j<rindex; j++)
                 rnode = rnode.prev;

             return rnode;
         }
         
         // 获取第index位置的节点的值
         public Object get(int index) {
             return getNode(index).data;
         }

         // 获取第1个节点的值
         public Object getFirst() {
             return getNode(0).data;
         }

         // 获取最后一个节点的值
         public Object getLast() {
             return getNode(size-1).data;
         }
         
      // 将节点插入到第index位置之前
         public void insert(int index, Object data) {
             if (index==0) {
                 Node node = new Node(data, head, head.next);
                 head.next.prev = node;
                 head.next = node;
                 size++;
                 return ;
             }

             Node inode = getNode(index);
             Node tnode = new Node(data, inode.prev, inode);
             inode.prev.next = tnode;
             inode.next = tnode;
             size++;
             return ;
         }

         // 将节点插入第一个节点处。
         public void insertFirst(Object data) {
             insert(0, data);
         }
         
 

         // 将节点追加到链表的末尾
         public void appendLast(Object data) {
             Node node = new Node(data, head.prev, head);
             head.prev.next = node;
             head.prev = node;
             size++;
         }

         // 删除index位置的节点
         public void del(int index) {
             Node inode = getNode(index);
             inode.prev.next = inode.next;
             inode.next.prev = inode.prev;
             inode = null;
             size--;
         }

         // 删除第一个节点
         public void deleteFirst() {
             del(0);
         }

         // 删除最后一个节点
         public void deleteLast() {
             del(size-1);
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
                 while(tempSize>=0){
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
}
