/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    public int depthIter(int key) {
    	//use iteration to determine and return depth of node
    	//binary search tree 
    	//avoid considering subtrees that couldnt contain the specified key
    	//return -1 if key is not found in the tree
    	
    	//use insertKeys()
    	//use levelOrderPrint()
    	
    	int count = 0;
    	//System.out.println(root.key);
    	while (root.key != key) {
    	
    		if (root.key != key) {
    			
    			if (key >= root.key) {
    				root = root.right;
    				//System.out.println(root.key);
    				count+=1;
    			}
    			else if (key < root.key) {
    				root = root.left;
    				//System.out.println(root.key);
    				count+=1;
    			}

    		}
    		
    		if (root == null) { //reached the end but no key to be found
    			return -1;
    		}
    		}
    	return count;
    	}
    
    private static int numEvenKeysRec(Node object) {
    	//recursion to find and return the number of even keys in tree/subtree
    	if (object == null) //reached the end
    		return 0;

    	int count = numEvenKeysRec(object.left) + numEvenKeysRec(object.right);
    	
    	if (object.key %2 == 0) //if it is even
    		return count + 1;
    	
    	else 
    		return count; 
    	
    }
    
    public int numEvenKeys() {
    	//wrapper method
    	return numEvenKeysRec(root);
    }
	
    
    public int deleteMax() {
    //use iteration to find and delete the node containing the largest key in the tree
    //return value of the largest key that was deleted
    //if tree is empty, method should return -1!
    //correctly handle any child the largest key may have!!
    	
    //find maximum and store value
    //delete the node and reconnect parent and child nodes
    	
    if (root == null) { //empty tree
    	return -1;
    } else {
    
    //max value will be found on the last node of the rightmost subtree so max value should at most have 1 left child
    
	    Node trav = root; //iterator
	    Node prevNode = root;
	    
	    while (trav.right!=null) {
	    	prevNode = trav;
	    	//System.out.println(root.parent.key);
	    	trav = trav.right;
	    }
	    
	    //replace root's parents with its child if it exists
	    
	    if (trav.left != null) {
	    	prevNode.right = trav.left;
	    	//root.parent.right = root.left;
	    } else {
	    	prevNode.right = null;
	    	//root = null;
	    } 
	    int max = trav.key;
	    //System.out.println(max);
	    return max;
    }
    
    }

    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }
    
    /* 
     * inner class for a preorder iterator 
     * IMPORTANT: You will not be able to actually use objects from this class
     * to perform a preorder iteration until you have modified the LinkedTree
     * methods so that they maintain the parent fields in the Nodes.
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;
        
        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            // Store a copy of the key to be returned.
            int key = nextNode.key;
            
            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                       (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                
                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }
            
            return key;
        }
    }
    
    public LinkedTreeIterator postorderIterator() {
    	//create and return an instance of your new class
    	PostorderIterator temp = new PostorderIterator();
    	return temp;
    }
    
    private class PostorderIterator implements LinkedTreeIterator {
    
    	private Node node;
    	private Node current; 
    	private Node lastnode;
    	
    	//given a reference named tree to an arbitrary linkedtree object, code will perform a complete postorder traversal of tree
    	//space complexity of O(1)
    	
    	/*
    	 * first node to be visited is the left subtree, then the right subtree and then finally the root of the BST
    	 */
    	
    	public PostorderIterator() { //constructor
    		
    		if (root == null) {
    			return;
    		}
    		node = root;
    		current = root;
    		lastnode = root;
    		
    		while (lastnode != null && lastnode.right !=null) { //to find the last node
    			lastnode = lastnode.right;
    		}
    		while (current != null && current.left != null) { //to find the current node we want to look at
    			current = current.left;
    		}

    	}

    	//time efficiency of O(1)
    	public boolean hasNext() {
    		return current != null && current.key <= lastnode.key;
    	}
    	
    	//no more than O(1) space, as efficient as possible
    	public int next() {
    		//return the value of the key in the next node to be visited
    		//advance the iterator so it is ready for the next call to method
    		Node temp = current;
    		
    		if (!hasNext()) { //base case, when there are no remaining nodes to visit
    			throw new NoSuchElementException();
    			
    		} else if (current.right != null) { //if current node has a right child
    			current = current.right;
    			
    			
    			while (current.left !=null) {
    				current = current.left;
    				
    			}
    			
    		} else { //current node does not have a right child
    			current = findparentnode(current, lastnode);
    			
    		}
    		//System.out.println(current.key);
    		
    		return temp.key;
    	}
    }
    
    	public Node findparentnode(Node current, Node lastnode)  {
    		Node node = root;
    		Node parent = null;
    		int val = current.key;
    		if (val == lastnode.key) {
    			return null;
    		}
    		while (node !=null) {
    			if (val < node.key) {
    				parent = node;
    				node = node.left;
    			} else if (val > node.key) {
    				node = node.right;
    			} else {
    				break;
    			}
    		}
    		return parent;
    	}
    
    	
    
    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 7, Problem 4
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 7, Problem 4. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;
        }
        
        if (root.left != null) {
            int depthInLeft = depthInTree(key, root.left);
            if (depthInLeft != -1) {
                return depthInLeft + 1;
            }
        }
        
        if (root.right != null) {
            int depthInRight = depthInTree(key, root.right);
            if (depthInRight != -1) {
                return depthInRight + 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing depth()/depthInTree() from Problem 4 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);
            tree.levelOrderPrint();
           
            
            //System.out.println( tree.depthIter(48) );
            
        
           // System.out.println( tree.depthIter(44) );
            //System.out.println( tree.depthIter(30) );

             
            int results = tree.depthIter(28);
           
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
                           
        /*
         * Add at least two unit tests for each method from Problem 6.
         * Test a variety of different cases. 
         * Follow the same format that we have used above.
         */
        
        System.out.println("--- Testing numEvenKeysRec()/numEvenKeys() from Problem 4 ---");
        System.out.println();
        System.out.println("(1) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);

            int results = tree.numEvenKeys();
           
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(5);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 5);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing deleteMax() from Problem 4 ---");
        System.out.println();
        System.out.println("(2) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);
  
            int results = tree.deleteMax();
            
            tree.levelOrderPrint();
           
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(80);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 80);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        

        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing numEvenKeysRec()/numEvenKeys() from Problem 4 ---");
        System.out.println();
        System.out.println("(2) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);
            
            tree.deleteMax();

            int results = tree.numEvenKeys();
           
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing deleteMax() from Problem 4 ---");
        System.out.println();
        System.out.println("(4) Testing on tree from Problem 3, depth of 28 node");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);
            tree.deleteMax();
            int results = tree.deleteMax();
            
            tree.levelOrderPrint();
           
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(62);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 62);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        

        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing depthIter from Problem 9 since the autograder has an issue ---");
        System.out.println();
        
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {41,13};
            
            tree.insertKeys(keys);
            tree.levelOrderPrint();
           
           int results = tree.depthIter(33);
           
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == -1);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("--- Testing PostOrderIterator from Problem 10 ---");
        System.out.println();

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {44, 35, 53, 23, 48, 62, 28, 57, 80};
            
            tree.insertKeys(keys);
            //tree.levelOrderPrint();
            
            LinkedTreeIterator iter = tree.postorderIterator();
            String results = "";
            while (iter.hasNext()) {
                int key = iter.next();
                 results += key + " ";
                // do something with key
            }
           
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("2,3,1");
            
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        

        System.out.println();    // include a blank line between tests
        
       
    }
    }
