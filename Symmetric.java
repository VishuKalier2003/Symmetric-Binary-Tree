/* Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center)...
 * Eg 1: Node Array = [1, 2, -1, -1, 2, -1, -1]                    Output = True (Symmetric)
 * Eg 2: Node Array = [1, 2, -1, 4, -1, -1, 2, 4, -1, -1, -1]      Output = False (Not Symmetric)
 */
import java.util.*;
public class Symmetric     // Superclass for Binary Tree Data Structure...
{
    public class Node     // Node class created for Node structure...
    {
        public int data;
        public Node left;
        public Node right;
        public Node(int value)
        {
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }
    public class BinaryTree       // Binary Tree properties and functions defined...
    {
        static int index = -1;     // static index variable...
        public Node InsertNode(int nodes[])
        {
            index++;
            if(nodes[index] == -1)
                return null;
            Node node = new Node(nodes[index]);
            node.left = InsertNode(nodes);
            node.right = InsertNode(nodes);
            return node;
        }
        public void ShowTree(Node root)
        {
            if(root == null)   // If Tree is empty nothing to show
                return;            // Control moves out of the function
            Node temp = root;
            ShowTree(root.left);     // Recursive Call
            System.out.println("Node: ");
            if(root.left != null)    // If left subtree is not empty
                System.out.println("\t"+temp.data+" ---> "+temp.left.data+"\t Left ( Occupied )");
            else      // If left subtree is empty
                System.out.println("\t"+temp.data+"\t\t Left ( Empty )");
            if(root.right != null)   // If right subtree is not empty
                System.out.println("\t"+temp.data+" ---> "+temp.right.data+"\t Right ( Occupied ) ");
            else     // If right subtree is empty
                System.out.println("\t"+temp.data+"\t\t Right ( Empty )");
            ShowTree(root.right);     // Recursive Call
        }
        protected static Queue<Integer> queue = new LinkedList<Integer>();    // static queue...
        protected static Queue<Integer> queue1 = new LinkedList<Integer>();   // static queue...
        public Queue<Integer> PreOrder(Node root)
        {   // Updating Queue for PreOrder...
            if(root == null)
                return queue;
            queue.add(root.data);     // Root Node...
            PreOrder(root.left);      // Left Child Node...
            PreOrder(root.right);     // Right Child Node...
            return queue;
        }
        public Queue<Integer> PostOrder(Node root)
        {   // Updating Queue for PostOrder...
            if(root == null)
                return queue1;
            queue1.add(root.data);    // Root Node...
            PostOrder(root.right);    // Left Child Node...
            PostOrder(root.left);     // Right Child Node...
            return queue1;
        }
        public boolean Symmetry(Node root)
        {
            Node temp = root;
            queue = PreOrder(root);      // Function call...
            queue1 = PostOrder(temp);    // Function call...
            while(!queue.isEmpty())
            {    // If Queue are same whether from left or right traversal...
                if(Objects.equals(queue.peek(), queue1.peek()))
                    System.out.println("Node Checked : "+queue.peek());
                else    // If Queue are different at some point...
                    return false;
                queue.remove();     // Removing checked Node from PreOrder Queue...
                queue1.remove();    // Removing checked Node from PostOrder Queue...
            }
            return true;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter Height of the Binary Tree (null nodes included) : ");
        x = sc.nextInt();
        int node[] = new int[(int)(Math.pow(2, x)-1)];  // A complete binary tree has 2^h - 1 nodes... 
        for(int i = 0; i < node.length; i++)
        {
            System.out.print("Enter data of "+(i+1)+" Node : ");
            node[i] = sc.nextInt();
        }
        Symmetric symmetric = new Symmetric();     // Superclass object created...
        BinaryTree binarytree = symmetric.new BinaryTree();    // Subclass object created...
        Node root = binarytree.InsertNode(node);    // root stores the Root Node of the Binary Tree...
        binarytree.ShowTree(root);
        System.out.println("The Binary Tree Provided is Symmetric : "+binarytree.Symmetry(root));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n) space...

/* DEDUCTIONS:- 
 * 1. We traverse the Binary Tree in preorder and postorder fashion and store elements in queue...
 * 2. We check Queue head from every iteration of both preorder and postorder queue...
*/