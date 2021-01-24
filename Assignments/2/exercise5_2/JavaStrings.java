/**
 * JavaStrings is a program that will process strings in three different ways:
 * computing the length of two concatenated strings without leading and trailing
 * whitespaces, creating a unique identifier for pet consisting of the owner's
 * first and last initial, first initial of the pet's name, and the pet's year
 * of birth, and finally generating a code word for recipe ingredients.
 * 
 * @author Nafisa Tabassum <a href=
 *         "mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */
public class JavaStrings {

    /**
     * Concatenates two user provided strings free of leading and trailing
     * whitespaces.
     * 
     * @param str1 First string
     * @param str2 Second string
     * @return Length of concatenated string as an integer
     */
    public int addTogether(String str1, String str2) {
        String trimmedStringsConcat = "";
        trimmedStringsConcat += str1.strip() + str2.strip();
        return trimmedStringsConcat.length();
    }

    /**
     * Concatenates first initial, last initial, pet's initial and pet's year of
     * birth.
     * 
     * @param firstName Owner's first name
     * @param lastName  Owner's last name
     * @param petName   Pet's name
     * @param year      Year pet was born
     * @return Unique animal identifier as a string e.g "EMD2010"
     */
    public String idProcessing(String firstName, String lastName, String petName, int year) {
        String firstNameInitial = String.valueOf(firstName.charAt(0));
        String lastNameInitial = String.valueOf(lastName.charAt(0));
        String petNameInitial = String.valueOf(petName.charAt(0));
        String yearString = String.valueOf(year);

        String animalUniqueIdentifier = firstNameInitial + lastNameInitial + petNameInitial + yearString;
        return animalUniqueIdentifier;
    }

    /**
     * Encodes recipe ingredients by shortening them to three letters and replacing
     * all vowels with a "z".
     * 
     * @param ingredient Recipe ingredient
     * @return Code word as a string
     */
    public String secretCode(String ingredient) {
        String codeWord = ingredient.substring(0, 3).toLowerCase().replaceAll("[aeiou]", "z");
        return codeWord;
    }

    public static void main(String[] args) {
        JavaStrings test = new JavaStrings();
        System.out.println(test.addTogether("Hello ", "World"));
        System.out.println(test.idProcessing("Alice", "Bob", "Bob", 2016));
        System.out.println(test.secretCode("carrot"));
    }
}
