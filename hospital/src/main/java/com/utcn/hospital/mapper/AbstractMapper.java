package com.utcn.hospital.mapper;

import java.util.Optional;

import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.entity.IdEntity;

public interface AbstractMapper<E, T> {

	Optional<E> toEntityUpdate(T dto);

	E toEntityInsert(T dto);

	T toDTO(E entity);

}
