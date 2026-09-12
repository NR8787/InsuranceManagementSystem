package com.nikhil.policyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "policies")
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
        
	    @NotNull(message = "UserId is required")
	    private Long userId;
       
	    @NotBlank(message = "PolicyNumber is required" )
	    private String policyNumber;
        
	    @NotBlank(message = "PolicyType is required")
	    private String policyType;

	    @Positive(message = "Primium  must be greater than 0")
	    private Double premium;

	    @Positive(message = "CoverageAmount must be greater than 0")
	    private Double coverageAmount;

		public Policy() {
			super();
			
		}

		public Policy(Long id, Long userId, String policyNumber, String policyType, Double premium,
				Double coverageAmount) {
			super();
			this.id = id;
			this.userId = userId;
			this.policyNumber = policyNumber;
			this.policyType = policyType;
			this.premium = premium;
			this.coverageAmount = coverageAmount;
		}

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
			return "Policy [id=" + id + ", userId=" + userId + ", policyNumber=" + policyNumber + ", policyType="
					+ policyType + ", premium=" + premium + ", coverageAmount=" + coverageAmount + "]";
		}

	    
	    
}
