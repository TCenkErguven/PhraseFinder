package com.visteknoloji.mapper;

import com.visteknoloji.dto.request.SavePhraseRequestDto;
import com.visteknoloji.entity.Author;
import com.visteknoloji.entity.Phrase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPhraseMapper {
    IPhraseMapper INSTANCE = Mappers.getMapper(IPhraseMapper.class);

    Phrase fromSavePhraseRequestDtoToPhrase(final SavePhraseRequestDto dto);

}
