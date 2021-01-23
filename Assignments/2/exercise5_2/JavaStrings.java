public class JavaStrings {

    public int addTogether(String str1, String str2) {
        String trimmedStringsConcat = "";
        trimmedStringsConcat += str1.strip() + str2.strip();
        return trimmedStringsConcat.length();
    }

    public String idProcessing(String firstName, String lastName, String petName, int year) {
        String firstNameInitial = String.valueOf(firstName.charAt(0));
        String lastNameInitial = String.valueOf(lastName.charAt(0));
        String petNameInitial = String.valueOf(petName.charAt(0));
        String yearString = String.valueOf(year);

        String animalUniqueIdentifier = firstNameInitial + lastNameInitial + petNameInitial + yearString;
        return animalUniqueIdentifier;
    }

    public String secretCode(String ingredient) {
        String codeWord = ingredient.substring(0, 3).toLowerCase().replaceAll("[aeiou]", "z");
        return codeWord;
    }

    public static void main(String[] args) {
        JavaStrings test = new JavaStrings();
        System.out.println(test.addTogether(" Hello ", "World "));
        System.out.println(test.idProcessing("Emily", "Marasco", "Dog", 2012));
        System.out.println(test.secretCode("APPLE"));
    }
}
