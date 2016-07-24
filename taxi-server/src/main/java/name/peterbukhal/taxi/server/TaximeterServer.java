package name.peterbukhal.taxi.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by
 *      petronic on 12.07.16.
 */
public class TaximeterServer {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        TaximeterServer taximeterServer = new TaximeterServer(7342);
        taximeterServer.startServer();
    }

    private TaximeterServer(int port) {
        executorService = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        System.out.println("Taximeter server started");

        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                fork(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopServer() {
        System.out.println("Taximeter server stopped");

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService.shutdownNow();
    }

    private ExecutorService executorService;

    private void fork(Socket socket) {
        executorService.submit(new ClientThread(socket));
    }

}
