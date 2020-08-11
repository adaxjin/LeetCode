import java.util.LinkedList;
import java.util.Queue;

/*
    Note: there is no empty after or before ‘,’. ‘#’ is null in the tree.

    1.“0”
     0
     / \
    ^  ^

    2. “1,2”
      1
     /
    2

    3. “1,#,2”
    1
      \
       2
 */

public class TreeGenerator { // LC449
    //Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur == null) {
                sb.append("#,");
            } else {
                sb.append(cur.val + ",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        // try to compress as possible as it can
        int i = sb.length();
        while (i >= 0){
            if (!sb.substring(i - 2, i).equals("#,")){
                break;
            }
            i -= 2;
        }
        sb.setLength(i);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String ss[] = data.split(",");
        if (ss.length == 0) {
            throw new IllegalArgumentException();
        }
        if (ss.length == 1 && ss[0].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(ss[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < ss.length) {
            TreeNode cur = queue.poll();

            TreeNode left =
                    ss[i].equals("#")
                            ? null : new TreeNode(Integer.valueOf(ss[i]));
            TreeNode right =
                    (++i >= ss.length || ss[i].equals("#"))
                            ? null : new TreeNode(Integer.valueOf(ss[i]));

            cur.left = left;
            cur.right = right;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }

            i++;
        }

        return root;
    }
}
