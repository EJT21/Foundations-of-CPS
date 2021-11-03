import java.util.Stack;
import java.util.Scanner;
import java.io.*;
class Assignment1
{
    public static void main(String[] args)throws IOException, MyException
    {
        Scanner in=new Scanner(new File("in.txt"));
        while(in.hasNextLine())
        {
            String t=in.nextLine();
            try
            {
                int x=Calculate(t);
                expression(t);
                System.out.println(" = "+ x);
            }
            catch(MyException e)
            {
              expression(t);  
              System.out.println(" = "+ e.getMessage());
            }
        }
    }
    public static int Calculate(String s)throws MyException
    {
        Stack<Integer> post = new Stack<Integer>();
        String[] temp=s.split(" ");
        int sum=0;
        Scanner kb=new Scanner(System.in);
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].equals(""))
            {
            
            }
            else if(Character.isDigit(temp[i].charAt(0)))
            {
                int x=Integer.parseInt(temp[i]);
                post.push(x);
            }
            else if(temp[i].equals("-"))
            {
                int x=post.pop();
                int x1=post.pop();
                post.push(x1-x);
            }
            else if(temp[i].charAt(0)=='-' && Character.isDigit(temp[i].charAt(1)))
            {
                int x=Integer.parseInt(temp[i]);
                post.push(x);
            }
            else if(temp[i].equals("#"))
            {
                int sq=post.pop();
                if(sq<0)
                {
                    throw new MyException(2);
                }
                post.push((int)(Math.sqrt(sq)));
            }
            else if(temp[i].equals("+"))
            {
                int x=post.pop();
                int x1=post.pop();
                int x2=x1+x;
                post.push(x2);
            }

            else if(temp[i].equals("*"))
            {
                int x=post.pop();
                int x1=post.pop();
                post.push(x1*x);
            }
            else if(temp[i].equals("/"))
            {
                int x=post.pop();
                int x1=post.pop();
                if(x==0)
                {
                    throw new MyException(1);
                }
                post.push(x1/x);
            }
            else if(temp[i].equals("_"))
            {
                int x=post.pop();
                post.push(-1*x);
            }
            else if(temp[i].equals("!"))
            {
                int x=post.pop();
                int fact=1;
                if(x<0)
                {
                    throw new MyException(3);
                }
                if(x==0)
                {
                    post.push(1);
                }
                for(int j=1;j<=x;j++)
                {
                    fact*=j;
                }
                post.push(fact);
            }
            else if(temp[i].equals("^"))
            {
                int x=post.pop();
                int x1=post.pop();
                post.push((int)(Math.pow(x,x1)));
            }
            else if(temp[i].charAt(0)=='<')//false=0
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b=x1<x;
                if(b==false)
                {
                    post.push(0);
                }
                else
                {
                    post.push(1);
                }
                
            }
            else if(temp[i].charAt(0)=='<' && temp[i].charAt(1)=='=')
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b= x1<=x;
                if(b==false)
                {
                    post.push(0);
                }
                else
                {
                    post.push(1);
                }
            }
            else if(temp[i].equals(">="))
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b= x1>=x;
                if(b==true)
                {
                    post.push(1);
                }
                else
                {
                    post.push(0);
                }
            }
            else if(temp[i].charAt(0)=='>')
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b=x1>x;
                if(b==true)
                {
                    post.push(1);
                }
                else
                {
                    post.push(0);
                }
            }
            else if(temp[i].charAt(0)=='=' && temp[i].charAt(1)=='=')
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b=(x1==x);
                if(b==true)
                {
                    post.push(1);
                }
                else
                {
                    post.push(0);
                }
            }
            else if(temp[i].equals("!="))
            {
                int x=post.pop();
                int x1=post.pop();
                boolean b=x1!=x;
                if(b==true)
                {
                    post.push(1);
                }
                else
                {
                    post.push(0);
                }
            }
            else if(temp[i].charAt(0)=='&' && temp[i].charAt(1)=='&')
            {
                int x=post.pop();
                int x1=post.pop();
                int z=(x1 & x);
                post.push(z);
            }
            else if(temp[i].charAt(0)=='|' && temp[i].charAt(1)=='|')
            {
                int x=post.pop();
                int x1=post.pop();
                int z=(x1 | x);
                post.push(z);
            }
            else if(temp[i].equals("a") | temp[i].equals("b") | temp[i].equals("c") | temp[i].equals("d") | temp[i].equals("e")
            | temp[i].equals("f") | temp[i].equals("g") | temp[i].equals("h") | temp[i].equals("i") | temp[i].equals("j") | temp[i].equals("k") | 
            temp[i].equals("l") | temp[i].equals("m") | temp[i].equals("n") | temp[i].equals("o") | temp[i].equals("p") | 
            temp[i].equals("q") | temp[i].equals("r") | temp[i].equals("s") | temp[i].equals("t") | temp[i].equals("u") | 
            temp[i].equals("v") | temp[i].equals("w") | temp[i].equals("x") | temp[i].equals("y") | temp[i].equals("z"))
            {
                System.out.println("Enter a value for "+ temp[i]);
                int x=kb.nextInt();
                post.push(x);
                for(int j=i+1;j<temp.length;j++)
                {
                    if(temp[j].contains(temp[i]))
                    {
                        temp[j]=Integer.toString(x);
                    }
                }
            }
            else if(temp[i].equals("value") | temp[i].equals("this") | temp[i].equals("that") | temp[i].equals("number") | temp[i].equals("variable"))
            {
                
                System.out.println("Enter a value for "+temp[i]);
                int x=kb.nextInt();
                post.push(x);
                for(int j=i+1;j<temp.length;j++)
                {
                    if(temp[j].contains(temp[i]))
                    {
                        temp[j]=Integer.toString(x);
                    }
                }
            }
            else if(temp[i].equals("$"))
            {
                int x=post.pop();
                sum=x;
                if(!post.isEmpty())
                {
                    throw new MyException(0);
                }
                return sum;
            }
            else
            {
                
            }
        }
        return sum;
    }
    public static void expression(String t)
    {
        String[] s=t.split(" ");
        for(int i=0;i<s.length;i++)
        {
            if(s[i].equals("$"))
            {
                System.out.print("$");
                return;
            }
            System.out.print(s[i]+" ");
        }
    }
}
class MyException extends Exception
{
      public MyException(int code)
      {
            super(change(code));
      }
      public static String change(int code)
      {
            String m="";
            switch(code)
            {
                case 0: m="Stack is not empty, invalid postfix expression";
                break;
                case 1: m="Can't divide by zero";
                break;
                case 2: m="Can't take sqaure of a negative number";
                break;
                case 3: m="Can't take factorial of negative number";
                break;
                default: m="error";
            }
            return m;
      }
}