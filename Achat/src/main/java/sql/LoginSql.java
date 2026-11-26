package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSql {
	
    public int LoginJudgement(String name) {
    	
        // MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "acaht";
        String password = "acaht";

        // SQL文の作成
        String sql1 = "SELECT * FROM accounts WHERE account_name = '";
        String sql2 = "';";
        String sql = sql1 + name + sql2;
        
    	int accountSeq = 0;

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
            	accountSeq = resultSet.getInt("account_seq");
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
        
		return accountSeq;
    }
    
    public void insertLoginName(String name) {
    	
        // MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "root";
        String password = "acaht";

        // SQL文の作成
        String sql = "INSERT INTO accounts (account_name) VALUES ('" + name + "');";
    	
        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // SQL クエリの実行
            Statement statement = conn.createStatement();
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
