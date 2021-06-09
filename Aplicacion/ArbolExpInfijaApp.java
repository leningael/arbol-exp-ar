package Aplicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import Modelo.ExpTree;
import Modelo.ExpresionIncorrectaExcepcion;
import Modelo.Funciones;

public class ArbolExpInfijaApp {
    public static void main(String[] args) {
        DecimalFormat frmt = new DecimalFormat("#.#");
        try{
            int contExp = 0;
            File archivoEntrada = new File(args[0]);
            Scanner scArchivo = new Scanner(archivoEntrada);
            FileWriter archivoSalida = new FileWriter("expresiones.txt");
            PrintWriter escribir = new PrintWriter(archivoSalida);
            while(scArchivo.hasNextLine()){
                String linea = scArchivo.nextLine();
                StringTokenizer st = new StringTokenizer(linea, ";");
                while(st.hasMoreTokens()){
                    String token = st.nextToken();
                    if(!token.trim().isEmpty()){
                        escribir.print("Exp " + ++contExp);
                        String expresion = null;
                        expresion = token.replaceAll(" ", "");
                        String postfija[] = Funciones.infijoAPostfijo(expresion);
                        //System.out.println(Funciones.obtenerExpresion(postfija));
                        ExpTree<String> expTree = new ExpTree<String>();
                        expTree = construirArbolExp(postfija);
                        expTree.traverse(1, escribir);
                        expTree.traverse(2, escribir);
                        expTree.traverse(3, escribir);
                        escribir.println("\nEvaluacion del arbol: " + frmt.format(expTree.getRoot().getEvaluacion())+"\n");
                        escribir.println(expTree.imprimirArbol());
                    }               
                }
            }
            System.out.println("Programa ejecutado con exito, revise los resultados en el archivo expresiones.txt");
            escribir.close();
            archivoSalida.close();
            scArchivo.close();
        }catch(FileNotFoundException e1){
            System.out.println("El archivo de entrada especificado no existe");
        }catch(IOException e2){
            System.out.println("Error en la creación del archivo de salida");
        }catch(ExpresionIncorrectaExcepcion e3){
            System.out.println(e3.getMessage());
        }
    }
    
    public static ExpTree<String> construirArbolExp(String[] expPostfija){
        String operadores = "+-*/^";
        Stack<ExpTree<String>> pilaExpTree = new Stack<ExpTree<String>>();
        for(int i = 0; i < expPostfija.length; i++){
            if(Funciones.isNumeric(expPostfija[i])){
                ExpTree<String> expTree = new ExpTree<>(expPostfija[i]);
                expTree.getRoot().setEvaluacion(Double.parseDouble(expPostfija[i]));
                pilaExpTree.push(expTree);
            }
            if(operadores.indexOf(expPostfija[i])!=-1){
                ExpTree<String> expTree = new ExpTree<>(expPostfija[i]);
                ExpTree<String> subDer = pilaExpTree.pop();
                ExpTree<String> subIzq = pilaExpTree.pop();
                expTree.getRoot().setRightChild(subDer.getRoot());
                expTree.getRoot().setLeftChild(subIzq.getRoot());
                switch (operadores.indexOf(expPostfija[i])) {
                    case 0:
                        expTree.getRoot().setEvaluacion(subIzq.getRoot().getEvaluacion()+subDer.getRoot().getEvaluacion());
                        break;
                    case 1:
                        expTree.getRoot().setEvaluacion(subIzq.getRoot().getEvaluacion()-subDer.getRoot().getEvaluacion());
                        break;
                    case 2:
                        expTree.getRoot().setEvaluacion(subIzq.getRoot().getEvaluacion()*subDer.getRoot().getEvaluacion());
                        break;
                    case 3:
                        expTree.getRoot().setEvaluacion(subIzq.getRoot().getEvaluacion()/subDer.getRoot().getEvaluacion());
                        break;
                    case 4:
                        expTree.getRoot().setEvaluacion(Math.pow(subIzq.getRoot().getEvaluacion(), subDer.getRoot().getEvaluacion()));
                        break;
                }
                pilaExpTree.push(expTree);
            }
        }
        return pilaExpTree.pop();
    }
}
