package edu.ucalgary.ensf409;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class StringProcessor {

    private final String storedString;

    // Constructor that stores a single string
    public StringProcessor(String input) {
        this.storedString = new String(input);
    }

    // Adds together stored and inputted string stripped of leading and trailing
    // whitespaces
    // Returns mirror of combined string
    public String addTogetherMirror(String inputString) {
        String combined = storedString.strip() + inputString.strip();
        String mirror = new StringBuffer(combined).reverse().toString();
        return mirror.toLowerCase();
    }

    // Creates unique string identifier that consists of first and last initial,
    // first initial of pet's name and pet's year of birth
    public static String idProcessing(String firstName, String lastName, String petName, int year) {
        String[] names = { firstName.strip(), lastName.strip(), petName.strip() };
        Pattern namePattern = Pattern.compile("^[A-Z](('|-|.|\\s|.\\s)?[a-zA-Z]*[a-z])*$");

        if (String.valueOf(year).length() != 4) {
            throw new IllegalArgumentException("Invalid pet birth year format");
        } else if (year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Pet birth year cannot exceed current year");
        }

        for (String name : names) {
            Matcher matcher = namePattern.matcher(name);

            if (name.length() < 2 || name.length() > 26) {
                throw new IllegalArgumentException("Name must be a minimum of two letters and a maximum of 26 letters");
            }

            if (!matcher.find()) {
                throw new IllegalArgumentException(
                        "Name must begin with capital letter, end with a letter, and not contain illegal punctuation");
            }
        }

        String petID = new String(String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0))
                + String.valueOf(petName.charAt(0)) + String.valueOf(year));
        return petID;
    }

    // Returns a string where each character in the stored string is shifted by a
    // user-input offset
    public String secretCode(int offset) {
        StringBuilder encodedString = new StringBuilder(storedString);

        offset = offset % 26 + 26;

        for (int i = 0; i < encodedString.length(); i++) {
            char ch = encodedString.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    encodedString.setCharAt(i, (char) ('A' + (encodedString.charAt(i) - 'A' + offset) % 26));
                } else {
                    encodedString.setCharAt(i, (char) ('a' + (encodedString.charAt(i) - 'a' + offset) % 26));
                }
            } else {
                encodedString.setCharAt(i, encodedString.charAt(i));
            }
        }
        return String.valueOf(encodedString);
    }

    // Getter for stored string
    public String getStoredString() {
        return this.storedString;
    }

}
