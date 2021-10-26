package main;

public class IllegalPositionException extends Exception{
    IllegalPositionException(String errorMesage){
        super(errorMesage);
    }
}
