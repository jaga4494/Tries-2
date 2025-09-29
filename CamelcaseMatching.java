class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if (queries == null || queries.length == 0) {
            return new ArrayList<>();
        }

        int p = pattern.length();
        int q = queries.length;
        

        List<Boolean> result = new ArrayList<>();
        
        for (int i = 0; i < q; ++i) {
            String s = queries[i];
            int pp = 0; // pattern's pointer
            boolean flag = true;
            for (int qp = 0; qp < s.length(); ++qp) {
                char c = s.charAt(qp);
                if (pp == p || c != pattern.charAt(pp)) {
                    if(Character.isUpperCase(c)) {
                        flag = false;
                        break;
                    }
                    continue;
                }
                // char matches, just move the pattern's pointer
                ++pp;
            }
            if (pp != p) { // pattern's pointer should have been completed for the whole pattern to match.
                flag = false;
            }
            result.add(flag);
        }

        return result;
    }
}