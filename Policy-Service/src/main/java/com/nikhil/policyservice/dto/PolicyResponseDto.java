package com.nikhil.policyservice.dto;

public class PolicyResponseDto {
	
	private Long id;
	
    private Long userId;
    
    private String policyNumber;
    
    private String policyType;
    
    private Double premium;
    
    private Double coverageAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	@Override
	public String toString() {
		return "PolicyResponseDto [id=" + id + ", userId=" + userId + ", policyNumber=" + policyNumber + ", policyType="
				+ policyType + ", premium=" + premium + ", coverageAmount=" + coverageAmount + "]";
	}
    
    

}
