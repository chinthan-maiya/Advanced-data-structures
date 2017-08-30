package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class RBTree 
{
	private Node root;
	public RBTree()
	{
		root=new Node(-1, 2);
	}
	public Node getRoot() 
	{
		return root;
	}
	public void setRoot(Node root) 
	{
		this.root = root;
	}
	public void insert(int a[])
	{
		int l=a.length;
		for(int i=0;i<l;i++)
			insert(a[i]);
	}
	public void insert(int data)
	{
		Node n=null;
		if(root.getVal()==-1)
			root.setVal(data);
		else 
		{
			Node temp=root;
			while(true)
			{
				if(data<temp.getVal())
				{
					if(temp.getLeft()==null)
					{
						n=new Node(data, 1);
						temp.setLeft(n);
						break;
					}
					else 
						temp=temp.getLeft();
				}
				else 
				{
					if(temp.getRight()==null)
					{
						n=new Node(data, 1);
						temp.setRight(n);
						break;
					}
					else 
						temp=temp.getRight();
				}
			}
			fixViolation(n);
		}
	}
	public void fixViolation(Node pt)
	{
		Node parent_pt = null;
	    Node grand_parent_pt = null;
	 
	    while ((pt != root) && (pt.getColour()!=2) &&
	           (pt.getParent().getColour() == 1))
	    {
	 
	        parent_pt = pt.getParent();
	        grand_parent_pt = parent_pt.getParent();
	
	        if (parent_pt == grand_parent_pt.getLeft())
	        {
	 
	            Node uncle_pt = grand_parent_pt.getRight();
	            if (uncle_pt!= null && uncle_pt.getColour() == 1)
	            {
	                grand_parent_pt.setColour(1);;
	                parent_pt.setColour(2);
	                uncle_pt.setColour(2);
	                pt = grand_parent_pt;
	            }
	 
	            else
	            {
	           
	                if (pt == parent_pt.getRight())
	                {
	                    rotateLeft(parent_pt);
	                    pt = parent_pt;
	                    parent_pt = pt.getParent();
	                }
	 
	          
	                rotateRight(grand_parent_pt);
	                swapColour(parent_pt, grand_parent_pt);
	                pt = parent_pt;
	            }
	        }
	        else
	        {
	            Node uncle_pt = grand_parent_pt.getLeft();
	 
	            
	            if ((uncle_pt != null) && (uncle_pt.getColour()==1))
	            {
	                grand_parent_pt.setColour(1);
	                parent_pt.setColour(2);
	                uncle_pt.setColour(2);
	                pt = grand_parent_pt;
	            }
	            else
	            {
	              
	                if (pt == parent_pt.getLeft())
	                {
	                    rotateRight(parent_pt);
	                    pt = parent_pt;
	                    parent_pt = pt.getParent();
	                }
	 
	                rotateLeft(grand_parent_pt);
	                swapColour(parent_pt, grand_parent_pt);
	                pt = parent_pt;
	            }
	        }
	    }
	 
	    root.setColour(2);
	}
	public void rotateLeft(Node pt)
	{
	    Node pt_right = pt.getRight();
	    pt.setRight(pt_right.getLeft());
	 
	    if (pt.getRight()!= null)
	        pt.getRight().setParent(pt);
	 
	    pt_right.setParent(pt.getParent());
	 
	    if (pt.getParent()== null)
	        root = pt_right;
	 
	    else if (pt == pt.getParent().getLeft())
	        pt.getParent().setLeft(pt_right);
	 
	    else
	        pt.getParent().setRight(pt_right);
	 
	    pt_right.setLeft(pt);
	    pt.setParent(pt_right);
	}
	 
	public void rotateRight(Node pt)
	{
	    Node pt_left = pt.getLeft();
	    pt.setLeft(pt_left.getRight());
	    if (pt.getLeft() != null)
	        pt.getLeft().setParent(pt);
	 
	    pt_left.setParent(pt.getParent());
	 
	    if (pt.getParent() == null)
	        root = pt_left;
	 
	    else if (pt == pt.getParent().getLeft())
	        pt.getParent().setLeft(pt_left);
	 
	    else
	        pt.getParent().setRight(pt_left);
	 
	    pt_left.setRight(pt);
	    pt.setParent(pt_left);
	}
	public void swapColour(Node a, Node b)
	{
		int t=a.getColour();
		a.setColour(b.getColour());
		b.setColour(t);
	}
	public void inorder()
	{
		Node temp=root;
		rec(temp);
	}
	public void rec(Node temp)
	{
		if(temp==null)
			return;
		rec(temp.getLeft());
		System.out.println(temp.getVal()+"-"+temp.getColour());
		rec(temp.getRight());
	}
	public void levelOrder()
	{
		Node temp=root;
		Queue<Node> q=new ArrayDeque<Node>();
		q.add(temp);
		while(!q.isEmpty())
		{
			temp=q.peek();
			q.poll();
			System.out.println(temp.getVal());
			Node l=temp.getLeft();
			Node r=temp.getRight();
			if(l!=null)
				q.add(l);
			if(r!=null)
				q.add(r);
		}
	}

}