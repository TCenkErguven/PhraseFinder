package com.visteknoloji.service;

import com.visteknoloji.dto.request.SavePhraseRequestDto;
import com.visteknoloji.entity.Author;
import com.visteknoloji.entity.Phrase;
import com.visteknoloji.entity.enums.EConjunctions;
import com.visteknoloji.exception.ErrorType;
import com.visteknoloji.exception.WordServiceException;
import com.visteknoloji.mapper.IPhraseMapper;
import com.visteknoloji.rabbitmq.model.SavePhraseModel;
import com.visteknoloji.rabbitmq.producer.SavePhraseProducer;
import com.visteknoloji.repository.IPhraseRepository;
import com.visteknoloji.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class PhraseService extends ServiceManager<Phrase,Integer> {
    private final IPhraseRepository phraseRepository;
    private final AuthorService authorService;
    private final SavePhraseProducer savePhraseProducer;

    public PhraseService(IPhraseRepository phraseRepository,
                         AuthorService authorService,
                         SavePhraseProducer savePhraseProducer){
        super(phraseRepository);
        this.phraseRepository = phraseRepository;
        this.authorService = authorService;
        this.savePhraseProducer = savePhraseProducer;
    }

    /**
     * Kayıt işlemi sonrasında Maplenmiş data Yazar isim soyismiyle beraber
     * Elasticsearch'e ekleme yapılacak
     * @param dto
     * @return
     */
    @Transactional
    public Boolean savePhrase(SavePhraseRequestDto dto){
        System.out.println(phraseRepository.existsByAuthorPhraseIgnoreCase(dto.getAuthorPhrase()));
        if(!phraseRepository.existsByAuthorPhraseIgnoreCase(dto.getAuthorPhrase())) {
            Author author = authorService.save(dto.getAuthorName(), dto.getAuthorSurname());
            Phrase phrase = IPhraseMapper.INSTANCE.fromSavePhraseRequestDtoToPhrase(dto);
            phrase.setAuthor(author);
            save(phrase);
            /**
             * Rabbit'e yollanacak veriler hazırlanacak
             */
            Arrays.stream(EConjunctions.values()).forEach(conjunction -> {
                phrase.setAuthorPhrase(phrase.getAuthorPhrase().replaceAll(" " + conjunction.toString() + " ", " "));
            });
            Pattern pattern = Pattern.compile("[\\p{Punct}&&[^çÇğĞiıİöÖşŞüÜ]]");
            phrase.setAuthorPhrase(pattern.matcher(phrase.getAuthorPhrase()).replaceAll(" ").toLowerCase());
            Map<String, Integer> wordFrequencyMap = new HashMap<>();
            Stream<String> wordsStream = Stream.of(phrase.getAuthorPhrase().split("\\s+"));
            wordsStream.forEach(word -> {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            });
            /**
             * Rabbit'le Elastic Service'e yollayacağız
             */
            savePhraseProducer.convertAndSendData(SavePhraseModel.builder()
                        .authorName(dto.getAuthorName())
                        .authorSurname(dto.getAuthorSurname())
                        .wordFrequencyMap(wordFrequencyMap)
                        .phraseId(phrase.getPhraseId())
                    .build());
            return true;
        }
        throw new WordServiceException(ErrorType.PHRASE_ALREADY_EXIST);
    }






}
