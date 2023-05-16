package ir.mapsa.autochargemodule.services;

import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.repositories.BaseRepository;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractService<TransactionRepository,TransactionEntity> {

    public TransactionEntity findByTrackingId(String trackingId){
        return repository.findByTrackingId(trackingId);
    }
}
