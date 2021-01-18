/**
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">
 *         nafisa.tabassum@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */

/*
 * Hello is a simple example program which prints the message "Hello?" followed
 * by each of the following primitive data types: boolean, char, byte, short,
 * int, long, float, and double on a separate line
 */

public class Hello {
    /**
     * This prints examples of automatic and explicit type casting of primitive data
     * types to the terminal window
     * 
     * @param args Optional command-line argument
     */
    public static void main(String[] args) {
        // Initialization of primitive data types
        boolean boolType = true;
        char charType = 'A';
        byte byteType = 100;
        short shortType = 6000;
        int intType = 200000;
        long longType = 300000000L;
        float floatType = 3.14f;
        double doubleType = 12.34d;

        // Automatic type casting
        System.out.println("Automatic type casting");
        double myDouble = intType; // Automatic casting int to double
        int myInt = byteType; // Automatic casting byte to int

        System.out.println(myDouble);
        System.out.println(myInt);

        // Explicit type casting
        System.out.println("Explicit type casting");
        short myShort = (short) doubleType; // Explicit casting double to short
        char myChar = (char) longType; // Explicit casting long to char

        System.out.println(myShort);
        System.out.println(myChar);

    }
} // End of class declaration
