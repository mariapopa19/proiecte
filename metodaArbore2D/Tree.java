package metodaArbore2D;

public class Tree {
	Node root;
	int cd = 0;
	int size = 2;
	
	
	public Tree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(int []x) {
		root = insert(x, root, cd);
	}
	
	public Node insert(int []x, Node t, int cd) {
	// cd = 0 -> Vertical
		if(t == null)
			t = new Node(x, cd);
		else if(x[cd] < t.data[cd])
			t.left = insert(x, t.left, (cd + 1)%size);
		else
			t.right = insert(x, t.right, (cd + 1)%size);
		return t;
	}
	
	public void preorder() {
		preorder(root);
	}
	
	public void preorder(Node root) {
		if(root != null) {
			System.out.println("("+root.data[0]+","+root.data[1]+")" + root.t);
			System.out.println("Left: ");
			preorder(root.left);
			System.out.println("Right: ");
			preorder(root.right);
		}
	}
}
