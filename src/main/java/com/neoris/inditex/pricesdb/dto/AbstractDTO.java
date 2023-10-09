package com.neoris.inditex.pricesdb.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.neoris.inditex.pricesdb.utils.MapperUtils;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractDTO {

  @Override
  public String toString() {
    try {
      return MapperUtils.writeValueAsString(this);
    } catch (final JsonProcessingException e) {
      log.error(e.getMessage(), e);
      return e.toString();
    }
  }

}
