package com.project.bookStore.service.impl;

import com.project.bookStore.dataAccess.entities.Author;
import com.project.bookStore.dataAccess.repositories.AuthorRepository;
import com.project.bookStore.dtos.AuthorDTO;
import com.project.bookStore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;


    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorDTO getById(int id) {
        Author author=this.authorRepository.findById(id).orElseThrow();
        return this.modelMapper.map(author,AuthorDTO.class);
    }

    @Override
    public void add(AuthorDTO authorDto) {
        Author author =  this.modelMapper.map(authorDto, Author.class);
        this.authorRepository.save(author);
    }

    @Override
    public void update(AuthorDTO authorDto) {
        Author author =   this.modelMapper.map(authorDto, Author.class);
        this.authorRepository.save(author);
    }

    @Override
    public void delete(int id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<AuthorDTO> authorDtoList) {
        List<Author> customers = authorDtoList.stream()
                .map(authorDto ->   this.modelMapper.map(authorDtoList, Author.class))
                .collect(Collectors.toList());
       this.authorRepository.deleteAll(customers);
    }
}
