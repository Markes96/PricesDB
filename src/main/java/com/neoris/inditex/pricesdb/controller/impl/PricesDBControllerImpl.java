package com.neoris.inditex.pricesdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.markes96.dto.api.ApiRequestDataDTO;
import com.markes96.dto.api.response.ApiErrorResponseDTO;
import com.markes96.dto.api.response.ApiOkResponseDTO;
import com.neoris.inditex.pricesdb.controller.PricesDBController;
import com.neoris.inditex.pricesdb.dto.api.PricesDBApiRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import com.neoris.inditex.pricesdb.service.PricesDBService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/Neoris/Inditex/PricesDB")
public class PricesDBControllerImpl implements PricesDBController {

  @Autowired
  PricesDBService pricesDBService;

  @Override
  @PostMapping("/getOperatingProductRate")
  public ResponseEntity<ApiRequestDataDTO> getOperatingProductRate(
      @RequestBody(required = true) @Validated final PricesDBApiRequestDataDTO data) {

    if (this.pricesDBService == null) {
      return ResponseEntity.internalServerError().build();
    }

    try {
      final RateDTO rate = this.pricesDBService.getOperatingProductRate(data);
      return ResponseEntity.ok(new ApiOkResponseDTO<>(rate));
    } catch (final QueryTimeoutException e) {
      return new ResponseEntity<>(new ApiErrorResponseDTO(e, HttpStatus.GATEWAY_TIMEOUT.value()),
          HttpStatus.GATEWAY_TIMEOUT);
    } catch (final TransientDataAccessException e) {
      return new ResponseEntity<>(
          new ApiErrorResponseDTO(e, HttpStatus.SERVICE_UNAVAILABLE.value()),
          HttpStatus.SERVICE_UNAVAILABLE);
    } catch (final DataAccessException e) {
      return new ResponseEntity<>(
          new ApiErrorResponseDTO(e, HttpStatus.INTERNAL_SERVER_ERROR.value()),
          HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (final EntityNotFoundException e) {
      return new ResponseEntity<>(new ApiErrorResponseDTO(e, HttpStatus.NOT_FOUND.value()),
          HttpStatus.NOT_FOUND);
    } catch (final RateNotFoundException e) {
      return new ResponseEntity<>(new ApiErrorResponseDTO(e, HttpStatus.NOT_FOUND.value()),
          HttpStatus.NOT_FOUND);
    }

  }

}
