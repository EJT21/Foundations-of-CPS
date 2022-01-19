import java.util.Scanner;
import java.io.*;
class SplayTree<E extends Comparable<E>> 
{
    static int count=9;
    public static void main(String[] args)throws IOException
    {
        SplayTree<Integer> tree=new SplayTree<Integer>();
        Scanner kb=new Scanner(System.in);
        Scanner in=new Scanner(new File("in.txt"));
        while(in.hasNext())
        {
            int x=in.nextInt();
            tree.add(x);
        }
        tree.printSideways();
        System.out.println("SplayTree simulation started, press <Enter> to continue");
        String s=kb.nextLine();
        msg();
        while(!s.equals("Quit"))
        { 
            s=kb.nextLine();
            String[] t=s.split(" ");
            if(t[0].equals("S"))
            {
                int x=Integer.parseInt(t[1]);
                tree.splay(x);
                tree.printSideways();
                System.out.println("=============================");
                msg();  
            }
            else if(t[0].equals("F"))
            {
                int x=Integer.parseInt(t[1]);
                tree.search(x);
                tree.printSideways();
                System.out.println("=============================");
                msg();  
            }
            else if(t[0].equals("I"))
            {
                int x=Integer.parseInt(t[1]);
                tree.insert(x);
                tree.printSideways();
                System.out.println("=============================");
                msg();  
            }
            else if(t[0].equals("D"))
            {
                int x=Integer.parseInt(t[1]);
                tree.delete(x);
                tree.printSideways();
                System.out.println("=============================");
                msg();  
            }
            
        }
        System.out.print("End of the program, Goodbye");
    }
    public BTNode<E> root;
    BTNode<E> rightTurn=null;//for precessor method
    public void search(int data)//need to traverse tree
    {
        BTNode<E> find=search(data,this.root);
        splay(data);
        if(find==null)//not in the tree
        {
            System.out.println("Search is unsuccessful");
            return;
        }
        if(find.data==data)
        {
            System.out.println("Search is successful");
        }
        else
        {
            System.out.println("Search is unsuccessful");
        }
    }
    public BTNode<E> search(int data, BTNode<E> r)
    {
        if(r==null || data==r.data)//if we find, we return it
        {
            return r;
        }
        if(data<r.data)//go to left if its less, value must be less
        {
            return search(data,r.left);
        }
        return search(data,r.right);//go to right if its greater
    }
    public SplayTree()
    {
        root=null;
    }
    public void leftRotate(BTNode<E> r)
    {
        BTNode<E> temp=r.right;
        r.right=temp.left;
        if(temp.left!=null)
        {
            temp.left.parent=r;
        }
        temp.parent=r.parent;
        if(r.parent==null)
        {
            this.root=temp;
        }
        else if(r==r.parent.left)
        {
            r.parent.left=temp;
        }
        else
        {
            r.parent.right=temp;
        }
        temp.left=r;
        r.parent=temp;
    }
    public void rightRotate(BTNode<E> r)
    {
        BTNode<E> temp=r.left;
        r.left=temp.right;
        if(temp.right!=null)
        {
            temp.right.parent=r;
        }
        temp.parent=r.parent;
        if(r.parent==null)
        {
            this.root=temp;//
        }
        else if(r==r.parent.right)
        {
            r.parent.right=temp;
        }
        else
        {
            r.parent.left=temp;
        }
        temp.right=r;
        r.parent=temp;
    }
    public boolean isEmpty()
    {
        if(root==null)
            return true;
        return false;
    }
    public void setRoot(int data)
    {
        root=new BTNode<E>(data);
    }
    public void add(int data)
    {
        if(contains(data))
        {
            System.out.println("Duplicate keys");
            splay(data);
            return;
        }
        BTNode<E> temp=new BTNode<E>(data);
        BTNode<E> r=this.root;
        BTNode<E> placeHolder=null;
        while(r!=null)//placing the Node in the correct spot
        {//must preserve Binary tree property
            placeHolder=r;
            if(temp.data<r.data)
            {
                r=r.left;
            }
            else
            {
                r=r.right;
            }
        }
        temp.parent=placeHolder;
        if(placeHolder==null)
        {
            root=temp;
        }
        else if(temp.data<placeHolder.data)
        {
            placeHolder.left=temp;
        }
        else
        {
            placeHolder.right=temp;
        }
    }
    public void splay(int x)
    {
        //These two are for if we are splaying at a node that we do not have in the tree
        System.out.println("Splay is done");
        if(!contains(x) && x>MIN(root).data)
        {
            BTNode<E> dad=findPrecessor(x);
            splay(dad);
        }
        else if(!contains(x) && x<MIN(root).data)
        {
            splay(MIN(root));
        }
        //if we splay at a node that is in the tree
        if(contains(x))
        {
            BTNode<E> dad=findPrecessor(x);
            splay(dad);
        }
    }
    public BTNode<E> findPrecessor(int x)
    {
        return findPrecessor(x,this.root);
    }
    public BTNode<E> findPrecessor(int x, BTNode<E> r)
    {
        while(r!=null)//Last right turn we make is the precessor, if equal this will still work
        {
            if(r.data<=x)
            {
                rightTurn=r;
                r=r.right;
            }
            else
            {
                r=r.left;
            }
        }
        return rightTurn;
    }
    public void splay(BTNode<E> r)
    {
        while(r.parent!=null)//this indicates we finished splaying
        {//we will do 6 checks of combinations
            if(r.parent.parent==null)//single rotation
            {
                if(r==r.parent.left)
                {
                    rightRotate(r.parent);//zig
                }
                else
                {
                    leftRotate(r.parent);//zag
                }
            }//Double rotations
            else if(r==r.parent.left && r.parent==r.parent.parent.left)
            {
                rightRotate(r.parent.parent);
                rightRotate(r.parent);// zig zig
            }
            else if(r==r.parent.right && r.parent==r.parent.parent.right)
            {
                leftRotate(r.parent.parent);
                leftRotate(r.parent);//zag zag
            }
            else if(r==r.parent.right && r.parent==r.parent.parent.left)
            {
                leftRotate(r.parent);//zig zag
                rightRotate(r.parent);
            }
            else
            {
                rightRotate(r.parent);//zag zig
                leftRotate(r.parent);
            }
        }
    }
    public boolean contains(int x)//Used for determining if we insert
    {
        return contains(x,root);
    }
    public boolean contains(int x,BTNode<E> r)
    {
        if(r==null)
            return false;
        if(r.data==x)
            return true;
        else if(r.data>x)
            return contains(x,r.left);
        else
            return contains(x,r.right);
    }
    public void insert(int data)
    {
        if(contains(data))
        {
            System.out.println("Duplicate keys");
            splay(data);
            return;
        }
        System.out.println("Key is inserted into the tree");
        BTNode<E> temp=new BTNode<E>(data);
        BTNode<E> r=this.root;
        BTNode<E> placeHolder=null;
        while(r!=null)//placing the Node in the correct spot
        {//must preserve Binary tree property
            placeHolder=r;
            if(temp.data<r.data)
            {
                r=r.left;
            }
            else
            {
                r=r.right;
            }
        }
        temp.parent=placeHolder;
        if(placeHolder==null)
        {
            root=temp;
        }
        else if(temp.data<placeHolder.data)
        {
            placeHolder.left=temp;
        }
        else
        {
            placeHolder.right=temp;
        }
        //after we insert the node in the correct position we splay
        splay(temp);
    }
    public void delete(int data)
    {
       BTNode<E> find=search(data,this.root);
       splay(data);
       if(root.data!=data)
       {
           splay(data); 
           System.out.println(data+ " is not in the tree");
           return;
       }
       System.out.println("The key was deleted from the tree");
       delete(this.root,data);
    }
    public void delete(BTNode<E> r, int data)
    {//We have x,p,g from class examples that we will swap around
        BTNode<E> x=null,p=null,g=null;
        while(r!=null)
        {
            if(r.data==data)//Our root is the data to delete
            {
                x=r;
            }
            if(r.data<=data)
            {
                r=r.right;
            }
            
            else
            {
                r=r.left;
            }
        }
        if(x==null)
        {
            System.out.println("Item not in tree, can not be deleted");
            return;
        }
        splay(x);
        if(x.right!=null)
        {
            p=x.right;
            p.parent=null;//root is now null
        }
        else
        {
            p=null;
        }
        g=x;
        g.right=null;
        x=null;//Need to combine disjoint trees, need a new method merge
        if(g.left!=null)
        {
            g.left.parent=null;
        }
        root=merge(g.left,p);
        g=null;
    }
    public static void msg()
    {
        System.out.println("S 1000 - Splay the tree at 1000");
        System.out.println("F 2000 - search/find the node with key 2000");
        System.out.println("I 3000 - insert a node with key 3000");
        System.out.println("D 4000 - delete the node with key 4000");
    }
    public BTNode<E> merge(BTNode<E> g, BTNode<E> p)
    {
        if(g==null)
        {
            return p;
        }
        if(p==null)
        {
            return g;
        }
        BTNode<E> temp=MAX(g);//need to preserve BST 
        splay(temp);
        temp.right=p;
        p.parent=temp;
        return temp;
    }
    public BTNode<E> MAX(BTNode<E> r)//rightmost Node in tree
    {
        while(r.right!=null)
        {
            r=r.right;
        }
        return r;
    }
    public BTNode<E> MIN(BTNode<E> r)//leftmost Node in tree
    {
        while(r.left!=null)
        {
            r=r.left;
        }
        return r;
    }
    public void printSideways(BTNode<E> root, int space)
    {
       if(root==null)
       {
           return;
       }
       space+=count;
       printSideways(root.right,space);
       System.out.print("\n");
       for(int i=count;i<space;i++)
       {
           System.out.print(" ");
       }
       System.out.print(root.data + "\n");
       printSideways(root.left,space);
    }
   public void printSideways()
   {
       printSideways(root,0);
   }
}
class BTNode<E> 
{
    int data;
    BTNode<E> parent,left,right;
    public BTNode(int data)
    {
        this.data=data;
        this.parent=null;
        this.left=null;
        this.right=null;
    }
    public void setLeft(BTNode<E> e)
    {
        left=e;
    }
    public void setRight(BTNode<E> e)
    {
        right=e;
    }
}