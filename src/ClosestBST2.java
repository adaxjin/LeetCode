import java.util.ArrayList;
import java.util.List;

public class ClosestBST2 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        //cc
        List<Integer> array = inOrder(root);
        int idx = binarySearch(array, target);
        res.add(array.get(idx));
        while (k-- > 1){
            for (int i = idx; i > 0; i--){
                for (int j = idx; j < array.size(); j++){
                    if (Math.abs(array.get(i - 1) - array.get(idx)) > Math.abs(array.get(j + 1) - array.get(idx))){
                        res.add(j + 1);
                    } else {
                        res.add(i - 1);
                    }
                }
            }
        }
        return res;
    }

    private List<Integer> inOrder(TreeNode root){
        List<Integer> array = new ArrayList<>();
        if (root == null) return array;
        inOrder(root.left);
        array.add(root.val);
        inOrder(root.right);
        return array;
    }

    private int binarySearch(List<Integer> array, double target){
        int left = 0, right = array.size() - 1, mid = 0;
        while (left < right){
            mid = left + (right - left) / 2;
            if (target - array.get(mid) == 0) {
                return mid;
            } else if (target - array.get(mid) > 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Math.abs(array.get(left) - target) > Math.abs(array.get(right) - target) ? right : left;
    }

    public static void main(String[] args) {

    }
}
