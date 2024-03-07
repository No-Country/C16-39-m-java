package com.c1639.backend.mapper.user;

import com.c1639.backend.dto.user.LoggedUserDto;
import com.c1639.backend.dto.user.UserSignedUpDto;
import com.c1639.backend.dto.user.UserToLoginDto;
import com.c1639.backend.dto.user.UserToSignUpDto;
import com.c1639.backend.model.user.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserToSignUpDto userToSignUpDto);

    UserSignedUpDto userToUserSignedUpDto(User user);

    User toEntity(LoggedUserDto loggedUserDto);

    LoggedUserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(LoggedUserDto loggedUserDto, @MappingTarget User user);

    User toEntity(UserToLoginDto userToLoginDto);

    UserToLoginDto toDto1(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserToLoginDto userToLoginDto, @MappingTarget User user);
}
