class Prime
{
    public static void main(String[] args)
    {
        System.out.println("These are the prime numbers less than 100");
        for(int i=1;i<=100;i++)
        {
            for(int j=2;j<=i;j++)
            {
                if(i==j)
                {
                    System.out.print(j+" ");
                }
                if(i%j==0)
                {
                    break;
                }
            }
        }
    }
}
