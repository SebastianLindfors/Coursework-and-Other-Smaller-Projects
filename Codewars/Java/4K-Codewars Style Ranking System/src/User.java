/** This class solves the Codewars Kata: Codewars Style Ranking System
 *  https://www.codewars.com/kata/51fda2d95d6efda45e00004e/
 *
 *  Author: Sebastian Lindfors
 */
public class User {

    final private int[] RANKS = {-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};

     int rank;
     int rankPointer;
     int progress;

    public User() {

        rank = -8;
        rankPointer = 0;
        progress = 0;

    }

    public void incProgress(int rank) {

        if (rank > 8 || rank < -8) throw new IllegalArgumentException("Rank must be berween -8 and 8");
        if (rank == 0) throw new IllegalArgumentException("Rank zero does not exist");

        if (this.rank == 8) return;

        int activityRankPointer = -1;
        for (int i = 0; i < RANKS.length; i++) {
            if (rank == RANKS[i]) {
                activityRankPointer = i;
            }
        }
        if (this.rankPointer - activityRankPointer >= 2) return;
        else if (this.rankPointer - activityRankPointer == 1) this.progress++;
        else if (this.rankPointer - activityRankPointer == 0) this.progress = this.progress + 3;
        else { progress += (int) Math.pow(activityRankPointer - this.rankPointer, 2) * 10;}

        while (progress >= 100 && this.rank < 8) {
            this.progress -= 100;
            if (this.rankPointer < RANKS.length - 1) {
                this.rankPointer++;
                this.rank = RANKS[rankPointer];
            }
        }
        if (this.rank == 8) {
            progress = 0;
        }

    }
}
