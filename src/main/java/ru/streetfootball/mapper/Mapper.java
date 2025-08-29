package ru.streetfootball.mapper;

public interface Mapper<ENTITY, DTO> {

    DTO dtoFromEntity(ENTITY entity);

    ENTITY entityFromDto(DTO dto);

    void updateEntity(ENTITY entity, DTO dto);

}
