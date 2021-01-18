/**
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">
 *         nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 * @since 1.0
 */

/*
 * Animal is a simple program that retrieves characteristics such as number of
 * legs and color of an animal provided by the user
 */

public class Animal {
    /**
     * This prints the number of legs and the color of an animal to the terminal
     * window.
     * 
     * @param color Color of the animal
     * @param legs  Number of legs the animal has, default is 4
     */

    private int legs = 4;
    private String color;

    public Animal(String color, int legs) {
        this.color = color;
        this.legs = legs;
    }

    public Animal(String color) {
        this.color = color;
    }

    public int getLegs() {
        return this.legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
} // End of class declaration
