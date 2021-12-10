import java.util.ArrayList;

public class Order {
    private int id;
    private String userLogin;
    private ArrayList<String> article = new ArrayList<>();

    public Order (int id, String userLogin, ArrayList<String> article) {
        this.id = id;
        this.userLogin = userLogin;
        this.article = article;
    }
    public Order(){ }

    @Override
    public String toString() {
        return "ORDER:\nID :" + id + "; userLogin: " + userLogin  + "; article(s): " + article;
    }
}
