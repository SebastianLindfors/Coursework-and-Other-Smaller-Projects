public class Cure_Cancer {

    public static int[] mutationLocation(char [][] body) {

        boolean firstLineIsRight = false;

        int discrepancyAtIndex = -1;

        for (int i = 0; i < body[0].length; i++) {
            if (body[0][i] != body[1][i]) {
                discrepancyAtIndex = i;
                break;
            }
        }
        if (discrepancyAtIndex == -1) firstLineIsRight = true;

        if (firstLineIsRight) {
            for (int i = 2; i < body.length; i++) {
                for (int j = 0; j < body[i].length; j++) {
                    if (body[0][j] != body[i][j]) return new int[]{i, j};
                }
            }
            return new int[]{};
        }
        else {
            if (body[2][discrepancyAtIndex] == body[0][discrepancyAtIndex]) return new int[] {0,discrepancyAtIndex};
            else return new int[] {1, discrepancyAtIndex};
        }
    }

}


