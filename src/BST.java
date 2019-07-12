import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: ZhuRuiJie
 * @Date: 2019/6/26 16:46
 * @Description: 二分搜索树
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //添加元素e
    public void add(E e){
        root = add(root,e);
    }

    //递归，在node为根的树中，插入元素e
    private Node add(Node node,E e){
        //最小递归条件
        if(node == null){
            size++;
            return new Node(e);
        }

        //递归部分判断
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }else{
            node.right = add(node.right,e);
        }
        return node;
    }

    //查询是否存在元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    //递归
    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }else if(e.compareTo(node.e) == 0){
            return true;
        }else if(e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    //先序遍历
    public void preOrder(){
        preOrder(root);
    }

    //递归
    private void preOrder(Node node){
        if(node==null){
            return;
        }

        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }

    //非递归二分搜索树
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right!=null)
                stack.push(cur.right);
            if(cur.left!=null)
            stack.push(cur.left);
        }
    }

    //中序
    public void inOrder(){
        inOrder(root);
    }

    //递归
    private void inOrder(Node node){
        if(node==null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序
    public void postOrder(){
        postOrder(root);
    }

    //递归
    private void postOrder(Node node){
        if(node==null){
            return;
        }

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left!=null)
                q.offer(cur.left);
            if(cur.right!=null)
                q.offer(cur.right);
        }
    }

    //寻找最小元素
    public E minimum(){
        if(size==0)
            throw new IllegalArgumentException("BST is Empty!");

        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //寻找最大元素
    public E maximum(){
        if(size==0)
            throw new IllegalArgumentException("BST is Empty!");

        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right==null){
            return node;
        }
        return maximum(node.right);
    }

    //移除并返回最大元素
    public E removeMin(){
        E ret = minimum();

        root = removeMin(root);

        return ret;
    }

    private Node removeMin(Node node){
        if(node.left==null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax(){
        E ret = maximum();

        root = removeMax(root);

        return ret;
    }

    private Node removeMax(Node node){
        if(node.right==null){
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }

        node.right = removeMax(node);
        return node;
    }

    //删除节点
    public void remove(E e){

    }

    private Node remove(Node node,E e){
        if(node==null){
            return null;
        }

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else{

            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node rightMinNode = minimum(node.right);
            rightMinNode.right = removeMin(node.right);
            rightMinNode.left = node.left;

            node.left=null;
            node.right=null;
            return rightMinNode;

        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        generateBSTString(root,0,sb);
        return sb.toString();
    }

    /**
     * @Author Zhu Rui Jie
     * @Description //TODO
     * @Date 11:18 2019/6/27
     * @Param Node node 当前节点
     *        int depth 当前深度
     *        StringBuilder sb 字符串
     **/
    private void generateBSTString(Node node,int depth,StringBuilder sb){
        if(node==null){
            sb.append(generateDepthString(depth)+"null\n");
            return;
        }

        sb.append(generateDepthString(depth)+node.e+"\n");

        generateBSTString(node.left,depth+1,sb);
        generateBSTString(node.right,depth+1,sb);

    }

    private String generateDepthString(int depth){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<depth;i++){
            sb.append("--");
        }
        return sb.toString();
    }
}
