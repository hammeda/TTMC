package fr.ttmc.auth;

import fr.ttmc.dtos.LoginResponseDto;
import fr.ttmc.dtos.RegisterDto;
import fr.ttmc.entities.User;
import fr.ttmc.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface AuthMapper {

    User fromRegister(RegisterDto registerDto);

    @Mapping(target = "token", expression = "java(fr.ttmc.tools.JwtUtils.generateToken(security))")
    LoginResponseDto toResponse(UserSecurity security);
}