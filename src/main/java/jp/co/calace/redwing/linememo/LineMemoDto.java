package jp.co.calace.redwing.linememo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


// it deals with database 
public class LineMemoDto {
	
	private Integer id;
	private String memo;
	private Date updDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	//type conversion 
	public void setSqlTimestamp(Timestamp sqlTimestamp) {
		if (sqlTimestamp == null) {
			System.out.println("sqlTime is null");
			return;
		}
		updDate = new Date(sqlTimestamp.getTime()); // what is the souce ?
	}
	
	// getter for display !!
	public String getUpdDateStr() {
			if (updDate == null) {
				return "upd_date is not set";
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyy/MM/dd HH:mm:ss",Locale.JAPAN);
			return formatter.format(updDate.getTime());
		}
	

}
