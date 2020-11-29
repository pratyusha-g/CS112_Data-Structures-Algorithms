import java.util.Arrays;

public class ChainedHashTable implements HashTable {
	private int numKeys; //number of keys in hashtable
	private Node[] table;
	private int size;
	
    // a helper linked list data type
    private static class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.values = new LLQueue<Object>();
            this.values.insert(val);
            this.next = next;
        }
    }
	
	public ChainedHashTable(int size) {
		numKeys = 0;
		if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
		else {
			this.size = size;
			table = new Node[size];
		}
		
	}
	
	/* first hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
	
	/*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
	public boolean insert(Object key, Object value) { 
		

		if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        } 
		
		int index = h1(key);
		
		if (table[index] == null) {
			table[index] = new Node(key, value, null);
			numKeys++;
			return true;
		}
		
		
		Node trav = table[index];
		
		//insert element in linked list at that index
		while (trav!=null && !key.equals(trav.key)) { //move to the next node if key does not match
			trav = trav.next;
		}
		
		if (trav == null) { //if it reaches the last node but there is still no matching key 
			//need to create a new node with the new unique key
			table[index] = new Node(key, value, table[index]);
			numKeys++; //update the number of keys
		
		} else if (key.equals(trav.key)) { //if there is an existing key
			trav.values.insert(value); //add value to existing key
	
		} 
		
		return true;
	}
	
	/*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
	
	
	public Queue<Object> search(Object key) {
		
		if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        } 
		
		int index = h1(key);
		
		Node trav = table[index];
		while (trav!=null) {
			if(key.equals(trav.key)) {
				return trav.values;
			}
			else {
				trav = trav.next;
			}
		}
		
		return null;	
	}
	
	
	/* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
	
	
	public Queue<Object> remove(Object key) {
		
		if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        } 
		
		int index = h1(key);
			
		if(key.equals(table[index].key)) {
			LLQueue<Object> temp = table[index].values;
			
			table[index] = table[index].next;
			numKeys--;
			
			return temp;
		}
		
		
		Node trav_before = table[index];
		Node trav = table[index];
		
		
		while (trav!=null) {
			if(!key.equals(trav.key)) {
				trav_before = trav;
				trav = trav.next;
			}
			else { //if the key is found
				LLQueue<Object> temp = trav.values; //store values of key to be removed
					
				trav_before.next = trav.next;
				trav = trav.next;
				numKeys--;
					
				return temp;
				
				}
			}
				
		
		return null;

	}
	
	
	
	public String toString() { 
		
		String temp = "";
		
		for (int i = 0; i < table.length; i++) {
			Node trav = table[i];
			
			if (trav == null) {
				if (i != table.length -1) {
					temp += null + ", ";
				}
				else {
					temp += null + "";
				}
			}
			else {
				temp+= "[";
				
				while (trav!=null) {
					
					if (trav.next == null) {
						temp += trav.key + "";
						trav=trav.next;
					} else {
					temp += trav.key + ", ";
					trav=trav.next;
					}
				}
				if (i == table.length -1) {
					temp+="]";
				} else {
				temp+="], ";
				}
			}
		}

		return "{" + temp + "}";
		
	}
	
	public int getNumKeys() { //accessor method
		return numKeys;
	}
	
	public double load() { //load factor of the table --> no. of keys/size
		return (double) numKeys/size ;
		
	}
	

	public Object[] getAllKeys() { //all keys of table
		Object[] arr = new Object[numKeys];
		
		int count = 0;
			
		for (int i = 0; i < table.length; i++) {
			Node trav = table[i];
			while (trav!=null) {
				arr[count] = trav.key;
				trav = trav.next;
				count++;
			}
		}		

		return arr;
	}
	
	public void resize(int n) { //grow the table to new size n, no return value
		if (n < size) {
            throw new IllegalArgumentException("n must be larger than current size");
        }
		else if (n == size) {
			return;
		}
		else {
			
			ChainedHashTable new_table = new ChainedHashTable(n);
			
			for (int i = 0; i < table.length; i++) { //go through each bucket
				Node trav = table[i];
				while (trav!=null) {
					new_table.insert(trav.key, trav.values);
					trav = trav.next;
					}
				}
			table = new_table.table;
			this.numKeys = new_table.numKeys;
			size = n;
			}
		
		
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("--- testing insert() and toString() ---");
        System.out.println();
        System.out.println("(1) insert howdy, goodbye and apple keys");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	
            ChainedHashTable results = table;
            System.out.println(results);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(2) insert repeated keys with different values");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
        	
            ChainedHashTable results = table;
            System.out.println(results);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
		
        System.out.println("--- testing search() and toString() ---");
        System.out.println();
        System.out.println("(1) search for goodbye key");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.search("goodbye");
            System.out.println(results);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
		
        System.out.println("(2) search for apple key");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.search("apple");
            System.out.println(results);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
		
        System.out.println("--- testing remove() and toString() ---");
        System.out.println();
        System.out.println("(1) remove goodbye");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.remove("goodbye");
            System.out.println(results);
            System.out.println(table);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
		
        System.out.println("(2) remove apple");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.remove("apple");
            System.out.println(results);
            System.out.println(table);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing  and toString() ---");
        System.out.println();
        System.out.println("(1) remove goodbye");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.remove("goodbye");
            System.out.println(results);
            System.out.println(table);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing toString() ---");
        System.out.println();
        System.out.println("(1) table");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            ChainedHashTable results = table;
            System.out.println(results);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println("(2) table after removal ");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            Queue<Object> results = table.remove("howdy");
            System.out.println(results);
            System.out.println(table);
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing getAllKeys() ---");
        System.out.println();
        System.out.println("(1) after inserting");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            String results = Arrays.toString(table.getAllKeys());
            System.out.println(results);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("(2) after removal");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
        	table.remove("apple");
        	
        	String results = Arrays.toString(table.getAllKeys());
            System.out.println(results);        
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing getNumKeys() ---");
        System.out.println();
        System.out.println("(1) after inserting");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            int results = table.getNumKeys();
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("(2) after removal");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	table.remove("goodbye");
        	
            int results = table.getNumKeys();
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing load() ---");
        System.out.println();
        System.out.println("(1) after inserting");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
            double results = table.load();
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0.6);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0.6);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing load() ---");
        System.out.println();
        System.out.println("(2) after more inserting");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	table.insert("trouble", 4);
        	
            double results = table.load();
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0.8);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0.8);
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- testing resize() ---");
        System.out.println();
        System.out.println("(1) resize after inserting ");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	
        	System.out.println(table);
        	
            table.resize(7);
           
            System.out.println(table);
            
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println();
        System.out.println("(2) resize after more inserting ");
        
        try {
            
        	ChainedHashTable table = new ChainedHashTable(5);
        	table.insert("howdy", 15);
        	table.insert("goodbye", 10);
        	table.insert("apple", 5);
        	table.insert("goodbye", 5);
        	table.insert("apple", 20);
        	table.insert("apple", 12);
        	table.insert("try", 7);
        	table.insert("trouble", 3);
        	table.insert("help", 2);
        	
        	System.out.println(table);
        	
            table.resize(10);
           
            System.out.println(table);
            
            
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        
        
		/*
		ChainedHashTable table = new ChainedHashTable(5);
		
		table.insert("howdy", 15);
		System.out.println(table);
		System.out.println(table.load());
				
		table.insert("goodbye", 10);
		System.out.println(table);
		System.out.println(table.load());
		
		
		table.insert("apple", 5);
		System.out.println(table);
		System.out.println(table.load());
		
		table.insert("goodbye", 15);
		System.out.println(table);
		System.out.println(table.load());
		
		System.out.println(table.search("goodbye"));
		
		//System.out.println(table.remove("goodbye"));
		//System.out.println(table.toString());
		
		
		System.out.println(Arrays.toString(table.getAllKeys()));
		
		table.resize(7);
		System.out.println(table.toString());
		*/
		

	}

}
