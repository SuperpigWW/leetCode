import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections support indexing like Lists, so a regular
     * for loop will not work here. However, all Collections support the
     * enhanced for loop.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot initialize BST with null collection.");
        }
        
        for (T element : data) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot add null data to BST.");
            }
            add(element);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to BST.");
        }
        
        root = addHelper(root, data);
    }

    /**
     * Private helper method for adding data to the BST recursively.
     *
     * @param node the current node in the recursion
     * @param data the data to add
     * @return the node after the addition
     */
    private BSTNode<T> addHelper(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }
        
        int comparison = data.compareTo(node.getData());
        
        if (comparison < 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        } else if (comparison > 0) {
            node.setRight(addHelper(node.getRight(), data));
        }
        
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from BST.");
        }
        
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Private helper method for removing data from the BST recursively.
     *
     * @param node the current node in the recursion
     * @param data the data to remove
     * @param dummy a dummy node to store the removed data
     * @return the node after the removal
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("Data not found in BST.");
        }
        
        int comparison = data.compareTo(node.getData());
        
        if (comparison < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (comparison > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;
            
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null && node.getRight() != null) {
                return node.getRight();
            } else {
                BSTNode<T> successorDummy = new BSTNode<>(null);
                node.setRight(removeSuccessor(node.getRight(), successorDummy));
                node.setData(successorDummy.getData());
            }
        }
        
        return node;
    }

    /**
     * Private helper method to find and remove the successor (leftmost node in right subtree).
     *
     * @param node the current node in the recursion
     * @param dummy a dummy node to store the successor data
     * @return the node after the successor is removed
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(removeSuccessor(node.getLeft(), dummy));
            return node;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data in BST.");
        }
        
        return getHelper(root, data);
    }

    /**
     * Private helper method for getting data from the BST recursively.
     *
     * @param node the current node in the recursion
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    private T getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Data not found in BST.");
        }
        
        int comparison = data.compareTo(node.getData());
        
        if (comparison < 0) {
            return getHelper(node.getLeft(), data);
        } else if (comparison > 0) {
            return getHelper(node.getRight(), data);
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data in BST.");
        }
        
        return containsHelper(root, data);
    }

    /**
     * Private helper method for checking if data exists in the BST recursively.
     *
     * @param node the current node in the recursion
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false otherwise
     */
    private boolean containsHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        
        int comparison = data.compareTo(node.getData());
        
        if (comparison < 0) {
            return containsHelper(node.getLeft(), data);
        } else if (comparison > 0) {
            return containsHelper(node.getRight(), data);
        } else {
            return true;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    /**
     * Private helper method for pre-order traversal.
     *
     * @param node the current node in the recursion
     * @param result the list to store the traversal
     */
    private void preorderHelper(BSTNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.getData());
            preorderHelper(node.getLeft(), result);
            preorderHelper(node.getRight(), result);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    /**
     * Private helper method for in-order traversal.
     *
     * @param node the current node in the recursion
     * @param result the list to store the traversal
     */
    private void inorderHelper(BSTNode<T> node, List<T> result) {
        if (node != null) {
            inorderHelper(node.getLeft(), result);
            result.add(node.getData());
            inorderHelper(node.getRight(), result);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    /**
     * Private helper method for post-order traversal.
     *
     * @param node the current node in the recursion
     * @param result the list to store the traversal
     */
    private void postorderHelper(BSTNode<T> node, List<T> result) {
        if (node != null) {
            postorderHelper(node.getLeft(), result);
            postorderHelper(node.getRight(), result);
            result.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            BSTNode<T> current = queue.remove();
            result.add(current.getData());
            
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        
        return result;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Private helper method for calculating the height of a node.
     *
     * @param node the current node in the recursion
     * @return the height of the node
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }
}
