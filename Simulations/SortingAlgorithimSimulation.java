import java.util.ArrayList;
class SortingAlgorithimSimulation
{
    static int icount=0;
    static int Scount=0;
    static int Hcount=0;
    static int Kcount=0;
    static int Gcount=0;
    static int SScount=0;
    static int qcount=0;
    static int mcount=0;
    static int hcount=0;
    public static void main(String[] args)
    {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s%-17s\n","N","insertionsort","Shell","Gonnet","Hibbard","Knuth","Sedgewick","Heapsort","Mergesort","Quicksort","NlogN");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i=1000;i<=10000;i+=1000)
        {
                int[] array=create(i);
            
                int[] temp=copy(array);
                insertion(temp);
                
                int[] temp1=copy(array);
                ShellS(temp1);
                
                int[] temp2=copy(array);
                ShellG(temp2);
                
                int[] temp3=copy(array);
                ShellH(temp3);
                
                int[] temp4=copy(array);
                ShellK(temp4);
                
                int[] temp5=copy(array);
                Shellsed(temp5);
                
                int[] temp6=copy(array);
                quickSort(temp6);
                
                int[] temp7=copy(array);
                mergeSort(temp7);
                
                int[] temp8=copy(array);
                heapsort(temp8);
                
                double x6=i*log(i);
                System.out.printf("%,-10d%,-17d%,-17d%,-17d%,-17d%,-17d%,-17d%,-17d%,-17d%,-17d%-10.2f\n",i,icount,Scount,Gcount,Hcount,Kcount,SScount,hcount,mcount,qcount,x6);
                qcount=0;
                mcount=0;
                hcount=0;
                icount=0;
                Scount=0;
                Hcount=0;
                Kcount=0;
                Gcount=0;
                SScount=0;
        }
        /*
        int[] test=create(5000);
        print(test);
        System.out.println();
        ShellH(test);
        print(test);
        System.out.println();
        check(test);
        dup(test);
        */
    }
    public static int[] create(int size)
    {
        int[] array=new int[size];
        for(int i=0;i<array.length;i++)
        {
            int x=(int)(Math.random()*Integer.MAX_VALUE);
            array[i]=x;
        }
        return array;
    }
    public static int[] copy(int[] a)
    {
        int[] copy=new int[a.length];
        for(int i=0;i<a.length;i++)
        {
            copy[i]=a[i];
        }
        return copy;
    }
    public static void print(int[] array)
    {
        for(int i=0;i<array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
    }
    public static void insertion(int[] a)//good
    {
        for(int i=1;i<a.length;i++)//repeat the steps
        {
            int x=a[i];
            int j=i-1;
            while(j>=0 && a[j]>x)
            {
                icount++;
                a[j+1]=a[j];
                j--;
            }   
            icount++;
            a[j+1]=x;
        }
    }
    public static void ShellS(int[] a)
    {
        int j;
        for(int gap=a.length/2;gap>0;gap/=2)
        {
            for(int i=gap;i<a.length;i++)
            {
                int temp=a[i];
                for(j=i;j>=gap && temp < a[j-gap]; j-=gap)
                {
                    a[j]=a[j-gap];
                    Scount++;
                }
                Scount++;
                a[j]=temp;
            }
        }
    }
    public static void ShellG(int[] a)
    {
        int j;
        double r=a.length/2.2;
        for(int gap=(int)r;gap>0;gap=(int)(gap/2.2))
        {
            for(int i=gap;i<a.length;i++)
            {
                int temp=a[i];
                Gcount++;
                for(j=i;j>=gap && temp < a[j-gap]; j-=gap)
                {
                    a[j]=a[j-gap];
                    Gcount++;
                }
                a[j]=temp;
            }
        }
    }
    public static void ShellH(int[] a)
    {
       int j;
       int increment=1;
       int k=0;
       while(increment<a.length)
       {
           increment=(int)Math.pow(2,k);
           increment--;
           k++;
       }
       for(int gap=increment;gap>0;gap=(int)Math.pow(2,k)-1)
        {
            for(int i=gap;i<a.length;i++)
            {
                int temp=a[i];
                Hcount++;
                for(j=i;j>=gap && temp < a[j-gap]; j-=gap)
                {
                    a[j]=a[j-gap];
                    Hcount++;
                }
                a[j]=temp;
            }
            k--;
       }
    }
    public static void ShellK(int[] a)
    {
       int j;
       int increment=1;
       int k=0;
       while(increment<a.length)
       {
           increment=(int)Math.pow(3,k);
           increment++;
           increment/=2;
           k++;
       }
       for(int gap=increment;gap>0;gap=(int)(Math.pow(3,k)+1)/2)
        {
            for(int i=gap;i<a.length;i++)
            {
                int temp=a[i];
                Kcount++;
                for(j=i;j>=gap && temp < a[j-gap]; j-=gap)
                {
                    a[j]=a[j-gap];
                    Kcount++;
                }
                a[j]=temp;
            }
            k--;
       }
    }
    public static void Shellsed(int[] a)
    {
        int j;
        ArrayList<Integer> union=new ArrayList<Integer>();
        int k=0;
        int k1=2;
        int temp1=(int)(Math.pow(4,k))*9-9*(int)Math.pow(2,k)+1;
        //System.out.println(temp1);
        union.add(temp1);
        int temp2=(int)(Math.pow(4,k1))-3*(int)(Math.pow(2,k1))+1;
        //System.out.println(temp2);
        union.add(temp2);
        k++;
        k1++;
        while(temp1<a.length && temp2<a.length)
        {
            temp1=(int)(Math.pow(4,k))*9-9*(int)Math.pow(2,k)+1;
            if(temp1<a.length)
            {
                union.add(temp1);
            }
            temp2=(int)(Math.pow(4,k1))-3*(int)(Math.pow(2,k1))+1;
            if(temp2<a.length)
            {
               union.add(temp2); 
            }
            k++;
            k1++;
        }
        //System.out.println("The union is "+union);
        int r=union.size()-1;
        for(int gap=union.get(r);r>=0;gap=union.get(r))
        {
            //System.out.println("The increment is "+gap);
            for(int i=gap;i<a.length;i++)
            {
                int temp=a[i];
                SScount++;
                for(j=i;j>=gap && temp < a[j-gap]; j-=gap)
                {
                    a[j]=a[j-gap];
                    SScount++;
                }
                a[j]=temp;
            }
            r--;
            if(r<0)
            {
                break;
            }
        }
       
    }
    public static void quickSort(int[] list)
    {
        quickSort(list,0,list.length-1);        
    }
    public static void quickSort(int[] list, int first, int last)
    {
        if(last > first)
        {
            int pivotIndex=partition(list,first,last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }
    public static int partition(int[] list, int first, int last)
    {
        int pivot = list[first];
        int low = first + 1; 
        int high = last;
        while (high > low)
        {
            while (low <= high && list[low] <= pivot)
            {
                low++;
                qcount++;
            }
            while (low <= high && list[high] > pivot)
            {
                high--;
                qcount++;
            }
            if (high > low)
            {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot)
        {
            high--;
            qcount++;
        }
        qcount++;
        if (pivot > list[high])
        {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else
        {
            return first;
        }
    }
    public static void mergeSort(int[] list)
    {
        if(list.length>1)
        {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);
            
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2,secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }
    public static void merge(int[] list1, int[] list2, int[] temp)
    {
        int current1=0;
        int current2=0;
        int current3=0;
        while (current1 < list1.length && current2 < list2.length) 
        {
            mcount++;
            if (list1[current1] < list2[current2])
            {
                temp[current3++] = list1[current1++];
            }
            else
            {
                temp[current3++] = list2[current2++];
            }
        }
        while (current1 < list1.length)
        {
            temp[current3++] = list1[current1++];
            mcount++;
        }
        while (current2 < list2.length)
        {
            temp[current3++] = list2[current2++];
            mcount++;
        } 
    }
    //Heapsort algorithim
    public static void swapReferences(int[] a, int b, int c)
    {
        int temp=a[b];
        a[b]=a[c];
        a[c]=temp;
    }
    public static int leftChild(int i)
    {
        return 2*i+1;
    }
    public static void percentDown(int[] a, int i, int n)
    {
        int child;
        int temp;
        for(temp=a[i];leftChild(i)<n;i=child)
        {
            child=leftChild(i);
            if(child!=n-1 && a[child] < a[child+1])
            {
                child++;
                hcount++;
            }
            //hcount++;//first if
            hcount++;//else if
            if(temp < a[child])
            {
                a[i]=a[child]; 
            }
            else
            {
                break;
            }    
        }
        a[i]=temp;
    }
    public static void heapsort(int[] a)
    {
        for(int i=a.length/2-1;i>=0;i--)
        
            percentDown(a,i,a.length);
        for(int i=a.length-1;i>0;i--)
        {
            swapReferences(a,0,i);
            percentDown(a,0,i);
        }
    }
    public static double log(int a)
    {
        double ans=(Math.log(a)/Math.log(2));
        return ans;
    }
    public static void check(int[] array)
    {
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(array[j]>array[i])
                {
                    System.out.println("Something went wrong");
                    return;
                }
            }
        }
        System.out.println("This array is sorted");
    }
    public static void dup(int[] array)
    {
        ArrayList<Integer> temp=new ArrayList<Integer>();
        for(int i=0;i<array.length;i++)
        {
            if(!temp.contains(array[i]))
            {
                temp.add(array[i]);
            }
        }
        int diff=array.length-temp.size();
        System.out.println("The amount of duplicates are "+diff);
    }
}