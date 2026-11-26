package dto;

import java.util.List;

public class ChatlogDto{
	
	private int chatroomSeq;
	
	private String chatroomName;
	
	private List<String> chatlog;
	
	private List<Integer> accountSeq;
	
	private List<String> accountName;
	
	private List<String> postdatetime;

	// ゲッターセッター
	public int getChatroomSeq() {
		return chatroomSeq;
	}

	public void setChatroomSeq(int chatroomSeq) {
		this.chatroomSeq = chatroomSeq;
	}
	
	public String getChatroomName() {
		return chatroomName;
	}

	public void setChatroomName(String chatroomName) {
		this.chatroomName = chatroomName;
	}
	
	public List<String> getChatlog() {
		return chatlog;
	}

	public void setChatlog(List<String> chatlog) {
		this.chatlog = chatlog;
	}
	
	public List<Integer> getAccountSeq() {
		return accountSeq;
	}

	public void setAccountSeq(List<Integer> accountSeq) {
		this.accountSeq = accountSeq;
	}
	
	public List<String> getAccountName() {
		return accountName;
	}

	public void setAccountName(List<String> accountName) {
		this.accountName = accountName;
	}

	public List<String> getPostdatetime() {
		return postdatetime;
	}

	public void setPostdatetime(List<String> postdatetime) {
		this.postdatetime = postdatetime;
	}

}
