import java.util.Scanner;
class sortingLetters
{
	public static void main(String[] args)
	{
		Scanner kb=new Scanner(System.in);
		System.out.print("Enter the size of array you want: ");
		int n=kb.nextInt();
		char[] original=new char[n];
		char[] copy=new char[n];
		for(int i=0;i<original.length;i++)
		{
			System.out.print("Enter a value [a,z]: ");
			char c=kb.next().charAt(0);
			while(c<'a' || c>'z')
			{
				System.out.print(c+" is not in the interval. Please re-try: ");
				c=kb.next().charAt(0);
			}
			original[i]=c;
			copy[i]=c;
		}
		//sort
		for(int i=0;i<original.length-1;i++)
		{
			int max=i;
			for(int j=i+1;j<original.length;j++)
			{
				if(original[max]<original[j])
				{
					max=j;
				}
			}
			char temp=original[max];
			original[max]=original[i];
			original[i]=temp;
		}
		
		System.out.println("Original Array: ");
		for(int i=0;i<original.length;i++)
		{
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("\nSorted Array: ");
		for(int i=0;i<copy.length;i++)
		{
			System.out.print(original[i]+" ");
		}
		
		int count=0;
		for(int i=0;i<original.length;i++)
		{
			if(original[i]!=copy[i])
			{
				count++;
			}
		}
		double changes=count/(double)original.length;
		System.out.println("\n"+count+" values moved index. Which has the ration: "+changes);
		
	}
}
