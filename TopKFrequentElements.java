class Solution {
    // Another method to avoid the logk time introduced by PQ.
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            max = Math.max(max, map.get(n));
        }
        
        // try using List[]
        List[] list = new List[max + 1];

        for (int key : map.keySet()) {
            int value = map.get(key);
            if (list[value] == null) {
                list[value] = new ArrayList<>();
            }
            list[value].add(key);
        }

        int[] result = new int[k];
        int size = k;
        for (int i = max; i >= 0; --i) {
            if (list[i] == null || size <= 0) {
                continue;
            }
            List<Integer> l = list[i];
            for (int j = 0; j < l.size(); ++j) {
                result[size - 1] = l.get(j);
                --size;
            }
            
        }
        return result;

    }

    // PQ method.
    // TC: O(n log k)
    // SC: O(n)
    public int[] topKFrequentPQ(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // they are asking decreasing freq, so use min min heap (opposite)
        PriorityQueue<Integer> pq = new PriorityQueue<> ((x, y) -> map.get(x) - map.get(y));

        for (int n : map.keySet()) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int size = pq.size();
        int result[] = new int[size];
        
        while(!pq.isEmpty()) {
            result[size - 1] = pq.poll();
            size--;
        }

        return result;  
    }
}