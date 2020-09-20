// this class merges the inner sorted arrays of an outer array in ascending order
public class KLists {

    // this method evaluates the input. if input is valid, continues on merging the lists
    // otherwise ends the method appropriately
    public double[] mergeKLists(double[][] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        return divide(arr, 0, arr.length-1);
    }

    // the method keeps dividing the outer array
    public double[] divide(double[][] arr, int start, int end){

        if (start + 1 < end) {
            int mid = (start + end)/2;
            double[] left = divide(arr, start, mid);
            double[] right = divide(arr, mid+1, end);
            return merge(left, right);
        } else if (start + 1 == end) {
            return merge(arr[start], arr[end]);
        }
        return arr[start];
    }
    // thie method merges every two lists
    public double[] merge(double[] left, double[] right) {

       if (left == null) {
           return right;
       }
       if (right == null) {
           return left;
       }
       int size = left.length + right.length;
       double[] result = new double[size];
       int i = 0;
       int j = 0;
       int k = 0;

       while (i < left.length && j < right.length) {
           if (left[i] < right[j]) {
               result[k] = left[i];
               k++;
               i++;
           } else {
               result[k] = right[j];
               k++;
               j++;
           }
       }
       // copying the remaining elements in the left array to arr
        while (i < left.length) {
            result[k] = left[i];
            k++;
            i++;
        }
        // copying the remaining elements in the right array to arr
        while (j < right.length) {
            result[k] = right[j];
            k++;
            j++;
        }
        return result;
    }

    public void printList(double[] array) {
        if(array == null || array.length == 0){
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        double[][] input1 ={{1.1, 4, 4, 5, 5},
                {1.1, 3.3, 4.4},
                {2.2, 6.6}};
        KLists kl = new KLists();
        double[] output = kl.mergeKLists(input1);
        System.out.println("OUTPUT1:");
        kl.printList(output);

        double[][] input2 = {};
        output = kl.mergeKLists(input2);
        System.out.println("OUTPUT2:");
        kl.printList(output);

        double[][] input3 = {{}};
        output = kl.mergeKLists(input3);
        System.out.println("OUTPUT3:");
        kl.printList(output);

        double[][] input4 = {{9.7, 17.1},
                {15.8},
                {12.7, 18.5, 21.27}};
        output = kl.mergeKLists(input4);
        System.out.println("OUTPUT4:");
        kl.printList(output);

    }

}

