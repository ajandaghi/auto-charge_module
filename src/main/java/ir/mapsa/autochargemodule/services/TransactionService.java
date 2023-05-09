package ir.mapsa.autochargemodule.services;

import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.repositories.BaseRepository;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractService<BaseRepository<TransactionEntity, String>,TransactionEntity> {
}
