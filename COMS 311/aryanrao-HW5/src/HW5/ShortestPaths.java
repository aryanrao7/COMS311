package HW5;

import java.util.ArrayList;

public class ShortestPaths {
    private int[][] graph;

    public ShortestPaths(int[][] g) {
        graph = g;
    }

    public ArrayList<Integer>[] computeShortestPaths(int s) {
        int n = graph.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] paths = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            paths[i] = new ArrayList<>();
        }

        distances[s] = 0;
        paths[s].add(0);

        for (int count = 0; count < n - 1; count++) {
            int u = getMinDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] >= 0 && distances[u] != Integer.MAX_VALUE &&
                        distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                    paths[v] = new ArrayList<>(paths[u]);
                    paths[v].add(v);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        ArrayList<Integer>[] resultPaths = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            resultPaths[i] = new ArrayList<>();
            resultPaths[i].add(distances[i]);
            resultPaths[i].addAll(paths[i]);
        }

        return resultPaths;
    }

    private int getMinDistance(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= min) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}