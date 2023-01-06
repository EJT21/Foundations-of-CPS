class SelectionSort
{
	public static void main(String[] args)
	{
		int[] a={1,6,2,-3,7,4,20,0,1,-10};
		for(int j=0;j<a.length-1;j++)
		{
			//find the min
			int minIndex=j;
			for(int i=minIndex+1;i<a.length;i++)
			{
				if(a[i]<a[minIndex])
				{
					minIndex=i;
				}
			}
			
			//swap
			int temp=a[j];
			a[j]=a[minIndex];
			a[minIndex]=temp;
		}
		
		//print
		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
	}
}
