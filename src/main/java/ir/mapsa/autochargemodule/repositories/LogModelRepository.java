package ir.mapsa.autochargemodule.repositories;


import ir.mapsa.autochargemodule.aspectlogging.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogModelRepository extends MongoRepository<LogModel, String> {
}
