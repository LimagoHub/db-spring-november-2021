package de.client;

import de.tiere.Schwein;

public class SchweineTest {

    public static void main(String[] args) {
        Schwein piggy = new Schwein("Miss Piggy");
        System.out.println(piggy);

        piggy.fressen(); // Synchron
        System.out.println(piggy);

        while (piggy.getGewicht() < 11){}
        System.out.println(piggy);

    }
}
