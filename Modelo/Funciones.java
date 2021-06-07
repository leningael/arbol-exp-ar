package Modelo;

import java.util.Stack;

public class Funciones {
    public static String[] infijoAPostfijo(String expresion) throws ExpresionIncorrectaExcepcion{
        Stack<Character> pila = new Stack<Character>();
        String operadores = "+-*/^";
        String original = expresion + " ";
        char expresionInfija[] = original.toCharArray();
        expresionInfija[expresionInfija.length-1] = ')';
        String expresionPostfija[] = new String[expresion.length()];
        int j = 0;
        int flag = 0;
        pila.push('(');
        for(int i = 0; i<expresionInfija.length; i++){
            if(expresionInfija[i] == '('){
                pila.push('(');
                flag = 1;
            }
            if(expresionInfija[i] >= 48 && expresionInfija[i] <= 57){
                if(expresionPostfija[j] == null)
                    expresionPostfija[j] = String.valueOf(expresionInfija[i]);
                else
                    expresionPostfija[j] += expresionInfija[i];
                flag = 0;
            }
            if(operadores.indexOf(expresionInfija[i])!=-1){
                if(i==0)//Si inicia la expesión con un operador
                    throw new ExpresionIncorrectaExcepcion("Expresión incorrecta, no se puede iniciar con un operador");
                if(flag == 1)//Operador no seguido de un numero
                    throw new ExpresionIncorrectaExcepcion("Expresión incorrecta, se esperaba un número antes del operador");
                if(expresionInfija[i-1] != ')')
                    j++;
                switch (operadores.indexOf(expresionInfija[i])) {
                    case 0:
                    case 1:
                        while(pila.peek() != '('){
                            expresionPostfija[j++]=String.valueOf(pila.pop());
                        }
                        pila.push(expresionInfija[i]);
                        break;
                    case 2:
                    case 3:
                        while((operadores.indexOf(pila.peek())==2||operadores.indexOf(pila.peek())==3||operadores.indexOf(pila.peek())==4)&&pila.peek() != '(')
                            expresionPostfija[j++]=String.valueOf(pila.pop());
                        pila.push(expresionInfija[i]);
                        break;
                    case 4:
                        while(operadores.indexOf(pila.peek())==4&&pila.peek()!='(')
                            expresionPostfija[j++]=String.valueOf(pila.pop());
                        pila.push('^');
                        break;
                }
                flag=1;
            }
            if(expresionInfija[i] == ')'){
                if(operadores.indexOf(expresionInfija[expresionInfija.length-2])!=-1)//Si la expresión termina con un operador
                    throw new ExpresionIncorrectaExcepcion("Expresión incorrecta, no se puede terminar con un operador");
                if(expresionInfija[i-1] != ')')
                    j++;
                if(pila.empty())//Si no se tienen parentesis balanceados
                    throw new ExpresionIncorrectaExcepcion("Expresión Incorrecta, no se tienen parentesis balanceados");
                while(pila.peek() != '('){
                    expresionPostfija[j++]=String.valueOf(pila.pop());
                }
                pila.pop();
            }
        }
        if(!pila.empty())
            throw new ExpresionIncorrectaExcepcion("Expresion Incorrecta");
        String expresionResultante[] = new String[j];
        for(int i = 0; i < j; i++){
            expresionResultante[i] = expresionPostfija[i];
        }
        return expresionResultante;
    }
    
    public static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e1){
            return false;
        }
    }

    /*public static String obtenerExpresion(String[] arreglo){
        String expresion = "";
        for(int i = 0; i < arreglo.length; i++)
            expresion +=  arreglo[i];
        return expresion;
    }*/
}
