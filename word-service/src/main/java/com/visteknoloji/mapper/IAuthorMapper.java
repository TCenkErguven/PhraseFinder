package com.visteknoloji.mapper;

import com.visteknoloji.dto.request.SavePhraseRequestDto;
import com.visteknoloji.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthorMapper {
    IAuthorMapper INSTANCE = Mappers.getMapper(IAuthorMapper.class);

    Author fromSavePhraseRequestDtoToAuthor(final SavePhraseRequestDto dto);
}
