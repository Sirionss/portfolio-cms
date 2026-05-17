package com.portfolio.portfolio_cms.repository;

import com.portfolio.portfolio_cms.model.AboutSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AboutSectionRepository extends JpaRepository<AboutSection, Long> {

}
