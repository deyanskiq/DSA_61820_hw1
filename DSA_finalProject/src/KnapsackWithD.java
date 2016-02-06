package sofia.uni_sofia.fmi.SDA.knapsackProblem;

import java.util.List;

public class KnapsackWithD extends Knapsack {

	private List<Treasure> treasures;
	private int[][] dp;
	private int[][] tracker;

	public KnapsackWithD(int capacity, String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.itemCount = this.treasures.size();
		this.dp = new int[itemCount + 1][capacity + 1];
		this.tracker = new int[itemCount + 1][capacity + 1];
		this.bestSolution = 0;
		this.capacity = capacity;
	}

	public void calculate() {
		knapsack();
		print();
	}

	public void knapsack() {
		for (int i = 1; i <= itemCount; i++) {
			for (int j = 1; j <= capacity; j++) {
				Treasure curr = treasures.get(i - 1);

				if (curr.getWeight() > j) {
					// the result of this subproblem will be the value
					// without the current item
					dp[i][j] = dp[i - 1][j];
					tracker[i][j] = -1;
				} else {
					// the result of this subproblem will be the max value
					// between
					// the value of the current item + the value of the item
					// that we could afford with the remaining weight
					// and the value without the current item itself
					dp[i][j] = Math.max(dp[i - 1][j], curr.getValue() + dp[i - 1][j - curr.getWeight()]);

					if (curr.getValue() + dp[i - 1][j - curr.getWeight()] > dp[i - 1][j]) {
						tracker[i][j] = 1;
					} else {
						tracker[i][j] = -1;
					}
				}
			}
		}

		bestSolution = dp[itemCount][capacity];
	}

	public void print() {
		System.out.println("Dynamic solution:");
		System.out.println("The best solution is: " + bestSolution);
		System.out.println("The most expensive items Indiana Jones can loot are:");

		int i = itemCount;
		int w = capacity;

		while (i > 0) {
			if (tracker[i][w] == 1) {
				System.out.println(treasures.get(i - 1));
				i--;
				w -= treasures.get(i).getWeight();
			} else {
				i--;
			}
		}

	}

}
