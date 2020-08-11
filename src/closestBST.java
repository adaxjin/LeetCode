
public class closestBST {
    public int closestValue(ConstructTree.TreeNode root, double target) {
        if (root == null) throw new IllegalArgumentException();

        ConstructTree.TreeNode cur = root;
        ConstructTree.TreeNode parent = root;
        while (cur != null) {
            if (cur.val == target) {
                return cur.val;
            } else if (cur.val > target && parent.val > target) {
                parent = cur;
                cur = cur.left;
            } else if (cur.val < target && parent.val < target) {
                parent = cur;
                cur = cur.right;
            } else {
                return Math.abs(cur.val - target) > Math.abs(parent.val - target) ? parent.val : cur.val;
            }
        }
        return parent.val;
    }

    public static void main(String[] args) {
        closestBST testcase = new closestBST();
        Integer[] nums = {41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23};
        ConstructTree.TreeNode root = ConstructTree.constructTree(nums);
        double target = 3.285714;
        int test = testcase.closestValue(root, target);
        System.out.println(test);
    }
}


