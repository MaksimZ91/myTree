package org.example.MyTree;

public class Node {
    protected int value;
    protected Node leftChildren;
    protected  Node rightChildren;
    protected Colors color;

    public Node(int value) {
        this.value = value;
        this.leftChildren = null;
        this.rightChildren = null;
        this.color = Colors.RED;
    }
}
