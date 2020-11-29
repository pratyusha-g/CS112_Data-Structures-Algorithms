package ps3;

import java.util.Arrays;

/*
 * Student First Name:
 * Student Last Name:
 * Student BU Number:
 * Purpose: 
 */

public class Set  {
    private static final int SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int size;    // current size of the set
    private int next;       // index to next available slot in the set array
    
    
    public Set() {
      //initialize set as an empty set
      this.set = new int[SIZE];
      this.next = 0;
      this.size = SIZE;
    }
    
    
    public Set(int[] arr) {
        //A is an array not a set
    	//initialize set consisting of exactly elements of arr
    	//create empty set and then use insert(..) to add elements
    	
    	//create an empty set
    	this();
    	//use insert to add elements from array into newset
    	for (int i = 0; i < arr.length; i++) {
    		insert(arr[i]);
    	}
    	
    }
    
    public Set clone() {
    	
    	int[] array = new int[this.next];
    	for (int i = 0; i < this.next;i++) {
    		array[i] = this.set[i];
    	}
    	
    	
    	Set copyset = new Set(array);
    	return copyset;
    	/*
    	Set copyset = new Set();
    	
    	for (int i = 0; i < this.next; i++) {
    		copyset.insert(this.set[i]);
    		System.out.println(copyset);
    	}
    	return copyset;
    	*/
 	
    	
    	
    }
    
    // This method reallocates the array set to twice as big and copies all the elements over.
    // It is called only by insert.
    //
    // Note that this is the reason that in this class
    // the member size is not a class variable (i.e. static)
    // and it is not final, because the set can grow and size
    // will be modified accordingly.
    
    private void resize() {
        size *= 2;
        int[] temp = new int[size];
        for(int i = 0; i < set.length; ++i) {
            temp[i] = set[i];
        }
        set = temp;
    }
        
    public  String toString()  {
    	
    	String temp= "[";
    	for (int i = 0; i < this.next; i++) {
    		temp = temp + this.set[i];
    		if (i < this.next - 1) {
    			temp = temp + ",";
    		}
    	}
    	
    	return temp + ']';
        //cannot use array to string
    	//create char array from array S 
 
    } 
     
    public int cardinality() {
        return next;      
    }
    
    public  boolean isEmpty() {
        if (cardinality() == 0) {
        	return true;
        }
        return false;     
    }
      
    public boolean member(int k) { //prevents duplication
        for (int i = 0; i < this.next; i++) {
        	if (this.set[i] == k) {
        		return true;
        	}
        }
        return false;     
    }    
   
    public  boolean subset(Set s) {
        for (int i = 0; i < this.next; i++) {
            if (!s.member(set[i])) {
            	return false;
            }
        }
        return true;      
    }
    
    public  boolean equal(Set check) {
        if (this.next == check.next) {

            for (int i = 0; i < next; i++) {

                          if (!check.member(set[i])) {
                        	  return false; 

                          }

            }
            return true; 

}
return false; 

}

    

    public void insert(int k) {
    	//k not in set then add 
        if (!member(k)) {
        	//but if it results in an outofbounds error then resize first
        	if (this.next >= size) {
        		this.resize();
        	}
            this.set[this.next]=k; 
            this.next += 1;
        	}
        }
   
    public void delete(int k) {
        if (member(k)) {
        	//move all elements which occur after k to the left by one slot!
        	//need to find index of k
        	int index = 0;
        	for (int i=0; i < next; i++) {
        		if (set[i] == k) {
        			index = i;
        		}
        	}
        	for (int i = index; i < next-1; i++) {
        		set[i]=set[i+1];
        	}
        	set[next - 1] = 0;
        	next--;
        	
        }
    	
    }
    
    public Set union(Set T) { //all elements, without duplicates
        
    	Set s = new Set();
    	
    	for (int i = 0; i < T.next; i++) {
    		s.insert(T.set[i]);
    	
        }
    	
    	for (int i = 0; i < this.next; i++) {
    		s.insert(this.set[i]);
    	
        }
    	return s;
    }
    
   
    public Set intersection(Set T) { //find common elements in both sets
        
    	Set s = new Set();
    	for (int i = 0; i < next; i++) {
    		if (T.member(set[i])) {
    			s.insert(set[i]);
    		}
    	}
    	return s;
    	
    }
    
    
    public Set setdifference(Set T) {
       
    	Set s = new Set();
    	for (int i = 0; i < next; i++) {
    		if (!T.member(set[i])) { //checking it is not in the other set
    			s.insert(set[i]);
    		}
    	}
    	return s;
    	
    }


}