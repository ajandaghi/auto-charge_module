package ir.mapsa.autochargemodule.repositories;

import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.springframework.stereotype.Service;

public interface ProfileRepository extends BaseRepository<ProfileEntity,Long>{

    ProfileEntity findByWalletId(String walletId);

}
