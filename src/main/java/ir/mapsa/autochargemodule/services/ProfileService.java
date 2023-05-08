package ir.mapsa.autochargemodule.services;

import ir.mapsa.autochargemodule.models.dtos.ProfileDto;
import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import ir.mapsa.autochargemodule.repositories.BaseRepository;
import ir.mapsa.autochargemodule.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends AbstractService<BaseRepository<ProfileEntity,String>, ProfileEntity> {
}
