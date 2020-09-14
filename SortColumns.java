import java.util.ArrayList;
import java.util.List;

public class SortColumns {

    // this function returns the indices of the columns in the argument
    // it returns an empty array when columns are in sorted order
    // it returns -1 if there are any errors
    public List<Integer> minDeletionSize(String[] A) {
        List<Integer> l = new ArrayList<Integer>();

        for (int position = 0; position < A[0].length(); position++) {
            for (int row = 0; row < A.length - 1; row++) {
                //checking if all strings have the same length
                if(A[row].length() != A[0].length()){
                    l.clear();
                    l.add(-1);
                    return l;
                }
                // compare
                if (A[row].charAt(position) > A[row + 1].charAt(position)) {
                    l.add(position);
                    break;
                }
            }
        }
        return l;
    }

    public static void printList(List<Integer> list) {
        System.out.print("{ ");
        for(Integer i: list){
            System.out.print(i + " ");
        }
        System.out.print("}");
        System.out.println();
    }

    public static void main(String[] args) {

        String[] arr = {"zyx", "wvu", "tsr"};
        String[] arr2 = {"cba", "daf", "ghi"};
        String[] arr3 = {"a", "b"};
        String[] arr4 = {"Captain", "Marvel", "saved", "the", "Avengers"};
        SortColumns sc = new SortColumns();

        List<Integer> result = sc.minDeletionSize(arr);
        List<Integer> result2 = sc.minDeletionSize(arr2);
        List<Integer> result3 = sc.minDeletionSize(arr3);
        List<Integer> result4 = sc.minDeletionSize(arr4);
        System.out.println("The columns to be deleted are:");
        System.out.print("output1: ");
        printList(result);
        System.out.print("output2: ");
        printList(result2);
        System.out.print("output3: ");
        printList(result3);
        System.out.print("output4: ");
        printList(result4);



    }

}
