class TwoDArrays
{
    public static void main(String[] args)
    {
        int[][] t=create(12,6);
        fillArray(t,10,50);
        print(t);
    }
    //Write a method to create an n by m 2d array
    public static int[][] create(int n, int m)
    {
        return new int[n][m];
    }
    //write a method to fill a 2d array with values in range a,b
    public static void fillArray(int[][] m,int a, int b)
    {
        for(int i=0;i<m.length;i++)//row
        {
            for(int j=0;j<m[0].length;j++)//column
            {
                m[i][j]=(int)(Math.random()*(b-a))+a;
            }
        }
    }
    //write a method to print the array
    public static void print(int[][] m)
    {
        for(int i=0;i<m.length;i++)
        {
            for(int j=0;j<m[0].length;j++)
            {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    //write a method to transpose a 2d array
    public static int[][] transpose(int[][] m)
    {
        int[][] n= new int[m[0].length][m.length];
        //We create a new array with the row length and the column length reverse
        for(int i=0;i<m.length;i++)
        {
            for(int j=0;j<m[0].length;j++)
            {
                n[j][i]=m[i][j];
            }
        }
        return n;
    }
    public static int[][] copy(int[][] m)
    {
        int[][] n=new int[m.length][m[0].length];
        for(int i=0;i<m.length;i++)
        {
            for(int j=0;j<m[0].length;j++)
            {
                n[i][j]=m[i][j];
            }
        }
        return n;
    }
    //write a method to add 2 2d arrays together
    public static int[][] add(int[][] m, int[][] n)
    {
        if(m.length!=n.length || m[0].length!=n[0].length)
            return null;
        int[][] r = new int[m.length][m[0].length];
        for(int i=0;i<m.length;i++)
        {
            for(int j=0;j<m[0].length;j++)
            {
                r[i][j]=m[i][j]+n[i][j];
            }
        }
        return r;
    }
    //method to multiply matrixes
    public static int[][] multiply(int[][] m, int[][] n) // 3*3  3*2
    {
        if(m[0].length!=n.length)
            return null;
        int[][] r=new int[m.length][n[0].length];
        for(int i=0;i<m.length;i++)//this goes through first row in matrix
        {
            for(int j=0;j<n[0].length;j++)//this goes through columns of second matrix
            {
                int value=0;
                for(int k=0;k<m[0].length;k++)
                {
                    value+=m[i][k]*n[k][j];
                }
                r[i][j]=value; //store in int row i and column j
            }
        }
        return r;
    }
    //sorts a integer array
    public static void sort(int[] a)
    {
	for(int i=0;i<a.length-1;i++) //last one will be in order
	{
	    int minIndex=findMin(a,i); //find smallest element in unsorted section of array		
	    //swap smallest to front
	    int temp=a[minIndex];
	    a[minIndex]=a[i];
	    a[i]=temp; 
	}
    }	
    //finds the min in an array
    public static int findMin(int[] a,int x)//i is where we are in array
    {
	int minIndex=x;
	for(int i=x+1;i<a.length;i++)
	{
		if(a[i]<a[minIndex])
		{
			minIndex=i;
		}
	}
	return minIndex;
    }
    //Write a method to sort a 2d array
    public static void sort(int[][] m)
    {
	System.out.println("M row before sort: ");
	print(m);
	for(int i=0;i<m.length;i++)
	{
		sort(m[i]);//we will pass through the row index, and sort it 
	}
	System.out.println("M row sorted: ");
	print(m);
    }
}
