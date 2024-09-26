package com.sanofi.project.domain.accessories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface AcessoriesRepository extends JpaRepository<Acessories, Long> {
    Page<Acessories> findAllByActiveTrue(Pageable pageable);
}
