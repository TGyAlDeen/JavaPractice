package jp.co.calace.redwing.museum;

import java.sql.Timestamp;

public class MuseumEntity {
	
	private Integer id;
	private String author;
	private String imagename;
	private byte[] imagedata;
	private Timestamp updDate;
	private Integer commentId;
	private String commentator;
	private String comment;
	private Integer tagId;
	private String tag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public byte[] getImagedata() {
		return imagedata;
	}
	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp upd_Date) {
		this.updDate = upd_Date;
	}
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer comment_Id) {
		this.commentId = comment_Id;
	}
	public String getCommentator() {
		return commentator;
	}
	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	

}
