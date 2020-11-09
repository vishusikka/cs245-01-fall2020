public class Judge {

    public int findJudge(int N, int[][] trust) {

        int[] sum_first = new int[N]; // represents the first column of trust
        int[] sum_second = new int[N]; // represents the second column of trust

        // iterate through rows of trust
        for (int i = 0; i < trust.length; i++){
            sum_first[trust[i][0]-1]++;
            sum_second[trust[i][1]-1]++;
        }
        // finding the judge
        for (int i= 0; i < N; i++){
            if(sum_first[i] == 0 && sum_second[i] == N-1){
                return (i+1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Judge j = new Judge();
        int[][] trust1 = {{1, 2}};
        int[][] trust2 = {{1, 3}, {2, 3}};
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};
        int[][] trust4 = {{1, 2}, {2, 3}};
        int[][] trust5 = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(j.findJudge(2, trust1));
        System.out.println(j.findJudge(3, trust2));
        System.out.println(j.findJudge(3, trust3));
        System.out.println(j.findJudge(3, trust4));
        System.out.println(j.findJudge(4, trust5));


    }

}
