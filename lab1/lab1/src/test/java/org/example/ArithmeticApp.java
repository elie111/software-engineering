package org.example;

import java.util.Scanner;

public class ArithmeticApp {

    public static class StackItem<E> {
        public E value;
        public StackItem<E> next;

        public StackItem(E x) {
            value = x;
            next = null;
        }
    }
    public static class Stack<E> {
        private StackItem<E> top;

        public Stack() {
            top = null;
        }

        public void push(E x) {
            StackItem<E> newItem = new StackItem<E>(x);
            newItem.next = top;
            top = newItem;
        }

        public boolean empty() {
            return top == null;
        }

        public E peek() {
            return top.value;
        }

        public void pop() {
            top = top.next;
        }
    }

    public static class FullCalculator {

        private Stack<Character> operatorStack;
        private Stack<Integer> valueStack;
        private boolean error;

        public FullCalculator() {
            operatorStack = new Stack<Character>();
            valueStack = new Stack<Integer>();
            error = false;
        }

        private boolean isOperator(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/';
        }

        private int getPrecedence(char ch) {
            if (ch == '+' || ch == '-') {
                return 1;
            }
            if (ch == '*' || ch == '/') {
                return 2;
            }
            return 0;
        }

        private void processOperator(char t) {
            int a, b;
            if (valueStack.empty()) {
                System.out.println("Expression error.");
                error = true;
                return;
            } else {
                b = valueStack.peek();
                valueStack.pop();
            }
            if (valueStack.empty()) {
                System.out.println("Expression error.");
                error = true;
                return;
            } else {
                a = valueStack.peek();
                valueStack.pop();
            }
            int r = 0;
            if (t == '+') {
                r = a + b;
            } else if (t == '-') {
                r = a - b;
            } else if (t == '*') {
                r = a * b;
            } else if (t == '/') {
                if(b==0)
                {
                    error =true;
                    return;
                }
                r = a / b;
            } else {
                System.out.println("Operator error.");
                error = true;
            }
            valueStack.push(r);
        }

        public int processInput(String input,int sbase) {
            // The tokens that make up the input
            String[] tokens = input.split(" ");
            for (int n = 0; n < tokens.length; n++) {
                String nextToken = tokens[n];
                if(nextToken!="") {
                    char ch = nextToken.charAt(0);
                    if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F'))
                        tokens[n] = baseConversion(nextToken, sbase, 10);
                }
            }

            // Main loop - process all input tokens
            for (int n = 0; n < tokens.length; n++) {
                String nextToken = tokens[n];
                char ch='#';
                if(nextToken!="")
                    ch = nextToken.charAt(0);
                if (ch >= '0' && ch <= '9') {
                    int value = Integer.parseInt(nextToken);
                    valueStack.push(value);
                } else if (isOperator(ch) && nextToken.length()==1) {
                    if (operatorStack.empty() || getPrecedence(ch) > getPrecedence(operatorStack.peek())) {
                        operatorStack.push(ch);
                    } else {
                        while (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
                            char toProcess = operatorStack.peek();
                            operatorStack.pop();
                            processOperator(toProcess);
                        }
                        operatorStack.push(ch);
                    }
                } else if (ch == '(') {
                    operatorStack.push(ch);
                } else if (ch == ')') {
                    while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                        char toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    if (!operatorStack.empty() && operatorStack.peek() == '(') {
                        operatorStack.pop();
                    } else {
                        System.out.println("Error: unbalanced parenthesis.");
                        error = true;
                    }
                }

            }
            // Empty out the operator stack at the end of the input
            while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                char toProcess = operatorStack.peek();
                operatorStack.pop();
                processOperator(toProcess);
            }
            // Print the result if no error has been seen.
            if (error == false) {
                int result = valueStack.peek();
                valueStack.pop();
                if (!operatorStack.empty() || !valueStack.empty()) {
                    System.out.println("Expression error.");
                } else {
                    //System.out.println("The result is " + result);
                    return result;
                }
            }
            return  -1;
        }
    }
    public static String
    baseConversion(String number, int sBase, int dBase)
    {
        // Parse the number with source radix
        // and return in specified radix(base)
        return Integer.toString(
                Integer.parseInt(number, sBase), dBase);
    }
    public  static boolean checkcorrection(String s,int base)
    {
        String[] tokens = s.split("[+-/*]");
        char c='"';
        for (int i=0;i< tokens.length;i++)
        {
            if (tokens[i]=="")
            {
                System.out.println("Error: invalid expression: "+c+tokens[i]+c);
                return false;
            }
            if(base!=16) {
                int j;
                boolean flag=true;
                for (j = 0; j < tokens[i].length()&&   (tokens[i].charAt(j)==' '); j++)
                    ;
                int start=j;
                for (;j<tokens[i].length()&&(tokens[i].charAt(j)!=' ');j++)
                {
                    flag=false;
                    if(tokens[i].charAt(j)<'0'||tokens[i].charAt(j)>='0'+base)
                    {
                        System.out.println("Error: invalid expression: "+c+tokens[i].substring(start).split("[ ]+")[0]+c);
                        return false;
                    }
                }
                start=j;
                for (;j<tokens[i].length();j++)
                {
                    if(tokens[i].charAt(j)!=' ')
                    {
                        System.out.println("Error: invalid expression: "+c+tokens[i].substring(start)+c);
                        return false;
                    }
                }
                if(flag)
                {
                    System.out.println("Error: invalid expression: "+c+tokens[i]+c);
                    return false;
                }
            }
            else {

                int j,start;
                boolean flag=true;
                for (j = 0; j < tokens[i].length()&&   (tokens[i].charAt(j)==' '); j++)
                    ;
                start=j;
                for (;j<tokens[i].length()&&(tokens[i].charAt(j)!=' ');j++)
                {
                    flag=false;
                    if((tokens[i].charAt(j)<'0'||(tokens[i].charAt(j)>'9'&&tokens[i].charAt(j)<'A')||tokens[i].charAt(j)>'F'))
                    {
                        System.out.println("Error: invalid expression: "+c+tokens[i].substring(start).split("[ ]+")[0]+c);
                        return false;
                    }
                }
                start=j;
                for (;j<tokens[i].length();j++)
                {
                    if(tokens[i].charAt(j)!=' ')
                    {
                        System.out.println("Error: invalid expression: "+c+tokens[i].substring(start)+c);
                        return false;
                    }
                }
                if(flag)
                {
                    System.out.println("Error: invalid expression: "+c+tokens[i]+c);
                    return false;
                }
            }

        }
        return  true;
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String base;
        int k;
        String str1="6A  j";
        String[] str2= str1.split("[ ]+");
        //checkcorrection(str1,8);
        // The original input
        System.out.println("Enter base (2/8/10/16):");
        base = input.nextLine();
        k=Integer.parseInt(base);
        while (true) {
            if(k==2 || k==8 || k==10 || k==16)
                break;
            System.out.println("Error – this base isn’t supported. Please enter a base (2/8/10/16):");
            base = input.nextLine();
            k=Integer.parseInt(base);

        }
        System.out.println("Enter expression:");
        String userInput = input.nextLine();
        if(checkcorrection(userInput,k)) {
            String str = userInput;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '+' || str.charAt(i) == '/' || str.charAt(i) == '-' || str.charAt(i) == '*') {
                    str = str.substring(0, i) + ' ' + str.substring(i);
                    str = str.substring(0, i + 2) + ' ' + str.substring(i + 2);
                    i += 2;
                }
            }
//        str = str.substring(0, 1) + ' ' + str.substring(1);
//        str = str.substring(0, 3) + ' ' + str.substring(3);
            k = Integer.parseInt(base);
            FullCalculator calc = new FullCalculator();
            int res = calc.processInput(str, k);
            if (res >= 0)
                System.out.println("The value of expression " + userInput + " is : " + baseConversion(Integer.toString(res), 10, k).toUpperCase());
            else if (res == -1)
                System.out.println("Error: trying to divide by 0 (evaluated: \"0\")");
            //int x=Integer.parseInt("215f");
        }
    }
}
