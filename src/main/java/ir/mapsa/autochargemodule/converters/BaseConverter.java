package ir.mapsa.autochargemodule.converters;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<E,D> {
    E convertDto(D d);

    D convertEntity(E e);

    default List<E> convertDto(List<D> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(i -> convertDto(i))
                    .collect(Collectors.toList());
        }
        return null;
    }

    default List<D> convertEntity(List<E> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(i -> convertEntity(i))
                    .collect(Collectors.toList());
        }
        return null;
    }
}