package main;

import javafx.util.Pair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

public class Main {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private static synchronized String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static synchronized String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        System.out.println(toHexString(bytes));
        return toHexString(bytes);
    }


    public static synchronized Pair<String, Boolean> pass_checker(String pass_attempt){
        if(Objects.equals(hashPassword(pass_attempt), "40682260CC011947FC2D0B1A927138C5")){
            return new Pair<>(pass_attempt, true);
        }
        return new Pair<>("", false);
    }

    public static synchronized String randomWord(int length) {
        Random random = new Random();
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char)('a' + random.nextInt(26)));
        }
        System.out.println(word.toString());
        return word.toString();
    }

    static class AnotherRun implements Runnable{
        Pair<String, Boolean>  password = new Pair<>(null, false);
        @Override
        public void run(){
            password = pass_checker(randomWord(7));
            System.out.println(Thread.currentThread().getName());
        }
        public Pair<String, Boolean> getValue() {
            return password;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num_of_threads = Integer.parseInt(args[0]);
        AnotherRun[] anotherRuns = new AnotherRun[num_of_threads];
        Thread[] myThreads = new Thread[num_of_threads];
        boolean pass_founded = false;
        String password = "";
        while(!pass_founded) {
            for(int i = 0; i < num_of_threads; i++){
                anotherRuns[i] = new AnotherRun();
            }
            for(int i = 0; i < num_of_threads; i++){
                myThreads[i] = new Thread(anotherRuns[i]);
                myThreads[i].start();
            }
            for(int i = 0; i < num_of_threads; i++){
                myThreads[i].join();
                Pair<String, Boolean> return_value = anotherRuns[i].getValue();
                if(return_value.getValue()){
                    pass_founded = true;
                    password = return_value.getKey();
                    break;
                }
            }
        }
        System.out.println("Password is " + password);
    }
}
