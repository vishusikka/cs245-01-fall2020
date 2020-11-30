import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        //creating a road trip object
        RoadTrip rt = new RoadTrip();

        //reading csv files
        rt.read_attractions_file("/Users/vishvkarmasikka/IdeaProjects/RoadTrip/attractions.csv");
        rt.read_roads_file("/Users/vishvkarmasikka/IdeaProjects/RoadTrip/roads.csv");

        // Test 1 - from assignment write up
        System.out.println("Test 1:");
        List<String> attract = new ArrayList<String>();
        attract.add("Grand Canyon");
        attract.add("Paul Bunyon and Babe the Blue Ox");
        attract.add("Statue of Liberty");
        List<String> result = rt.route("San Francisco CA", "Miami FL", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        // Test 2
        System.out.println("\nTest 2:");
        attract.clear();
        result.clear();
        attract.add("Grand Canyo");
        attract.add("Paul Bunyon and Babe the Blue Ox");
        attract.add("Statue of Liberty");
        result = rt.route("San Francisco CA", "Miami FL", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        // Test 3
        System.out.println("\nTest 3:");
        attract.clear();
        attract.add("Grand Canyon");
        attract.add("Paul Bunyo and Babe the Blue Ox");
        attract.add("Statue of Liberty");
        result = rt.route("San Francisco CA", "Miami FL", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        // Test 4
        System.out.println("\nTest 4:");
        attract.clear();
        attract.add("Grand Canyon");
        attract.add("Paul Bunyon and Babe the Blue Ox");
        attract.add("Statue of Liberty");
        result = rt.route("San Frazisco CA", "Miami FL", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        // Test 5
        System.out.println("\nTest 5:");
        attract.clear();
        attract.add("Grand Canyon");
        attract.add("Paul Bunyon and Babe the Blue Ox");
        attract.add("Statue of Liberty");
        result = rt.route("San Francisco CA", "Miami", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        // Test 6
        System.out.println("\nTest 6:");
        attract.clear();
        attract.add("Graceland");
        attract.add("Seattle WA");
        attract.add("Hop-on Hop-off Tour");
        attract.add("Portland City Tour");
        result = rt.route("New York NY", "Chicago IL", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        //Test 7
        System.out.println("\nTest 7:");
        attract.clear();
        result.clear();
        result = rt.route("New York NY", "Seattle WA", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

        //Test 8
        System.out.println("\nTest 8:");
        result.clear();
        attract.add("Seattle WA");
        attract.add("Portland City Tour");
        result = rt.route("New York NY", "Seattle WA", attract);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }
    }
}
