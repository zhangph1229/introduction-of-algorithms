package cn.edu.neu.zhangph.ch12;

import java.util.Stack;

public class BiTree {
	private BiTree leftTree;// 左子树
	private BiTree rightTree;// 右子树
	protected Object data;// 节点数据
	public final static int MAX = 40;
	BiTree[] elements = new BiTree[MAX];// 层次遍历时保存各个节点
	int front;// 层次遍历时队首
	int rear;// 层次遍历时队尾
	// 构造函数
	public BiTree() {
		// TODO Auto-generated constructor stub
	}
	public BiTree(Object data) {
		this.data = data;
	}
	public BiTree(Object data, BiTree lefTree, BiTree righTree) {
		this.data = data;
		this.leftTree = lefTree;
		this.rightTree = righTree;
	}
	// 递归前序遍历二叉树
	public void preOrder(BiTree tree) {
		if (tree != null) {
			visit(tree.data);
			preOrder(tree.leftTree);
			preOrder(tree.rightTree);
		}
	}
	// 非递归实现前序遍历
	public void iterativePreOrder(BiTree tree) {
		Stack<BiTree> stack = new Stack<BiTree>();
		if (tree != null) {
			stack.push(tree);
			while (!stack.isEmpty()) {
				tree = stack.pop();
				visit(tree.data);
				if (tree.rightTree != null)
					stack.push(tree.rightTree);
				if (tree.leftTree != null)
					stack.push(tree.leftTree);
			}
		}
	}
	// 递归中序遍历二叉树
	public void inOrder(BiTree tree) {
		if (tree != null) {
			inOrder(tree.leftTree);
			visit(tree.data);
			inOrder(tree.rightTree);
		}
	}
	// 非递归实现中序遍历
	public void iterativeInOrder(BiTree tree) {
		Stack<BiTree> stack = new Stack<BiTree>();
		while (tree != null) {
			while (tree != null) {
				if (tree.rightTree != null)
					stack.push(tree.rightTree);// 当前节点右子入栈
				stack.push(tree);// 当前节点入栈
				tree = tree.leftTree;
			}
			tree = stack.pop();
			while (!stack.empty() && tree.rightTree == null) {
				visit(tree.data);
				tree = stack.pop();
			}
			visit(tree.data);
			if (!stack.empty())
				tree = stack.pop();
			else
				tree = null;
		}
	}
	// 递归后序遍历二叉树
	public void postOrder(BiTree tree) {
		if (tree != null) {
			postOrder(tree.leftTree);
			postOrder(tree.rightTree);
			visit(tree.data);
		}
	}
	// 非递归实现后序遍历
	public void iterativePostOrder(BiTree tree) {
		BiTree tempTree = tree;
		Stack<BiTree> stack = new Stack<BiTree>();
		while (tree != null) {
			// 左子树入栈
			for (; tree.leftTree != null; tree = tree.leftTree)
				stack.push(tree);
			// 当前节点无右子或右子已经输出
			while (tree != null
					&& (tree.rightTree == null || tree.rightTree == tempTree)) {
				visit(tree.data);
				tempTree = tree;// 记录上一个已输出节点
				if (stack.empty())
					return;
				tree = stack.pop();
			}
			// 处理右子
			stack.push(tree);
			tree = tree.rightTree;
		}
	}
	// 层次遍历二叉树
	public void LayerOrder(BiTree tree) {
		elements[0] = tree;
		front = 0;
		rear = 1;
		while (front < rear) {
			try {
				if (elements[front].data != null) {
					System.out.print(elements[front].data + " ");
					if (elements[front].leftTree != null)
						elements[rear++] = elements[front].leftTree;
					if (elements[front].rightTree != null)
						elements[rear++] = elements[front].rightTree;
					front++;
				}
			} catch (Exception e) {
				break;
			}
		}
	}
	// 求二叉树的高度
	public static int height(BiTree tree) {
		if (tree == null)
			return 0;
		else {
			int leftTreeHeight = height(tree.leftTree);
			int rightTreeHeight = height(tree.rightTree);
			return leftTreeHeight > rightTreeHeight ? leftTreeHeight + 1
					: rightTreeHeight + 1;
		}
	}
	// 求data所对应结点的层数，如果对象不在树中,结果返回-1;否则结果返回该对象在树中所处的层次,规定根节点为第一层
	public int level(Object data) {
		int leftLevel, rightLevel;
		if (this == null)
			return -1;
		if (data == this.data)
			return 1;
		leftLevel = leftTree == null ? -1 : leftTree.level(data);
		rightLevel = rightTree == null ? -1 : rightTree.level(data);
		if (leftLevel < 0 && rightLevel < 0)
			return -1;
		return leftLevel > rightLevel ? leftLevel + 1 : rightLevel + 1;
	}
	// 求二叉树的结点总数
	public static int nodes(BiTree tree) {
		if (tree == null)
			return 0;
		else {
			int left = nodes(tree.leftTree);
			int right = nodes(tree.rightTree);
			return left + right + 1;
		}
	}
	// 求二叉树叶子节点的总数
	public static int leaf(BiTree tree) {
		if (tree == null)
			return 0;
		else {
			int left = leaf(tree.leftTree);
			int right = leaf(tree.rightTree);
			if (tree.leftTree == null && tree.rightTree == null)
				return left + right + 1;
			else
				return left + right;
		}
	}
	// 求二叉树父节点个数
	public static int fatherNodes(BiTree tree) {
		if (tree == null || (tree.leftTree == null && tree.rightTree == null))
			return 0;
		else {
			int left = fatherNodes(tree.leftTree);
			int right = fatherNodes(tree.rightTree);
			return left + right + 1;
		}
	}
	// 求只有一个孩子结点的父节点个数
	public static int oneChildFather(BiTree tree) {
		int left, right;
		if (tree == null || (tree.rightTree == null && tree.leftTree == null))
			return 0;
		else {
			left = oneChildFather(tree.leftTree);
			right = oneChildFather(tree.rightTree);
			if ((tree.leftTree != null && tree.rightTree == null)
					|| (tree.leftTree == null && tree.rightTree != null))
				return left + right + 1;
			else
				return left + right;/* 加1是因为要算上根节点 */
		}
	}
	// 求二叉树只拥有左孩子的父节点总数
	public static int leftChildFather(BiTree tree) {
		if (tree == null)
			return 0;
		else {
			int left = leftChildFather(tree.leftTree);
			int right = leftChildFather(tree.rightTree);
			if ((tree.leftTree != null && tree.rightTree == null))
				return left + right + 1;
			else
				return left + right;
		}
	}
	// 求二叉树只拥有右孩子的结点总数
	public static int rightChildFather(BiTree tree) {
		if (tree == null || tree.rightTree == null)
			return 0;
		else {
			int left = rightChildFather(tree.leftTree);
			int right = rightChildFather(tree.rightTree);
			if (tree.leftTree == null && tree.rightTree != null)
				return left + right + 1;
			else
				return left + right;
		}
	}
	// 计算有两个节点的父节点的个数
	public static int doubleChildFather(BiTree tree) {
		int left, right;
		if (tree == null)
			return 0;
		else {
			left = doubleChildFather(tree.leftTree);
			right = doubleChildFather(tree.rightTree);
			if (tree.leftTree != null && tree.rightTree != null)
				return (left + right + 1);/* 加1是因为要算上根节点 */
			else
				return (left + right);
		}
	}
	// 访问根节点
	public void visit(Object data) {
		System.out.print(data + " ");
	}
	// 将树中的每个节点的孩子对换位置
	public void exChange() {
		if (this == null)
			return;
		if (leftTree != null)
			leftTree.exChange();
		if (rightTree != null)
			rightTree.exChange();
		BiTree temp = leftTree;
		leftTree = rightTree;
		rightTree = temp;
	}
	//递归求所有结点的和
	public  static int getSumByRecursion(BiTree tree){
		if(tree==null){
			return 0;
		}
		else{
			int left=getSumByRecursion(tree.leftTree);
			int right=getSumByRecursion(tree.rightTree);
			return Integer.parseInt(tree.data.toString())+left+right;
		}
		
	}
	//非递归求二叉树中所有结点的和
	public static int getSumByNoRecursion(BiTree tree){
		Stack<BiTree> stack = new Stack<BiTree>();
		int sum=0;
		if(tree!=null){
			stack.push(tree);
			while(!stack.isEmpty()){
				tree=stack.pop();
				sum+=Integer.parseInt(tree.data.toString());
				if(tree.leftTree!=null)
				    stack.push(tree.leftTree);
				if(tree.rightTree!=null)
					stack.push(tree.rightTree);
			}
			
		}
		return sum;
		
	}
}
