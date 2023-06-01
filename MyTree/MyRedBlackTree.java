package org.example.MyTree;

public class MyRedBlackTree {
    private Node root;

    public boolean add(int value){
        if (root != null){
            boolean result = checkValue(root, value);
            root = balance(root);
            root.color = Colors.BLACK;
            return result;
        } else {
            root = new Node(value);
            root.color = Colors.BLACK;
            return  true;
        }
    }

    private boolean checkValue(Node node, int value){
        if (node.value == value ) {
            return false;
        } else {
            if ( node.value > value ){
                if (node.leftChildren != null){
                    boolean result = checkValue(node.leftChildren, value);
                    node.leftChildren = balance(node.leftChildren);
                    return result;
                } else {
                    node.leftChildren = new Node(value);
                    return true;
                }
            } else {
                if (node.rightChildren != null ){
                    boolean result = checkValue(node.rightChildren, value);
                    node.rightChildren = balance(node.rightChildren);
                    return result;
                } else {
                    node.rightChildren = new Node(value);
                    return true;
                }
            }
        }
    }

    private Node balance(Node node){
        Node result = node;
        boolean rebalance;
        do {
            rebalance = false;
            if (result.rightChildren != null && result.rightChildren.color == Colors.RED &&
                    (result.leftChildren == null || result.leftChildren.color == Colors.BLACK)){
                rebalance = true;
                result = rightSwitch(result);
            }
            if (result.leftChildren != null && result.leftChildren.color == Colors.RED &&
                    result.leftChildren.leftChildren != null &&  result.leftChildren.leftChildren.color == Colors.RED){
                rebalance = true;
                result = leftSwitch(result);
            }
            if (result.leftChildren != null && result.leftChildren.color == Colors.RED &&
                    result.rightChildren !=null && result.rightChildren.color == Colors.RED){
                rebalance = true;
                switchColor(result);
            }


        } while (rebalance);
        return  result;
    }

    private Node leftSwitch(Node node){
        Node leftChild = node.leftChildren;
        Node betChild = leftChild.rightChildren;
        leftChild.rightChildren = node;
        node.leftChildren = betChild;
        leftChild.color = node.color;
        node.color = Colors.RED;
        return leftChild;
    }

    private Node rightSwitch(Node node){
       Node rightChild = node.rightChildren;
        Node betChild = rightChild.leftChildren;
        rightChild.leftChildren = node;
        node.rightChildren = betChild;
        rightChild.color = node.color;
        node.color = Colors.RED;
        return rightChild;
    }

    private void switchColor(Node node){
        node.rightChildren.color = Colors.BLACK;
        node.leftChildren.color = Colors.BLACK;
        node.color = Colors.RED;
    }


}




