package cn.xiewei.tree;

public class TreeNode<T extends Comparable<?>>   {
    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data) {
        this.value = data;
    }

    public T getData() {
        return value;
    }

    public void setData(T data) {
        this.value = data;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "[" + this.value + "]";
    }


    
}