import java.util.HashMap;
import java.util.Map;

/*
string S="abcdef"
string temp = S.substr(x, y); "bcde"

string T="bce"
T=temp.subseq()
return temp min(temp candidates)

string findSubstr(stringS, stringT){}

T="bcd" return "bcd"
"bcg" return ""

S: mmm456mmmmmm123m
T: m123m return m123m

S: mmm456mmmmmm123m
temp : mmm456mmmmmm123m
T: m123m reverse parsing

S: "ffffffffffeeeeeeeeessssssssa"
T: "fffessa" return fffeeeeeeeeessssssssa

0<S.size()<10^5
0<T.size()<100

ascii

*/


public class SubSequence {
    public String findSubstr(String s, String t){
        // corner case
        if (s == null || s.length() == 0) return null; // TODO
        if (t == null || t.length() == 0) return null;// TODO

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1); //把character都加到map里去初始值为1
        }

        int[] res = new int[]{-1, -1};

        int count = map.size();//这里keep一个cnt，来记录当前map的size，比如abca这里我的count = 3
        int min = s.length();//记录global minlength， 初始值可以是integerMAX，但是这里length就足够了
        int sl = 0;//这里记录我需要找到的最短距离的的初始idx，末的index是i

        for (int i = 0; i < s.length(); i++){
            if (count > 0){
                char fast = s.charAt(i);
                if (map.containsKey(fast)){
                    int curCount = map.get(fast);
                    if (curCount == 1) count--;//每一个character都有他的balance，比如abca， a：2，当我fast找到2个a的时候，这里就把cnt--，表示说我已经consume完了两个a
                    map.put(fast, curCount - 1);
                }

            }
            while (sl <= i && count <= 0){//注意啊，这里sl是我的做指针idx，下面出现的slow是对应的character
                if (i - sl + 1 <= min){
                    if(isValidSubSequence(t, s.substring(sl, i + 1))){ //如果isValidsubSequence（）就更新，不然就break，说明当前的相对顺序已经不ok了。
                        res[0] = sl;
                        res[1] = i;
                        min = i - sl + 1; //每一次缩短left的时候，所以最后这里就是我们要的最短substring。 这里更新valid的最短距离，
                    }
                }

                char slow = s.charAt(sl);

                if (map.containsKey(slow)){
                    int curCount = map.get(slow);
                    if (curCount == 0) count++; //移动左指针的时候， 还得把count还回去。
                    map.put(slow, curCount + 1);
                }

                sl++;
            }
        }
        return res[0] == -1 ? "" : s.substring(res[0], res[1] + 1);
    }

   private boolean isValidSubSequence(String a, String b){ //快慢指针，我们always assume a是短的那个，b是长的那个。
     int slow = 0, fast = 0;
     while (fast < b.length()){
       if (slow == a.length()) return true;//短的那个已经走到头了，长的没走完。 就是subsequence
       char aCur = a.charAt(slow);
       char bCur = b.charAt(fast);
       if (bCur == aCur){ //主体是比较两个subarray 的character，如果一个两个不想等，直接，就b往后走，因为b比较长，
           slow++;
       }
       fast++;//表指针是无脑往后走。因为b比较长
     }
     return slow == a.length();
   }


    public static void main(String[] args) {
        SubSequence testcase = new SubSequence();
        String s = "mmm456mmmmmm123m";
        String t = "m123m";
        String test = testcase.findSubstr(s, t);
        System.out.println(test);
    }
}
