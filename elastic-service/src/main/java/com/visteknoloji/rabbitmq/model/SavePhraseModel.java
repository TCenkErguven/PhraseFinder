package com.visteknoloji.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePhraseModel implements Serializable {
    private Integer phraseId;
    private Map<String,Integer> wordFrequencyMap;
    private String authorName;
    private String authorSurname;
}
