class InsertionSort
{
    public static void main(String[] args)
    {
        int[] array={6, 4, 9, 1, -5, 10};
        print(array);
        insertion(array);
        print(array);
    }
    public static void insertion(int[] a)
    {
        for(int i=1;i<a.length;i++)
        {
            int x=a[i];
            int j=i-1;
            while(j>=0 && a[j]>x)
            {
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=x;
        }
    }
    public static void print(int[] a)
    {
        for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}
