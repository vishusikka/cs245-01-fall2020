// This class provides the solution for TwoSum problem
public class TwoSum {
    // the brute force implementation of the TwoSum
    public int[] TwoSum_brute_force(int[] array, int target) {
        int[] result = {-1,-1};
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        int[] no_solution = {-1};
        return no_solution;
    }

    //The two pointer implementation of the TwoSum
    public int[] TwoSum_better_solution(int[] array, int target) {
        // sorting the array
        sort(array);

        // finding the pair
        int[] res = {-1,-1};
        int start = 0; // pointer to beginning of the array
        int end = array.length - 1; // pointer to end of the array
        while (start < end) {
            if (array[start] + array[end] == target) {
                res[0] = start;
                res[1] = end;
                return res;
            }
            else if (array[start] + array[end] > target) {
                end--;
            }
            else {
                start++;
            }
        }
        int[] no_solution = {-1};
        return no_solution;
    }

    // sorting the array for the two pointer implementation
    public void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            // finding the minimum
            for (int j = i+1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // swap minElement with first element
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
    public void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {

        int[] array = {2, 12, 8, 7};
        TwoSum ts = new TwoSum();

        System.out.print("input:");
        ts.printArray(array);
        int[] result = ts.TwoSum_brute_force(array, 9);
        System.out.print("brute force: ");
        ts.printArray(result);

        System.out.println("***********************");

        System.out.print("input:");
        ts.printArray(array);
        result = ts.TwoSum_better_solution(array, 9);
        System.out.print("sorted array:");
        ts.printArray(array);
        System.out.print("better solution: ");
        ts.printArray(result);

        System.out.println("***********************");

        int[] array2 = {1, 13, 18, 20, 20, 30};

        System.out.print("input:");
        ts.printArray(array2);
        result = ts.TwoSum_brute_force(array2, 21);
        System.out.print("brute force: ");
        ts.printArray(result);

        System.out.println("***********************");

        System.out.print("input:");
        ts.printArray(array2);
        result = ts.TwoSum_better_solution(array2, 21);
        System.out.print("sorted array:");
        ts.printArray(array2);
        System.out.print("better solution: ");
        ts.printArray(result);



    }

}
