package ir.mapsa.autochargemodule.repositories;

import com.fasterxml.jackson.databind.ser.Serializers;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface TransactionRepository extends  BaseRepository<TransactionEntity,String> {
    TransactionEntity findByDateBetweenAndUser(Date from, Date to, String user);

}
