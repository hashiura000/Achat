package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ChatlogDto;
import service.ChatRoomLogic;

/**
 * Servlet implementation class Home
 */
@WebServlet("/ChatRoom")
public class ChatRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッション取得
		HttpSession session = request.getSession();
		
		Integer accountSeq = (Integer) session.getAttribute("accountSeq");
		
		// 返却のDTOを宣言
		ChatlogDto dto = new ChatlogDto();
		
		// チャットルームのロジッククラスのインスタンス化
		ChatRoomLogic chatRoomLogic = new ChatRoomLogic();
		
		// リクエストの文字エンコーディングを設定
		request.setCharacterEncoding("UTF-8");
		// 入力されたチャットルーム名を取得
		String name = request.getParameter("name");
		
		// チャットルーム名を送って、チャットルームの情報を取得する
		dto = chatRoomLogic.ChatRoomSelect(name, accountSeq);
		
		if(Objects.isNull(dto.getChatlog())) {
			// リクエスト属性にリストを設定
			request.setAttribute("judgement", false);
		} else {
	        // リクエスト属性にリストを設定
			request.setAttribute("judgement", true);
			request.setAttribute("chatlog", dto);
			session.setAttribute("chatroomSeq", dto.getChatroomSeq());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/chatroom.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストの文字エンコーディングを設定
		request.setCharacterEncoding("UTF-8");
		// 入力されたチャットの内容を取得
		String chat = request.getParameter("chat");
		
		// 空文字ではなかったら処理に入る
		if(!chat.trim().isEmpty()) {
			
			// セッション取得
			HttpSession session = request.getSession();
			
			// セッションからアカウントSEQとチャットルームSEQを取得
			Integer accountSeq = (Integer) session.getAttribute("accountSeq");
			Integer chatroomSeq = (Integer) session.getAttribute("chatroomSeq");
			
			// チャットログテーブルに内容を追加
			ChatRoomLogic chatRoomLogic = new ChatRoomLogic();
			chatRoomLogic.chatInsert(accountSeq, chatroomSeq, chat);
			
			// セッションからログイン名を取得
		    String loginName = (String) session.getAttribute("loginName");
		    
		    // 現在の日時を取得
		    LocalDateTime now = LocalDateTime.now();
		    
		    // フォーマットパターンを指定
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

		    // 日付と時刻を指定したフォーマットで文字列に変換
		    String formattedDateTime = now.format(formatter);
		    
		    // 新しいメッセージを生成する
		    String replyMessage = "<li>送信者：" + loginName + "</li><li>送信日時：" + formattedDateTime + "</li><li>内容：" + chat + "</li><br>";
		    
		    // クライアントに新しいメッセージを返す
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(replyMessage);
		    
		}
	}
}
