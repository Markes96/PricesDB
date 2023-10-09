package com.neoris.inditex.pricesdb.service;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import jakarta.persistence.EntityNotFoundException;

public interface PricesDBService {

  RateDTO getOperatingProductRate(PricesDBAPIRequestDataDTO data)
      throws DataAccessException, TransientDataAccessException, QueryTimeoutException,
      EntityNotFoundException, RateNotFoundException;

}
