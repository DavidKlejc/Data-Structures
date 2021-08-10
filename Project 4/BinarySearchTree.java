/**
 *  This BinarySearchTree class will instantiate a binary search tree. It
 *  will hold all of the nodes in the tree and use several methods to insert,
 *  find, delete, and print nodes in the tree. 
 * 
 * @author Dawid Klejc
 * @version 3/27/2021
*/
public class BinarySearchTree {
	
	public Node root;
	Node minNode;

	/**
	 * This is a no parameter constructor that creates an empty tree. 
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * This will insert a node into the proper position in the  
	 * tree based on state name. 
	 * @param name the name of the current state.
	 * @param DR the covid-19 death rate of the current state. 
	 */
	public void insert(String name, double DR) {
		
		Node newNode = new Node();
		newNode.stateName = name;
		newNode.deathRate = DR;
		
		if(root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if(name.compareToIgnoreCase(current.stateName) < 1) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * This will search the tree for the state of the given name.
	 * @param name the current name of the state
	 * @return the states' current death rate if found, or -1 if not found
	 */
	public double find(String name) {
		
		Node current = root; 
		
		while(current != null && current.stateName.compareToIgnoreCase(name) != 0) {
			if(name.compareToIgnoreCase(current.stateName) < 1) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if(current == null) {
				return -1;
			}
		}
		return current.deathRate;
	}
	
	/**
	 * This will find and delete a given state from the tree.
	 * @param name the current name of the state 
	 */
	public void delete(String name) {
		
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		// Find node to be deleted
		while(current.stateName.compareToIgnoreCase(name) != 0) {
			parent = current;
			if(name.compareToIgnoreCase(current.stateName) < 0) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			} 
			if(current == null) {
				return;
			}
		}
		// Node to be deleted has no children
		if(current.leftChild == null && current.rightChild == null) {
			if(current == root) {
				root = null;
			} else if(isLeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		// Node to be deleted has one child
		} else if(current.rightChild == null) {
			if(current == root) {
				root = current.leftChild;
			} else if(isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		} else if(current.leftChild == null) {
			if(current == root) {
				root = current.rightChild;
			} else if(isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		// Node to be deleted has two children
		} else {
			Node successor = getSuccessor(current);
			if(current == root) {
				root = successor;
			} else if(isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}
		
	}
	
	/**
	 * This will traverse the tree in order using an inorder traversal (LNR) and print each node.  
	 * @param localRoot the local root of the binary search tree.
	 */
	public void printInorder(Node localRoot) {
		
		if(localRoot != null) {
			printInorder(localRoot.leftChild);
			localRoot.displayNode();
			printInorder(localRoot.rightChild);
		}
	}
	
	/**
	 * This will traverse the tree in a preorder traversal (NLR) and print each node. 
	 * @param localRoot the local root of the binary search tree.
	 */
	public void printPreorder(Node localRoot) {
		
		if(localRoot != null) {
			localRoot.displayNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
	}
	
	/**
	 * This will traverse the tree in a postorder traversal (LRN) and print each node. 
	 * @param localRoot the local root of the binary search tree.
	 */
	public void printPostorder(Node localRoot) {
		
		if(localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.displayNode();
		}
	}
	
	/**
	 * This will find and return the next inorder successor of a node.
	 * @param delNode the node to be deleted from the binary search tree.
	 * @return the successor node
	 */
	private Node getSuccessor(Node delNode) {
		
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		
		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		
		if(successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
	/**
	 * This will print the path for a given state name. The path is defined as
	 * the sequence of nodes (state names in this case), from root to the node. 
	 * @param name the current name of the state 
	 */
	public void printPathToNode(String name) {
		
		Node current = root; 
				
		System.out.printf(root.stateName + " -> ");
		while(current != null && current.stateName.compareToIgnoreCase(name) != 0) {
			if(name.compareToIgnoreCase(current.stateName) < 1) {
				current = current.leftChild;
				System.out.printf("%s", current.stateName);
			} else {
				current = current.rightChild;
				System.out.printf("%s", current.stateName);
			}
			if(current.stateName.compareToIgnoreCase(name) != 0) {
				System.out.printf(" -> ", current.stateName);
			} else {
				continue;
			}
		}
	}
}
