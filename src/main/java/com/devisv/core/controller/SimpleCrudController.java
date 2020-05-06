package com.devisv.core.controller;

import com.devisv.core.model.Model;
import com.devisv.core.service.SimpleCrudService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class SimpleCrudController <DTO_CREATE, DTO_UPDATE, DTO_OUT, ID, ENTITY extends Model> {

  protected final SimpleCrudService<ENTITY, ID> crudService;

  public SimpleCrudController(SimpleCrudService<ENTITY, ID> crudService) {
    this.crudService = crudService;
  }

  @GetMapping(path = "/")
  @ResponseBody
  public DTO_OUT get(@RequestParam("id") ID id) {
    return convertEntityToDto(
        crudService.getById(id)
    );
  }

  @GetMapping(path = "/all")
  @ResponseBody
  public List<DTO_OUT> getAll() {
    return crudService.getAll()
        .stream()
        .map(this::convertEntityToDto)
        .collect(Collectors.toList());
  }

  @PostMapping(path = "/")
  @ResponseBody
  public DTO_OUT post(@RequestBody DTO_CREATE dtoCreate) {
    return convertEntityToDto(
        crudService.create(
            convertCreateDtoToEntity(dtoCreate)
        )
    );
  }

  @PutMapping(path = "/")
  @ResponseBody
  public DTO_OUT put(@RequestParam("id") ID id, @RequestBody DTO_UPDATE dtoUpdate) {
    return convertEntityToDto(
        crudService.update(
            convertUpdateDtoToEntity(id, dtoUpdate)
        )
    );
  }

  protected DTO_OUT convertEntityToDto(ENTITY entity) {
    DTO_OUT dtoOut = createDtoOut();
    BeanUtils.copyProperties(entity, dtoOut);
    return dtoOut;
  };

  protected ENTITY convertCreateDtoToEntity(DTO_CREATE dtoCreate) {
    ENTITY entity = crudService.createEntity();
    BeanUtils.copyProperties(dtoCreate, entity);
    return entity;
  };

  protected ENTITY convertUpdateDtoToEntity(ID id, DTO_UPDATE dtoUpdate) {
    ENTITY entity = crudService.getById(id);
    BeanUtils.copyProperties(dtoUpdate, entity);
    return entity;
  };

  abstract DTO_OUT createDtoOut();

}
