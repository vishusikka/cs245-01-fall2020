import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class RoadTrip {

    HashMap<String, String> attractions_city_map;
    ArrayList<String> cities; // all unique cities
    Graph roadConnections; // representing all the roads
    ArrayList<ArrayList> permutations;

    //this function reads in the csv file of attraction and stores the data in the hashmap
    public void read_attractions_file(String filePath){
        attractions_city_map = new HashMap<String, String>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(",");
        boolean firstLine = true;
        while (sc.hasNext()) {
            //skipping the labels
            if (firstLine) {
                firstLine = false;
                sc.nextLine();
            } else {
                String l = sc.nextLine();
                String[] line_data = l.split(",");
                attractions_city_map.put(line_data[0], line_data[1]);
            }
        }
        sc.close();
    }

    //this function reads in the roads.csv file and stores the data in a graph data structure
    public void read_roads_file(String filePath) {
        cities = new ArrayList<String>();
        // read unique cities into the ArrayList
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            String l = sc.nextLine();
            String[] line_data = l.split(",");
            if (!cities.contains(line_data[0])){
                cities.add(line_data[0]);
            }
            if (!cities.contains(line_data[1])) {
                cities.add(line_data[1]);
            }
        }
        sc.close();
        int vertices = cities.size();

        //organize data into the graph
        roadConnections = new Graph(vertices);
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc2.useDelimiter(",");
        while (sc2.hasNext()) {
            String l = sc2.nextLine();
            String[] line_data = l.split(",");
            int id_from = cities.indexOf(line_data[0]);
            int id_to = cities.indexOf(line_data[1]);
            // adding information to the graph
            roadConnections.addEdge(id_from, id_to, Integer.parseInt(line_data[2]));
        }
        sc2.close();
    }

    public List<String> route(String starting_city, String ending_city, List<String> attraction) {
        //getting ids of each city
        int start = cities.indexOf(starting_city);
        int end = cities.indexOf(ending_city);

        //validating start city
        if (start == -1) {
            System.out.println("Error: " + starting_city + " doesn't exist in the database.");
            return null;
        }
        //validating end city
        if (end == -1) {
            System.out.println("Error: " + ending_city + " doesn't exist in the database.");
            return null;
        }

        //find cities of attractions
        List<Integer> att_cities = new ArrayList<Integer>();
        for (String s : attraction) {
            if (cities.indexOf(attractions_city_map.get(s)) != -1){
                att_cities.add(cities.indexOf(attractions_city_map.get(s)));
            } else {
                if (cities.indexOf(s) == -1) {
                    System.out.println("Error: " + s + " doesn't exist in the database.");
                    return null;
                } else {
                    att_cities.add(cities.indexOf(s));
                }
            }
        }

        //number of permutation of the visiting cities (except start and end)
        int perm_number = factorial(att_cities.size());
        // must visit cities including start and end cities and all middle cities
        List<Integer> must_visit = new ArrayList<Integer>();
        must_visit.add(start);
        for (int i = 0; i < att_cities.size(); i++) {
            must_visit.add(att_cities.get(i));
        }
        must_visit.add(end);
        permute(att_cities, start, end);
        //the following table is going to store the shortest path for each permutation
        int[][] table = new int[perm_number][must_visit.size()];

        //populating the table
        for (int j = 0; j < table[0].length-1; j++) {
            for (int i = 0; i < table.length; i++) {
                int[] short_path_res = dijkstra(roadConnections, (int) permutations.get(i).get(j));
                table[i][j] = short_path_res[(int) permutations.get(i).get(j+1)];
            }
        }

        //find the smallest row in the table
        int smallest_row = find_shortest(table);
        List<String> final_list = new ArrayList<String>();
        //creating the list of cities in order of visit
        for (int j = 0; j < table[0].length; j++) {
            final_list.add(cities.get((int) permutations.get(smallest_row).get(j)));
        }
        return final_list;
    }
    //this function adds up each row of the table and finds the smallest row
    //this smallest row represent shortest possible permutation in which the cities must be visited
    private int find_shortest(int[][] table) {
        int smallest_row = -1;
        int smallest_value = Integer.MAX_VALUE;
        for (int i = 0; i < table.length; i++) {
            int sum = 0;
            for (int j = 0; j < table[0].length-1; j++) {
                if (table[i][j] == Integer.MAX_VALUE) {
                    sum = Integer.MAX_VALUE;
                } else {
                    sum += table[i][j];
                }
            }
            if (sum < smallest_value) {
                smallest_value = sum;
                smallest_row = i;
            }
        }
        return smallest_row;
    }
    //this function finds all permutations of the cities to be visited
    //keeping the start and end city always at the beginning and end
    private void permute(List<Integer> att_cities, int start, int end) {
        int[] arr = new int[att_cities.size()];
        for (int i = 0; i < att_cities.size(); i++) {
            arr[i] = att_cities.get(i);
        }
        permutations = new ArrayList<ArrayList>();
        permute_helper(arr, 0, start, end);
    }
    private int[] permute_helper(int[] arr, int index, int start, int end) {
        //Base case
        if(index >= arr.length - 1){
            ArrayList<Integer> row = new ArrayList<Integer>();
            row.add(start);
            for(int i = 0; i < arr.length; i++){
                row.add(arr[i]);
            }
            row.add(end);
            permutations.add(row);
            return arr;
        }
        //Recursive case
        for(int i = index; i < arr.length; i++){
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
            permute_helper(arr, index+1, start, end);
            int temp2 = arr[index];
            arr[index] = arr[i];
            arr[i] = temp2;
        }
        return null;
    }

    // this function finds the factorial of the number
    private int factorial(int fact) {
        int factorial = 1;
        for (int i = 1; i <= fact; i++) {
            factorial *= i;
        }
        return factorial;
    }

    //this function uses dijkstra's algorithm to find the shortest path from the source
    //to all other nodes in the graph
    private int[] dijkstra(Graph graph, int source) {
        boolean known[] = new boolean[graph.getVertices()];
        int cost[] = new int[graph.getVertices()];
        for (int i = 0; i < graph.getVertices(); i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        cost[source] = 0;
        for (int i = 0; i < graph.getVertices() - 1; i++) {
            int min = Integer.MAX_VALUE;
            int min_idx = -1;
            //finding the minimum index
            for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
                if (known[vertex] == false) {
                    if (cost[vertex] <= min){
                        min = cost[vertex];
                        min_idx = vertex;
                    }
                }
            }
            known[min_idx] = true;
            for (int v = 0; v < graph.getVertices(); v++) {
                if (!known[v] && graph.getIndex(min_idx,v) != 0){

                    int sum = cost[min_idx] + graph.getIndex(min_idx,v);

                    if (cost[min_idx] != Integer.MAX_VALUE && sum < cost[v]){
                        cost[v] = sum;
                    }
                }
            }
        }
        return cost;
    }
}
