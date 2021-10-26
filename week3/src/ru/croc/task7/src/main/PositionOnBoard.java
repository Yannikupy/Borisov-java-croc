package main;

import java.util.Arrays;

public class PositionOnBoard {
    private int x;
    private int y;
    PositionOnBoard(){
        this.x = 0;
        this.y = 0;
    }
    PositionOnBoard(int a, int b) throws IllegalPositionException {
        if(a > 7 || b > 7){
            throw new IllegalPositionException("Wrong position");
        }
        else{
            x = a;
            y = b;
        }
    }
    private String getCharForNumber(int i) {
        char[] alphabet = "abcdefgh".toCharArray();
        if (i > 8) {
            return null;
        }
        return Character.toString(alphabet[i]);
    }
    private int getNumberForChar(char c) {
        int a = (int) c;
        int b = (int) 'a';
        return a - b;
    }
    public PositionOnBoard parse(String position) throws IllegalPositionException {
        PositionOnBoard pos = new PositionOnBoard();
        pos.x = getNumberForChar(position.charAt(0));
        pos.y = position.charAt(1) - '1';
        if(pos.x > 7 || pos.y > 7){
            throw new IllegalPositionException("Wrong position");
        }
        else return pos;
    }
    public String LegalMoveChecker(String[] positions) throws IllegalPositionException, IllegalMoveException {
        PositionOnBoard[] pos = new PositionOnBoard[positions.length];
        for (int i = 0; i < positions.length; i++){
            pos[i] = parse(positions[i]);
            System.out.println(pos[i]);
        }
        System.out.println(Arrays.toString(pos));
        for (int i = 1; i < positions.length; i++){  //проверяем расстояние между двумя полями, с которого и на который ходит конь
            if((((Math.abs(pos[i].x - pos[i - 1].x) == 2)  && (Math.abs(pos[i].y - pos[i - 1].y) == 1)
                    || ((Math.abs(pos[i].y - pos[i - 1].y) == 2)  && (Math.abs(pos[i].x - pos[i - 1].x) == 1 ))))){
            }
            else throw new IllegalMoveException("конь так не ходит " + pos[i-1] + "->" + pos[i]);
        }
        return "OK";
    }
    @Override
    public String toString() {
        String c = getCharForNumber(x);
        return (c  + String.valueOf(y + 1));
    }

}
