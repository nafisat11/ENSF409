
/**
 * SimpleArrays is a program that will manipulate 
 * user inputted strings in an array by concatenation.
 * 
 * @author Nafisa Tabassum
 * <a href="mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */

import java.util.Arrays;

public class SimpleArrays {

    public String[] arrayOfStrings = new String[4];

    /**
     * Populate an array with a fixed size of 4 elements with the user provided
     * string.
     * 
     * @param userString User provided string
     */
    public SimpleArrays(String userString) {
        Arrays.fill(arrayOfStrings, userString);
    }

    /**
     * Populate array of size 4 with the string "Hello, ENSF 409" if the user does
     * not provide a string.
     */
    public SimpleArrays() {
        Arrays.fill(arrayOfStrings, "Hello, ENSF 409");
    }

    /**
     * Concatenate strings in the array together from the array index to the end of
     * the array, delimited by "#".
     * 
     * @param arrayIndex Index in array that does not exceed bounds
     * @return Concatenated string delimited by "#".
     */
    public String arrayConcat(int arrayIndex) {
        return arrayCrop(arrayIndex, arrayOfStrings.length - 1);
    }

    /**
     * Concatenate strings in the array together from index 0 to the end of the
     * array, delimited by "#".
     * 
     * @return Concatenated string delimited by "#".
     */
    public String arrayConcat() {
        return arrayConcat(0);
    }

    /**
     * Concatenate strings in the array together from the array to the end of the
     * array, delimited by "#".
     * 
     * @param startIndex Starting array index
     * @param endIndex   Ending array index
     * @return "Fail" if the indices are invalid, "Match" if the indices are the
     *         same, and the concatenated string of the elements specified by the
     *         two indices, delimited by "#".
     */
    public String arrayCrop(int startIndex, int endIndex) {
        if (startIndex < 0 || startIndex > arrayOfStrings.length - 1 || endIndex < 0
                || endIndex > arrayOfStrings.length - 1) {
            return "Fail";
        }

        if (startIndex == endIndex) {
            return "Match";
        }

        if (endIndex < startIndex) {
            int tempIndex = 0;
            tempIndex = startIndex;
            startIndex = endIndex;
            endIndex = tempIndex;
        }

        String stringConcat = "";
        String hashDelimiter = "";

        for (int i = startIndex; i <= endIndex; i++) {
            stringConcat += hashDelimiter + arrayOfStrings[i];
            hashDelimiter = "#";
        }
        return stringConcat;
    }
}
