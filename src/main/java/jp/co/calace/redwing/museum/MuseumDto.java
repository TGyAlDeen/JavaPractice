package jp.co.calace.redwing.museum;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MuseumDto {
	
	private Integer id;
	private String author;
	private String imagename;
	private Date updDate;
	
	private List<CommentInfo> commentList;
	
	private List<String> tagList;

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

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public List<CommentInfo> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentInfo> commentList) {
		this.commentList = commentList;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
	
	public void setSqlTimestamp(Timestamp sqlTimestamp) {
		if (sqlTimestamp == null) {
			System.out.println("Time stamp is null");
			return;
		}
		updDate = new Date(sqlTimestamp.getTime());
	}
	// setter problem might show up 
	public String getUpdDateStr() {
		if (updDate == null) {
			return "upd_date no set";
		}
		SimpleDateFormat formatter =
				new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.JAPAN);
		return formatter.format(updDate.getTime());
	}
	

}
