import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 实现要求：
 * 1、根据代码片段实现一个简单的SOCKET ECHO多线程程序；
 * 2、接受到客户端连接后，服务端返回一个欢迎消息;
 * 3、接受到"bye"消息后， 服务端返回一个结束消息，并结束当前连接;
 * 4、支持通过telnet连接本服务端，并且可正常运行；
 * 5、服务端支持接受多个telnet客户端连接;
 * 6、注意代码注释书写。
 */
public class EchoApplication {

    public static void main(String[] args) throws IOException, InterruptedException {


        final int listenPort = 12345;

        // 启动服务端
        EchoServer server = new EchoServer(listenPort);
        server.startService();

        // 启动客户端
        EchoClient client = new EchoClient(listenPort);
        client.startService();

        // 在控制台输入，服务端直接原文返回输入信息
        // 控制台结果示例：
        /**
         2020-03-31 16:58:44.049 - Welcome to My Echo Server.(from SERVER)

         Enter: hello!
         2020-03-31 16:58:55.452 - hello!(from SERVER)

         Enter: this is koal.
         2020-03-31 16:59:06.565 - this is koal.(from SERVER)

         Enter: what can i do for you?
         2020-03-31 16:59:12.828 - what can i do for you?(from SERVER)

         Enter: bye!
         2020-03-31 16:59:16.502 - Bye bye!(from SERVER)
         */
    }

}

class EchoServer {
    private int port;

    // TODO
    public EchoServer(int port) {
        this.port = port;
    }

    public void startService() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Welcome to My Echo Server.(from SERVER)");
            Socket socket = serverSocket.accept();
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");
            InputStream in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            System.out.println("客户端：" + msg);
            File file;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int listenPort = 12345;

        // 启动服务端
        EchoServer server = new EchoServer(listenPort);
        server.startService();
    }

}

class EchoClient {
    private int port;

    public EchoClient(int port) {
        this.port = port;
    }

    public void startService() {
        try {
            Socket s = new Socket("127.0.0.1", port);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//            //向服务器端发送一条消息
//            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
//            bw.flush();
            Scanner in = new Scanner(System.in);
            in.useDelimiter("\n");
            boolean flag = true;
            while (flag) {
                System.out.println("Enter:");
                if (in.hasNext()) {
                    String str = in.next().trim();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    //向服务器端发送一条消息
                    bw.write(str);
                    bw.flush();
                    if ("end".equalsIgnoreCase(str)) {
                        flag = false;// 循环结束
                    }


                }

                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("服务器：" + mess);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int listenPort = 12345;

        // 启动服务端
        EchoClient client = new EchoClient(listenPort);
        client.startService();
    }


}
