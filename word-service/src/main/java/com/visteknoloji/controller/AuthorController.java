package com.visteknoloji.controller;

import com.visteknoloji.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.visteknoloji.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHOR)
public class AuthorController {
    private final AuthorService authorService;
}
