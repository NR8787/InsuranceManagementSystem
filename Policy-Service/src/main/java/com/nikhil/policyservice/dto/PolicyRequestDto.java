package com.nikhil.policyservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



public class PolicyRequestDto {
	
	@NotNull(message = "UserId is required")
    private Long userId;

    @NotBlank(message = "PolicyNumber is required")
    private String policyNumber;

    @NotBlank(message = "PolicyType is required")
    private String policyType;

    @Positive(message = "Premium must be greater than 0")
    private Double premium;

    @Positive(message = "CoverageAmount must be greater than 0")
    private Double coverageAmount;

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
		return "PolicyRequestDto [userId=" + userId + ", policyNumber=" + policyNumber + ", policyType=" + policyType
				+ ", premium=" + premium + ", coverageAmount=" + coverageAmount + "]";
	}

    
}
