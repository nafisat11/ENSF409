/**
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">
 *         nafisa.tabassum@ucalgary.ca</a>
 * @version 1.1
 * @since 1.0
 */

/*
 * Creature is a simple program that prints a creature
 */

public class Creature {
    /**
     * This prints "This is a placeholder for Creature " and the creature to the
     * terminal window.
     * 
     * @param args Handles command-line argument
     */
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        String myType = myAnimal.animalType();
        System.out.println("This is a placeholder for Creature " + myType);
    }
} // End of class declaration

class Animal {
    private String animalType = "dog";

    public String animalType() {
        return animalType;
    }
}
