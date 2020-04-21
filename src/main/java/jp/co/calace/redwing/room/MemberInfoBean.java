package jp.co.calace.redwing.room;

public class MemberInfoBean {
	
	//68 holds user info 
	private int itemId;
	private String categoryTitle;
	private String categoryDetail;
	
	
	//tagy
	public MemberInfoBean(int itemId, String categoryTitle, String categoryDetail) {
		
		this.itemId = itemId;
		this.categoryTitle = categoryTitle;
		this.categoryDetail = categoryDetail;
	}


	
	public MemberInfoBean(int itemId,String categoryTitle) {
		this(itemId, categoryTitle, "");
	}



	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getCategoryTitle() {
		return categoryTitle;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}


	public String getCategoryDetail() {
		return categoryDetail;
	}


	public void setCategoryDetail(String categoryDetail) {
		this.categoryDetail = categoryDetail;
	}
	
	
	
}
