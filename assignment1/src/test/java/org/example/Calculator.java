package org.example;
import java.util.Scanner;
//import java.util.Formatter;
public class Calculator {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String inputed="";
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter expression: ");
        inputed = input.nextLine();
        double answer = nomultiplay(inputed,0,inputed.length());
        System.out.print("The value of expression ");
        print(inputed,inputed.length());
        System.out.print(" is: ");


        System.out.format("%.5f",answer);
        input.close();
    }
    private static void print(String inputed,int placetoend) {
        int i;
        for (i=0;i<placetoend;i++) {
            if (inputed.charAt(i)!=' '){
                System.out.print(inputed.charAt(i));

            }

        }
    }

    private static double nomultiplay(String inputed , int placeToStart, int placetoend)
    {
        int theOkPower =0 , neg=0;
        String NewString= "";
        double LastNumber = 0;
        int i=placeToStart;
        if(inputed.charAt(i) == '-')
        {
            neg=1;
        }
        for(i=placeToStart+neg;i<placetoend ;i++) /* run on the question*/
        {
            if(inputed.charAt(i) == '.')
            {
                theOkPower = 1;
            }
            if(inputed.charAt(i) == '(')
            {
                theOkPower =0;
                /*option one */
                int j = i+1,ok=1;
                while(ok >0)
                {
                    if(inputed.charAt(j) == ')')
                    {
                        ok--;
                    }
                    if(inputed.charAt(j) == '(')
                    {
                        ok++;
                    }
                    j++;
                }

                LastNumber = nomultiplay(inputed, i+1, j-1);
                NewString= NewString + Double.toString(LastNumber);
                /*start again*/
                for(int t = j; t <placetoend;t++)
                {
                    NewString= NewString + inputed.charAt(t);
                }

                return nomultiplay(NewString, 0, NewString.length());

            }
            else
            {
                if(inputed.charAt(i) == '*' || inputed.charAt(i) == '/' )
                {
                    int wellitssucks = 0;
                    theOkPower =0;
                    int iSaver=i;
                    while(inputed.charAt(i+1)==' ' ||inputed.charAt(i+1)=='-' )
                    {
                        if(inputed.charAt(i+1)=='-')
                        {
                            wellitssucks = 1;
                        }
                        i++;
                    }
                    if(inputed.charAt(i+1) == '(')
                    {
                        NewString= NewString +Double.toString(LastNumber)+ inputed.charAt(iSaver);
                        if(wellitssucks == 1)
                        {
                            NewString = NewString + "-";
                        }
                    }
                    else
                    {

                        double sum= 0;
                        double [] after = new double[2];
                        after = NumberAfter(inputed,i);
                        double AfterNum = after[0];
                        for(int me=i;me<=i+after[1];me++)
                        {
                            if(inputed.charAt(me)=='-')
                            {
                                AfterNum = AfterNum * -1;

                            }
                        }
                        if(neg == 1)
                        {
                            LastNumber=LastNumber * -1;
                        }
                        if(inputed.charAt(iSaver) == '*')
                        {
                            sum = LastNumber * AfterNum;

                        }
                        else
                        {
                            sum = LastNumber / AfterNum;
                        }
                        LastNumber = sum;
                        i = (int) (i + after[1]);


                    }

                }
                else
                {
                    if(inputed.charAt(i) == '+' || inputed.charAt(i) == '-' )
                    {
                        neg=0;
                        theOkPower =0;
                        NewString = NewString + Double.toString(LastNumber) + inputed.charAt(i);
                        LastNumber = 0;
                        if( inputed.charAt(i) == '-')
                        {
                            neg=1;
                        }

                    }
                    else
                    {
                        if(inputed.charAt(i) >= '0' && inputed.charAt(i) <= '9'  ) /* missed the  . */
                        {
                            if(theOkPower == 0)
                            {
                                LastNumber =LastNumber*10 + inputed.charAt(i) - '0';
                            }
                            else
                            {
                                LastNumber = LastNumber + (inputed.charAt(i) - '0')/(Math.pow(10, theOkPower));
                                theOkPower++;
                            }


                        }
                    }
                }
            }



        }
        NewString = NewString + Double.toString(LastNumber);
        LastNumber=0;
        return finalizeit(NewString,0,NewString.length());
    }
    private static double finalizeit(String newString, int placeToStart, int placetoend) {
        double []after= new double [2];
        after = NumberAfter(newString, -1);
        double Sum= after[0];

        for(int i = placeToStart ;i < placetoend ; i++)
        {
            if(newString.charAt(i) == '+')
            {
                double []test= new double [2];
                test =  NumberAfter(newString,i);
                double AfterNum = test[0];

                Sum = Sum +AfterNum;
                i = (int) (i + test[1]);


            }
            else
            {

                if(newString.charAt(i) == '-')
                {
                    double []test= new double [2];
                    test = NumberAfter(newString,i);
                    double AfterNum = test[0];
                    Sum = Sum - AfterNum;
                    i = (int) (i + test[1]);


                }
            }
        }

        return Sum;
    }


    private static double[] NumberAfter(String inputed, int  i) {  /* if its the end no completed */
        // TODO Auto-generated method stub
        i++;
        double[] retur = new double[2];
        int lenght=0;
        double NumberToRetrun = 0,theOkayPower=0;
        while(i<inputed.length() && ((inputed.charAt(i) >= '0'  && inputed.charAt(i)<= '9') || inputed.charAt(i) == '.' ))
        {
            lenght++ ;
            if(inputed.charAt(i)=='.')
            {
                theOkayPower = 1;
            }
            else
            {
                if(theOkayPower == 0)
                {
                    NumberToRetrun =NumberToRetrun*10 + inputed.charAt(i) - '0';
                }
                else
                {
                    NumberToRetrun =NumberToRetrun + (inputed.charAt(i) - '0')/(Math.pow(10, theOkayPower));
                    theOkayPower ++;
                }
            }

            i++;
        }
        retur[0]=NumberToRetrun;
        retur[1]=lenght;
        return retur;
    }




}
