package tree;

public class Node
{
	private int val;
	private Node left, right, parent;
	private int colour;
	
	public Node(int val, int colour) 
	{
		this.val = val;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.colour = colour;
	}
	public int getVal()
	{
		return val;
	}
	public void setVal(int val) 
	{
		this.val = val;
	}
	public Node getLeft() 
	{
		return left;
	}
	public void setLeft(Node left) 
	{
		this.left = left;
		if(left!=null)
			left.setParent(this);
	}
	public Node getRight() 
	{
		return right;
	}
	public void setRight(Node right) 
	{
		this.right = right;
		if(right!=null)
			right.setParent(this);
	}
	public Node getParent() 
	{
		return parent;
	}
	public void setParent(Node parent) 
	{
		this.parent = parent;
	}
	public int getColour() 
	{
		return colour;
	}
	public void setColour(int colour) 
	{
		this.colour = colour;
	}
}
