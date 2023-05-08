package ir.mapsa.autochargemodule.converters;

import ir.mapsa.autochargemodule.models.dtos.ProfileDto;
import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel ="spring")
public interface ProfileConverter extends BaseConverter<ProfileEntity, ProfileDto> {
}
