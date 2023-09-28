package com.visteknoloji.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePhraseRequestDto {
    @NotBlank
    private String authorPhrase;
    @NotBlank
    private String authorName;
    @NotBlank
    private String authorSurname;
}
