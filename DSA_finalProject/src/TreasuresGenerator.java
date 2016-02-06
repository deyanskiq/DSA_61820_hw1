package sofia.uni_sofia.fmi.SDA.knapsackProblem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreasuresGenerator {

	private final static int NAME = 0;
	private final static int VALUE = 1;
	private final static int WEIGHT = 2;

	private List<Treasure> treasures;

	public TreasuresGenerator(String file) {
		this.treasures = new ArrayList<>();
		this.generateTreasures(file);

	}

	private void generateTreasures(String file) {

		try (Scanner fileInput = new Scanner(new File(file))) {

			String line = null;
			while (fileInput.hasNextLine()) {
				line = fileInput.nextLine();
				addTreasure(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addTreasure(String treasureInfo) {
		String[] arrayWithTreasureInfo = treasureInfo.split(" ");

		treasures.add(new Treasure(arrayWithTreasureInfo[NAME], Integer.parseInt(arrayWithTreasureInfo[VALUE]),
				Integer.parseInt(arrayWithTreasureInfo[WEIGHT])));
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}
}
