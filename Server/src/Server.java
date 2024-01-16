import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server started!");

            System.out.println("Client connected");

            while (true) {
                try (Phone phone = new Phone(server)) {
                    new Thread(() -> {

                        String request = phone.readLide();
                        System.out.println("Request: " + request);
                        String response = "Hello" + request.length();
                        phone.writeLine(response);

                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
