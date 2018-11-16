
public class DisjoinSet {
    int[] parent;
    int[] rank;

    public DisjoinSet(int n) {
        parent = new int[n];
        rank = new int[n];
    }

    public void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    public int find(int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

    public void union(int i, int j) {
        int i_id = find(i);
        int j_id = find(j);
        if (i_id == j_id) {
            return;
        }
        if (rank[i_id] > rank[j_id]) {
            parent[j_id] = i_id;
        } else {
            parent[i_id] = j_id;
            if (rank[i_id] == rank[j_id]) {
                rank[j_id] = rank[j_id] + 1;
            }
        }
    }
}
