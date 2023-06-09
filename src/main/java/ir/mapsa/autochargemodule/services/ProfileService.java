package ir.mapsa.autochargemodule.services;

import ir.mapsa.autochargemodule.models.dtos.ProfileDto;
import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import ir.mapsa.autochargemodule.repositories.BaseRepository;
import ir.mapsa.autochargemodule.repositories.ProfileRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ProfileService extends AbstractService<ProfileRepository, ProfileEntity> {

    public ProfileEntity findByWalletId(String walletId) {return repository.findByWalletId(walletId);}

}
