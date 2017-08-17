import java.sql.*;

/**
 * Created by hasee on 2017/8/16.
 */
public class DictionaryServer {
    Connection con;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/dictionary";
    String user = "root";
    String password = "123456";
    public String findMeaning(String inputword)
    {
        String resultMeaning = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String sql = "select * from wordtable";
            ResultSet rs = statement.executeQuery(sql);
            String word = null;
            String meaning = null;
            while(rs.next()){
                word = rs.getString("word");
                meaning = rs.getString("meaning");
                if(word.equals(inputword))
                {
                  resultMeaning = meaning;
                  break;
                }
                else
                {
                    resultMeaning = "not found in the dictionary";
                }

            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            resultMeaning = "Sorry,the server breaks down!";
            e.printStackTrace();
        } catch(SQLException e) {
            resultMeaning = "Sorry,the server breaks down!";
            e.printStackTrace();
        }catch (Exception e) {
            resultMeaning = "Sorry,the server breaks down!";
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
            return resultMeaning;

        }

    }
    public String addMeaning(String inputword, String inputmeaning)
    {
        String resultAdd = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
            {

                System.out.println("Succeeded connecting to the Database!");
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO wordtable (word, meaning) VALUES (?,?)");
                preparedStatement.setString(1,inputword);
                preparedStatement.setString(2,inputmeaning);
                preparedStatement.executeUpdate();
                resultAdd = "add Successfully";
            }
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            resultAdd = "Sorry,the server breaks down";
            e.printStackTrace();
        } catch(SQLException e) {
            resultAdd = "Sorry,this word are already in the dictionary";
            e.printStackTrace();
        }catch (Exception e) {
            resultAdd = "Sorry,the server breaks down";
            e.printStackTrace();
        }finally{
            System.out.println("字典修改情况报告！");
            return resultAdd;
        }

    }

    public String deleteWord(String inputword)
    {
        String deleteResult = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
            {

                System.out.println("Succeeded connecting to the Database!");
                String find = findMeaning(inputword);
                con = DriverManager.getConnection(url,user,password);
                if (find != "not found in the dictionary")
                {
                    PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM wordtable Where word = ?");
                    preparedStatement.setString(1,inputword);
                    preparedStatement.executeUpdate();
                    deleteResult = "delete Successfully";
                }
                else
                {
                    deleteResult = "Sorry,this word are not exist in  the dictionary";
                }

            }
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            deleteResult = "Sorry,the server breaks down";
            e.printStackTrace();
        } catch(SQLException e) {
            deleteResult = "Sorry,this word are not exist in  the dictionary";
            e.printStackTrace();
        }catch (Exception e) {
            deleteResult = "Sorry,the server breaks down";
            e.printStackTrace();
        }finally{
            System.out.println("字典修改情况报告！");
            return deleteResult;
        }

    }

    public static void main(String[] agrs)
    {
        DictionaryServer dictionaryServer = new DictionaryServer();
        //String meaning = dictionaryServer.findMeaning("cool");
        //String addResult = dictionaryServer.addMeaning("hello","你好");
        //String deleteResult = dictionaryServer.deleteWord("happy");
        //System.out.println(meaning);
       //System.out.println(addResult);
        //System.out.println(deleteResult);

    }
}
