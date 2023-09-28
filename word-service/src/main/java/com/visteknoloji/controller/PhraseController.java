package com.visteknoloji.controller;

import com.visteknoloji.dto.request.SavePhraseRequestDto;
import com.visteknoloji.entity.Phrase;
import com.visteknoloji.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.visteknoloji.constants.ApiUrls.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PHRASE)
public class PhraseController {
    private final PhraseService phraseService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody @Valid SavePhraseRequestDto dto){
        return ResponseEntity.ok(phraseService.savePhrase(dto));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Phrase>> findAll(){
        return ResponseEntity.ok(phraseService.findAll());
    }
}
