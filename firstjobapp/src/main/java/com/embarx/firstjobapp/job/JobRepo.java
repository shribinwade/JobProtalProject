package com.embarx.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface JobRepo  extends JpaRepository<Job,Long> {
}
