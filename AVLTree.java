import java.util.ArrayList;

public class AVLTree {


    //Class AVLTree.Node, used to construct the tree
    class AVLNode {
        String plantName;
        MyPriorityQueue plantList;
        int height;
        AVLNode left;
        AVLNode right;
        public AVLNode(String plantName, String Data) {
            this.plantName = plantName;
            this.plantList = new MyPriorityQueue();
            plantList.Enqueue(Data);
        }
        //Accessor and Modifier Methods for the Node Class
        public String getplantName() {
            return plantName;
        }

        public AVLNode getRight() {
            return right;
        }

        public AVLNode getLeft() {
            return left;
        }

        public int getHeight() {
            return height;
        }

        public MyPriorityQueue getplantList() {
            return plantList;
        }

        public void setplantName(String plantName) {
            this.plantName = plantName;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public AVLNode root = null;

    public AVLNode getRoot() {
        return root;
    }
    //Helper Insertion Method for the recursive insertion method
    public void insert(String plantName,String Data) {
        if(root == null) {
            root = new AVLNode(plantName, Data);
        } else{
            root = insert(root, plantName, Data);
        }
    }
    private AVLNode insert(AVLNode node, String plantName ,String Data) {
        if (node == null || isEmpty()) {
            return new AVLNode(plantName, Data);
        }
        if(node.plantName.equals(plantName)){
            node.plantList.Enqueue(Data);
        }
        int comparedValue = node.plantName.compareTo(plantName);
        if (comparedValue > 0) {
            node.left = insert(node.left, plantName, Data);
        } else if (comparedValue < 0) {
            node.right = insert(node.right, plantName, Data);
        }
        return rebalance(node);
    }
    //This method is used to search for a website name in the AVL tree
    public AVLNode find(String plantNeeded) {
        AVLNode current = root;
        while (current != null) {
            if (current.plantName.equals(plantNeeded)) {
                break;
            }
            current = current.plantName.compareTo(plantNeeded) < 0 ? current.right : current.left;
        }
        return current;
    }
    //This method is used to rebalance the tree if needed
    private AVLNode rebalance(AVLNode node) {
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (height(node.right.right) > height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        } else if (balance < -1) {
            if (height(node.left.left) > height(node.left.right)) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        return node;
    }
    //This method is used to rotate right when balance is required
    private AVLNode rotateRight(AVLNode node) {
        AVLNode left = node.left;
        AVLNode temp = left.right;
        left.right = node;
        node.left = temp;
        updateHeight(node);
        updateHeight(left);
        return left;
    }
    //This method is used to rotate left when balance is required
    private AVLNode rotateLeft(AVLNode node) {
        AVLNode right = node.right;
        AVLNode temp = right.left;
        right.left = node;
        node.right = temp;
        updateHeight(node);
        updateHeight(right);
        return right;
    }
    //This method updates the height attribute in the node's attributes
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    //This method is able to return the height of the node
    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }
    //This method checks if the tree needs balancing
    public int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }
    //This method is used to print the AVL Tree in-order
    public void Display(){
        printInorder(root);
    }
    public void printInorder(AVLNode root) {
        if(root != null) {

            printInorder(root.left);
            System.out.println(root.plantName + ",  City List " + root.plantList.toString());
            printInorder(root.right);
        }
    }
    //Auxillary method used to check if the tree is empty
    public boolean isEmpty(){
        return root == null;
    }
    public ArrayList<String> returnInorder(AVLNode root, ArrayList<String> plantNames) {
        if(root != null) {
            plantNames = returnInorder(root.left,plantNames);
            plantNames.add(root.plantName);
            plantNames= returnInorder(root.right,plantNames);
        }
        return plantNames;
    }
    public ArrayList<String> returnDisplay(){
        ArrayList<String>plantNames=new ArrayList<String>();
         return returnInorder(root,plantNames);
    }

}