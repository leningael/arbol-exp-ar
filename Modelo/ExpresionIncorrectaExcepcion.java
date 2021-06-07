package Modelo;

public class ExpresionIncorrectaExcepcion extends RuntimeException{
    public ExpresionIncorrectaExcepcion(String message){
        super(message);
    }
}