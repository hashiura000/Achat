package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.ChatlogDto;

public class ChatSql {
	
    public int ChatRoomSelect(String name) {
    	
        // MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "acaht";
        String password = "acaht";

        // SQL文の作成
        String sql1 = "SELECT * FROM chatroom WHERE deleteFlg = false AND chatroom_name = '";
        String sql2 = "';";
        String sql = sql1 + name + sql2;
        
        int result = 0;

        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 結果の表示
            while (resultSet.next()) {
                result = resultSet.getInt("chatroom_seq");
            }

            // リソースの解放
            resultSet.close();
            statement.close();
            conn.close();
            
            // 接続を閉じる
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }
        
		return result;
    }
    
    public ChatlogDto ChatlogSelect(int chatroomseq) {
    
    	// MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "root";
        String password = "achat";

        // SQL文の作成
        String sql1 = "SELECT * FROM chatlog AS cl JOIN chatroom AS cr ON cl.chatroom_seq = cr.chatroom_seq JOIN accounts AS a ON a.account_seq = cl.account_seq WHERE cr.chatroom_seq = '";
        String sql2 = "' ORDER BY cr.chatroom_seq  DESC;";
        String sql = sql1 + String.valueOf(chatroomseq) + sql2;
        
        ChatlogDto result = new ChatlogDto();
        List<String> chatlog = new ArrayList<String>();
        List<Integer> accountSeq = new ArrayList<Integer>();
        List<String> accountName = new ArrayList<String>();
        List<String> postdatetime = new ArrayList<String>();

        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
            // クエリの実行と結果セットの取得
            ResultSet rs = statement.executeQuery(sql);

            // 結果の表示
            while (rs.next()) {
            	chatlog.add(rs.getString("chatlog"));
            	accountSeq.add(rs.getInt("account_seq"));
            	accountName.add(rs.getString("account_name"));
            	postdatetime.add(rs.getString("postdatetime"));
            }
            
            result.setChatlog(chatlog);
            result.setAccountSeq(accountSeq);
            result.setAccountName(accountName);
            result.setPostdatetime(postdatetime);
            
            // リソースの解放
            rs.close();
            statement.close();
            conn.close();
            
            // 接続を閉じる
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }
		return result;
    }
    
    public void chatInsert(int accountSeq, int chatroomSeq, String chat) {
    	
    	// MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "root";
        String password = "acaht";

        // SQL文の作成
        String sql1 = "INSERT INTO chatlog (chatroom_seq, account_seq, chatlog, postdatetime, updatetime) VALUES (";
        String sql2 =  chatroomSeq + "," + accountSeq + "," + "'" + chat + "'" + ", now(), now());";
        String sql = sql1 + sql2;
    	
        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
            // クエリの実行と結果セットの取得
            statement.executeUpdate(sql);
            
            // リソースの解放
            statement.close();
            conn.close();
            
            // 接続を閉じる
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }
    }
    
    public void chatRoomeDelete(int chatroomSeq) {
    	
    	// MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "root";
        String password = "acaht";

        // SQL文の作成
        String sql = "UPDATE chatroom SET deleteFlg = true WHERE chatroom_seq = " + chatroomSeq + ";";
        
        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
            // クエリの実行と結果セットの取得
            statement.executeUpdate(sql);
            
            // リソースの解放
            statement.close();
            conn.close();
            
            // 接続を閉じる
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }  
    }
    public void chatRoomeInsert(String name, int accountSeq) {
    	
    	// MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "root";
        String password = "acaht";
        
        // SQL文の作成
        String sql = "INSERT INTO chatroom (chatroom_name, account_seq, deleteFlg, postdatetime, updatetime) VALUES ('" + name + "'," + accountSeq + ", false, now(), now());";
        
        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
            // クエリの実行と結果セットの取得
            statement.executeUpdate(sql);
            
            // リソースの解放
            statement.close();
            conn.close();
            
            // 接続を閉じる
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }  
    }
}
