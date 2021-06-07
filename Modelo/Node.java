package Modelo;

import java.text.DecimalFormat;

public class Node<T extends Comparable<T>>{
    DecimalFormat frmt = new DecimalFormat("#.#");
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private double evaluacion;

    public Node(T data){
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void displayNode(){
        System.out.print('('); 
        System.out.print(data); 
        System.out.print(")");
    }

    public T getData(){
        return this.data;
    }
    public void setData(T data){
        this.data = data;
    }

    public Node<T> getLeftChild(){
        return this.leftChild;
    }

    public Node<T> getRightChild(){
        return this.rightChild;
    }

    public void setLeftChild(Node<T> left){
        this.leftChild = left;
    }

    public void setRightChild(Node<T> right){
        this.rightChild = right;
    }
    
    public double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(rightChild!=null) {
            rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│     " : "      "), false, sb);
        }
        if(Funciones.isNumeric(String.valueOf(data)))
            sb.append(prefix).append(isTail ? "└──── " : "┌──── ").append("("+data.toString()+")").append("\n");
        else
            sb.append(prefix).append(isTail ? "└──── " : "┌──── ").append("("+data.toString()+")="+frmt.format(evaluacion)).append("\n");
        if(leftChild!=null) {
            leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "      " : "│     "), true, sb);
        }
        return sb;
    }
    
    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }
}