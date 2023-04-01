/*final int width = 850;
final int height = 600;*//*


class Node {
    private int x, y;
    int item;
    private Node right, left;

    public Node(int n, int x, int y) {
        this.item = n;
        this.x = x;
        this.y = y;
        this.right = null;
        this.left = null;
    }
}

class Tree {
    private int n;
    // private Node[] treeArray;
    private Node[] treeArray;

    public Tree(int n) {
        this.n = n;
        this.treeArray = new Node[n];
    }

    public void fillArray() {
        int x, y;
        for (int i = 0; i < n; i++) {
            y = (int) (Math.floor(Math.log(i) / Math.log(2))) ;
            treeArray[i] = new Node(i + 1, y, y);
        }
    }

    public void inorder(Node v) {
        if (v == null) {
            return;
        }
        */
/*postorder(v.left);
        Node.drawNode();
        postorder(v.right);*//*

    }

    public void postorder(int i) {
        */
/*if (i == null) {
            return;
        }*//*

        */
/*postorder(v.left);
        postorder(v.right);
        Node.drawNode();*//*

    }

    public void preorder(Node v) {
        if (v == null) {
            return;
        }
        */
/*Node.drawNode();
        preorder(v.left);
        preorder(v.right);*//*

    }

    public void levelorder() {

    }
}

public class Izziv2 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // String type = args[1];
        Tree drevo = new Tree(n);
        drevo.fillArray();
        System.out.println(drevo);
        */
/*switch (type) {
            case "inorder": {
                tree.inorder();
                break;
            }
            case "postorder": {
                tree.postorder();
                break;
            }
            case "preorder": {
                tree.preorder();
                break;
            }
            case "levelorder": {
                tree.levelorder();
                break;
            }
            default: {
                System.out.println("Unknown operation!");
                break;
            }
        }*//*

    }
}

// node.left = i * 2 + 1
// node.right = i * 2 + 2
*/
