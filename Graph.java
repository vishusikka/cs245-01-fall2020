public class Graph {
    int vertices;
    int[][] matrix; //this is matrix representation of the graph
    public Graph(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }
    public void addEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
        matrix[to][from] = weight;
    }
    public int getIndex(int i, int j) {
        return matrix[i][j];
    }
    public int getVertices() {
        return vertices;
    }
}
