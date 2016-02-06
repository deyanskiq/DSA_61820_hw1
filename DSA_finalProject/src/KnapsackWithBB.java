package sofia.uni_sofia.fmi.SDA.knapsackProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author DDenchev: Solution to the knapsack problem using branch and bounding
 *         method. Instead of using breadth-first search it is used best-first
 *         search with branch and bound pruning which decrease the complexity of
 *         the algorithm.
 * 
 *         We improve our search by using our bound to do more than just
 *         determine whether a node is promising. After visiting all the
 *         children of a given node, we can look at all the promising,
 *         unexpanded nodes and expand beyond the one with the best bound. Then
 *         recall that a node is promising if its bound is better than the value
 *         of the best solution found so far.
 * 
 *         If used breadth-first search (which is similar to backtracking which
 *         use deep-first search) instead of best-first search the complexity
 *         would be o(2^n) , where n - number of the elements
 * 
 */

public class KnapsackWithBB extends Knapsack {

	private List<Node> nodes;
	private List<Treasure> treasures;
	private int[] values;
	private int[] weights;

	public KnapsackWithBB(int capacity, String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.capacity = capacity;
		this.itemCount = this.treasures.size();
		this.bestSolution = 0;
		this.values = treasures.stream().mapToInt(Treasure::getValue).toArray();
		this.weights = treasures.stream().mapToInt(Treasure::getWeight).toArray();
	}

	public void calculate() {
		knapsack();
		print();
	}

	/**
	 * using priority queue to store and then to get elements in a decreasing
	 * order
	 */
	@Override
	public void knapsack() {

		// initialize priority queue to be empty
		PriorityQueue<Node> queue = new PriorityQueue<>(10, new BoundComparator());
		Node root = new Node();
		Node childOrNot;
		root.computeBound(capacity, itemCount, values, weights);

		nodes = new ArrayList<>();
		queue.add(root);

		// using BFS (Best first search)
		while (!queue.isEmpty()) {
			// remove node with best bound
			Node current = queue.poll();
			childOrNot = new Node();

			// Check if node is still promising; Set childOrNot to the child
			// that
			// includes the next item;
			if (current.getBound() > bestSolution) {
				childOrNot.setLevel(current.getLevel() + 1);

				childOrNot.setValue(current.getValue() + values[childOrNot.getLevel() - 1]);

				childOrNot.setWeight(current.getWeight() + weights[childOrNot.getLevel() - 1]);

				Treasure currentTreasure = treasures.get(childOrNot.getLevel() - 1);

				// If the node is promising bestSolution gets the value of
				// childOrNot
				if (childOrNot.getWeight() <= capacity && childOrNot.getValue() > bestSolution) {
					bestSolution = childOrNot.getValue();

				}
				childOrNot.computeBound(capacity, itemCount, values, weights);

				if (childOrNot.getBound() > bestSolution) {

					// adding all treasures to the left-child
					childOrNot.getTreasures().addAll(current.getTreasures());
					childOrNot.getTreasures().add(currentTreasure);

					nodes.add(new Node(childOrNot));
					queue.add(new Node(childOrNot));

				}
				// set childOrNot to the child that doesn't include the next
				// item e.g the right-child
				childOrNot.setWeight(current.getWeight());
				childOrNot.setValue(current.getValue());
				childOrNot.computeBound(capacity, itemCount, values, weights);

				if (childOrNot.getBound() > bestSolution) {

					// adding treasures just from the previous node to the
					// left-child
					childOrNot.setTreasure(current.getTreasures());
					nodes.add(new Node(childOrNot));

					queue.add(new Node(childOrNot));
				}
			}
		}
	}

	@Override
	public void print() {
		System.out.println("Solution with branch and bounding:");
		System.out.println("The best solution is: " + bestSolution);
		System.out.println("The most expensive items Indiana Jones can loot are:");
		for (Node node : nodes) {
			if (node.getValue() == bestSolution) {
				for (Treasure treasure : node.getTreasures()) {
					System.out.println(treasure);
				}
				break;
			}
		}
	}
}
