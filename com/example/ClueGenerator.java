package com.example;

public class ClueGenerator {

    private static final int MAX_CLUES = 5;
    private static final int MIN_CLUES = 3;
    private final int NUM_BOXES;

    private int numClues;
    private Clue[] clues;

    public ClueGenerator() {
        int numBoxes = 0;
        for (int i = Puzzle.WIDTH; i > 0; i--) {
            numBoxes += i;
        }
        NUM_BOXES = numBoxes;
        numClues = (int) (Math.random() * (MAX_CLUES - MIN_CLUES + 1) + MIN_CLUES);
        clues = new Clue[numClues];
    }


    public Clue[] generateClues() {
        //split the number of clues into the different types
        int either = (int) (Math.random() * (numClues - 1) + 1);
        int exclusive = (int) (Math.random() * (numClues - either - 1) + 1);
        int exactlyOne = numClues - either - exclusive;

        //generate the clues
        Clue[] eitherClues = generateEither(either);
        Clue[] exclusiveClues = generateExclusive(exclusive);
        Clue[] exactlyOneClues = generateExactlyOne(exactlyOne);

        //combine the clues
        int index = 0;
        for(int i = 0; i < numClues; i++){
            if(i < either){
                clues[i] = eitherClues[i];
            }else if(i < either + exclusive){
                clues[i] = exclusiveClues[i - either];
            }else{
                clues[i] = exactlyOneClues[i - either - exclusive];
            }
        }

        return clues;


    }

    /**
     * Generate clues where x is either y or z
     * @param num number of clues to generate
     * @return array of clues
     */
    private Clue[] generateEither(int num){
        Clue[] eitherClues = new Clue[num];

        for(int i = 0; i < num; i++){
            //check the solution to make the clue, 1-NUM_BOXES
            int boxNum = (int) (Math.random() * NUM_BOXES);

            //get the int array of the box
            int[] box = new int[3];
            int index = 0;
            for(int j = 0; j < Puzzle.WIDTH; j++){
                for(int k = 0; k < Puzzle.HEIGHT - j; k++){
                    for(int l = 0; l < Puzzle.BOX_SIZE; l++){
                        if(index == boxNum){
                            box = Main.getPuzzle().getSolutionBoard()[j][k];
                            break;
                        }
                        index++;
                    }
                }
            }

            //pick draw two random numbers from the box
            int num1 = (int) (Math.random() * Puzzle.BOX_SIZE);
            
            //get the answer
            int answer1 = box[num1];

            //get a wrong answer
            int answer2 = (answer1 + (int) Math.rint(Puzzle.BOX_SIZE - 1))%Puzzle.BOX_SIZE;
            
            
            

            //make the clue
            eitherClues[i] = new Clue(String.format("box %d row %d is either %d or %d.\n", boxNum + 1,num1 + 1, answer1  + 1, answer2 + 1), boxNum, new int[]{num1, answer2,}, ClueType.EITHER);

        }
        return eitherClues;
    }

    /**
     * Generate clues where x is in the same row as y
     * @param num
     * @return
     */
    private Clue[] generateExclusive(int num){
        Clue[] exclusiveClues = new Clue[num];
        
        for(int i = 0; i < num; i++){
            //we need two boxes for this
        int boxNum1 = (int) (Math.random() * NUM_BOXES);
        int boxNum2 = (int) (Math.random() * NUM_BOXES);
        while(boxNum1 == boxNum2){
            boxNum2 = (int) (Math.random() * NUM_BOXES);
        }

        //get the int array of the box
        int[] box1 = new int[3];
        int[] box2 = new int[3];

        int index = 0;
            for(int j = 0; j < Puzzle.WIDTH; j++){
                for(int k = 0; k < Puzzle.HEIGHT - j; k++){
                    for(int l = 0; l < Puzzle.BOX_SIZE; l++){
                        if(index == boxNum1){
                            box1 = Main.getPuzzle().getSolutionBoard()[j][k];
                            break;
                        }else if( index == boxNum2){
                            box2 = Main.getPuzzle().getSolutionBoard()[j][k];
                            break;
                        }
                        index++;
                    }
                }
            }

            //pick a random number from the first box
            int num1 = (int) (Math.random() * Puzzle.BOX_SIZE);
            
            //get the answer
            int answer1 = box1[num1];

            //make the clue
            exclusiveClues[i] = new Clue(String.format("box %d row %d is the same as box %d row %d.\n", boxNum1 + 1,num1 + 1, boxNum2 + 1, num1 + 1), boxNum1, new int[]{num1, boxNum2, num1}, ClueType.EXCLUSIVE);
        }
        


        return exclusiveClues;
    }

    /**
     * Generate clues where x is y
     * @param num number of clues to generate
     * @return
     */
    private Clue[] generateExactlyOne(int num){
        Clue[] exactlyOneClues = new Clue[num];
        for(int i = 0; i < num; i++){
            //check the solution to make the clue, 1-NUM_BOXES
            int boxNum = (int) (Math.random() * NUM_BOXES);

            //get the int array of the box
            int[] box = new int[3];
            int index = 0;
            for(int j = 0; j < Puzzle.WIDTH; j++){
                for(int k = 0; k < Puzzle.HEIGHT - j; k++){
                    for(int l = 0; l < Puzzle.BOX_SIZE; l++){
                        if(index == boxNum){
                            box = Main.getPuzzle().getSolutionBoard()[j][k];
                            break;
                        }
                        index++;
                    }
                }
            }

            //reveal exactly one solution
            int num1 = (int) (Math.random() * Puzzle.BOX_SIZE);

            //get the answer
            int answer1 = box[num1];

            //make the clue
            exactlyOneClues[i] = new Clue(String.format("box %d row %d is %d.\n", boxNum + 1,num1 + 1, answer1  + 1), boxNum, new int[]{num1}, ClueType.EXACTLY_ONE);
        }
        return exactlyOneClues;
    }


    public int getNumClues() {
        return numClues;
    }
    
}
