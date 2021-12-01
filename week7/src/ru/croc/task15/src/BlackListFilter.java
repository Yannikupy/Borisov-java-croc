import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public interface BlackListFilter<T> {
    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blackList) {
        ArrayList<T> result = new ArrayList<>();
        Iterator<T> it = comments.iterator();
        while(it.hasNext()){
            T current_comment = it.next();
            if(!blackList.test(current_comment)){
                result.add(current_comment);
            }
        }
        return result;
    }
}
