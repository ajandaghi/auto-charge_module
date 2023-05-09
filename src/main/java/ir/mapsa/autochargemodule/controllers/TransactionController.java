package ir.mapsa.autochargemodule.controllers;

import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto-charge/transaction")
public class TransactionController extends AbstractController<TransactionEntity, TransactionDto> {
}
