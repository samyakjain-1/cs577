import java.util.*;

public class hw4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int instances = scanner.nextInt();
        
        while (instances-- > 0) {
            int cacheSize = scanner.nextInt();
            int numRequests = scanner.nextInt();
            int[] requests = new int[numRequests];
            
            for (int i = 0; i < numRequests; i++) {
                requests[i] = scanner.nextInt();
            }
            
            System.out.println(computePageFaults(cacheSize, requests));
        }
        scanner.close();
    }
    
    private static int computePageFaults(int cacheSize, int[] requests) {
        if (cacheSize == 0) return requests.length; // Edge case: No cache space
        
        Set<Integer> cache = new HashSet<>(); // Current pages in cache
        Map<Integer, Queue<Integer>> futureOccurrences = new HashMap<>(); // Tracks future requests
        int pageFaults = 0;
        
        // Precompute future occurrences
        for (int i = 0; i < requests.length; i++) {
            futureOccurrences.putIfAbsent(requests[i], new LinkedList<>());
            futureOccurrences.get(requests[i]).offer(i);
        }
        
        for (int i = 0; i < requests.length; i++) {
            int page = requests[i];
            futureOccurrences.get(page).poll(); // Remove current occurrence
            
            if (cache.contains(page)) continue; // Page hit, move on
            
            pageFaults++;
            
            if (cache.size() < cacheSize) {
                cache.add(page); // Add directly if space available
            } else {
                // Find page to evict: The one that is used furthest in future
                int pageToEvict = -1, maxFutureUse = -1;
                
                for (int cachedPage : cache) {
                    if (futureOccurrences.get(cachedPage).isEmpty()) {
                        pageToEvict = cachedPage;
                        break; // This page is never used again, best candidate
                    }
                    
                    int nextUse = futureOccurrences.get(cachedPage).peek();
                    if (nextUse > maxFutureUse) {
                        maxFutureUse = nextUse;
                        pageToEvict = cachedPage;
                    }
                }
                
                cache.remove(pageToEvict);
                cache.add(page);
            }
        }
        
        return pageFaults;
    }
}
