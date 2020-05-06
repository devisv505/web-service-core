package com.devisv.core.service;

import com.devisv.core.exception.NotFoundException;
import com.devisv.core.model.Model;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class SimpleCrudService <ENTITY extends Model, ID> {

  protected final CrudRepository<ENTITY, ID> repository;

  protected SimpleCrudService(CrudRepository<ENTITY, ID> repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public ENTITY getById(ID id) {
    return repository.findById(id).orElseThrow(() -> getNotFountException());
  }

  @Transactional(readOnly = true)
  public List<ENTITY> getAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional
  public boolean delete(ID id) {
    try {
      repository.delete(getById(id));
    } catch (NotFoundException e) {
      return false;
    }

    return true;
  }

  @Transactional
  public ENTITY update(ENTITY entity) {
    return repository.save(entity);
  }

  public ENTITY create(ENTITY entity) {
    return repository.save(entity);
  }

  public abstract ENTITY createEntity();

  protected abstract NotFoundException getNotFountException();
}
