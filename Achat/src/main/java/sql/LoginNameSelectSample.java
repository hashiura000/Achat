package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginNameSelectSample {
    public static void main(String[] args) {

        // MySQL 接続情報
        String url = "jdbc:mysql://localhost:3306/Achat";
        String user = "acaht";
        String password = "acaht";

        // SQL文の作成
        String sql1 = "SELECT * FROM accounts WHERE account_name = '";
        String sql2 = "acaht";
        String sql3 = "';";
        String sql = sql1 + sql2 + sql3;

        try {
            // JDBC ドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL データベースに接続しました");

            // SQL クエリの実行
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 結果の表示
            while (resultSet.next()) {
                // 例えば、"column_name" は取得したいカラム名に置き換える
                String columnName = resultSet.getString("account_name");
                System.out.println("Column Value: " + columnName);
//                System.out.println();
            }

            // リソースの解放
            resultSet.close();
            statement.close();
            conn.close();

            // 接続を閉じる
            conn.close();
            System.out.println("MySQL データベース接続をクローズしました");
            
        } catch (SQLException e) {
            System.out.println("SQL エラー: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }
    }
}
