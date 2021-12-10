import java.util.Objects;

public class Product {
    private String Article;
    private String Item;
    private int cost;

    public Product(String Article, String Item, int cost) {
        this.Article = Article;
        this.Item = Item;
        this.cost = cost;
    }

    public Product(String Article) {
        this.Article = Article;
    }

    public Product() {
    }

    public String getArticle() {
        return Article;
    }

    public String getItem() {
        return Item;
    }

    public int getCost() {
        return cost;
    }

    public void setArticle(String article) {
        this.Article = article;
    }

    public void setItem(String item) {
        this.Item = item;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "productCode: " + Article + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return cost == product.cost && Article.equals(product.Article) && Item.equals(product.Item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Article, Item, cost);
    }
}