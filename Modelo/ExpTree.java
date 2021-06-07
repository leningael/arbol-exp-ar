package Modelo;

import java.io.PrintWriter;

public class ExpTree<T extends Comparable<T>>{
    Node<T> root;

    public ExpTree(){
        root = null;
    }
    public ExpTree(T data){
        Node<T> newNode = new Node<T>(data);
        root = newNode;
    }
    public void traverse(int type, PrintWriter escribir) {
        switch(type) {
        case 1:
            escribir.print("\nPreorder traversal: ");
            preOrder(root, escribir);
            break;
        case 2:
            escribir.print("\nInorder traversal: "); 
            inOrder(root, escribir);
            break;
        case 3:
            escribir.print("\nPostorder traversal: "); 
            postOrder(root, escribir);
            break;
        }
    }

    private void preOrder(Node<T> auxRoot, PrintWriter escribir) {
        if(auxRoot!=null){
            escribir.print(auxRoot.getData() + " ");
            preOrder(auxRoot.getLeftChild(), escribir);
            preOrder(auxRoot.getRightChild(), escribir);
        }
    }

    private void inOrder(Node<T> auxRoot, PrintWriter escribir) {
        if(auxRoot!=null){
            inOrder(auxRoot.getLeftChild(), escribir);
            escribir.print(auxRoot.getData() + " ");
            inOrder(auxRoot.getRightChild(), escribir);
        }
    }

    private void postOrder(Node<T> auxRoot, PrintWriter escribir) {
        if(auxRoot!=null){
            postOrder(auxRoot.getLeftChild(), escribir);
            postOrder(auxRoot.getRightChild(), escribir);
            escribir.print(auxRoot.getData() + " ");
        }
    }
    public Node<T> getRoot() {
        return root;
    }
}
