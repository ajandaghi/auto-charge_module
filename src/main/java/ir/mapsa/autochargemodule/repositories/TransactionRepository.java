package ir.mapsa.autochargemodule.repositories;

import com.fasterxml.jackson.databind.ser.Serializers;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TransactionRepository extends  BaseRepository<TransactionEntity,String> {

//  @Query(value = "select * from transaction_entity where user=:user and trans_date between :from and :to",nativeQuery = true)
//    List<TransactionEntity> findByTransDateBetweenAndUser(@Param("from") Date from, @Param("to") Date to,@Param("user") String user);

   List<TransactionEntity> findByTransDateAfterAndTransDateBeforeAndUser(Date from,Date after,String user);

}
