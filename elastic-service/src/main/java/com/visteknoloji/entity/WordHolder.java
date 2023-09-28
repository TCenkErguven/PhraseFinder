package com.visteknoloji.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(indexName="word_holder")
public class WordHolder extends BaseEntity {
    @Id
    private String wordHolderId;
    private Map<String,Integer> wordFrequencyMap;
    private String authorName;
    private String authorSurname;
    private Integer phraseId;

}
