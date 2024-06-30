package com.exam.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ARepository extends JpaRepository<A, Long> {
    // Define additional methods here if needed

    // Example: Find A entity by name
    A findByName(String name);

    // Example: Find all A entities by some criteria
    List<A> findAllBySomeCriteria(String criteria);
}
