package sofia.uni_sofia.fmi.SDA.knapsackProblem;

public abstract class Knapsack {
	protected int capacity;
	protected int itemCount;
	protected int bestSolution;

	public abstract void knapsack();

	public abstract void print();
}
