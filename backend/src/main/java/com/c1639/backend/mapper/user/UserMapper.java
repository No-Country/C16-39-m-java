package com.c1639.backend.mapper.user;

import com.c1639.backend.dto.user.UserSignedUpDto;
import com.c1639.backend.dto.user.UserToSignUpDto;
import com.c1639.backend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserToSignUpDto userToSignUpDto);

    UserSignedUpDto userToUserSignedUpDto(User user);
}
