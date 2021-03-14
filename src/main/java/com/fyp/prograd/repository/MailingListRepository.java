package com.fyp.prograd.repository;

import com.fyp.prograd.model.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailingListRepository extends JpaRepository<MailingList, Long> {
    Optional<MailingList> findByCompanyId(Long companyId);
}
