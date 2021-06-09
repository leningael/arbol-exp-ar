package Modelo;

public class Node<T extends Comparable<T>>{
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
}