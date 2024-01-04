package erehwon;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private final int size;
    private double fractionVacant;
    private double fractionRed;
    private double happinessThreshold;
    private final int neighbourhoodDistance;
    private boolean isEnd = false;
    private int residentsMovingPerTimeStep;
    private int residentsMovingPerSimulation;
    private final List<Double> happinessValues = new ArrayList<>(1);
    private final static int MAX_REPETITIONS = 20;


    /**
     * Creates a new square grid with the given specifications and randomized cell colors.
     *
     * @param size                 int, the size of the grid, both length and width. size > 0.
     * @param neighborhoodDistance int, the distance within which cells are considered neighbors.
     *                             neighbourhoodDistance > 0.
     * @param fractionVacant       double, the fraction of vacant cells out of the total
     *                             number of cells in the grid.
     *                             1 >= fractionVacant >= 0.
     * @param fractionRed          double, the fraction of red cells out of the non-vacant cells
     *                             in the grid.
     *                             1 >= fractionRed >= 0.
     * @param happinessThreshold   double, the fraction of neighbouring cells that need to be
     *                             of the same color as the cell for the cell to be happy.
     *                             1 >= happinessThreshold >= 0.
     */
    public RedBlueGrid(int size,
                       int neighborhoodDistance,
                       double fractionVacant,
                       double fractionRed,
                       double happinessThreshold) {

        this.grid = new Color[size][size];
        this.fractionRed = fractionRed;
        this.fractionVacant = fractionVacant;
        this.happinessThreshold = happinessThreshold;
        this.neighbourhoodDistance = neighborhoodDistance;
        this.size = size;

        int numberCells = size * size;
        int numberVacant = (int) (numberCells * fractionVacant);
        int numberRed = (int) ((fractionRed) * (numberCells - numberVacant));
        int numberBlue = numberCells - numberRed - numberVacant;

        Random randomizeColour = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = COLORS[0];
            }
        }

        for (int i = 0; i < numberRed; i++) {

            int row = randomizeColour.nextInt(size);
            int col = randomizeColour.nextInt(size);

            while (this.grid[row][col] != COLORS[0]) {

                row = randomizeColour.nextInt(size);
                col = randomizeColour.nextInt(size);

            }
            this.grid[row][col] = COLORS[1];
        }

        for (int i = 0; i < numberBlue; i++) {

            int row = randomizeColour.nextInt(size);
            int col = randomizeColour.nextInt(size);

            while (this.grid[row][col] != Color.WHITE) {

                row = randomizeColour.nextInt(size);
                col = randomizeColour.nextInt(size);

            }
            this.grid[row][col] = COLORS[2];
        }
    }


    /**
     * Gets the color of the cell with the given coordinates.
     *
     * @param row   int, the row number of the cell whose color we need.
     *              0 <= row < size.
     * @param col   int, the column number of the cell whose color we need.
     *              0 <= col < size.
     * @return Color, the color of the cell with the given coordinates. One of RED, BLUE or WHITE.
     **/
    public Color getColor(int row, int col) {
        return this.grid[row][col];
    }


    /**
     * Changes the color of the given cell only if it is vacant
     * and returns true if the change was successful and false otherwise.
     *
     * @param row       int, the row number of the cell whose color we want to set.
     *                  0 <= row < size.
     * @param col       int, the column number of the cell whose color we want to set.
     *                  0 <= col < size.
     * @param color     Color, the color that we want to set the cell to.
     *                  One of RED, BLUE or WHITE.
     * @return boolean, returns true if the color change was successful and false otherwise.
     */
    public boolean setColor(int row, int col, Color color) {
        if (getColor(row, col) == COLORS[0]) {
            this.grid[row][col] = color;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Shifts the color of the given cell in the order:
     * WHITE -> RED -> BLUE -> WHITE -> ...
     *
     * @param row   int, the row number of the cell we want to shift.
     *              0 < row < size.
     * @param col   int, the column number of the cell we want to shift.
     *              0 < col < size.
     */
    public void shiftColor(int row, int col) {
        if (this.getColor(row, col) == COLORS[0]) {
            this.grid[row][col] = COLORS[1];
        } else if (this.getColor(row, col) == COLORS[1]) {
            this.grid[row][col] = COLORS[2];
        } else {
            this.grid[row][col] = COLORS[0];
        }
    }


    /**
     * Recolours the cells according to the given requirements,
     * and changes the happiness threshold to a new given value.
     *
     * @param fractionVacant        double, the fraction of vacant cells in the grid.
     *                              1 >= fractionVacant >= 0.
     * @param fractionRed           double, the fraction of red cells out of
     *                              the non-vacant cells in the grid.
     *                              1 >= fractionRed >= 0.
     * @param happinessThreshold    double, the fraction of neighbouring cells that need to be
     *                              of the same color as the cell for the cell to be happy.
     *                              1 >= happinessThreshold >= 0.
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {

        this.isEnd = false;
        this.grid = new Color[size][size];
        this.fractionRed = fractionRed;
        this.fractionVacant = fractionVacant;
        this.happinessThreshold = happinessThreshold;


        int numberCells = size * size;
        int numberVacant = (int) (numberCells * (fractionVacant));
        int numberRed = (int) ((fractionRed) * (numberCells - numberVacant));
        int numberBlue = numberCells - numberRed - numberVacant;

        Random randomizeColour = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = COLORS[0];
            }
        }

        for (int i = 0; i < numberBlue; i++) {

            int row = randomizeColour.nextInt(size);
            int col = randomizeColour.nextInt(size);

            while (this.grid[row][col] != COLORS[0]) {

                row = randomizeColour.nextInt(size);
                col = randomizeColour.nextInt(size);

            }
            this.grid[row][col] = COLORS[2];
        }

        for (int i = 0; i < numberRed; i++) {

            int row = randomizeColour.nextInt(size);
            int col = randomizeColour.nextInt(size);

            while (this.grid[row][col] != COLORS[0]) {

                row = randomizeColour.nextInt(size);
                col = randomizeColour.nextInt(size);

            }
            this.grid[row][col] = COLORS[1];
        }
    }


    /**
     * Determines whether the given non-vacant cell is happy.
     * A cell is happy when its neighbours have the same color as it.
     *
     * @param row   int, the row number of the desired cell on the color grid.
     *              0 <= row < size.
     * @param col   int, the column number of the desired cell on the color grid.
     *              0 <= col < size.
     * @return boolean, true if the cell is happy and false otherwise.
     */
    public boolean isHappy(int row, int col) {
        int[] traveller = new int[]{row, col};

        int goodNeighbourNumber = 0;
        int neighbourNumber = 0;
        Color color = this.getColor(row, col);

        int rowAdjuster = 1;
        int colAdjuster = 1;
        for (int i = 1; i <= this.neighbourhoodDistance; i++) {
            int reachedEnd = 0;
            while (!(reachedEnd == 2)) {
                traveller[0] += rowAdjuster;
                traveller[1] += colAdjuster;
                if (Arrays.equals(traveller, new int[]{row + i, col + i})) {
                    reachedEnd++;
                    if (reachedEnd == 2) {
                        colAdjuster = 1;
                        rowAdjuster = 1;
                    } else {
                        rowAdjuster = -1;
                        colAdjuster = 0;
                    }
                }
                if (Arrays.equals(traveller, new int[]{row - i, col + i})) {
                    rowAdjuster = 0;
                    colAdjuster = -1;
                }
                if (Arrays.equals(traveller, new int[]{row - i, col - i})) {
                    rowAdjuster = 1;
                    colAdjuster = 0;
                }
                if (Arrays.equals(traveller, new int[]{row + i, col - i})) {
                    rowAdjuster = 0;
                    colAdjuster = 1;
                }

                if (isValid(traveller[0], traveller[1])) {
                    neighbourNumber++;
                    if (this.getColor(traveller[0], traveller[1]) == color) {
                        goodNeighbourNumber++;
                    }
                }
            }
        }
        if (neighbourNumber == 0) {
            return false;
        }
        return (((double) goodNeighbourNumber) / (double) neighbourNumber >= this.happinessThreshold);
    }


    /**
     * Determines the fraction of happy residents in Erehwon.
     *
     * @return double, the fraction of happy residents in Erehwon.
     */
    public double fractionHappy() {
        int numberHappy = 0;
        int numNotVacant = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.getColor(i, j) != COLORS[0]) {
                    numNotVacant++;
                    if (this.isHappy(i, j)) {
                        numberHappy++;
                    }
                }
            }
        }
        if (numNotVacant == 0) {
            return 0.0;
        }
        return ((double) numberHappy / (double) numNotVacant);
    }


    /**
     * Simulates one time step of the migration of unhappy residents in Erehwon.
     * Shifts as many random unhappy residents
     * as there vacant cells to those vacant cells.
     */
    public void oneTimeStep() {
        this.residentsMovingPerTimeStep = 0;

        int[][] vacantSet = this.getVacantSet();

        if (!(vacantSet.length > this.size * this.size)) {
            int vacantAvailable = vacantSet.length;
            int[][] unhappySet = this.getUnhappySet();
            if (!(unhappySet.length > size * size)) {
                int[][] randomUnhappySet = chooseRandom(vacantAvailable, unhappySet);
                for (int i = 0; i < randomUnhappySet.length; i++) {
                    this.residentsMovingPerTimeStep++;
                    this.shiftResident(randomUnhappySet[i], vacantSet[i]);
                }
            }
        }
    }


    /**
     * Simulates multiple time steps of movement between all cells in the color grid
     * where unhappy cell migrating to randomly chosen vacant cells.
     *
     * @param numSteps  int, the number of migration time steps of
     *                  the unhappy residents of Erehwon.
     */
    public void simulate(int numSteps) {

        this.residentsMovingPerSimulation = 0;
        double initialValue;
        int numSame;

        for (int i = 0; i < numSteps; i++) {
            this.oneTimeStep();

            this.residentsMovingPerSimulation += this.residentsMovingPerTimeStep;

            double fractionHappy = fractionHappy();
            if(fractionHappy == 1.0) {
                isEnd = true;
            }

            this.happinessValues.add(0, fractionHappy);

            try {
                initialValue = this.happinessValues.get(i);
                numSame = 0;
                for(int j = 0; j < MAX_REPETITIONS; j++){
                    if(initialValue == this.happinessValues.get(j)){
                        numSame++;
                    }
                }
                if(numSame == MAX_REPETITIONS) {
                    isEnd = true;
                }
            } catch (Exception ignored) {
            }
        }
    }



    /**
     * Chooses a given number of integer array coordinates
     * randomly from a given set of coordinates.
     *
     * @param numberRequired    int, the number of elements required to be randomly chosen.
     * @param unrandomSet       int, the set of elements from which we must randomly
     *                          choose elements from.
     * @return randomArray int[][], an array of randomly chosen coordinates.
     */
    private int[][] chooseRandom(int numberRequired, int[][] unrandomSet) {
        Random random = new Random();
        int[][] randomArray = new int[numberRequired][2];

        for (int i = 0; i < numberRequired; i++) {
            int randomIndex = random.nextInt(unrandomSet.length);
            randomArray[i] = unrandomSet[randomIndex];
        }
        return randomArray;
    }


    /**
     * Returns the set of coordinates of all vacant cells in the grid.
     *
     * @return int[][], the array of coordinates of all cells that are vacant.
     */
    private int[][] getVacantSet() {

        int openCells = 0;
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getColor(i, j) == COLORS[0]) {
                    openCells++;
                }
            }
        }

        if (openCells == 0) {
            return new int[(size * size) + 1][1];
        }

        int[][] vacantCells = new int[openCells][2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getColor(i, j) == COLORS[0]) {
                    int[] cell = new int[2];
                    cell[0] = i;
                    cell[1] = j;
                    vacantCells[index] = cell;
                    index++;
                }
            }
        }
        return vacantCells;
    }


    /**
     * Returns the set of coordinates of all unhappy cells in the grid.
     *
     * @return int[][], the array of coordinates of all cells that are unhappy.
     */
    private int[][] getUnhappySet() {

        int unhappyCells = 0;
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!isHappy(i, j)) {
                    unhappyCells++;
                }
            }
        }

        if (unhappyCells == 0) {
            return new int[(size * size) + 1][1];
        }

        int[][] unhappyResidents = new int[unhappyCells][2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!isHappy(i, j)) {
                    int[] cell = new int[2];
                    cell[0] = i;
                    cell[1] = j;
                    unhappyResidents[index] = cell;
                    index++;
                }
            }
        }
        return unhappyResidents;
    }


    /**
     * Moves residents from the given coordinate to another given coordinate on the grid.
     *
     * @param from  int[], the coordinates of the cell that needs to be shifted.
     *              Coordinates must be valid.
     * @param to    int[], the coordinates of the destination cell.
     *              Coordinates must be valid.
     */
    private void shiftResident(int[] from, int[] to) {
        this.grid[to[0]][to[1]] = getColor(from[0], from[1]);
        this.grid[from[0]][from[1]] = COLORS[0];
    }


    /**
     * Checks if a given coordinate is within the existing grid.
     *
     * @param row int, the row number of the cell to check.
     *            0 <= row < size.
     * @param col int, the column number of the cell to check.
     *            0 <= col < size.
     * @return boolean, true if the cell is within the grid and false otherwise.
     */
    private boolean isValid(int row, int col) {
        if (row >= 0 && row < this.grid.length) {
            return (col >= 0 && col < this.grid[row].length);
        } else {
            return false;
        }
    }


    /**
     * Gets the fraction of cells in Erehwon that are vacant.
     *
     * @return double, the fraction of cells in Erehwon that are WHITE.
     */
    public double getFractionVacant() {
        return this.fractionVacant;
    }

    /**
     * Gets the fraction of cells out of the non-vacant cells
     * in Erehwon that are RED.
     *
     * @return double, the fraction of non-vacant cells that are RED.
     */
    public double getFractionRed() {
        return this.fractionRed;
    }

    /**
     * Gets the fraction of cells out of the non-vacant cells
     * in Erehwon that are BLUE.
     *
     * @return double, the fraction of non-vacant cells that are RED.
     */
    public double getFractionBlue() {
        if (this.fractionVacant == 1.0) {
            return 0.0;
        } else {
            return (1.0 - this.fractionRed);
        }
    }

    /**
     * Gets the happiness threshold for the residents of Erehwon.
     *
     * @return double, the happiness threshold. 0 <= happinessThreshold <= 1.
     */
    public double getHappinessThreshold() {
        return this.happinessThreshold;
    }

    /**
     * Returns the number of residents of Erehwon that migrated
     * in one time step.
     *
     * @return int, the number of residents that moved in one time step.
     */
    public int getResidentsMovingPerTimeStep() {
        return this.residentsMovingPerTimeStep;
    }

    /**
     * Returns the total number of times the residents of Erehwon
     * migrated in one full simulation.
     *
     * @return int, the total number of times residents moved in one simulation.
     */
    public int getResidentsMovingPerSimulation() {
        return this.residentsMovingPerSimulation;
    }

    /**
     * Returns true if the simulation needs to end and false otherwise.
     * The simulation need to end if all residents are happy or
     * if the simulation cannot change the happiness of its residents
     * for 10 time steps.
     *
     * @return boolean, true if the simulation need to end, and false otherwise.
     */
    public boolean getEnd() {
        return this.isEnd;
    }

}

