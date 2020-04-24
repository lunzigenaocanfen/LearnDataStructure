package com.great.tree6.lession6_2;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * 二分搜索树
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* 向二分搜索树中添加新的元素e */
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else
            add(root, e);
    }

    /* 向二分搜索树中添加新的元素e */
    public void add2(E e) {
            root = add2(root, e);
    }
    //添加元素
    private void add(Node node, E e) {
        if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }
        if (e.compareTo(node.e) < 0)
            add(node.left, e);
        else
            add(node.right, e);
    }
    //改进后
    private Node add2(Node node, E e) {
        if (node == null) {
            size ++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0)
            node.left = add2(node.left, e);
        else
            node.right = add2(node.right, e);

        return node;
    }
    //查找元素
    public boolean contains(E e) {
        return contains(root, e);
    }
    //递归查找元素
    private boolean contains(Node node, E e) {
        if(node ==null)
            return false;
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }else
            return contains(node.right, e);
    }
    //非递归实现查找元素
    public boolean containsByDigui(Node node, E e) {
        Node root = node;
        while (root != null && e.compareTo(root.e) != 0) {
            if (e.compareTo(root.e) < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if(root ==null)
            return false;
        return true;
    }

    //--------------------
    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }
    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e.toString());
        preOrder(node.left);
        preOrder(node.right);
    }

    //非递归前序遍历的方法。
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    //中序遍历
    public void inOrder() {
        inOrder(root);
    }
    //中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node){
        if(node==null)
            return;
        inOrder(node.left);
        System.out.println(node.e.toString());
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder() {
        postOrder(root);
    }
    //后序遍历以node为根的二分搜索树，递归算法
    private void postOrder(Node node){
        if(node==null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e.toString());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node,int depth,StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
