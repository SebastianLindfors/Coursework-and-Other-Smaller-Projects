
/**
 * This class is a solution to codewars kata: 4K-Snail
 * https://www.codewars.com/kata/521c2db8ddc89b9b7a0000c1/
 *
 * Author: Sebastian Lindfors
 */
public class Snail {

    public static int[] snail(int[][] array) {

        if (array.length == 0) return new int[] {};

        int[] output = new int[array.length * array.length];
        int outputPointer = array.length;

        char[] direction = {'S', 'W', 'N', 'E'};

        int steps = array.length;
        int xCoordinate = 0;
        int yCoordinate = array.length - 1;

        for (int i = 0; i < steps; i++) {
            output[i] = array[0][i];
        }


        steps--;

        int straights = 0;
        while (steps > 0) {
            char currentDirection = direction[straights % 4];
            switch (currentDirection) {
                case 'S':
                    for (int i = 0; i < steps; i++) {
                        xCoordinate++;
                        output[outputPointer++] = array[xCoordinate][yCoordinate];
                    }
                    straights++;
                    break;
                case 'W':
                    for (int i = 0; i < steps; i++) {
                        yCoordinate--;
                        output[outputPointer++] = array[xCoordinate][yCoordinate];
                    }
                    straights++;
                    steps--;
                    break;
                case 'N':
                    for (int i = 0; i < steps; i++) {
                        xCoordinate--;
                        output[outputPointer++] = array[xCoordinate][yCoordinate];
                    }
                    straights++;
                    break;
                case 'E':
                    for (int i = 0; i < steps; i++) {
                        yCoordinate++;
                        output[outputPointer++] = array[xCoordinate][yCoordinate];
                    }
                    straights++;
                    steps--;
                    break;
            }


        }

        return output;
    }

}
