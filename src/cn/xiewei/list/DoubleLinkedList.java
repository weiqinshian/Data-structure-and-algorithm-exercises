package cn.xiewei.list;

public class DoubleLinkedList {
    
    public static void  main(String[] args) {
        DoubleLinkedList doubleLinked = new DoubleLinkedList();
        doubleLinked.insert(0,11);
        doubleLinked.appendLast("A");
        doubleLinked.appendLast("B");
        doubleLinked.appendLast("C");
        doubleLinked.appendLast("D");
        //��ӡ��ǰ������Ϣ
//        singleList.display();
        //ɾ��C
        System.out.println("ɾ��C֮��");
//        singleList.delete("C");
        doubleLinked.display();
        //����B
//        System.out.println(singleList.find("B"));
        
    }
    private int size;//���ڱ�������ڵ����
    private Node head;//�����ͷ�ڵ㣬Node����
    
    /**
     * ���캯����ʼ�������Ա����
     * ���캯��Ҫ������ͷ
     */
    public DoubleLinkedList() {
        // ��������ͷ����ע�⣺��ͷû�д洢���ݣ�
        head = new Node(null, null, null);
        head.prev = head.next = head;
        // ��ʼ�����ڵ������Ϊ0
        size=0;
    }
    
        /**
         * ͨ���ڲ�������װ�������ÿ���ڵ����Ϣ
         * ˫������������ָ��
         * @author XW
         * @create_date 2019��5��23��
         */
        private class Node{
            private Object data;//����ڵ�ֵ
            private Node prev;//�൱������ڵ�ָ�룬ָ����һ������ڵ�
            private Node next;//�൱������ڵ�ָ�룬ָ����һ������ڵ�
            /**
             * �вι��캯����Ϊ����ڵ㸳ֵ
             * @param data
             */
            public Node(Object data,Node prev, Node next) {
                this.data=data ;
                this.prev=prev;
                this.next=next;
            }
        }
    
        // ���ؽڵ���Ŀ
         public int size() {
             return size;
         }
     
         // ���������Ƿ�Ϊ��
         public boolean isEmpty() {
             return size==0;
         }
         
         // ��ȡ��indexλ�õĽڵ�
         private Node getNode(int index) {
             if (index<0 || index>=size)
                 throw new IndexOutOfBoundsException();
             // �������
             if (index <= size/2) {
                 Node node = head.next;//head.next��ʾ�ڶ����ڵ�
                 for (int i=0; i<index; i++)
                     node = node.next;
                 return node;
             }

             // �������
             Node rnode = head.prev;//head.prev��ʾ���һ���ڵ�
             int rindex = size - index -1;
             for (int j=0; j<rindex; j++)
                 rnode = rnode.prev;

             return rnode;
         }
         
         // ��ȡ��indexλ�õĽڵ��ֵ
         public Object get(int index) {
             return getNode(index).data;
         }

         // ��ȡ��1���ڵ��ֵ
         public Object getFirst() {
             return getNode(0).data;
         }

         // ��ȡ���һ���ڵ��ֵ
         public Object getLast() {
             return getNode(size-1).data;
         }
         
      // ���ڵ���뵽��indexλ��֮ǰ
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

         // ���ڵ�����һ���ڵ㴦��
         public void insertFirst(Object data) {
             insert(0, data);
         }
         
 

         // ���ڵ�׷�ӵ������ĩβ
         public void appendLast(Object data) {
             Node node = new Node(data, head.prev, head);
             head.prev.next = node;
             head.prev = node;
             size++;
         }

         // ɾ��indexλ�õĽڵ�
         public void del(int index) {
             Node inode = getNode(index);
             inode.prev.next = inode.next;
             inode.next.prev = inode.prev;
             inode = null;
             size--;
         }

         // ɾ����һ���ڵ�
         public void deleteFirst() {
             del(0);
         }

         // ɾ�����һ���ڵ�
         public void deleteLast() {
             del(size-1);
         }
         
         /**
          * ��ʾ�ڵ���Ϣ
          *   
          * @author XW
          * @create_date 2019��5��23��
          * @return void
          */
         public void display(){
             if(size >0){
                 Node node = head;
                 int tempSize = size;
                 if(tempSize == 1){//��ǰ����ֻ��һ���ڵ�
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
             }else{//�������һ���ڵ㶼û�У�ֱ�Ӵ�ӡ[]
                 System.out.println("[]");
             }
             
         }
}
