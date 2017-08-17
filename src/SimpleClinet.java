import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by hasee on 2017/8/11.
 */
public class SimpleClinet {
    private JFrame frame;
    private JButton find,add,delete;
    private void initialize2() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(Color.GRAY);
        frame.setBounds(100, 100, 300,100 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("English-Chinese Dictionary");
        JTextField textField1=new JTextField();
        textField1.setColumns(10);
        textField1.setBounds(0,0,200,40);
        find = new JButton("确定");
        find.setBounds(220,0,70,40);
        frame.getContentPane().add(find);

        frame.getContentPane().add(textField1);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false);
                JOptionPane.showMessageDialog(frame,"查询");
            }
        });
    }


    public static void main(String[] agrs) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        Socket client = new Socket("localhost",1234);
//        //InputStream massage = client.getInputStream();
//        //DataInputStream formServer = new DataInputStream(massage);
//        //String show = formServer.readUTF();
//        //System.out.println(show);
//        try {
//            while (true)
//            {
//                OutputStream outputStream = client.getOutputStream();
//                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//                dataOutputStream.writeUTF(scanner.nextLine());
//                InputStream inputStream = client.getInputStream();
//                DataInputStream dataInputStream = new DataInputStream(inputStream);
//                String result = dataInputStream.readUTF();
//                System.out.println(result);
//            }
//
//        } finally {
//            client.close();
//        }
//
    }
}
