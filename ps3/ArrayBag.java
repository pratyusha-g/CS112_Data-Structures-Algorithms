package ps3;

/* 
 * ArrayBag.java
 * 
 * Computer Science 112
 */

import java.util.*;

/**
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
		}
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    public int capacity() {
    	//return max number of items arraybag can hold
    	//value does not depend on the number of items that are currently in the arraybag
    	//same as max size specified!!
    	return (this.items).length;
    }
    
    public boolean isEmpty() {
    	if (this.numItems == 0) {
    		return true;
    	}
    	return false;
    }
    
    public boolean addAll(Object[] newItems) {
    	//add items from array into bag and return boolean if it fails or succeeds
    	//if enough room, items added and return true
    	
    	//if not, none added, return false
    	
    	//USE EXISTING METHODS
    	
    	if ((this.items).length - this.numItems >= newItems.length) {
    		for (Object i: newItems) {
    			add(i);
    		}
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    public ArrayBag merge(ArrayBag other) {
    	//create and return arraybag containing 1 occurrence of any item found in either called object or in the other
    	//resulting bag should not have duplicates
    	//give arraybag max size that is sum of 2 bag's max sizes
    	
    	if (other == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	else if (this.numItems == 0 && other.numItems == 0) {
    		ArrayBag newbag = new ArrayBag();
    		return newbag;
    	}
    	
    	else {
    	
    	
    	//create bag with size of 2 bag's max sizes
    	ArrayBag newbag = new ArrayBag((this.items).length + (other.items).length);
    	
    	//get all unique items from 1 bag and then check them through second bag
    	Object[] arr1 = this.toArray();
    	//add all unique items from bag1 into newbag
    	for (Object i: arr1) {
    		if (!newbag.contains(i)) {
    			newbag.add(i);
    		}

    	}
    	//add all unique items from bag2 into newbag
    	Object[] arr2 = other.toArray();
    	for (Object i: arr2) {
    		if (!newbag.contains(i)) {
    			newbag.add(i);
    		}
    	}
    	return newbag;
    	
    	
    }
    }

    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        
    	/*
    	// Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
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
        
    	*/
        //test method 1
        ArrayBag b1 = new ArrayBag(10);
        ArrayBag b2 = new ArrayBag(20);
        System.out.println(b1.capacity());
        System.out.println(b2.capacity());
        b1.add("hello");
        System.out.println(b1.capacity()); 
        
        //test method 2
        ArrayBag c1 = new ArrayBag(10);
        System.out.println(c1.isEmpty());
        c1.add("hello");
        System.out.println(c1.isEmpty());
        
        //test method 3
        ArrayBag d1 = new ArrayBag(6);
        String[] words = {"hello", "how", "are", "you?"};
        System.out.println(d1.addAll(words));
        System.out.println(d1);
        
        String[] words2 = {"not", "bad", "thanks!"};
        System.out.println(d1.addAll(words2));
        System.out.println(d1);
        
        String[] words3 = {"not", "bad!"};
        System.out.println(d1.addAll(words3));
        System.out.println(d1);
        
        //test method 4
        ArrayBag e1 = new ArrayBag(10);
        String[] letters1 = {"a", "a", "b", "d", "f", "f", "f", "g"};
        e1.addAll(letters1);
        ArrayBag e2 = new ArrayBag(8);
        String[] letters2 = {"a", "b", "c", "d", "d", "e", "f"};
        e2.addAll(letters2);
        ArrayBag e3 = e1.merge(e2);
        System.out.println(e3);
        System.out.println(e3.capacity());
        System.out.println(e3.numItems());
        System.out.println(e1);
        System.out.println(e2);
        

    }
}
