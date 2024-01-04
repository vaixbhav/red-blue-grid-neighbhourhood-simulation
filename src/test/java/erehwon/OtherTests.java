package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class OtherTests {
    @Test
    public void testShiftWhite() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 1, 0, 0.35);

        Color initColor= rbGrid.getColor(0, 0);
        Color nextColor = Color.WHITE;

        rbGrid.shiftColor(0, 0);

        if(initColor == Color.WHITE) {
            nextColor = Color.RED;
        } else if(initColor == Color.RED) {
            nextColor = Color.BLUE;
        }

        assertEquals(rbGrid.getColor(0, 0), nextColor);

    }

    @Test
    public void testShiftRed() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 0, 1, 0.35);

        Color initColor= rbGrid.getColor(0, 0);
        Color nextColor = Color.WHITE;

        rbGrid.shiftColor(0, 0);

        if(initColor == Color.WHITE) {
            nextColor = Color.RED;
        } else if(initColor == Color.RED) {
            nextColor = Color.BLUE;
        }

        assertEquals(rbGrid.getColor(0, 0), nextColor);

    }

    @Test
    public void testShiftBlue() {
        RedBlueGrid rbGrid = new RedBlueGrid(1, 1, 0, 0, 0.35);

        Color initColor= rbGrid.getColor(0, 0);
        Color nextColor;

        rbGrid.shiftColor(0, 0);

        if(initColor == Color.WHITE) {
            nextColor = Color.RED;
        } else if(initColor == Color.RED) {
            nextColor = Color.BLUE;
        } else {
            nextColor = Color.WHITE;
        }

        assertEquals(rbGrid.getColor(0, 0), nextColor);

    }

    @Test
    public void resetSameValues() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 0.5, 0.5, 0.5);
        rbGrid.reset(0.5, 0.5, 0.5);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(rbGrid.getColor(i, j) == Color.WHITE) {
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED) {
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = (double) numberVacant / (64);

        if(numberVacant != 64){
            fractionRed = (double) numberRed / (64 - numberVacant);
            fractionBlue = (double) numberBlue / (64 - numberVacant);
        }

        assertEquals(0.5, fractionVacant);
        assertEquals(0.5, fractionRed);
        assertEquals(0.5, fractionBlue);
        assertEquals(0.5, rbGrid.getHappinessThreshold());
    }

    @Test
    public void resetDifferentValues() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 0, 0, 0.5);
        rbGrid.reset(0.5, 0.5, 0);

        int numberRed = 0;
        int numberVacant = 0;
        int numberBlue = 0;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(rbGrid.getColor(i, j) == Color.WHITE) {
                    numberVacant++;
                } else if(rbGrid.getColor(i, j) == Color.RED) {
                    numberRed++;
                } else {
                    numberBlue++;
                }
            }
        }

        double fractionRed = 0;
        double fractionBlue = 0;
        double fractionVacant = (double) numberVacant / (64);

        if(numberVacant != 64){
            fractionRed = (double) numberRed / (64 - numberVacant);
            fractionBlue = (double) numberBlue / (64 - numberVacant);
        }

        assertEquals(0.5, fractionVacant);
        assertEquals(0.5, fractionRed);
        assertEquals(0.5, fractionBlue);
        assertEquals(0, rbGrid.getHappinessThreshold());
    }

    @Test
    public void testIsHappySizeTwo() {
        RedBlueGrid rbGrid = new RedBlueGrid(2, 1, 0.5, 0.5, 0.3);
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(rbGrid.getColor(i, j) != Color.WHITE) {
                    assertFalse(rbGrid.isHappy(i, j));
                }
            }
        }
    }

    @Test
    public void testIsHappySizeOne() {
        RedBlueGrid rbGridVacant = new RedBlueGrid(1, 1, 1, 0, 0.3);
        RedBlueGrid rbGridRed = new RedBlueGrid(1, 1, 0, 1, 0.3);
        RedBlueGrid rbGridBlue = new RedBlueGrid(1, 1, 0, 0, 0.3);

        assertFalse(rbGridVacant.isHappy(0, 0));
        assertFalse(rbGridRed.isHappy(0, 0));
        assertFalse(rbGridBlue.isHappy(0, 0));
    }

    @Test
    public void testFractionHappyAllVacant() {
        RedBlueGrid rbGridVacant = new RedBlueGrid(10, 1, 1, 0, 0.3);

        assertEquals(0.0, rbGridVacant.fractionHappy());
    }
    @Test
    public void testFractionHappy() {
        RedBlueGrid rbGrid2 = new RedBlueGrid(2, 1, 0.0, 0.75, 0.3);
        assertEquals(0.75, rbGrid2.fractionHappy());
    }

    @Test
    public void testOneTimeStep() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 0.5, 0.5, 0.3);
        rbGrid.oneTimeStep();
        assertEquals(32, rbGrid.getResidentsMovingPerTimeStep());
    }

    @Test
    public void testOneTimeStepAllVacant() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 1, 1, 0.3);
        rbGrid.oneTimeStep();
        assertEquals(0, rbGrid.getResidentsMovingPerTimeStep());
    }

    @Test
    public void testOneTimeStepAllRed() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 0, 1, 0.3);
        rbGrid.oneTimeStep();
        assertEquals(0, rbGrid.getResidentsMovingPerTimeStep());
    }

    @Test
    public void testOneTimeStepAllBlue() {
        RedBlueGrid rbGrid = new RedBlueGrid(8, 1, 0, 0, 0.5);
        rbGrid.oneTimeStep();
        assertEquals(0, rbGrid.getResidentsMovingPerTimeStep());
    }

    @Test
    public void testGetFraction() {
        RedBlueGrid rbGrid1 = new RedBlueGrid(8, 1, 0.3, 0.4, 0.5);
        RedBlueGrid rbGrid2 = new RedBlueGrid(8, 1, 1, 0, 0.5);

        assertEquals(0.3, rbGrid1.getFractionVacant());

        assertEquals(0.4, rbGrid1.getFractionRed());

        assertEquals(0.6, rbGrid1.getFractionBlue());
        assertEquals(0.0, rbGrid2.getFractionBlue());



    }

    @Test
    public void testSimulate() {
        RedBlueGrid rbGrid1 = new RedBlueGrid(2, 1, 0, 0.5, 1.0/3.0);
        RedBlueGrid rbGrid2 = new RedBlueGrid(100, 2, 0.3, 0.4, 0.3);
        RedBlueGrid rbGrid3 = new RedBlueGrid(8, 1, 0.5, 0.5, 0.3);

        rbGrid1.simulate(100);
        rbGrid2.simulate(100);
        rbGrid3.simulate(100);

        assertEquals(0, rbGrid1.getResidentsMovingPerSimulation());
        assertEquals(100 * (100 * 100 * 0.3), rbGrid2.getResidentsMovingPerSimulation());
        assertEquals(100 * (8 * 8 * 0.5), rbGrid3.getResidentsMovingPerSimulation());
    }

    @Test
    public void testEndPlateau() {
        RedBlueGrid rbGrid = new RedBlueGrid(2, 1, 0.5, 0.5, 1);

        rbGrid.simulate(50);
        assertTrue(rbGrid.getEnd());
    }

    @Test
    public void testEndAllHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(2, 1, 0, 1, 0.75);

        rbGrid.simulate(25);
        assertTrue(rbGrid.getEnd());
    }
}