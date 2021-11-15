package main;

import java.util.*;

class Filter implements BlackListFilter{

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (Iterator<String> iterator = comments.iterator(); iterator.hasNext(); ){
            String value = iterator.next();
            for (String banned_word : blackList){
                if (value.equals(banned_word)){
                    iterator.remove();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args){
        List<String> list_of_comments = new ArrayList<>();
        list_of_comments.add("fine");
        list_of_comments.add("bad");
        list_of_comments.add("cool");
        list_of_comments.add("bad");
        Set<String> set_of_black_words = new HashSet<>();
        set_of_black_words.add("bad");
        Filter filter = new Filter();
        filter.filterComments(list_of_comments, set_of_black_words);
        System.out.println(list_of_comments);
    }
}
