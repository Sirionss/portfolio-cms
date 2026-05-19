package com.portfolio.portfolio_cms.repository;

import com.portfolio.portfolio_cms.model.ContactLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContactLinkRepository extends JpaRepository<ContactLink, Long> {

}
