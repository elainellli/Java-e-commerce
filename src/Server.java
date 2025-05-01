import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 8888;
    private static Map<String, String> users = new HashMap<>(); // username -> password

    public static void main(String[] args) {
        // 初始化假用户
        users.put("alice", "1234");
        users.put("bob", "abcd");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");

                // 为每个客户端启动一个线程
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(
                            socket.getOutputStream(), true)
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Received: " + line);
                    String[] parts = line.split("\\|");
                    if (parts[0].equals("LOGIN") && parts.length == 3) {
                        String username = parts[1];
                        String password = parts[2];
                        if (users.containsKey(username) && users.get(username).equals(password)) {
                            out.println("SUCCESS");
                        } else {
                            out.println("FAIL");
                        }
                    } else if (parts[0].equals("GET_PRODUCTS")) {
                        out.println("Apple|2.99|50;Banana|1.99|30");
                    } else {
                        out.println("UNKNOWN_COMMAND");
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }
    }
}
