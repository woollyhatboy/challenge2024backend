package com.sanofi.project.domain.bag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long> {
    Page<Bag> findAllByActiveTrue(Pageable pageable);
}
