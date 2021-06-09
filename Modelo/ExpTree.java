package Modelo;

import java.io.PrintWriter;
import java.text.DecimalFormat;

public class ExpTree<T extends Comparable<T>>{
    DecimalFormat frmt = new DecimalFormat("#.#");
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

    public StringBuilder imprimirArbol(StringBuilder prefix, boolean isTail, StringBuilder sb, Node<T> auxRoot) {
        if(auxRoot.getRightChild()!=null) {
            if(auxRoot==root){
                imprimirArbol(new StringBuilder().append(prefix).append(" "), false, sb, auxRoot.getRightChild()); 
            }else{
                if(isTail){
                    imprimirArbol(new StringBuilder().append(prefix).append("│     "), false, sb, auxRoot.getRightChild());
                }else{
                    imprimirArbol(new StringBuilder().append(prefix).append("      "), false, sb, auxRoot.getRightChild());
                }
            }
        }
        if(Funciones.isNumeric(String.valueOf(auxRoot.getData()))){
            if(isTail){
                sb.append(prefix).append("└────").append("("+auxRoot.getData().toString()+")").append("\n");
            }else{
                sb.append(prefix).append("┌────").append("("+auxRoot.getData().toString()+")").append("\n");
            }
        }else{
            if(auxRoot==root){
                sb.append(prefix).append("("+auxRoot.getData().toString()+")="+frmt.format(auxRoot.getEvaluacion())).append("\n");
            }else{
                if(isTail){
                    sb.append(prefix).append("└────").append("("+auxRoot.getData().toString()+")="+frmt.format(auxRoot.getEvaluacion())).append("\n");
                }else{
                    sb.append(prefix).append("┌────").append("("+auxRoot.getData().toString()+")="+frmt.format(auxRoot.getEvaluacion())).append("\n");
                }
            }
        }
        if(auxRoot.getLeftChild()!=null) {
            if(auxRoot==root){
                imprimirArbol(new StringBuilder().append(prefix).append(" "), true, sb, auxRoot.getLeftChild());
            }else{
                if(isTail){
                    imprimirArbol(new StringBuilder().append(prefix).append("      "), true, sb, auxRoot.getLeftChild());
                }else{
                    imprimirArbol(new StringBuilder().append(prefix).append("│     "), true, sb, auxRoot.getLeftChild());
                }
            }
        }
        return sb;
    }
    
    public String imprimirArbol() {
        return imprimirArbol(new StringBuilder(), true, new StringBuilder(), root).toString();
    }
}
