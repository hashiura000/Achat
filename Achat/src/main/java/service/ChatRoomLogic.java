package service;

import dto.ChatlogDto;
import sql.ChatSql;

public class ChatRoomLogic {
	
	public ChatlogDto ChatRoomSelect (String name, int accountSeq){
		
		ChatlogDto dto = new ChatlogDto();
		
		ChatSql chatSql = new ChatSql();
		int chat = chatSql.ChatRoomSelect(name);
		
		if(chat == 0) {
			// チャットルームがなかったら作成する
			chatSql.chatRoomeInsert(name, accountSeq);
			
			chat = chatSql.ChatRoomSelect(name);
		}
		
		// チャットログの内容を取得する
		dto = chatSql.ChatlogSelect(chat);
		dto.setChatroomSeq(chat);
		
		return dto;
	}
	
	public void chatInsert(int accountSeq, int chatroomSeq, String chat) {
		
		ChatSql chatInsert = new ChatSql();
		chatInsert.chatInsert(accountSeq, chatroomSeq, chat);
		
	}
	
	public void chatRoomDelete(int chatroomSeq) {
		
		ChatSql ChatSql = new ChatSql();
		ChatSql.chatRoomeDelete(chatroomSeq);
		
	}
}
