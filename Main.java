public class Main {

	public static void main(String[] args) {
		
		//User Built LinkedList class
		MyLinkedList theList = new MyLinkedList();
		MyLinkedListIterator theItr;
		
		theItr = theList.zeroth();
		//Print the list values
		printList(theList);

		
		//insert integer objects into linked list
		for (int i = 0; i < 10; i++) {
			//invoking insert method to insert the integer object  into MYLinkedList
			theList.insert(new Integer(i), theItr);
			//printing the inserted objects from the MyLinkedList
			printList(theList);
			//Moving the cursor to next position to  insert the  next Object
			theItr.next();
		}
		
		//just print the listsize from the given list.
		System.out.println("Size was: " + listSize(theList));
		
		//invoking to remove the given Object from MyLinkedList
		theList.remove(new Integer(1));
		printList(theList);
		System.out.println("Size was: " + listSize(theList));
		
		//invoking to remove the given Object from MyLinkedList
		theList.remove(new Integer(3));
		printList(theList);
		System.out.println("Size was: " + listSize(theList));
		
	}

	/*
	 * Method to retun the size of the  given MYLinkedList object
	 * @param - MyLinkedList
	 */
	public static int listSize(MyLinkedList theList) {
		MyLinkedListIterator itr;
		int size = 0;
		for (itr = theList.first(); itr.isValid(); itr.next())
			size++;
		return size;
	}

	/*
	 * Method to print the list values from the given list
	 * @param - MyLinkedList 
	 */
	public static void printList(MyLinkedList theList) {
		if (theList.isEmpty())
			System.out.print("Empty list");
		else {
			MyLinkedListIterator itr = theList.first();
			for (; itr.isValid(); itr.next())
				System.out.print(itr.retrieve() + " ");
		}
		System.out.println();
	}
}

/*
 * Custom Class to manage the LinkedList data
 */
class MyLinkedList {

	private MyNode header;

	public MyLinkedList() {
		header = new MyNode(null);
	}

	public boolean isEmpty() {
		return header.next == null;
	}

	public void makeEmpty() {
		header.next = null;
	}

	public MyLinkedListIterator zeroth() {
		return new MyLinkedListIterator(header);
	}
	
	
	public MyLinkedListIterator first() {
		return new MyLinkedListIterator(header.next);
	}

	/*
	 * Inserts a new Object into the LinkedList
	 * @Param Object which needs to be inserted into it
	 * @Param iterator position to insert
	 * @return void
	 */
	public void insert(Object x, MyLinkedListIterator p) {
		if (p != null && p.current != null)
			p.current.next = new MyNode(x, p.current.next);
	}
	
	/*
	 * Method tries to find the given object reference and returns the Object
	 * @param Object which needs to be identified.
	 * @return void
	 */
	public MyLinkedListIterator find(Object x) {
		MyNode itr = header.next;
		while (itr != null && !itr.element.equals(x)){
			itr = itr.next;
		}
		return new MyLinkedListIterator(itr);
	}

	public MyLinkedListIterator findPrevious(Object x) {
		MyNode itr = header;

		while (itr.next != null && !itr.next.element.equals(x))
			itr = itr.next;

		return new MyLinkedListIterator(itr);
	}

	/*
	 * Method removes the given object from the List
	 * @param - Object which needs to be removed
	 * @return - void 
	 */
	public void remove(Object x) {
		MyLinkedListIterator p = findPrevious(x);

		if (p.current.next != null)
			p.current.next = p.current.next.next; // Bypass deleted node
	}

}
/*
 * Customized class to iterate the Custom Linked List
 */

class MyLinkedListIterator {
	
	MyNode current;
	
	MyLinkedListIterator(MyNode theNode) {
		current = theNode;
	}

	public boolean isValid() {
		return current != null;
	}

	public Object retrieve() {
		return isValid() ? current.element : null;
	}

	//Move to next position
	public void next() {
		if (isValid())
			current = current.next;
	}


}

class MyNode {
	//Object and its position
	public Object element;
	public MyNode next;

	//Constructor
	public MyNode(Object theElement) {
		this(theElement, null);
	}

	//Constructor
	public MyNode(Object theElement, MyNode n) {
		element = theElement;
		next = n;
	}

}