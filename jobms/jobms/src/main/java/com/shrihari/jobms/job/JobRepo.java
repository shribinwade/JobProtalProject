package com.shrihari.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo  extends JpaRepository<Job,Long> {
}
