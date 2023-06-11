package com.project.bookStore.dataAccess.repositories;

import com.project.bookStore.dataAccess.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
