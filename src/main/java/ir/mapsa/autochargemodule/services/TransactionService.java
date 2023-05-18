package ir.mapsa.autochargemodule.services;

import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.repositories.BaseRepository;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService extends AbstractService<TransactionRepository,TransactionEntity> {

    public TransactionEntity findByTrackingId(String trackingId){
        return repository.findByTrackingId(trackingId);
    }

    public List<TransactionEntity> findByWalletId(String walletId) {return repository.findByWalletId(walletId);}

}
