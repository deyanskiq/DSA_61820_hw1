package sofia.uni_sofia.fmi.SDA.knapsackProblem;

import java.util.Comparator;

public class BoundComparator implements Comparator<Node> {

	@Override
	public int compare(Node node1, Node node2) {

		// in decreasing order
		return node2.getBound() - node1.getBound();
	}

}
