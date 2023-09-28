package com.visteknoloji.service;

import com.visteknoloji.dto.request.GetAuthorRequestDto;
import com.visteknoloji.entity.WordHolder;
import com.visteknoloji.entity.enums.EConjunctions;
import com.visteknoloji.exception.ElasticServiceException;
import com.visteknoloji.exception.ErrorType;
import com.visteknoloji.mapper.IWordHolderMapper;
import com.visteknoloji.rabbitmq.model.SavePhraseModel;
import com.visteknoloji.repository.IWordHolderRepository;
import com.visteknoloji.utility.Calculator;
import com.visteknoloji.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class WordHolderService extends ServiceManager<WordHolder,String> {
    private final IWordHolderRepository wordHolderRepository;

    public WordHolderService(IWordHolderRepository wordHolderRepository){
        super(wordHolderRepository);
        this.wordHolderRepository = wordHolderRepository;
    }

    public void saveWordHolder(SavePhraseModel model) {
        WordHolder wordHolder = IWordHolderMapper.INSTANCE.fromSavePhraseModelToWordHolder(model);
        save(wordHolder);
    }

    public void deleteAll(){
        wordHolderRepository.deleteAll();
    }


    public Iterable<WordHolder> getAll() {
        return wordHolderRepository.findAll();
    }

    public String getAuthor(GetAuthorRequestDto dto) {

        Arrays.stream(EConjunctions.values()).forEach(conjunction -> {
            dto.setSearchedPhrase(dto.getSearchedPhrase().replaceAll(" " + conjunction.toString() + " ", " "));
        });
        Pattern pattern = Pattern.compile("[\\p{Punct}&&[^çÇğĞiıİöÖşŞüÜ]]");
        dto.setSearchedPhrase(pattern.matcher(dto.getSearchedPhrase()).replaceAll(" ").toLowerCase());
        Map<String, Integer> searchedWordFrequencyMap = new HashMap<>();
        Stream<String> wordsStream = Stream.of(dto.getSearchedPhrase().split("\\s+"));
        wordsStream.forEach(word -> {
            searchedWordFrequencyMap.put(word, searchedWordFrequencyMap.getOrDefault(word, 0) + 1);
        });
        /**
         * Tüm kayıtlı yazarların kullandığı kelimeler getirilecek ve hesaplama yapılmaya başlanacak
         */
        Map<String, Double> cosineSimilarities = new HashMap<>();

        Iterable<WordHolder> wordHolderIterable = wordHolderRepository.findAll();
        if (!wordHolderIterable.iterator().hasNext()) {
            throw new ElasticServiceException(ErrorType.NOT_FOUND);
        }
        wordHolderIterable.forEach(x->{
            double similarity = Calculator.calculateCosineSimilarity(searchedWordFrequencyMap,x.getWordFrequencyMap());
            cosineSimilarities.put(x.getWordHolderId(),similarity);
        });

        /**
         *  1'e yakın olma durumları incelenecek en yakın olan istediğimiz.
         */
        WordHolder wordHolder = findById(Calculator.calculateQualifier(cosineSimilarities)).orElseThrow(()->{
            throw new ElasticServiceException(ErrorType.AUTHOR_NOT_FOUND);
        });
        return wordHolder.getAuthorName() + " " + wordHolder.getAuthorSurname();
    }





}
