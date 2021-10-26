package main;

public class Main {
    public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
        PositionOnBoard pos = new PositionOnBoard();
        System.out.println(pos.LegalMoveChecker(args));
    }
}
