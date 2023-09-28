package com.visteknoloji.service;

import com.visteknoloji.entity.Author;
import com.visteknoloji.repository.IAuthorRepository;
import com.visteknoloji.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService extends ServiceManager<Author,Integer> {

    private final IAuthorRepository authorRepository;

    public AuthorService(IAuthorRepository authorRepository){
        super(authorRepository);
        this.authorRepository = authorRepository;
    }

    public Author save(String authorName,String authorSurname){
        Optional<Author> authorOptional = authorRepository.findByAuthorNameAndAuthorSurnameIgnoreCase(authorName, authorSurname);
        System.out.println(authorOptional);
        if (authorOptional.isEmpty()) {
            String name = authorName.toLowerCase().trim();
            String surname = authorSurname.toLowerCase().trim();
            StringBuilder formattedName = new StringBuilder(name);
            formattedName.setCharAt(0, Character.toUpperCase(formattedName.charAt(0)));
            StringBuilder formattedSurname = new StringBuilder(surname);
            formattedSurname.setCharAt(0, Character.toUpperCase(formattedSurname.charAt(0)));
            Author newAuthor = Author.builder()
                    .authorName(formattedName.toString())
                    .authorSurname(formattedSurname.toString())
                    .build();
            return save(newAuthor);
        } else {
            return authorOptional.get();
        }
    }

}
