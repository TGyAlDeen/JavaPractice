package jp.co.calace.redwing.linememo;

import java.sql.Timestamp;


/*
 create table line_memo (
id int auto_increment primary key,
memo varchar(256),upd_date datetime);
 */
public class LineMemoEntity {

	private Integer id;
	private String memo;
	private Timestamp updDate;
	
	//getter & setters
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
	public Timestamp getUpdDate() {
		return updDate;
	}
	// database table column name should match with setter function 
	public void setUpd_date(Timestamp upd_date) { //same name as database feild 
		this.updDate = upd_date;
	}
	
	
}
