package com.utcn.hospital.mapper;

import java.util.List;

public interface ListMapper<E,T> {
	
	List<T> toDTOs(List<E> entities);
	List<E> toEntities(List<T> dtos);

}
