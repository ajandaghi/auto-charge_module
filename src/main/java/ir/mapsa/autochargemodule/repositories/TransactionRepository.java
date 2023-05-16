package ir.mapsa.autochargemodule.repositories;

import com.fasterxml.jackson.databind.ser.Serializers;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TransactionRepository extends  BaseRepository<TransactionEntity,String> {
   List<TransactionEntity> findByTransDateAfterAndTransDateBeforeAndUser(Date from, Date to, String user);
   TransactionEntity findByTrackingId(String trackingId);

}
