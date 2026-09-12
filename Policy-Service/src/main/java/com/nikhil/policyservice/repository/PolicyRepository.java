package com.nikhil.policyservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.policyservice.entity.Policy;

@Repository
public interface PolicyRepository  extends JpaRepository<Policy,Long> {
	
	
}
