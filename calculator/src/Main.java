import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myobj = new Scanner(System.in);
        System.out.println( " Enter Character that you want to perform operations.");
        System.out.println("( + ) Addition.");
        System.out.println("( - ) Subtraction.");
        System.out.println("( * ) Multiplication.");
        System.out.println("( / ) Division.");
        System.out.println("( 0 ) Exit.");

        char operator = myobj.next().charAt(0);

        while(operator != '0') {

            System.out.println(" Enter value 1 ");
            float num1 = myobj.nextFloat();
            System.out.println(" Enter value 2 ");
            float num2 = myobj.nextFloat();

            switch (operator) {
                case '+':
                    System.out.println(" The Addition of " + num1 + " and " + num2 + " is " + (add(num1, num2)));
                    break;
                case '-':
                    System.out.println(" The Subtraction of " + num1 + " and " + num2 + " is " + (sub(num1, num2)));
                    sub(num1, num2);
                    break;
                case '*':
                    System.out.println(" The Multiplication of " + num1 + " and " + num2 + " is " + (mul(num1, num2)));
                    break;
                case '/':
                    if (num2 != 0)
                        System.out.println(" The Division of " + num1 + " and " + num2 + " is " + (div(num1, num2)));
                    else
                        System.out.println(" Cannot divided by zero ");
                    break;
                case '0':
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println(" Invalid operator  ");

            }

            System.out.println( " Enter Character that you want to perform operations.");
            System.out.println("( + ) Addition.");
            System.out.println("( - ) Subtraction.");
            System.out.println("( * ) Multiplication.");
            System.out.println("( / ) Division.");
            System.out.println("( 0 ) Exit.");

            operator = myobj.next().charAt(0);

        }
    }

    private static float add(float num1, float num2){
        return num1 + num2 ;
    }

    private static float sub(float num1, float num2){
        return num1 - num2 ;
    }

    private static float mul(float num1, float num2){
        return num1 * num2 ;
    }
    private static float div(float num1, float num2){
        return num1 / num2 ;
    }


    }