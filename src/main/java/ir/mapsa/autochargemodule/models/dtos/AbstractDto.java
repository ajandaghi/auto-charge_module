package ir.mapsa.autochargemodule.models.dtos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@EqualsAndHashCode
public class AbstractDto {

    private String walletId;


    private Integer version;

    private Date createdDate;

    private Date lastModifiedDate;
}
