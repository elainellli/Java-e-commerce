import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Client {
    private static UI ui;
    public static void main(String[] args) {
        ui = new UI();
        ui.initUI();
    }
}

class UI {
    private JFrame frame;
    private JPanel main;
    private CardLayout cardLayout;
    private ProductPanel productPanel;
    private OrderPanel orderPanel;
    private CartPanel cartPanel;
    private LoginPanel loginPanel;

    public void initUI() {
        frame = new JFrame("E-Commerce Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        productPanel = new ProductPanel();
        orderPanel = new OrderPanel();
        cartPanel = new CartPanel();
        loginPanel = new LoginPanel();

        main.add(loginPanel, "Login");
        main.add(productPanel, "Products");
        main.add(cartPanel, "ShoppingCart");
        main.add(orderPanel, "Orders");

        JPanel navbar = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton productsButton = new JButton("Products");
        JButton cartButton = new JButton("ShoppingCart");
        JButton ordersButton = new JButton("Orders");

        navbar.add(loginButton);
        navbar.add(productsButton);
        navbar.add(cartButton);
        navbar.add(ordersButton);

        loginButton.addActionListener(e -> cardLayout.show(main, "Login"));
        productsButton.addActionListener(e -> cardLayout.show(main, "Products"));
        cartButton.addActionListener(e -> cardLayout.show(main, "ShoppingCart"));
        ordersButton.addActionListener(e -> cardLayout.show(main, "Orders"));

        frame.setLayout(new BorderLayout());
        frame.add(navbar, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setVisible(true);

        cardLayout.show(main, "Login");
    }
}

class ProductPanel extends JPanel {

}

class OrderPanel extends JPanel {

}

class CartPanel extends JPanel {

}

class LoginPanel extends JPanel {
    public LoginPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Login");
        add(label, BorderLayout.NORTH);

        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        usernameField.setPreferredSize(new Dimension(100, 20));
        passwordField.setPreferredSize(new Dimension(100, 20));
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(usernameLabel, gbc);
        gbc.gridx = 1;
        main.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(passwordLabel, gbc);
        gbc.gridx = 1;
        main.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        main.add(loginButton, gbc);
        add(main, BorderLayout.CENTER);
    }

}