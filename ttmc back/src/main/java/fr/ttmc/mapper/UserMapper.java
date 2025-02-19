package fr.ttmc.mapper;

import fr.ttmc.dtos.UserDto;
import fr.ttmc.entities.User;
import fr.ttmc.generic.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}