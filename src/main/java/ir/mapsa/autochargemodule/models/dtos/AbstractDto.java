package ir.mapsa.autochargemodule.models.dtos;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class AbstractDto {

    private String walletId;


    private Integer version;

    private Date createdDate;

    private Date lastModifiedDate;
}
