import java.util.*;

public class TravelingSalesman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of places
        System.out.print("Enter the number of places: ");
        int numPlaces = scanner.nextInt();

        // Initialize distances matrix
        int[][] distances = new int[numPlaces + 1][numPlaces + 1];
        for (int i = 1; i <= numPlaces; i++) {
            for (int j = 1; j <= numPlaces; j++) {
                if (i != j) {
                    System.out.print("Enter distance from Place " + i + " to Place " + j + ": ");
                    distances[i][j] = scanner.nextInt();
                }
            }
        }

        // Create a list of place names
        List<String> placeNames = new ArrayList<>();
        for (int i = 1; i <= numPlaces; i++) {
            System.out.print("Enter name for Place " + i + ": ");
            placeNames.add(scanner.next());
        }

        // Find the shortest route
        int shortestDistance = Integer.MAX_VALUE;
        List<Integer> shortestRoute = new ArrayList<>();

        List<Integer> places = new ArrayList<>();
        for (int i = 1; i <= numPlaces; i++) {
            places.add(i);
        }

        do {
            int currentDistance = 0;
            int prevPlace = 0; // Starting point

            for (int place : places) {
                currentDistance += distances[prevPlace][place];
                prevPlace = place;
            }
            currentDistance += distances[prevPlace][0]; // Return to starting point

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                shortestRoute = new ArrayList<>(places);
            }
        } while (nextPermutation(places));

        // Print the result
        System.out.println("Shortest distance: " + shortestDistance);
        System.out.print("Shortest route: ");
        for (int place : shortestRoute) {
            System.out.print(placeNames.get(place - 1) + " ");
        }

        scanner.close();
    }

    // Generate next permutation of places
    private static boolean nextPermutation(List<Integer> places) {
        // Implement nextPermutation logic here
        // (Hint: Use similar logic as in the previous example)

        // Return true if there's a next permutation, false otherwise
        return false;
    }
}
