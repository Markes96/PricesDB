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
import com.neoris.inditex.pricesdb.controller.PricesDatabaseController;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIResponseDTO;
import com.neoris.inditex.pricesdb.dto.api.response.PricesDBAPIErrorResponseDTO;
import com.neoris.inditex.pricesdb.dto.api.response.PricesDBAPIOkResponseDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import com.neoris.inditex.pricesdb.service.PricesDBService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/Neoris/Inditex/PricesDB")
public class PricesDatabaseControllerImpl implements PricesDatabaseController {

  @Autowired
  PricesDBService pricesDBService;

  @Override
  @PostMapping("/getOperatingProductRate")
  public ResponseEntity<PricesDBAPIResponseDTO> getSimilarProducts(
      @RequestBody(required = true) @Validated final PricesDBAPIRequestDataDTO data) {

    if (this.pricesDBService == null) {
      return ResponseEntity.internalServerError().build();
    }

    try {
      final RateDTO rate = this.pricesDBService.getOperatingProductRate(data);
      return ResponseEntity.ok(new PricesDBAPIOkResponseDTO<>(rate));
    } catch (final QueryTimeoutException e) {
      return new ResponseEntity<PricesDBAPIResponseDTO>(
          new PricesDBAPIErrorResponseDTO(e, HttpStatus.GATEWAY_TIMEOUT.value()),
          HttpStatus.GATEWAY_TIMEOUT);
    } catch (final TransientDataAccessException e) {
      return new ResponseEntity<PricesDBAPIResponseDTO>(
          new PricesDBAPIErrorResponseDTO(e, HttpStatus.SERVICE_UNAVAILABLE.value()),
          HttpStatus.SERVICE_UNAVAILABLE);
    } catch (final DataAccessException e) {
      return new ResponseEntity<PricesDBAPIResponseDTO>(
          new PricesDBAPIErrorResponseDTO(e, HttpStatus.INTERNAL_SERVER_ERROR.value()),
          HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (final EntityNotFoundException e) {
      return new ResponseEntity<PricesDBAPIResponseDTO>(
          new PricesDBAPIErrorResponseDTO(e, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    } catch (final RateNotFoundException e) {
      return new ResponseEntity<PricesDBAPIResponseDTO>(
          new PricesDBAPIErrorResponseDTO(e, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
  }

}
