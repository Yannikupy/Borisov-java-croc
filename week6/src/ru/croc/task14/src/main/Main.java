package main;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static int count_matches(String watches_of_current_user, String watches_of_one_user){
        int counter = 0;
        for (String element : watches_of_current_user.split(",")){
            for(String element2 : watches_of_one_user.split(",")){
                if (Objects.equals(element, element2)){
                    counter++;
                }
            }
        }
        return counter;
    }
    public static String recommendation_algorithm(String watches_of_current_user, Map<Integer, String> watches){
        List<String[]> film_by_user_list = new ArrayList<>();
        Set<String> result_film_set = new HashSet<>();
        for(Integer key : watches.keySet()) {
            if (count_matches(watches_of_current_user, watches.get(key)) > (watches.get(key).split(",").length / 2)){
                film_by_user_list.add(watches.get(key).split(","));
            }
        }
        for (String[] strings : film_by_user_list) {
            result_film_set.addAll(Arrays.asList(strings));
        }
        for(String watched_film : watches_of_current_user.split(",")){
            result_film_set.removeIf(film -> Objects.equals(watched_film, film));
        }
        String current_recommend = null;
        for(String recommend_film : result_film_set){
            int counter = 0;
            int counter1 = 0;
            for(String[] watched_films : film_by_user_list){
                for(String watched_film : watched_films){
                    if(Objects.equals(recommend_film, watched_film)){
                        counter1++;
                    }
                }
            }
            if(counter1 > counter){
                current_recommend = recommend_film;
                counter = counter1;
                counter1 = 0;
            }
        }
        return current_recommend;
    }
    public static void main(String[] args) throws IOException {
        final Path path_to_films = Path.of("src/resources/films.txt");
        final Path path_to_history_of_watches = Path.of("src/resources/watches.txt");
        File films = new File(String.valueOf(path_to_films));
        File history_of_watches = new File(String.valueOf(path_to_history_of_watches));
        Scanner sc = new Scanner(history_of_watches);
        Map<Integer, String> watches = new HashMap<>();
        int i = 0;
        while (sc.hasNext()){
            String concat = sc.next();
            watches.put(i, concat);
            i++;
        }
        Scanner sc_from_user = new Scanner(System.in);
        String watches_of_curr_user = sc_from_user.next();
        FileReader fr = new FileReader(films);
        String recommend_film = null;
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null){
            String[] parser = line.split(",");
            if(Objects.equals(recommendation_algorithm(watches_of_curr_user, watches), parser[0])){
                recommend_film = parser[1];
            }
            line = reader.readLine();
        }
        System.out.println(recommend_film);
    }
}
