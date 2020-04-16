package jp.co.calace.redwing.museum;

public class CommentInfo {

	private int commentId;
	private String commentator;
	private String comment;
	
	
	public CommentInfo(int commentId, String commentator, String comment) {
		super();
		this.commentId = commentId;
		this.commentator = commentator;
		this.comment = comment;
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public String getCommentator() {
		return commentator;
	}


	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}


	public String getComment() {
		return comment == null ? "" : comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
