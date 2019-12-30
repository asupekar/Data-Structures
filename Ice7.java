
package asupekar_ice7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 *
 * @author Aishwarya
 * @version 1.0
 */
public class Ice7 {

	private static final String directory = "/Users/aishisupe/eclipse-workspace/CPSC5003/src/asupekar_ice7/";

	public static void step1(String[] args) throws IOException {
		CsvFile csv = new CsvFile(directory + "LND01.csv");
		while (!csv.eof()) {
			HashMap<String, String> row = csv.next();
			for (String key : row.keySet())
				System.out.println(key + ": " + row.get(key));
			break;
		}
		csv.close();
	}

	public static void step4(String[] args) throws IOException {
		HashMap<Integer, PopDens> data = new HashMap<>();
		CsvFile csv = new CsvFile(directory + "LND01.csv");
		while (!csv.eof()) {
			HashMap<String, String> row = csv.next();
			int countyID = Integer.parseInt(row.get("STCOU"));
			String place = row.get("Areaname");
			double area = Double.parseDouble(row.get("LND110210D"));
			PopDens value = new PopDens(place, area);
			data.put(countyID, value);
		}
		csv.close();
		for (int countyID : data.keySet()) {
			PopDens pd = data.get(countyID);
			System.out.println(countyID + ": " + pd.density());
		}
	}

	public static void main(String[] args) throws IOException {
		HashMap<Integer, PopDens> data = new HashMap<>();

		// pass 1
		CsvFile csv = new CsvFile(directory + "LND01.csv");
		while (!csv.eof()) {
			HashMap<String, String> row = csv.next();
			int countyID = Integer.parseInt(row.get("STCOU"));
			String place = row.get("Areaname");
			double area = Double.parseDouble(row.get("LND110210D"));
			PopDens value = new PopDens(place, area);
			data.put(countyID, value);
		}
		csv.close();

		CsvFile csv2 = new CsvFile(directory + "DEC_10_SF2_PCT1_with_ann.csv");
		while (!csv2.eof()) {
			HashMap<String, String> row = csv2.next();
			if (row.get("POPGROUP.id").equals("001")) {
				int countyID = Integer.parseInt(row.get("GEO.id2"));
				int population = Integer.parseInt(row.get("D001"));
				PopDens value = data.get(countyID);
				value.population = population;
			}
		}
		csv2.close();

		System.out.println("for reference:");
		System.out.println("King County, WA: " + data.get(53033));
		System.out.println("Manila: 41515 people/sq.km.");
		System.out.println("Mumbai: 28508 people/sq.km.");
		System.out.println();

		ArrayList<PopDens> v = new ArrayList<>();
		for (int countyID : data.keySet()) {
			PopDens value = data.get(countyID);
			if (value.population > 0)
				v.add(value);
		}
		v.sort(null);
		for (int i = 0; i < 25; i++)
			System.out.printf("%d: %s\n", i + 1, v.get(i));
		System.out.println("...");
		for (int i = v.size() - 25; i < v.size(); i++)
			System.out.printf("%d: %s\n", i + 1, v.get(i));
	}

	private static class PopDens implements Comparable<PopDens> {
		private static final double SQ_KILOMETERS_PER_SQ_MILE = 2.58999;

		public String place;

		public int population;

		public double area;

		public PopDens(String place, double area) {
			this.place = place;
			this.population = 0;
			this.area = area;
		}

		@Override
		public int compareTo(PopDens other) {
			if (density() < other.density())
				return 1;
			if (density() > other.density())
				return -1;
			return 0;
		}

		public double density() {
			return population / (area * SQ_KILOMETERS_PER_SQ_MILE);
		}

		@Override
		public String toString() {
			return String.format("%s: %.3f people/sq.km.", place, density());
		}
	}
}
