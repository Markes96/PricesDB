package com.neoris.inditex.pricesdb.dto.api.response;

import java.time.LocalDateTime;
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
public class PricesDBAPIErrorResponseDTO extends PricesDBAPIResponseDTO {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private int status;
  private String error;
  private String message;

  public PricesDBAPIErrorResponseDTO(final Exception e, final int status) {
    this.status = status;
    this.error = e.getLocalizedMessage();
    this.message = e.getMessage();
  }
}
