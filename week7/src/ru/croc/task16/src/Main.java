import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Integer[] borders_of_age_groups = new Integer[args.length + 1];
        for(int i = 0; i < args.length; i++){
            borders_of_age_groups[i] = Integer.valueOf(args[i]);
        }
        borders_of_age_groups[borders_of_age_groups.length - 1] = 123;
        Map<String,Integer> name_age = new HashMap<>();
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);
        String line;
        while(!Objects.equals(line = input.readLine(), "END")){
            String[] line_delimited = line.split(",");
            name_age.put(line_delimited[0], Integer.parseInt(line_delimited[1]));
        }
        Map<String, List<String>> by_age_group = new HashMap<>();
        Integer prev_group = 0;
        for(Integer age_group : borders_of_age_groups){
            List<String> persons = new ArrayList<>();
            for(String Person : name_age.keySet()){
                if((prev_group < name_age.get(Person)) && (name_age.get(Person) <= age_group)){
                    persons.add(Person);
                }
            }
            if(prev_group == 101){
                by_age_group.put(prev_group.toString() + "+", persons);
            }
            if(age_group == 123) continue;
            by_age_group.put(prev_group.toString() + "-" + age_group.toString(), persons);
            prev_group = age_group + 1;
        }
        List<String> list = new ArrayList<String>(by_age_group.keySet());

        Collections.reverse(list);
        for(String key1 : list){
            List<String> by_age = by_age_group.get(key1);
            by_age.sort((o1, o2) -> {
                if(name_age.get(o1) < name_age.get(o2)) return 1;
                if(name_age.get(o1) > name_age.get(o2)) return -1;
                else return o1.compareTo(o2);
            });
            if(!by_age_group.get(key1).isEmpty()) {
                System.out.print(key1 + ": ");
            }
            for(String key2 : by_age){
                System.out.print(key2 + " (" + name_age.get(key2) + ") ");
            }
            if(!by_age_group.get(key1).isEmpty()) {
                System.out.println();
            }
        }
    }
}
