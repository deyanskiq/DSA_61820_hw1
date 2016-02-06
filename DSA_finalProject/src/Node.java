package sofia.uni_sofia.fmi.SDA.knapsackProblem;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int weight;
	private int value;
	private int level;
	private int bound;
	private List<Treasure> treasures;

	public Node(Node node) {
		this.weight = node.weight;
		this.value = node.value;
		this.level = node.level;
		this.treasures = node.treasures;
		this.bound = node.bound;
	}

	public Node() {
		treasures = new ArrayList<>();
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasure(List<Treasure> treasure) {
		this.treasures = treasure;
	}

	public int getBound() {
		return bound;
	}

	protected void computeBound(int capacity, int itemCount, int[] values, int[] weights) {
		int i, j;
		int totalWeight;

		if (this.getWeight() >= capacity) {
			bound = 0;

		} else {
			bound = this.getValue();
			i = this.getLevel() + 1;
			totalWeight = this.getWeight();

			while (i <= itemCount && totalWeight + weights[i - 1] <= capacity) {
				totalWeight += weights[i - 1];
				bound += values[i - 1];
				i++;
			}
			j = i;
			if (j <= itemCount) {
				bound += (capacity - totalWeight) * (values[j - 1] / weights[j - 1]);

			}

		}
	}

}
