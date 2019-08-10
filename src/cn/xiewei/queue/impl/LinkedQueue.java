package cn.xiewei.queue.impl;

import cn.xiewei.queue.Interface.IQueue;
import cn.xiewei.queue.Interface.LNode;


/**
 * 链队列
 * <p>
 * 链队列中 需要有队头和队尾指针，且为了表示方便 这里添加一个头节点
 * 因此链队列判空的条件为 队头和队尾指针均指向头节点
 * 链队列的操作即是单链表的插入和删除操作的特殊情况
 *
 * @param <T>
 */
public class LinkedQueue<T> implements IQueue {


    private LNode Header;//头指针 指向头节点  即队首元素的前一个位置 （作用 方便删除队首元素，方便判断队列是否满）
    private LNode TaillPoint;//尾指针
    private Integer size;

    public IQueue initQueue() {
        if (Header == null) {
            Header = new LNode<T>(); //实例化头节点
            //头尾指针均指向头节点
            TaillPoint = Header;
            size = 0;
        }
        return this;
    }

    public IQueue destroyQueue() {
        //销毁
        Header = null;
        TaillPoint = Header;
        size = 0;
        return this;
    }

    public IQueue clearQueue() {
        //头尾指针均指向头节点
        TaillPoint = Header;
        size = 0;
        return this;
    }

    public Boolean isEmpty() {
        if (TaillPoint == Header) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Integer getLength() {
        return size;
    }

    public T getHead() {
        return (T) Header.getNext().getData();
    }

    public Boolean add(Object e) {
        //入队 从队尾入
        LNode newNode = new LNode<T>((T) e, null);
        TaillPoint.setNext(newNode);
        TaillPoint = newNode;
        size++;
        return Boolean.TRUE;
    }

    public T delete() {
        //删除的时候 如果是最后一个元素, 这个时候尾指针需要调整为指向头节点。 ps:至始至终 头节点本身不需要变，但是头节点指向的next指针要变
        if (Header.getNext().getNext()==null ) {
            T e = (T) Header.getNext().getData();
            Header.setNext(null);
            TaillPoint = Header;
            return e;
        }
        T e = (T) Header.getNext().getData();
        Header.setNext(Header.getNext().getNext());
        size--;
        return e;
    }


    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<Integer>();
        linkedQueue.initQueue();
        linkedQueue.add(1);
        linkedQueue.add(2);
        linkedQueue.add(3);
        Integer s = linkedQueue.size;
        System.out.println(linkedQueue.getHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println("依次打印队列中元素："+linkedQueue.delete());
        }
        System.out.println(linkedQueue.isEmpty());
    }

}
