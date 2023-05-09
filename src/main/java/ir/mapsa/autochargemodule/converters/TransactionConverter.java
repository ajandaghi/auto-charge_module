package ir.mapsa.autochargemodule.converters;

import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel ="spring")
@Service
public interface TransactionConverter extends BaseConverter<TransactionEntity, TransactionDto> {
}
