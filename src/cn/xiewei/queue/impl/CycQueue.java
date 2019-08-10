package cn.xiewei.queue.impl;

import cn.xiewei.queue.Interface.IQueue;

/**
 * 循环队列
 * <p>
 * 注意：判空和判满的两种情况：
 * 情况1.另设一个标识位区别队列是空还是满
 * 情况2.少用一个元素空间，约定以"队列头指针在队尾指针的下一位位置上" 作为队列满的标志
 *
 * @param <T>
 */

public class CycQueue<T> implements IQueue {

    private Integer MAXSIZE = 6; //循环队列最大长度为7  0~6
    private Object[] arr;
    private Integer front;//头指针，若队列不为空，指向队头元素
    private Integer rear; //尾指针，若队列不为空，指向队列尾元素的下一个位置

    public IQueue initQueue() {
        arr = new Object[MAXSIZE];
        front = rear = 0;
        return this;
    }

    public IQueue destroyQueue() {
        arr = null;
        rear = front = 0;
        return this;
    }

    public IQueue clearQueue() {
        rear = front = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        return this;
    }

    public Boolean isEmpty() {
        if (front == rear) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Integer getLength() {
        return (rear - front + MAXSIZE) % MAXSIZE; //求环形队列的元素个数
    }

    public Object getHead() {
        return arr[front];
    }

    //入队前判满
    public Boolean add(Object e) {
        //队列头指针在队尾指针的下一位位置上  说明满了
        if ((rear + 1) % MAXSIZE == front) {
            return Boolean.FALSE;
        }
        arr[rear] = e;
        rear = (rear + 1) % MAXSIZE;
        return Boolean.TRUE;
    }

    //出队前判空
    public Object delete() {
        if (rear == front) {
            return null;
        }
        T e = (T) arr[front];
        front = (front + 1) % MAXSIZE;
        return e;
    }


    public static void main(String[] args) {
        CycQueue<Integer> cycQueue = new CycQueue<Integer>();
        cycQueue.initQueue();
        cycQueue.add(1);
        cycQueue.add(2);
        cycQueue.add(3);
        cycQueue.add(4);
        cycQueue.add(5);
        cycQueue.add(6);

        Integer s = cycQueue.getLength();
        System.out.println(cycQueue.getHead());
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println("依次打印队列中元素："+cycQueue.delete());
        }
        System.out.println(cycQueue.isEmpty());

        cycQueue.add(4);
        cycQueue.add(5);
        cycQueue.add(6);
        s = cycQueue.getLength();
        for (Integer integer = 0; integer < s; integer++) {
            System.out.println("依次打印队列中元素："+cycQueue.delete());
        }
        System.out.println(cycQueue.isEmpty());

    }
}
