package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class StarterTests {

    @Test
    public void testCorrectCreation() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE){
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED){
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = (double) numberVacant / (100);

        if(numberVacant != 100){
            fractionRed = (double) numberRed / (100 - numberVacant);
            fractionBlue = (double) numberBlue / (100 - numberVacant);
        }

        assertEquals(0.3, fractionVacant);
        assertEquals(0.4, fractionRed);
        assertEquals(0.6, fractionBlue);
    }

    @Test
    public void testSizeOne() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 1, 0, 0.35);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 1; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE){
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED){
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = numberVacant;

        if(numberVacant != 1){
            fractionRed = (double) numberRed / (1 - numberVacant);
            fractionBlue = (double) numberBlue / (1 - numberVacant);
        }

        assertEquals(1, fractionVacant);
        assertEquals(0, fractionRed);
        assertEquals(0, fractionBlue);
    }

    @Test
    public void testAllBlue() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 0, 0, 0.35);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 1; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE){
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED){
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = numberVacant;

        if(numberVacant != 1){
            fractionRed = (double) numberRed / (1 - numberVacant);
            fractionBlue = (double) numberBlue / (1 - numberVacant);
        }

        assertEquals(0, fractionVacant);
        assertEquals(0, fractionRed);
        assertEquals(1, fractionBlue);
    }

    @Test
    public void testAllRed() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 0, 1, 0.35);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 1; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE){
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED){
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = numberVacant;

        if(numberVacant != 1){
            fractionRed = (double) numberRed / (1 - numberVacant);
            fractionBlue = (double) numberBlue / (1 - numberVacant);
        }

        assertEquals(0, fractionVacant);
        assertEquals(1, fractionRed);
        assertEquals(0, fractionBlue);
    }


    @Test
    public void testAllVacant() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 1, 0, 0.35);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 1; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE){
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED){
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = numberVacant;

        if(numberVacant != 1){
            fractionRed = (double) numberRed / (1 - numberVacant);
            fractionBlue = (double) numberBlue / (1 - numberVacant);
        }

        assertEquals(1, fractionVacant);
        assertEquals(0, fractionRed);
        assertEquals(0, fractionBlue);
    }

    @Test
    public void testSetColor() {
        RedBlueGrid rbGrid1 = new RedBlueGrid(1, 1, 1, 0, 0.35);
        RedBlueGrid rbGrid2 = new RedBlueGrid(1, 1, 0, 1, 0.35);
        RedBlueGrid rbGrid3 = new RedBlueGrid(1, 1, 1, 0, 0.35);
        RedBlueGrid rbGrid4 = new RedBlueGrid(1, 1, 1, 0, 0.35);

        assertTrue(rbGrid1.setColor(0, 0, Color.RED));
        assertEquals(rbGrid1.getColor(0, 0), Color.RED);

        assertTrue(rbGrid3.setColor(0, 0, Color.BLUE));
        assertEquals(rbGrid3.getColor(0, 0), Color.BLUE);

        assertTrue(rbGrid4.setColor(0, 0, Color.BLUE));
        assertEquals(rbGrid4.getColor(0, 0), Color.BLUE);

        assertFalse(rbGrid2.setColor(0, 0, Color.BLUE));
        assertEquals(rbGrid2.getColor(0, 0), Color.RED);

        assertFalse(rbGrid2.setColor(0, 0, Color.RED));
        assertEquals(rbGrid2.getColor(0, 0), Color.RED);

        assertFalse(rbGrid2.setColor(0, 0, Color.WHITE));
        assertEquals(rbGrid2.getColor(0, 0), Color.RED);

    }
}



