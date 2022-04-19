import java.util.ArrayList;
class OurLinkedList<E>
{
    private Node<E> head;
    public OurLinkedList()//creates the empty list
    {
        head=null;
    }
    public boolean isEmpty()
    {
        if(head==null)
            return true;
        return false;
        
        
        //return head==null;
    }
    public void addFirst(E e)
    {
        if(head==null)//if(isEmpty())
        {
            head=new Node<E>(e);
        }
        else
        {
            Node<E> n=new Node<E>(e);
            n.setLink(head);
            head=n;
        }
    }
    
    public String toString()
    {
        String s="head => ";
        Node<E> temp=head;
        while(temp!=null)
        {
            s+=temp.getData()+" => ";
            temp=temp.getLink();
        }
        s+="null";
        return s;
    }
    public int size()
    {
        /*if(isEmpty())
            return 0;*/
        Node<E> temp=head;
        int count=0;
        while(temp!=null)
        {
            count++;
            temp=temp.getLink();
        }
        return count;
    }
    private Node<E> getLast()
    {
        if(head==null)//without this case temp.getLink() give null pointer exception
            return head;
        Node<E> temp=head;
        while(temp.getLink()!=null)
        {
            temp=temp.getLink();
        }
        return temp;
    }
    /***
    Node<E> temp=head;
    if(temp==null)
    {
        
    }
        while(temp.getLink()!=null)
        {
            temp=temp.getLink();
        }
    //OR    
        Node<E> temp=head;
        while(temp!=null)
        {
            if(temp.getLink()==null)
                return temp;
                //break;
            temp=temp.getLink();
        }
    ***/
    public void add(E e)
    {
        if(isEmpty())//size()==0
        {
            addFirst(e);
            return;
        }
        //else{
        Node<E> n=new Node<E>(e);
        Node<E> last=getLast();
        last.setLink(n);
        //}
    }
    public E remove()
    {
        if(size()==0)
            return null;
        Node<E> temp=head;
        head=head.getLink();
        temp.setLink(null); 
        return temp.getData();
    }
    
    //removes 1st occurence of e
    /*public boolean remove(E e)
    {
        if(isEmpty() || !contains(e))//contains(e)==false
        {
            return false;
        }
        //OurLinkedList<E> tracker=new OurLinkedList<E>();
        /**
        Make a copy of the list into tracker and then delete
        */
        //special case: e is in first node
        /*if(indexOf(e)==0)//head.getData().equals(e)
        {
            remove();//removes first node
            return true;
        }*/
        
        
        //e is in a node somewhere in the middle or at the end
        /*Node<E> tracker=head;
        Node<E> prev=head;
        while(tracker.getLink()!=null)
        {
            if(tracker.getData().equals(e))
                break;
            prev=tracker;
            tracker=tracker.getLink();
        }
        prev.setLink(tracker.getLink());
        tracker.setLink(null);
        return true;
    }*/
    
    public static OurLinkedList<Object> toList(Object[] a)
    {
        OurLinkedList<Object> ll=new OurLinkedList<Object>();//create empty linked list
        for(int i=0;i<a.length;i++)
        {
            ll.add(a[i]);
        }
        return ll;
    }
    
    //Node<E> get(int i)    
    public Node<E> get(int i)
    {
        if(i<0 || i>=size())
            return null;//we could also use an exception
        Node<E> temp=head;
        int index=0;
        while(index!=i)
        {
            temp=temp.getLink();
            index++;
        }
        return temp;
    }
    
    //add(int i, E e): boolean
    public boolean add(int i,E e)
    {
        if(i<0 || i>=(size()+1))
            return false;
        Node<E> n=new Node<E>(e);
        if(i==0)
            addFirst(e);
        else if(i==size()+1)
            add(e);
        else
        {
            Node<E> x=get(i);
            Node<E> prev=get(i-1);
            prev.setLink(n);
            n.setLink(x);
        }
        return true;
    }
            
    //remove(int i): E
    public E remove(int i)
    {
        if(isEmpty() || i<0 || i>=size())
            return null;
        if(i==0)
            return remove();
        Node<E> x=get(i);
        E data=x.getData();
        Node<E> prev=get(i-1);
        prev.setLink(x.getLink());
        x.setLink(null);
        return data;
    }
    
    //removeLast()
    public E removeLast()
    {
        if(isEmpty())
            return null;
        if(size()==1)
            return remove();
        Node<E> l=getLast();
        Node<E> prev=get(size()-2);
        prev.setLink(null);
        return l.getData();
    }
    //contains
    public boolean contains(E e)
    {
        if(isEmpty())
            return false;
        Node<E> temp=head;
        while(temp!=null)
        {
            if(temp.getData().equals(e))
                return true;
            temp=temp.getLink();
        }
        return false;
    }
    //indexOf
    public int indexOf(E e)
    {
        if(!contains(e))
            return -1;
        int index=0;
        Node<E> temp=head;
        Node<E> temp1=get(0);
        System.out.println(temp.getData()==temp1.getData());
        while(!temp.getData().equals(e))
        {
            temp=temp.getLink();
            index++;
        }
        return index;
    }
    
    //count
    public int count(E e)
    {
        if(!contains(e))
            return 0;
        int c=0;
        Node<E> temp=head;
        while(temp!=null)
        {
            if(temp.getData().equals(e))
                c++;
            temp=temp.getLink();
        }
        return c;
    }
    
    //toList(Arraylist)
    public OurLinkedList<E> toList(ArrayList<E> al)
    {
        OurLinkedList<E> ll=new OurLinkedList<E>();
        for(int i=0;i<al.size();i++)
        {
            ll.add(al.get(i));
        }
        return ll;
    }
    
    //toList(): ArrayList
    public ArrayList<E> toList()
    {
        ArrayList<E> al=new ArrayList<E>();
        for(int i=0;i<size();i++)
        {
            al.add(get(i).getData());
        }
        return al;
    }
    public void reverse()
    {
        Node<E> pointer=head;
        Node<E> previous=null, current=null;
        while(pointer!=null)
        {
            current=pointer;
            pointer=pointer.getLink();
            //Now reverse
            current.setLink(previous);
            previous=current;
            head=current;
        }
    }
    public Node<E> ii(Node<E> a, Node<E> b)
    {
        if(a==null || b==null)
            return null;
        int countA=0;
        int countB=0;
        Node<E> tempA=a;
        Node<E> tempB=b;
        while(tempA!=null)
        {
            countA++;
            tempA=tempA.getLink();
        }
        while(tempB!=null)
        {
            countB++;
            tempB=tempB.getLink();
        }
        //Now we have size of a and b
        int diff = Math.max(countA,countB) - Math.min(countA,countB); // 2
        int max = Math.max(countA,countB);
        if(countA > countB)
        {
            int count=0;
            while(count!=diff)
            {
                tempA=tempA.getLink();
                count++;
            }
        }
        else //countB > countA
        {
            int count=0;
            while(count!=diff)
            {
                tempB=tempB.getLink();
                count++;
            }
        }
        while(tempA !=null)
        {
            if(tempA.getData().equals(tempB.getData()))
            {
                return tempA;
            }
            tempA=tempA.getLink();
            tempB=tempB.getLink();
        }
        return null;
    }
}
class Node<E> 
{
    private E data;//data is any object 
    private Node<E> link;//this makes node recursive.  link is a reference variable to another Node object
    
    public Node()
    {
        data=null;
        link=null;
    }
    public Node(E d)
    {
        data=d;
        link=null;
    }
    public void setData(E d)
    {
        data=d;
    }
    public E getData()
    {
        return data;
    }
    public Node<E> getLink()
    {
        return link;
    }
    public void setLink(Node<E> l)
    {
        link=l;
    }
    //intersection of linkedlist method
    public Node<E> ii(Node<E> a, Node<E> b)
    {
        if(a==null || b==null)
            return null;
        int countA=0;
        int countB=0;
        Node<E> tempA=a;
        Node<E> tempB=b;
        
        Node<E> tempC=a;
        Node<E> tempD=b;
        while(tempC!=null)
        {
            countA++;
            tempC=tempC.getLink();
        }
        
        while(tempD!=null)
        {
            countB++;
            tempD=tempD.getLink();
        }
        //Now we have size of a and b
        int diff = Math.max(countA,countB) - Math.min(countA,countB); // 2
        int max = Math.max(countA,countB);
        if(countA > countB)
        {
            int count=0;
            while(count!=diff)
            {
                tempA=tempA.getLink();
                count++;
            }
        }
        else //countB > countA
        {
            int count=0;
            while(count!=diff)
            {
                tempB=tempB.getLink();
                count++;
            }
        }
        while(tempA !=null)
        {
            //System.out.println("A");
            if(tempA.getData().equals(tempB.getData()))
            {
                return tempA;
            }
            tempA=tempA.getLink();
            tempB=tempB.getLink();
        }
        return null;
    }
}
