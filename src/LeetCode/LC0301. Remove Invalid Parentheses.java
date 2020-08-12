/*
分叉：看到一个左括号或右括号，删掉还是保留
非括号只保留 
只有一种括号，还是用delta

两个参数rmL rmR如何计算
- 中间过程中delta < 0，右括号多了，rmR++
- 结束时delta != 0，左括号多，rmL++

S1:
此题答案中不能出现重复，res需要先加进hashset里查重
如果可以出现重复答案，直接return array list
*/

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        // cc
        Set<String> res = new HashSet<>();
        int[] rm = calRemove(s);
        dfs(res, new StringBuilder(), s, 0, rm[0], rm[1], 0);
        return new ArrayList<String>(res);
    }
    
    private void dfs(Set<String> res, StringBuilder path, String s, int idx, int rmL, int rmR, int delta){
        int len = s.length();
        // success
        if (rmL == 0 && rmR == 0 && delta == 0 && idx == len){
            res.add(path.toString());
            return;
        }
        
        // fail
        if (idx == len || rmL < 0 || rmR < 0 || delta < 0){
            return; // 树上搜索不需要check visited
        }
        
        // 分叉 - 左括号保留或删除，右括号保留或删除，非括号只保留
        char ch = s.charAt(idx);
        idx++;
        if (ch == '('){
            // remove
            dfs(res, path, s, idx, rmL - 1, rmR, delta); // delta是当前path中左右括号数的delta，此处删除没更新path
            // keep
            path.append('(');
            dfs(res, path, s, idx, rmL, rmR, delta + 1);
            path.setLength(path.length() - 1); // reset
            
        } else if (ch == ')'){
            // remove
            dfs(res, path, s, idx, rmL, rmR - 1, delta);
            // keep
            path.append(')');
            dfs(res, path, s, idx, rmL, rmR, delta - 1);
            path.setLength(path.length() - 1);
            
        } else { // char
            path.append(ch);
            dfs(res, path, s, idx, rmL, rmR, delta);
            path.setLength(path.length() - 1);
        }
    }
    
    private int[] calRemove(String s){  // 0: L, 1: R
        int rmL = 0, rmR = 0;
        for (char ch : s.toCharArray()){
            if (ch == '('){ // 遇到左括号
                rmL++; 
            } else if (ch == ')'){ // 遇到右括号
                if (rmL > 0){ // 如果已经有过左括号，抵消
                    rmL--;
                } else { // 如果右括号当前数量比左括号多了
                    rmR++;
                }
            }
        }
        return new int[] {rmL, rmR};
    }
}


// S2: Without using hashset - deal in dfs
class Solution {
   public List<String> removeInvalidParentheses(String s) {
       List<String> result = new ArrayList<>();
       if (s == null) return result;
       
       //check how many '(' and ')' we need to remove
       int rmL = 0;
       int rmR = 0;
       for (char c : s.toCharArray()) {
           if (c == '(') rmL++;
           if (c == ')') {
               if (rmL == 0) rmR++;
               else rmL--;
           }
       }
       dfs(result, s.toCharArray(), 0, rmL, rmR, 0, new StringBuilder());
       return result;
   }
   
   private void dfs(List<String> result, char[] s, int i, int rmL, int rmR, 
                     int open, StringBuilder sb) {
       if (i == s.length && rmL == 0 && rmR == 0 && open == 0) {
           result.add(sb.toString());
           return;
       }
       
       if (i >= s.length || rmL < 0 || rmR < 0 || open < 0) return;
       
       //cache length before remove/keep, prepare for backtracking
       int length = sb.length();
       //left '('
       if (s[i] == '(') {
           //remove '('
           dfs(result, s, i + 1, rmL - 1, rmR, open, sb);

           //keep '('
           int rep = 1;
           while (i + rep < s.length && s[i + rep] == '(') {
               rep++;
           }
           sb.append(s, i, rep);
           dfs(result, s, i + rep, rmL, rmR, open + rep, sb);
           sb.setLength(length);
       }
       //right ')'
       else if (s[i] == ')') {
           //remove ')'
           dfs(result, s, i + 1, rmL, rmR - 1, open, sb);
           
           //keep ')'
           int rep = 1;
           while (i + rep < s.length && s[i + rep] == ')') {
               rep++;
           }
           sb.append(s, i, rep);
           dfs(result, s, i + rep, rmL, rmR, open - rep, sb);
           sb.setLength(length);
       } else {
           sb.append(s[i]);
           dfs(result, s, i + 1, rmL, rmR, open, sb);
           sb.setLength(length);
       }
   }
}
