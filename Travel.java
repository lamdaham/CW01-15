import java.util.*;

public class Travel {
	public static void main(String[] args) {

		// Stores city names
		ArrayList<String> cityNames = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);

		// Stores data in the format [indexOf startCity, indexOf destinationCity, distance]
		ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>();


		// Loops and obtains cities and distance
		while(scanner.hasNextLine()) {
			Scanner n = new Scanner(scanner.nextLine());

			//Get the two cities
			String city1 = n.next();
			n.next();
			String city2 = n.next();
			n.next();
			int distance =  n.nextInt();


			// Stores the travel data in the format [indexOf startCity, indexOf destinationCity, distance]
			ArrayList<Integer> dataCopy = new ArrayList<Integer>();


			// Add the two cities to cityName, no repeats
			if (!cityNames.contains(city1)) {
				cityNames.add(city1);
			}
			if (!cityNames.contains(city2)) {
				cityNames.add(city2);
			}

			// Stores data into dataCopy
			dataCopy.add(cityNames.indexOf(city1));
			dataCopy.add(cityNames.indexOf(city2));
			dataCopy.add(distance);
			
			// Adds dataCopy to the larger ArrayList
			copy.add(dataCopy);
		}



		// Data to store distance
		int[][] data = new int[cityNames.size()][cityNames.size()];


		// For-each loop setting distance into the data
		for (ArrayList<Integer> x:copy) {
			data[x.get(0)][x.get(1)] = x.get(2);
			data[x.get(1)][x.get(0)] = x.get(2);
		}



		// Prints out the stored data
		// for (int[]x: data ) {
		// 	System.out.print("[");
		// 	for (int i = 0; i< x.length-1; i++) {
		// 		System.out.print(x[i]+", ");
		// 	}
		// 	System.out.println(x[x.length-1]+"]");
		// }

	}
}