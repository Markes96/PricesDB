package com.neoris.inditex.pricesdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.TransientDataAccessException;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import com.neoris.inditex.pricesdb.exception.RateNotFoundException;
import com.neoris.inditex.pricesdb.testtemplate.AbstractMkTestTemplate;
import com.neoris.inditex.pricesdb.testtemplate.annotation.MkTestTemplate;
import com.neoris.inditex.pricesdb.testtemplate.annotation.MkTestTemplateConfiguration;
import com.neoris.inditex.pricesdb.testtemplate.enumeration.TestType;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@MkTestTemplateConfiguration(path = "pricesdbqueries", testType = TestType.LOAD_FILE,
    exampleName = "query.json")
public class PricesDBServiceTest extends AbstractMkTestTemplate {

  @Autowired
  PricesDBService service;

  @MkTestTemplate
  RateDTO exampleTest(final PricesDBAPIRequestDataDTO data)
      throws QueryTimeoutException, TransientDataAccessException, DataAccessException,
      EntityNotFoundException, RateNotFoundException {
    return this.service.getOperatingProductRate(data);
  }

}
