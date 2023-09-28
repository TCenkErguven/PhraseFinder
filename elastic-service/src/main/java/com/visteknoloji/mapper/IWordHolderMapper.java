package com.visteknoloji.mapper;

import com.visteknoloji.entity.WordHolder;
import com.visteknoloji.rabbitmq.model.SavePhraseModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IWordHolderMapper {
    IWordHolderMapper INSTANCE = Mappers.getMapper(IWordHolderMapper.class);

    WordHolder fromSavePhraseModelToWordHolder(final SavePhraseModel model);
}
