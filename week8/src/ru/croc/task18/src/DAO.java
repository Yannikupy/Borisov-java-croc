import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    Product findProduct(String productCode) throws SQLException {
        Product returnProduct = new Product();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM ARTICLE_ITEMS p WHERE p.ARTICLE_ID = ?")){
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    returnProduct.setArticle(result.getString("ARTICLE_ID"));
                    returnProduct.setItem(result.getString("ITEM"));
                    returnProduct.setCost(result.getInt("PRICE"));
                }
            }
        }
        if (returnProduct.getArticle() == null)
            return null;
        return returnProduct;
    }

    Product createProduct(Product product) throws SQLException {
        if (findProduct(product.getArticle()) != null)
            System.out.println("Товар уже в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO ARTICLE_ITEMS VALUES(?,?,?)")) {
                statement.setString(1, product.getArticle());
                statement.setString(2, product.getItem());
                statement.setInt(3, product.getCost());
                statement.execute();
            }
        }
        return product;
    }

    Product updateProduct(Product product) throws SQLException {
        if (findProduct(product.getArticle()) == null)
            System.out.println("Такого товара нет в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement
                    ("UPDATE ARTICLE_ITEMS " +
                            "SET ITEM = ?, PRICE = ? " +
                            "WHERE ARTICLE_ID = ?")) {
                statement.setString(1, product.getItem());
                statement.setInt(2, product.getCost());
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return product;
    }

    void deleteProduct(String productCode) throws SQLException {
        if (findProduct(productCode) == null)
            System.out.println("Такого товара нет в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM CUSTOMERS_BUYS o " +
                            "WHERE o.ARTICLE = ?; " +
                            "DELETE FROM ARTICLE_ITEMS p " +
                            "WHERE p.ARTICLE_ID = ?")) {
                statement.setString(1, productCode);
                statement.setString(2, productCode);
                statement.execute();
            }
        }
    }

    int getLastOrderID() throws SQLException {
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT ARTICLE_ID FROM ARTICLE_ITEMS o ORDER BY o.ARTICLE_ID DESC")) {
                result.next();
                return result.getInt("ID");
            }
        }
    }

    Order createOrder(String userLogin, List<Product> products) throws SQLException {
        int ID = getLastOrderID() + 1;
        ArrayList<String> productsArticles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO CUSTOMERS_BUYS VALUES(?,?,?)")) {
            for (Product product : products) {
                if (findProduct(product.getArticle()) == null)
                    System.out.println("Такого товара нет в базе данных");
                productsArticles.add(product.getArticle());
                statement.setInt(1, ID);
                statement.setString(2, userLogin);
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return new Order(ID, userLogin, productsArticles);
    }

}
