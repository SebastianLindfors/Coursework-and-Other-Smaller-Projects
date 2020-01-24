import java.util.Arrays;
import java.util.Comparator;

/**
 *  Solution to the Codewars Kata: Sports League Ranking
 *
 *  Author Sebastian Lindfors
 */




public class RankingTable {

    public static int[] computeRanks(int number, int[][] games) {

        int[][] pointsEarned = new int[number][4];

        for (int i = 0; i < number; i++) {
            pointsEarned[i][3] = i;
        }

//        for (int i = 0; i < pointsEarned.length; i++) {
//            for (int j = 0; j <pointsEarned[i].length ; j++) {
//                System.out.print(pointsEarned[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int[] game : games) {
            if (game[2] == game[3]) {
                pointsEarned[game[0]][0] += 1;
                pointsEarned[game[1]][0] += 1;

                pointsEarned[game[0]][2] += game[2];
                pointsEarned[game[1]][2] += game[3];
            }
            else if (game[2] > game[3]) {

                pointsEarned[game[0]][0] += 2;

                pointsEarned[game[0]][1] += game[2] - game[3];
                pointsEarned[game[1]][1] += game[3] - game[2];

                pointsEarned[game[0]][2] += game[2];
                pointsEarned[game[1]][2] += game[3];
            }
            else {
                pointsEarned[game[1]][0] += 2;

                pointsEarned[game[0]][1] += game[2] - game[3];
                pointsEarned[game[1]][1] += game[3] - game[2];

                pointsEarned[game[0]][2] += game[2];
                pointsEarned[game[1]][2] += game[3];
            }



            
        }

//        System.out.println("-------------------------------------------------------------------------");
//
//        for (int i = 0; i < pointsEarned.length; i++) {
//            for (int j = 0; j <pointsEarned[i].length ; j++) {
//                System.out.print(pointsEarned[i][j] + " ");
//            }
//            System.out.println();
//        }

        Arrays.sort(pointsEarned, new scoreSorter());

//        System.out.println("-------------------------------------------------------------------------");
//
//        for (int i = 0; i < pointsEarned.length; i++) {
//            for (int j = 0; j <pointsEarned[i].length ; j++) {
//                System.out.print(pointsEarned[i][j] + " ");
//            }
//            System.out.println();
//        }

        int[] output = new int[number];

//        System.out.println("-------------------------------------------------------------------------");

        int currentRank = 1;
        int skip = 0;
        for (int i = 0; i < pointsEarned.length - 1; i++) {
            if (pointsEarned[i][0] > pointsEarned[i+1][0]) {
                output[pointsEarned[i][3]] = currentRank;
                currentRank = currentRank + skip + 1;
                skip = 0;
                continue;
            }
            else if (pointsEarned[i][1] > pointsEarned[i+1][1]) {
                output[pointsEarned[i][3]] = currentRank;
                currentRank = currentRank + skip + 1;
                skip = 0;
                continue;
            }
            else if (pointsEarned[i][2] > pointsEarned[i+1][2]) {
                output[pointsEarned[i][3]] = currentRank;
                currentRank = currentRank + skip + 1;
                skip = 0;
                continue;
            }
            else {
                output[pointsEarned[i][3]] = currentRank;
                skip++;
                continue;
            }
        }

        if (pointsEarned[number - 2][0] == pointsEarned[number - 1][0]) {
            if (pointsEarned[number - 2][1] == pointsEarned[number - 1][1]) {
                if (pointsEarned[number - 2][2] == pointsEarned[number - 1][2]) {
                    output[pointsEarned[number - 1][3]] = currentRank;
                }
                else output[pointsEarned[number - 1][3]] = currentRank + skip;
            }
            else output[pointsEarned[number - 1][3]] = currentRank + skip;
        }
        else output[pointsEarned[number - 1][3]] = currentRank + skip;

//        System.out.println("-------------------------------------------------------------------------");
//
//        for (int i = 0; i < output.length; i++) {
//            System.out.print(output[i] + " ");
//        }

       return output;
    }

    private static class scoreSorter implements Comparator<int[]> {

        @Override
        public int compare(int[] t2, int[] t1) {
            if (t1[0] - t2[0] == 0) {
                if (t1[1] - t2[1] == 0) {
                    return t1[2] - t2[2];
                }
                else{
                    return t1[1] - t2[1];
                }
            }
            else {
                return t1[0] - t2[0];
            }
    }
    }


}
