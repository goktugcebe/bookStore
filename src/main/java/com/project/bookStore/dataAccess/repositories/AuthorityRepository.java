package com.project.bookStore.dataAccess.repositories;

import com.project.bookStore.dataAccess.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findFirstByAuthority(String name);
}
