package com.visteknoloji.repository;

import com.visteknoloji.entity.WordHolder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWordHolderRepository extends ElasticsearchRepository<WordHolder,String> {
}
