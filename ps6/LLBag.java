import java.util.Scanner;

public class LLBag implements Bag{
	 private class Node {
	        private Object item;
	        private Node next;
	        
	        private Node(Object i, Node n) {
	            item = i;
	            next = n;
	        }
	 }
	 
	 private Node head;     // dummy head node
	 private int length;    // # of items in the list
	 
	 
	 
	 
	 public LLBag() {
		 head = new Node(null, null);
	     length = 0;
	 }
	 private Node getNode(int i) {
	        Node trav = head;
	        int travIndex = -1;
	        
	        while (travIndex < i) {
	            travIndex++;
	            trav = trav.next;
	        }
	        
	        return trav;
	    }
	    /* 
	     * adds the specified item to the Bag.  Returns true on success
	     * and false if there is no more room in the Bag.
	     */
	    public boolean add(Object item) {
	    	
	    	Node newNode = new Node(item, null); //create a new node
	    	Node prevNode = getNode(length-1); //find the last node
	    	prevNode.next = newNode; //add the new node to the last node
	    	length++;
	    	return true;
	    	
	    }

	    /* 
	     * removes one occurrence of the specified item (if any) from the
	     * Bag.  Returns true on success and false if the specified item
	     * (i.e., an object equal to item) is not in the Bag.
	     */
	    public boolean remove(Object item) {
	    	if (head.item == item) {
	    		head = head.next;
	    		length--;
	    	} else {
		    	for (int i = 1; i< length; i++) {
		    		if (getNode(i).item == item) {
		    			getNode(i-1).next = getNode(i+1);
		    			length--;
		    			
		    		}
		    	}
	    	
	    }
	    	return true;
	    }

	    /*
	     * returns true if the specified item is in the Bag, and false
	     * otherwise.
	     */
	    public boolean contains(Object item) {
	    	for (int i = 0; i < length; i++) {
	    		if (getNode(i).item == item) {
	    			return true;
	    		}
	    	}
	    	return false;
	    	
	    }

	    /*
	     * returns the number of items in the Bag.
	     */
	    public int numItems() {
	    	return length;
	    }

	   
	    /*
	     * grab - returns a reference to a randomly chosen item in the Bag.
	     */
	    
	    
	    public Object grab() {
	    	if (length == 0) {
                return null; 
          }
          int random = (int) (Math.random() * length); //in order to get a random item from the bag

          Node curr = head;

          for (int i = 0; i < random; i++) {
                curr = curr.next;
          }
          return curr.item;

    }
	    	
	    

	    /*
	     * toArray - return an array containing the current contents of the bag
	     */
	    
	    public Object[] toArray() {
	    	
	    	Object[] arr = new Object[length];
	    	Node curr = head;
	    	for (int i = 0; i < length; i++) {
	    		arr[i] = curr.item;
	    		curr = curr.next;
	    	}
	    	return arr;
	    }
	    
	    
	    public String toString() {
	    	
	    	String temp = "{";
	    	Node curr = head;
	    	while (curr != null) {
	    		temp += curr.item;
	    		curr = curr.next;
	    		if (curr!=null) {
	    			temp += ", ";
	    		}
	    	}
	    	temp += "}";
	    	return temp;
    	
	    }
	    
	

	
	
	
	public static void main(String[] args) {
		// Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        Bag bag1 = new ArrayBag(numItems);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        System.out.println(bag1.toString());
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
       
        System.out.println(bag1.numItems());
        
        Object item2 = bag1.grab();
        System.out.println("grabbed " + item2);
        System.out.println();
        
        

	}

}
