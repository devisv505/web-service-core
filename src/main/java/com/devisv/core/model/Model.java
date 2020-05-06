package com.devisv.core.model;

import java.io.Serializable;

public interface Model<ID extends Serializable> {

  ID getId();

}
