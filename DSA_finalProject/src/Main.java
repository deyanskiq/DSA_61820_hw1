package sofia.uni_sofia.fmi.SDA.knapsackProblem;

/**
 * Indiana Jones often faces difficult dilemmas in his travels. On his last
 * journey into the crypt on a long-forgotten emperor he found countless
 * treasures but he could only carry no more than N kilograms of loot on the way
 * back. Help him make the most of the situation by solving the famous knapsack
 * problem. Since he'll be using your program for some time, you are required to
 * solve the problem using both dynamic programming AND branch & bound.
 **/

public class Main {

	public static void main(String[] args) {

		String fileWithTreasures = "treasures.txt";
		KnapsackWithD test1 = new KnapsackWithD(16, fileWithTreasures);
		test1.calculate();
		System.out.println();

		KnapsackWithBB test2 = new KnapsackWithBB(16, fileWithTreasures);
		test2.calculate();
	}
}
