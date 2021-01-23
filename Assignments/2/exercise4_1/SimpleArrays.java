import java.util.Arrays;

public class SimpleArrays {

    public String[] arrayOfStrings = new String[4];

    public SimpleArrays(String userString) {
        Arrays.fill(arrayOfStrings, userString);
    }

    public SimpleArrays() {
        Arrays.fill(arrayOfStrings, "Hello, ENSF 409");
    }

    public String arrayConcat(int arrayIndex) {
        return arrayCrop(arrayIndex, arrayOfStrings.length - 1);
        // String stringConcat = "";
        // String delimiter = "";

        // for (int i = arrayIndex; i < arrayOfStrings.length; i++) {
        // stringConcat += delimiter + arrayOfStrings[i];
        // delimiter = "#";
        // }
        // return stringConcat;
    }

    public String arrayConcat() {
        return arrayConcat(0);
    }

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

    public static void main(String args[]) {
        SimpleArrays test = new SimpleArrays("test");
        SimpleArrays test2 = new SimpleArrays();

        System.out.println(test.arrayConcat(1));
        System.out.println(test2.arrayConcat(0));
        System.out.println(test2.arrayConcat(4));

        System.out.println(test.arrayCrop(1, 3));

    }

}
