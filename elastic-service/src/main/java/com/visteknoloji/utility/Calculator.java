package com.visteknoloji.utility;

import com.visteknoloji.exception.ElasticServiceException;
import com.visteknoloji.exception.ErrorType;

import java.util.Map;

public class Calculator {

    public static double calculateCosineSimilarity(Map<String,Integer> searchedWordFrequencyMap, Map<String,Integer> wordHolderMap){
        double dotProduct = 0;
        for(String word : searchedWordFrequencyMap.keySet()){
            if(wordHolderMap.containsKey(word)){
                dotProduct += searchedWordFrequencyMap.get(word) * wordHolderMap.get(word);
            }
        }
        double magnitude1 = calculateVectorMagnitude(searchedWordFrequencyMap);
        double magnitude2 = calculateVectorMagnitude(wordHolderMap);

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0; // Handle the case of a zero magnitude (division by zero)
        } else {
            return dotProduct / (magnitude1 * magnitude2);
        }
    }

    public static double calculateVectorMagnitude(Map<String,Integer> vector){
        double magnitude = 0;
        for (int frequency : vector.values()) {
            magnitude += Math.pow(frequency, 2);
        }
        return Math.sqrt(magnitude);
    }

    public static String calculateQualifier(Map<String,Double> cosineSimilarities){
        String worldHolderId = "";
        Double valueHolder = null;
        for (Map.Entry<String, Double> entry : cosineSimilarities.entrySet()) {
            if(valueHolder==null || valueHolder<= entry.getValue()){
                valueHolder = entry.getValue();
                worldHolderId = entry.getKey();
            }
        }
        if(valueHolder == 0.0){
            throw new ElasticServiceException(ErrorType.DOES_NOT_MATCH);
        }
        return worldHolderId;
    }
}
