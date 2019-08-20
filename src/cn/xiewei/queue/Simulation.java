package cn.xiewei.queue;

import cn.xiewei.queue.impl.LinkedQueue;
/**
 * simulation n.  ģ��; ����; ��װ; ð��
 * 
 * @author XW
 * @create_date 2019��8��20��
 */
public class Simulation {

    private static Long TotalTime; //�ܶ���ʱ��
    private static int CustomerNum;//�ͻ�����
    private static Long CloseTime = 18 * 3600L;//���й���ʱ�� ����6��ʱ�̵�����

    static class Event { //�¼��� Ԫ��
        private int eventType;//�¼�����
        private Long time;//�¼�������ʱ��
    }

    static class QEvent { //�ŶӵĶ���Ԫ��
        private Long ArrivalTime; //����ʱ��
        private int Duration;//���������ʱ��
    }

    static class QueueWindow {
        private int windowNum;//���ں�
        private LinkedQueue<QEvent> WindowQueue;//�ô��ڵ��ŶӶ���
    }


    private static LinkedQueue<Event> eventQueues = new LinkedQueue<Event>();//�¼�����
    private static LinkedQueue<QEvent>[] queues = new LinkedQueue[3]; //4�����ڵĿͻ�����

    public static void OpenForDay() {
        //��ʼ���ͻ������Ͷ���ʱ��Ϊ0
        TotalTime = 0L;
        CustomerNum = 1;
        //���ɵ�һ���ͻ������¼�
        Event event = new Event();
        long rad= (long)( Math.random() * 30 * 60L);
        event.time = 8 * 3600L +rad; //8�㿪��  30�����������һ���ͻ�
        System.out.println("���ɵ�һ���ͻ������¼�,�ÿͻ�Ϊ8��"+rad/60+"�ֵ���");
        event.eventType = 0;
        eventQueues.initQueue(); //��ʼ���¼�����
        eventQueues.add(event); //�������¼������¼�������
        //��ʼ�����ڶ���
        for (int i = 0; i < queues.length; i++) {
            LinkedQueue<QEvent> qEventLinkedQueue = new LinkedQueue<QEvent>();
            qEventLinkedQueue.initQueue();
            queues[i] = qEventLinkedQueue;
        }

    }


    public static char getEventType() {
        if (eventQueues.getHead().eventType == 0) {
            return 'A';
        } else {
            return 'D';
        }

    }


    /**
     * �ҳ��ĸ������ŶӵĶ����� ��̵Ķ���
     *
     * @return
     */
    private static QueueWindow getShortestQueue() {
        int max = 0;
        int maxTag = 0;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].getLength() >= max) {
                max = queues[i].getLength();
                maxTag = i;
            }
        }
        QueueWindow queueWindow = new QueueWindow();
        queueWindow.windowNum = maxTag;
        queueWindow.WindowQueue = queues[maxTag];
        return queueWindow;
    }

    /**
     * ����ͻ������¼�
     * <p>
     * ����ͻ������¼�,�����Ŀͻ���ֻ����뵽�ĸ����ڶ����У���̵��Ǹ���
     * ͬʱ ������δ���� ������һ���µĿͻ������¼������¼�������ǰһ���ͻ������¼�֮��
     * �������Ķ���Ϊ�� ���ٲ���һ���ͻ��뿪�¼�
     * ����Ԫ���а���������ʱ�䣬��������ʱ��
     */
    public static void CustomerArrived() {
        //ԭ���¼�����������¼�����
        Event event = eventQueues.delete();

        //�����Ŷ��¼�
        QEvent qEvent = new QEvent();
        qEvent.ArrivalTime = event.time;
        qEvent.Duration = 5 * 60 + (int) (Math.random() * 20 * 60); //��������ʱ�� ��5~20��������

        //�ҳ���̵Ĵ��ڶ���
        QueueWindow queueWindow = getShortestQueue();
        //�ж���������Ƿ�Ϊ�գ��ǵĻ��ٲ���һ���ͻ��뿪�¼�
        if (queueWindow.WindowQueue.isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.time = qEvent.ArrivalTime + qEvent.Duration; //�ͻ����뿪ʱ�䣬�����䵽���ʱ��+���µ�ʱ��
            leaveEvent.eventType = queueWindow.windowNum;
            eventQueues.add(leaveEvent);
        }
        //�����¼����
        queueWindow.WindowQueue.add(qEvent);

        //�����µĿͻ������¼�
        Event newEvent = new Event();
        newEvent.eventType = 0;
        newEvent.time = event.time + 10 * 60L + (long) (Math.random() * 20 * 60L); //ǰһ���ͻ�����֮��  10��30�����������һ���ͻ�
        if (newEvent.time >= CloseTime) {
            return; //��ֹ�����µĿͻ������¼�
        }
        eventQueues.add(newEvent); //�µĵ����¼����
        CustomerNum++;//�ͻ���+1
    }

    /**
     * *����ͻ��뿪�¼��������¼����� ����Ӧ�Ĵ��ڶ���ͷ�����¼�����,ͳ�ƶ���ʱ��
     * ������в�Ϊ�գ����ٲ���һ���ͻ��뿪�¼�
     *
     * @param
     */
    private static void CustomerDeparture() {
        Event event = eventQueues.delete();
        QEvent qEvent = queues[event.eventType].delete();//��Ӧ���ڶ��е��Ŷ��¼�����
        TotalTime = TotalTime + (event.time - qEvent.ArrivalTime);//����ʱ��  �����뿪�¼�����ʱ���ȥ����ʱ��

        //����ô��ڲ�Ϊ�� �����������һ���ͻ��뿪�¼�
        if (!queues[event.eventType].isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.eventType = event.eventType;
            leaveEvent.time = event.time + queues[event.eventType].getHead().Duration;// �µĿͻ��뿪ʱ�� ������һ���ͻ��뿪��ʱ��+�ÿͻ��Լ��İ���ʱ��
            eventQueues.add(leaveEvent);
        }
    }


    public static void main(String[] args) {
        OpenForDay();//��ʼ�� ��ʼһ��
        while (!eventQueues.isEmpty()) {   //�¼��б�Ϊ��ʱ��
            switch (getEventType()) { //�¼�����
                case 'A':
                    CustomerArrived();
                    break; //����ͻ������¼�
                case 'D':
                    CustomerDeparture();
                    break;//����ͻ��뿪�¼�
                default:
                    System.out.println("�¼����ʹ���");
                    ;
            }


        }


        System.out.println("�����ܹ�:" + CustomerNum + "���ͻ����ܶ���ʱ��Ϊ" + TotalTime + "��" + "������" + TotalTime / 60 + "��");
        System.out.println("ÿ����ƽ������ʱ��Ϊ:" + TotalTime / CustomerNum + "�룬 Ҳ����" + (TotalTime / CustomerNum) / 60 + "��");
    }
}
