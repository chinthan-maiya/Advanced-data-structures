package tree;

public class Main 
{
	public static void main(String args[])
	{
		RBTree tree1=new RBTree();
		RBTree tree2=new RBTree();
		int a[]={1,2};
		tree1.insert(a);
		int b[]={14,12,11,13,16,15,17};
		//tree2.insert(b);
		tree2.setRoot(new Node(14, 2));
		tree2.getRoot().setLeft(new Node(12,1));
		tree2.getRoot().setRight(new Node(16,1));
		tree2.getRoot().getLeft().setLeft(new Node(11,2));
		tree2.getRoot().getLeft().setRight(new Node(13,2));
		tree2.getRoot().getRight().setLeft(new Node(15,2));
		tree2.getRoot().getRight().setRight(new Node(17,2));
		merge(tree1, tree2, a, b);
	}
	
	public static void merge(RBTree t1, RBTree t2, int a[],int b[])
	{
		Node L=t1.getRoot();
		Node R=t2.getRoot();
		int p=getBlackHeight(L);
		int q=getBlackHeight(R);
		RBTree mergedTree=new RBTree();
		Node node;
		int c=0;
		if(p<=q)
		{
			RemovalHelper helper=removeLargest(L,a);
			c=helper.getC();
			if(helper.isRoot())
				L=helper.getNode();
			
		}
		else 
		{
			RemovalHelper helper=removeSmallest(R,b);
			c=helper.getC();
			if(helper.isRoot())
				R=helper.getNode();
		}
		p=getBlackHeight(L);
		q=getBlackHeight(R);
		node=change(L,R,p,q,c);			
		mergedTree.setRoot(node);
		mergedTree.inorder();
		System.out.println("--");
		mergedTree.levelOrder();
	}
	public static Node getNodeWithBlackHeight(Node node, int p, int q)
	{
		Node temp=node;
		if(p==0||q==0)
			return null;
		if(p<=q)
		{
			while(temp!=null)
			{
				if(temp.getColour()==2)
				{
					if(q==p)
						return temp;
					q--;
				}
				temp=temp.getLeft();
			}
		}
		else 
		{
			while(temp!=null)
			{
				if(temp.getColour()==2)
				{
					if(q==p)
						return temp;
					p--;
				}
					temp=temp.getRight();
			}
		}
		return null;
	}
	public static int getBlackHeight(Node node)
	{
		Node temp=node;
		int h=0;
		while(temp!=null)
		{
			if(temp.getColour()==2)
				h++;
			temp=temp.getLeft();
		}
		return h;
	}
	public static RemovalHelper removeLargest(Node node, int a[])
	{
		Node temp=node;
		RemovalHelper helper=new RemovalHelper();
		while(true)
		{
			if(temp.getRight()==null)
			{
				if(temp!=node)
				{
					Node lNode=temp.getLeft();
					int v=temp.getVal();
					RBTree dTree=new RBTree();
					if(lNode==null)
					{
						int b[]=new int[a.length-1];
						
						int k=0;
						for(int i=0;i<b.length;i++)
						{
							if(a[k]!=v)
								b[i]=a[k++];
						}
						for(int i=0;i<b.length;i++)
						{
							dTree.insert(b[i]);
						}
						helper.setIsRoot(true);
						helper.setNode(dTree.getRoot());
					}
					else if(temp.getColour()==2)
						lNode.setColour(2);
					temp.getParent().setRight(lNode);
					helper.setIsRoot(false);
				}
				else 
				{
					node=temp.getLeft();
					if(node!=null)
						node.setColour(2);
					helper.setIsRoot(true);
					helper.setNode(node);
				}
				helper.setC(temp.getVal());
				return helper;
			}
			temp=temp.getRight();
		}
	}
	public static RemovalHelper removeSmallest(Node node, int b[])
	{
		Node temp=node;
		RemovalHelper helper=new RemovalHelper();
		while(true)
		{
			if(temp.getLeft()==null)
			{
				if(temp!=node)
				{
					Node rNode=temp.getRight();
					int v=temp.getVal();
					RBTree dTree=new RBTree();
					if(rNode==null)
					{
						int a[]=new int[b.length-1];
						
						int k=0;
						for(int i=0;i<a.length;i++)
						{
							if(b[k]!=v)
								a[i]=b[k++];
						}
						for(int i=0;i<a.length;i++)
						{
							dTree.insert(a[i]);
						}
						helper.setIsRoot(true);
						helper.setNode(dTree.getRoot());
					}
					if(temp.getColour()==2)
						rNode.setColour(2);
					temp.getParent().setLeft(rNode);
					helper.setIsRoot(false);
				}
				else 
				{
					node=temp.getRight();
					if(node!=null)
						node.setColour(2);
					helper.setIsRoot(true);
					helper.setNode(node);
				}
				helper.setC(temp.getVal());
				return helper;
			}
			temp=temp.getLeft();
		}
	}
	public static Node change(Node L, Node R, int p, int q, int c)
	{
		if(p<=q)
		{
			Node R1=getNodeWithBlackHeight(R,p,q);
			Node C=new Node(c, 1);
			if(R1==null)
			{
				RBTree someTree=new RBTree();
				someTree.setRoot(R);
				someTree.insert(c);
				return someTree.getRoot();
			}
			Node temp=R1.getParent();
			C.setLeft(L);
			C.setRight(R1);
			if(p==q)
			{
				C.setColour(2);
				return C;
			}
			else 
			{
				temp.setLeft(C);
				return balanceIt(C,p,q);
			}
		}
		else 
		{
			Node L1=getNodeWithBlackHeight(L,p,q);
			Node C=new Node(c, 1);
			if(L1==null)
			{
				RBTree someTree=new RBTree();
				someTree.setRoot(L);
				someTree.insert(c);
				return someTree.getRoot();
			}
			Node temp=L1.getParent();
			C.setLeft(L1);
			C.setRight(R);
			temp.setRight(C);
			return balanceIt(C,p,q);
		}
	}
	public static Node balanceIt(Node C,int p,int q)
	{
		if(C.getParent()==null)
		{
			if(C.getColour()==1)
				C.setColour(2);
			return C;
		}
		if(C.getParent().getColour()==2)
		{
			Node root=C;
			while(true)
			{
				if(root.getParent()==null)
					return root;
				root=root.getParent();
			}
		}
		else 
		{
			if(C.getParent().getParent()==null)
			{
				C.getParent().setColour(2);
				return C.getParent();
			}
			else 
			{
				Node parent=C.getParent();
				Node grandparent=parent.getParent();
				Node greatgrand=grandparent.getParent();	
				if(p<=q)
				{
					Node sibling=parent.getRight();
					C.setColour(2);
					parent.setLeft(C);
					parent.setRight(grandparent);
					parent.setParent(greatgrand);	
					grandparent.setLeft(sibling);
				}
				else 
				{
					Node sibling=parent.getLeft();
					C.setColour(2);
					parent.setRight(C);
					parent.setLeft(grandparent);
					parent.setParent(greatgrand);
					grandparent.setRight(sibling);
				}
				if(greatgrand==null)
					return parent;
				else if(p<=q)
					greatgrand.setLeft(parent);
				else 
					greatgrand.setRight(parent);
				return balanceIt(parent,p,q);
			}
		}
	}
}
