package com.neoris.inditex.pricesdb.dto.api.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIResponseDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PricesDBAPIOkResponseDTO<T> extends PricesDBAPIResponseDTO {

  @JsonUnwrapped
  private T responseBody;
}
