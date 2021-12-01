import java.nio.file.Paths;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Main {
    static void read_orders(String path_to_file, List<String> customers_buys, List<String> article_items){
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(path_to_file));
        } catch (IOException e) {
            System.out.println("Error with opening file");
            return;
        }
        String tmp;
        while (scanner.hasNextLine()) {
            tmp = scanner.nextLine();
            String[] data = tmp.split(",");
            customers_buys.add(data[0] + "," + data[1] + "," + data[2]);
            if (!article_items.toString().contains(tmp.split(",")[2]))
                article_items.add(data[2] + "," + data[3] + "," + data[4]);
        }
    }

    public static void main(String args[]) throws ClassNotFoundException {
        String path = args[0];
        List<String> customers_buys = new ArrayList<>();
        List<String> article_items = new ArrayList<>();
        read_orders(path, customers_buys, article_items);
        String connectionUrl = "jdbc:h2:tcp://localhost/~/task17_db";
        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection(connectionUrl, "sa", "00");
             Statement stmt = conn.createStatement()
        ) {
            //Создаем таблицы
            String CREATE_ARTICLE_ITEMS = "CREATE TABLE ARTICLE_ITEMS" +
                    "(article_id VARCHAR(255) PRIMARY KEY, " +
                    "item VARCHAR(255) not NULL, " +
                    "price INTEGER not NULL)";

            String CREATE_CUSTOMERS_BUYS = "CREATE TABLE CUSTOMERS_BUYS " +
                    "(id INTEGER NOT NULL, " +
                    " login VARCHAR(255) not NULL, " +
                    " article VARCHAR(255), " +
                    "FOREIGN KEY (article) REFERENCES ARTICLE_ITEMS(article_id))";

            stmt.executeUpdate(CREATE_ARTICLE_ITEMS);
            stmt.executeUpdate(CREATE_CUSTOMERS_BUYS);

            //Вставляем в таблицы значения
            String INSERT_ARTICLE_ITEMS = "INSERT INTO ARTICLE_ITEMS VALUES(?,?,?)";
            for (String item : article_items) {
                try (PreparedStatement statement = conn.prepareStatement(INSERT_ARTICLE_ITEMS)) {
                    statement.setString(1, item.split(",")[0]);
                    statement.setString(2, item.split(",")[1]);
                    statement.setInt(3, Integer.parseInt(item.split(",")[2]));
                    statement.execute();
                }
            }
            String INSERT_CUSTOMER_BUYS = "INSERT INTO CUSTOMERS_BUYS VALUES(?,?,?)";
            for (String buy : customers_buys) {
                try (PreparedStatement statement = conn.prepareStatement(INSERT_CUSTOMER_BUYS)) {
                    statement.setInt(1, Integer.parseInt(buy.split(",")[0]));
                    statement.setString(2, buy.split(",")[1]);
                    statement.setString(3, buy.split(",")[2]);
                    statement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
