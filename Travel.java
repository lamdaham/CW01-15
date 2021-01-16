import java.util.*;

public class Travel {


	// Regroups the permutations
	public static void regroup(ArrayList<Integer> perms, int size) {
		for (int i = 0; i<perms.size()-1 ; i++) {
			if (perms.get(i) == size) {
				perms.set(i+1, perms.get(i+1)+1);
				perms.set(i, 0);
			}
		}
	}

	// Checks if there are duplicates in the list
	public static boolean noDup(ArrayList<Integer> perms) {
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i = 0; i<perms.size(); i++) {
			if (!p.contains(perms.get(i))) {
				p.add(perms.get(i));
			} else {
				return false;
			}
		}
		return true;
	}


	// Calculate distance of a route
	public static int distance(ArrayList<Integer> perms, int[][] data) {
		int output = 0;
		for(int i = 1; i<perms.size(); i++) {
			output += data[perms.get(i)][perms.get(i-1)];
		}
		return output;
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

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



		// Generate Permutations
		int min = 2147483647;

		ArrayList<String> route = new ArrayList<String>();
		// ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> perms = new ArrayList<Integer>();
		
		//Starts the process
		for(int i = 1; i<=cityNames.size(); i++) {
			perms.add(0);
		}


		//Goes through every combination including duplicates
		while(perms.get(perms.size()-1)<perms.size()) {

			//Increments the first index; when it is greater than max size, it regroups;
			perms.set(0, perms.get(0)+1);
			regroup(perms, cityNames.size());

			//If no duplicates in the list, it calculates distance and records the route
			if (noDup(perms)) {
				if (distance(perms, data)<min) {
					min = distance(perms, data);
					route.clear();
					for (int i = 0; i<perms.size() ; i++) {
						route.add(cityNames.get(perms.get(i)));
					}
				}
			}
		}

		double time = (System.currentTimeMillis()-start)/1000.0;
		System.out.println(min);
		System.out.println(route);
		System.out.println(time);

		




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