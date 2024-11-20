public class upTree{

    int[][] trees;

    public int size;

    upTree(int size) {
        this.trees = new int[size][2];
        this.size = size;
        for (int i = 0; i < this.trees.length; i++) {
            // parent - default is self
            this.trees[i][0] = i;
            // size of tree
            this.trees[i][1] = 1;
        }
    }
    public void union(int node1, int node2){
        // fix to the oldest ancestors before merge
        node1 = find(node1);
        node2 = find(node2);
        if (node1 != node2) {
            if (this.trees[node1][1] > this.trees[node2][1]) {
                this.trees[node2][0] = node1;
                this.trees[node1][1] += this.trees[node2][1];
            } else {
                this.trees[node1][0] = node2;
                this.trees[node2][1] += this.trees[node1][1];
            }
        }
    }

    public int find(int node) {
        if (this.trees[node][0] != node) {
            this.trees[node][0] = find(this.trees[node][0]);
        }
        return this.trees[node][0];
    }

    public void printTree(){
        for (int i = 0; i < this.trees.length; i++) {
            System.out.println(i + " [" + this.trees[i][0] + "] [" + this.trees[i][1] + "]");
        }
    }

    public void dumbUnion(int node1, int node2) {
        // fix to the oldest ancestors before merge
        node1 = dumbFind(node1);
        node2 = dumbFind(node2);
        if (node1 != node2) {
            if (this.trees[node1][1] > this.trees[node2][1]) {
                this.trees[node2][0] = node1;
                this.trees[node1][1] += this.trees[node2][1];
            } else {
                this.trees[node1][0] = node2;
                this.trees[node2][1] += this.trees[node1][1];
            }
        }
    }

    public int dumbFind(int node) {
        while (this.trees[node][0] != node) {
            node = trees[node][0];
        }
        return node;
    }
}