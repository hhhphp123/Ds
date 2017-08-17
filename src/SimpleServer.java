import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by hasee on 2017/8/11.
 */
public class SimpleServer {
    public static class ServerThread extends Thread
    {
        String result = null;
        private Socket socket;
        public ServerThread(Socket socket){
            this.socket=socket;
        }
        public void run()
        {
            try {
                while(true)
                {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    String accpect = dataInputStream.readUTF();
                    String[] lookUp = accpect.split(",");
                    if(Integer.parseInt(lookUp[0]) == 0)
                    {
                       DictionaryServer dictionaryServer = new DictionaryServer();
                       result = dictionaryServer.findMeaning(lookUp[1]);
                    }
                    if(Integer.parseInt(lookUp[0]) == 1)
                    {
                        DictionaryServer dictionaryServer = new DictionaryServer();
                        result = dictionaryServer.addMeaning(lookUp[1],lookUp[2]);
                    }
                    if(Integer.parseInt(lookUp[0]) == 2)
                    {
                        DictionaryServer dictionaryServer = new DictionaryServer();
                        result = dictionaryServer.deleteWord(lookUp[1]);
                    }
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    dataOutputStream.writeUTF(result);
                    System.out.println(result);
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] agrs) throws IOException {
            int i = 0;
            ServerSocket socketServer = new ServerSocket(1234,100);
            Socket socket = null;
            while (true)
            {
                socket = socketServer.accept();
                new ServerThread(socket).start();
                System.out.println(i++);
            }





//        Socket client = socketServer.accept();
//        OutputStream out = client.getOutputStream();
//        DataOutputStream massage = new DataOutputStream(out);
//        massage.writeUTF("hello client");
//        System.out.println("RUNNING");
    }
}
