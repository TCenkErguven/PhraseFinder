package com.visteknoloji.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveWordHolderRequestDto {
    private Map<String,Integer> wordFrequencyMap;
    private String authorName;
    private String authorSurname;
}
