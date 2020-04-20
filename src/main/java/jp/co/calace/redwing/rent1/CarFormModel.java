package jp.co.calace.redwing.rent1;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/*
 * 67-1 form value holder class
 */
public class CarFormModel {
	
	private String selectedCarType;
	
	@NotEmpty(message = "Insert rent start date")
	@Pattern(regexp = "\\d{4}/\\d{1,2}/\\d{1,2}$",message = "Error in start rent date format")
	private String rentStartDate;
	
	@NotEmpty(message = "Insert rent end date")
	@Pattern(regexp = "\\d{4}/\\d{1,2}/\\d{1,2}$",message = "Error in end rent date format")
	private String rentEndDate;
	
	

	// setters & getters
	public String getSelectedCarType() {
		return selectedCarType;
	}

	public void setSelectedCarType(String selectedCarType) {
		this.selectedCarType = selectedCarType;
	}

	public String getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public String getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(String rentEndDate) {
		this.rentEndDate = rentEndDate;
	}
	

	
	
	
}
