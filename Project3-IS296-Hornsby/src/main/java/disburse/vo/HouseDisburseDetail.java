package disburse.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="T_2013Q4_HOUSE_DISBURSE")
public class HouseDisburseDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
	@Column(name = "BIOGUIDE_ID")
	private String bioGuideID;
	private String office;
	private String category;
	private String payee;
	@Column(name = "START_DATE")
	private String startDate;
	@Column(name = "END_DATE")
	private String endDate;
	private String purpose;
	private double amount;
	private String year;
	
	
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getBioGuideID() {
		return bioGuideID;
	}
	public void setBioGuideID(String bioGuideID) {
		this.bioGuideID = bioGuideID;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public double getAmount() {
		return amount;
	}
	@JsonProperty("AMOUNT")
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getYear() {
		return year;
	}
	@JsonProperty("YEAR")
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "HouseDisburseDetail [recordId=" + recordId + ", bioGuideID=" + bioGuideID + ", office=" + office + ", category="
				+ category + ", payee=" + payee + ", startDate=" + startDate + ", endDate=" + endDate + ", purpose="
				+ purpose + ", amount=" + amount + ", year=" + year + "]";
	}

	
	
}
