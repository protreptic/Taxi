package name.peterbukhal.taxi.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import name.peterbukhal.android.taxi.protocol.Ping;
import name.peterbukhal.android.taxi.protocol.Pong;

/**
 * Created by
 *      petronic on 12.07.16.
 */
public class ClientThread implements Runnable {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket) {
        try {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            //
        }

        System.out.println("Client " + socket.getInetAddress().getHostName() + " connected");
    }

    @Override
    public void run() {
        final byte[] buffer = new byte[4 * 1024];

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Ping ping = Ping.ADAPTER.decode(
                        Arrays.copyOf(buffer, inputStream.read(buffer)));

                System.out.println(ping.toString());

                outputStream.write(
                        Pong.ADAPTER.encode(
                                new Pong.Builder()
                                        .timestamp(System.currentTimeMillis())
                                        .build()));
            } catch (Exception e) {
                System.out.println("Client "
                        + socket.getInetAddress().getHostName() + " disconnected");

                break;
            }
        }
    }

}
