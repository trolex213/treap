//Troy Zhang
import java.util.Random;
import java.util.Stack;
public class Treap<E extends Comparable<E>> {

    private Random priorityGenerator;
    private Node<E> root;

    private static class Node<E extends Comparable<E>> {
        public E data; // key for the search
        public int priority; // random heap priority
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority) {
            if (data == null) {
                throw new IllegalStateException();
            }

            this.data = data;
            this.priority = priority;
            this.left = this.right = null;

        }

        public Node<E> rotateRight() {
            Node<E> current = this;
            Node<E> root = current.left;
            current.left = root.right;
            root.right = current;
            return root;
        }

        public Node<E> rotateLeft() {
            Node<E> current = this;
            Node<E> root = current.right;
            current.right = root.left;
            root.left = current;
            return root;
        }

        public String toString(){
            return "( key = " + data + " , priority = " + priority + ")";
        }

    }

    public Treap() {}

    public Treap(long seed) {
        this.priorityGenerator = new Random(seed);
    }

    boolean add(E key) {
        return add(key, this.priorityGenerator.nextInt());
    }

    boolean add(E key, int priority) {

        Stack<Node<E>> nodeStack = new Stack<>();

        Node<E> newNode = new Node<>(key, priority);

        // base case: check if tree is empty
        if (root == null) {
            root = newNode;
            return true;
        }

        // create pointer node to start traversing from the root
        Node<E> x = root;

        while (x != null) {

            nodeStack.push(x);

            if (key.compareTo(x.data) < 0) {
                x = x.left;
            } else if (key.compareTo(x.data) > 0) {
                x = x.right;
            } else {
                return false;
            } // key already exists, no duplicate allowed.
        }

        Node<E> y = nodeStack.peek();

        if (key.compareTo(y.data) < 0) { //compareTo operator: >0 means greater | <0 means less than
            y.left = newNode;
        } else {
            y.right = newNode;
        }
        reheap(nodeStack, newNode);
        return true;
    }


    public void reheap(Stack<Node<E>> nodeStack, Node<E> current) {
        if (nodeStack.isEmpty()) {
            return;
        }

        Node<E> parent = nodeStack.pop();

        while (current.priority > parent.priority) {
            //perform rotation:
            Node<E> newCurrent;
            if (current.data.compareTo(parent.data) < 0) {
                newCurrent = parent.rotateRight();
            } else {
                newCurrent = parent.rotateLeft();
            }

            current = newCurrent;

            if (nodeStack.isEmpty()) {
                root = current;
                return;
            }

            parent = nodeStack.pop();

            if (current.data.compareTo(parent.data) < 0) {
                parent.left = current;
            } else {
                parent.right = current;
            }
        }

    }

    private Node<E> deleteNode(Node<E> root, E key) {
        // the node we want to delete will get trickled down the tree until it becomes a leaf node, and then we will set the node to null, deleting it.
        //base case: the key is not found in the tree
        if (root == null) {
            return null; // how we will delete the Node.
        }

        //if key is less than the root node, perform recursion on the left subtree.
        if (key.compareTo(root.data) < 0) {
            root.left = deleteNode(root.left, key);
        }
        // if key is more than the root node, perform recrusion on the right subtree.
        else if (key.compareTo(root.data) > 0) {
            root.right = deleteNode(root.right, key);
        }
        // if key is found
        else {
            // case 1: the node to be deleted has no children nodes (leaf node)
            if (root.left == null && root.right == null) {
                root = null;
            }
            // case 2: the node to be deleted has two children nodes
            else if (root.left != null && root.right != null) {

                if (root.left.priority < root.right.priority) {
                    root = root.rotateLeft();
                    root.left = deleteNode(root.left, key);
                } else {
                    root = root.rotateRight();
                    root.right = deleteNode(root.right, key);
                }
            }
            // case 3: the node to be deleted has only one child
            else {
                Node<E> child;

                if (root.left == null) {
                    child = root.right;
                } else {
                    child = root.left;
                }
                root = child;
            }
        }
        return root;


    }

    boolean delete(E key){
        Stack<Node<E>> nodeStack = new Stack<>();

        if (root == null){
            return false;
        }

        // Pointer to start traversing from the root node
        // To search for the node to be deleted.
        Node<E> x = root;

        while (x != null){
            nodeStack.push(x);

            if (key.compareTo(x.data) < 0){
                x = x.left;
            }
            else if (key.compareTo(x.data) > 0){
                x = x.right;
            }
            else { // key is found
                Node<E> current = nodeStack.pop(); // removing the node to be deleted from the Stack.
                Node<E> parent = nodeStack.peek(); // get the parent node of the node to be removed.

                if (current.data.compareTo(parent.data) < 0) {
                    parent.left = deleteNode(current, current.data);
                } else {
                    parent.right = deleteNode(current, current.data);
                }

                return true;
            }
        }

        return false;

    }

    private boolean find(Node<E> root, E key){

        if (root == null){
            return false;
        }

        // Pointer to start traversing from the root node
        // To search for the node to be deleted.
        Node<E> x = root;

        while (x != null){
            if (key.compareTo(x.data) < 0){
                x = x.left;
            }
            else if (key.compareTo(x.data) > 0){
                x = x.right;
            }
            else { // key is found
                return true;
            }
        }

        return false;
    }

    boolean find(E key){
        return find(root, key);
    }

    private StringBuilder preOrderTraversal(Node<E> root, StringBuilder result, int depth){
        // For J-Unit testing indetation of tree.
        // Print indentation based on depth.
        for (int i = 0; i < depth; i++){
            result.append("   ");
        }

        if (root == null){
            return result.append("   null");
        }
        else {
            result.append("   ").append(root.toString());
            result.append("\n   ").append(preOrderTraversal(root.left, new StringBuilder(""), depth + 1));
            result.append("\n   ").append(preOrderTraversal(root.right, new StringBuilder(""), depth + 1));
        }

        return result;
    }


    public String toString() {
        StringBuilder result = new StringBuilder("");
        return String.valueOf(preOrderTraversal(root, result, 0));
    }

}
