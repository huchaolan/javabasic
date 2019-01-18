package generics.demo;

public class LinkedStack <T>{
/*
 * 使用内部类描述堆栈的节点
 */
	private static class Node<U>{
		U item;//保存节点的对象
		Node<U> next;//连接上一个对象
		Node(){//空节点
			item= null;
			next = null;
		}
		Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
		boolean end(){//是否为哨兵节点
			return item==null&& next==null;
		}
	}
	
	private Node<T> top = new Node<T>();//哨兵节点
	
	public void push(T item){//添加元素
		top = new Node<T>(item,top);
	}
	
	public T pop(){//弹出元素
		T result = top.item;
		if(!top.end()){
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args){
		LinkedStack<String> lss= new LinkedStack<String>();
		for(String s:"Pahsers on stun!".split(" ")){
			lss.push(s);
		}
		
		String s;
		while((s=lss.pop())!=null){
			System.out.println(s);
		}
	}
}
