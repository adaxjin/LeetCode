/*
check delta = #(() - #())
1. at the end delta == 0
2. in the middle, delta always >= 0
3. 只能描述单一括号的关系，不能描述不同种括号嵌套的关系
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        //cc
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), n, 0); // can inline because no use of path in other places except for dfs
        return res;
    }
    
    private void dfs(List<String> res, StringBuilder path, int n, int delta){
        int len = path.length();
        // success
        if (len == 2*n && delta == 0){
            res.add(path.toString());
            return;
        }
        
        // fail
        if (len == 2*n || delta < 0){
            return;
        }
        
        // (
        path.append('(');
        dfs(res, path, n, delta + 1);
        path.setLength(len);
        
        // ) 
        path.append(')');
        dfs(res, path, n, delta - 1);
        path.setLength(len);
    }
}
