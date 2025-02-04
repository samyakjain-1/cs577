import java.util.*;

public class hw2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine().trim());
        
        while (t-- > 0) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            Map<String, List<String>> graph = new LinkedHashMap<>(); // Maintain insertion order
            
            for (int i = 0; i < n; i++) {
                String[] parts = scanner.nextLine().split(" ");
                String node = parts[0];
                graph.putIfAbsent(node, new ArrayList<>());
                for (int j = 1; j < parts.length; j++) {
                    graph.get(node).add(parts[j]);
                }
            }
            
            Set<String> visited = new LinkedHashSet<>();
            for (String startNode : graph.keySet()) {
                if (!visited.contains(startNode)) {
                    dfs(graph, startNode, visited);
                }
            }
            
            System.out.println(String.join(" ", visited));
        }
        scanner.close();
    }
    
    private static void dfs(Map<String, List<String>> graph, String node, Set<String> visited) {
        Stack<String> stack = new Stack<>();
        stack.push(node);
        
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                List<String> neighbors = graph.getOrDefault(current, new ArrayList<>());
                for (int i = neighbors.size() - 1; i >= 0; i--) { // Reverse to maintain order
                    stack.push(neighbors.get(i));
                }
            }
        }
    }
}
