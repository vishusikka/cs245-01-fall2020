/*
* Binary search tree data structure
* @author : 
*/

public class BST<T> {
    /*
    * The root of the BST
    */
    Node<T> root;

    /*
    * Node class for a BST
    */
    private class Node<T>
    {
        Comparable<T> data;
        Node<T> left;
        Node<T> right;
        int instance;

        Node(Comparable<T> item)
        {
            data = item;
            instance = 1;
        }
    }

    public BST()
    {
        root = null;
    }

    /*
    * Find function that finds an item in the BST
    * @param item to be found
    * @return boolean if the item was found
    */
    public boolean find(Comparable<T> item)
    {
        return find(item, root);
    }

    /*
    * Function override of the find function
    * @param item to be found
    * @param node the current node you are at
    * @return boolean if the item was found
    */
    private boolean find(Comparable<T> item, Node<T> node)
    {
        //TODO FILL IN FUNCTION
        //throw new UnsupportedOperationException("Not yet implemented");
        if (node == null) {
            return false;
        }
        int it =Integer.parseInt(item.toString());
        int data = Integer.parseInt(node.data.toString());
        if (it == data) {
            return true;
        }
        if (it  < data) {
            return find(item, node.left);
        } else if (it > data) {
            return find(item, node.right);
        }

        return false;

    }

    /*
    * Insert an item to the tree
    * @param item to insert
    */
    public void insert(Comparable<T> item)
    {
        root = insert(item, root);
    }

    /*
    * Helper function for insert
    * @param item to add
    * @param node you are at
    * @return node you traverse to
    */
    private Node<T> insert(Comparable<T> item, Node<T> node)
    {
        //TODO FILL IN FUNCTION
        if (node == null) {
            Node<T> n = new Node<T>(item);
            node = n;
            return n;
        }
        int it =Integer.parseInt(item.toString());
        int data =Integer.parseInt(node.data.toString());
        if (it < data) {
            node.left = insert(item, node.left);
        } else if (it > data) {
            node.right = insert(item, node.right);
        }
        return node;
    }

    /*
    * Function for deletion of a node
    * @param item to delete
    */
    public void delete(Comparable<T> item)
    {
        root = delete(item, root);
    }

    /*
    * Helper function for deletion of a node
    * @param item to delete
    * @param node you are at
    * @return node you traverse to
    */
    private Node<T> delete(Comparable<T> item, Node<T> node)
    {
        //TODO FILL IN FUNCTION
        if (node == null) {
            return null;
        }
        if (item.compareTo((T) node.data) > 0) {
            node.right = delete(item, node.right);
            return node;
        } else if (item.compareTo((T) node.data) < 0) {
            node.left = delete(item, node.left);
            return node;
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.right.left == null) {
                    node.data = node.right.data;
                    node.right = node.right.left;
                } else {
                    node.data = removeSmallest(node.right);
                }
                return node;
            }
        }
    }

    public Comparable<T> removeSmallest(Node<T> node) {
        if (node.left.left == null) {
            Comparable<T> smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        } else {
            return removeSmallest(node.left);
        }
    }

    /*
    * Function to find the range sum of the binary tree
    * @param L the left bound
    * @param R the right bound
    * @return The sum of the range in the binary tree
    */
    int result;
    public int rangeSum(int L, int R)
    {
        //TODO FILL IN FUNCTION
        //throw new UnsupportedOperationException("Not yet implemented");
        result = 0;
        rangeSum(root, L, R);
        return result;
    }
    public void rangeSum(Node<T> node, int L, int R){
        if(node == null){
            return;
        }else{
            int data = Integer.parseInt(node.data.toString());
            if(data <= R && data >= L){
                result += data;
            }
            if(data > L){
                rangeSum(node.left, L, R);
            }
            if (data < R) {
                rangeSum(node.right, L, R);
            }
        }

    }

    /*
    * Function to print the Binary tree
    */
    public void print()
    {
        print(root);
        System.out.println();
    }

    /*
    * Helper Function to print the Binary tree
    * @param root the root of the tree
    */
    private void print(Node<T> root)
    {
        //TODO FILL IN FUNCTION
        // in order traversal
        if (root != null) {

            print(root.left);
            System.out.print(root.data + " ");
            print(root.right);

        }
    }

}