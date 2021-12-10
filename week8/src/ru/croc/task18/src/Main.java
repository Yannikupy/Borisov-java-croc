import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:h2:tcp://localhost/~/task17_db";

        try (Connection connection = DriverManager.getConnection(connectionUrl, "sa", "00")) {
            executingCommands(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void executingCommands(Connection connection) throws SQLException {
        DAO dao = new DAO(connection);
        Scanner sc = new Scanner(System.in);

        WHILE:
        while (true) {
            System.out.println("Введите команду:");
            String[] command = sc.nextLine().split(" ");
            switch (command[0]) {
                //ТОВАР <артикул_товара> <название> <цена>
                case "ТОВАР":
                case "товар":
                    if (command.length != 4) {
                        System.out.println("Команда введена неверно!");
                        break;
                    }
                    try {
                        dao.createProduct(new Product(command[1], command[2], Integer.parseInt(command[3])));
                        System.out.println("Команда успешно выполнена!");
                    } catch (NumberFormatException e) {
                        System.out.println("Неправильный формат ввода параметров товара!");
                    }
                    break;
                //ИЗМЕНИТЬ <артикул_товара> <новое_название> <новая_цена>
                case "ИЗМЕНИТЬ":
                case "изменить":
                    if (command.length != 4) {
                        System.out.println("Команда введена неверно!");
                        break;
                    }
                    try {
                        dao.updateProduct(new Product(command[1], command[2], Integer.parseInt(command[3])));
                        System.out.println("Команда успешно выполнена!");
                    } catch (NumberFormatException e) {
                        System.out.println("Неправильный формат ввода параметров товара!");
                    }
                    break;
                //УДАЛИТЬ <артикул_товара>
                case "УДАЛИТЬ":
                case "удалить":
                    if (command.length != 2) {
                        System.out.println("Команда введена неверно!");
                        break;
                    }
                    dao.deleteProduct(command[1]);
                    System.out.println("Команда успешно выполнена!");
                    break;
                //ЗАКАЗ <логин_пользователя> <артикул_товара_1>[, <артикул_товара_N>]
                case "ЗАКАЗ":
                case "заказ":
                    if (command.length < 3) {
                        System.out.println("Недостаточное количество параметров!");
                        break;
                    }
                    ArrayList<Product> productsForOrder = new ArrayList<>();
                    for (int i = 2; i < command.length; i++) {
                        productsForOrder.add(new Product(command[i]));
                    }
                    dao.createOrder(command[1], productsForOrder);
                    System.out.println("Команда успешно выполнена!");
                    break;
                case "КОНЕЦ":
                case "конец":
                    break WHILE;
                default:
                    System.out.println("Команда введена неверно!");
                    break;
            }
        }
    }
}