package jp.co.calace.redwing.dailyrec;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RecBean {
	
	private String submitMode;
	@Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$",message = "please enter with yyyy/mm/dd format")
	private String updDate;

	@NotEmpty(message = "Please enter activity details")
	private String RecAction;

	public String getMode() {
		return submitMode;
	}

	public void setMode(String submitMode) {
		this.submitMode = submitMode;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getRecAction() {
		return RecAction;
	}

	public void setRecAction(String recAction) {
		RecAction = recAction;
	}
	
	
	
	
}
