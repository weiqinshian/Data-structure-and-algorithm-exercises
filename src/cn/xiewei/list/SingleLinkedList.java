package cn.xiewei.list;

/**
 * java ʵ�ֵ�����
 * 
 * @author XW
 * @create_date 2019��5��23��
 */
public class SingleLinkedList {
    private int size;//���ڱ�������ڵ����
    private Node head;//�����ͷ�ڵ㣬Node����
    
    /**
     * ���캯����ʼ�������Ա����
     */
    public SingleLinkedList() {
        size=0;
        head=null;
    }
    
    /**
     * ͨ���ڲ�������װ�������ÿ���ڵ����Ϣ
     * @author XW
     * @create_date 2019��5��23��
     */
    private class Node{
        private Object data;//����ڵ�ֵ
        private Node next;//�൱������ڵ�ָ�룬ָ����һ������ڵ�
        /**
         * �вι��캯����Ϊ����ڵ㸳ֵ
         * @param data
         */
        public Node(Object data) {
            this.data=data ;
        }
    }
    
    /**
     * ������ͷ��ӽڵ�
     *   
     * @author XW
     * @create_date 2019��5��23��
     * @return Object
     */
    public Object addHead(Object data) {
        Node newhead=new Node(data);//����һ����Ϊnewhead�Ľڵ�
        if (size==0) {
            head=newhead;
        } else {
            newhead.next=head;//������ͷ��ָ��ָ��ɱ�ͷ
            head=newhead;
        }
        size++;
        return data;
    }
    
    /**
     * ������ͷɾ���ڵ�
     *   
     * @author XW
     * @create_date 2019��5��23��
     * @return Object
     */
    public Object deleteHead() {
        if (size==0) {
            return null;
        }
        Object object =head.data;
        head=head.next;//ԭ����ͷָ��ָ��Ľڵ㣬��Ϊ�±�ͷ��
        size--;
    
        return object;    
    }
    
  /**
   * ����ָ��Ԫ�أ��ҵ��˷��ؽڵ�Node���Ҳ�������null
   *   
   * @author XW
   * @create_date 2019��5��23��
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
     * ɾ��ָ����Ԫ�أ�ɾ���ɹ�����true
     *  
     * ɾ���ڵ�֮����һ��ָ���л��Ĺ���
     * @author XW
     * @create_date 2019��5��23��
     * @return boolean
     */
    public boolean delete(Object value){
        if(size == 0){
            return false;
        }
        Node deleteNode = head;//deleteNode��ʾ��Ҫɾ���Ľڵ�
        Node previous = head;//previous��Ҫɾ���ڵ��ǰһ���ڵ�
        //ͨ��whileѭ���ҵ���Ҫɾ���Ľڵ�
        while(deleteNode.data != value){
            if(deleteNode.next == null){
                return false;
            }
            previous = deleteNode;
            deleteNode = deleteNode.next;            
        }
        //���ɾ���Ľڵ��ǵ�һ���ڵ�
        if(deleteNode == head){
            head = deleteNode.next;
            size--;
        }else{//ɾ���Ľڵ㲻�ǵ�һ���ڵ�
            previous.next = deleteNode.next;
            size--;
        }
        return true;
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
        }else{//�������һ���ڵ㶼û�У�ֱ�Ӵ�ӡ[]
            System.out.println("[]");
        }
        
    }
    
    public static void  main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //��ӡ��ǰ������Ϣ
        singleList.display();
        //ɾ��C
        System.out.println("ɾ��C֮��");
        singleList.delete("C");
        singleList.display();
        //����B
        System.out.println(singleList.find("B"));
        
    }

}
