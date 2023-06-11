package com.project.bookStore.service;

import com.project.bookStore.dataAccess.entities.Author;
import com.project.bookStore.dtos.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();
    AuthorDTO getById(int id);
    void add(AuthorDTO authorDto);
    void update(AuthorDTO authorDto);
    void delete(int id);
    void deleteAll(List<AuthorDTO> authorDtoList);

}
