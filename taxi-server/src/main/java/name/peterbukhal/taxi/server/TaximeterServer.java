package name.peterbukhal.taxi.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public class TaximeterServer {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        final TaximeterServer taximeterServer = new TaximeterServer(7342);
        taximeterServer.startServer();

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                taximeterServer.stopServer();
            }

        });
    }

    /**
     *
     *
     * @param port сетевой порт
     */
    private TaximeterServer(final int port) {
        executorService = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        System.out.println("Taximeter server started.");

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
        System.out.println("Taximeter server stopped.");

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService.shutdownNow();
    }

    private ExecutorService executorService;

    private void fork(final Socket socket) {
        executorService.submit(new ClientThread(socket));
    }

}
