import java.util.LinkedList;



/*
 * 队列，保存要访问的URL
 * */
public class Queue {
	//利用链表实现队列
	private LinkedList queue=new LinkedList();
	
	//入队列
	public void enQueue(Object t){
		queue.addLast(t);
	}
	
	//出队列
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	//判断是否为空
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	
	//是否包含t
	public boolean contains(Object t){
		return queue.contains(t);
	}
	
	//
	public boolean empty(){
		return queue.isEmpty();
	}
}
