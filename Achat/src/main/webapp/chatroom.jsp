<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>チャットルーム</title>
    <style>
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // フォームが送信されたときの処理
            $('#chatForm').submit(function(event) {
                event.preventDefault(); // フォームのデフォルトの動作をキャンセル
                
                // フォームのデータを取得
                var formData = $(this).serialize();
                
                // Ajaxリクエストを送信
                $.ajax({
                    type: 'POST',
                    url: 'ChatRoom', // 送信先のサーブレットURL
                    data: formData,
                    success: function(response) {
                        // 成功時の処理: 新しいメッセージを画面に追加
                        $('#chatArea').append(response);
                        $('#messageInput').val(''); // 入力フィールドをクリア
                    },
                    error: function() {
                        alert('メッセージの送信に失敗しました。');
                    }
                });
            });
        });
    </script>
</head>
<body>
	<h2>チャットルーム</h2>
	<div id="chatArea">
	<%@ page import="dto.ChatlogDto, java.util.ArrayList, java.util.List, javax.servlet.http.HttpSession" %>
	<% 
		//リクエスト属性からユーザー情報を取得
		boolean judgement = (boolean) request.getAttribute("judgement");
		//セッションオブジェクトを取得
		Integer accountSeq = (Integer) session.getAttribute("accountSeq");
		Integer chatroomSeq = (Integer) session.getAttribute("chatroomSeq");
	
		// チャットログがあるか判定
		if (judgement) {
		
		    // リクエスト属性からユーザー情報を取得
		    ChatlogDto chatlogs = (ChatlogDto) request.getAttribute("chatlog");
		    
			List<String> names = chatlogs.getAccountName();
			List<String> chats = chatlogs.getChatlog();
			List<String> time = chatlogs.getPostdatetime();
	    	
			for (int i = 0; i < names.size(); i++) { 
	%>
	        <li>送信者：<%= names.get(i) %> <%;%></li>
	        <li>送信日時：<%= time.get(i) %> <%;%></li>
	        <li>内容：<%= chats.get(i) %> <%;%></li><br>
	        <% } %>
		<% } else {
		%>まだチャットは開始されていません<br><br><%
		}
	%>
	</div>	
		<form id="chatForm">
	        <!-- チャットメッセージ送信フォーム -->
	        <input type="text" id="messageInput" name="chat" placeholder="メッセージを入力">
	        <button type="submit">送信</button>
	    </form><br>
			チャットルームナンバー：<%= chatroomSeq  %><br>
			自分のアカウントナンバー：<%= accountSeq  %><br>
		<form action="/Achat/ChatDelete" method="post">
	    	<!-- チャットルーム削除フォーム -->
            <input type="submit" value="削除">
        </form>
        <a href="home.jsp" class="button">ホーム画面に戻る</a>
</body>
</html>