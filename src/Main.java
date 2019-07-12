/**
 * @Author: ZhuRuiJie
 * @Date: 2019/6/27 11:11
 * @Description:
 */
public class Main {
    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int nums[] = {5,3,6,8,2,4};
        for(int num : nums){
            bst.add(num);
        }

    }
}
