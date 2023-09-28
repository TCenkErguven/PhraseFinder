package com.visteknoloji.controller;

import com.visteknoloji.dto.request.GetAuthorRequestDto;
import com.visteknoloji.entity.WordHolder;
import com.visteknoloji.service.WordHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.visteknoloji.constants.ApiUrls.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(WORD_HOLDER)
public class WordHolderController {
    private final WordHolderService wordHolderService;

    @GetMapping(GET_ALL)
    public ResponseEntity<Iterable<WordHolder>> getAll(){
        return ResponseEntity.ok(wordHolderService.getAll());
    }

    @DeleteMapping(DELETE_ALL)
    public ResponseEntity<Void> deleteAll(){
        wordHolderService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping(GET_AUTHOR)
    public ResponseEntity<String> getAuthor(@RequestBody @Valid GetAuthorRequestDto dto){
        return ResponseEntity.ok(wordHolderService.getAuthor(dto));
    }




}
