package com.neoris.inditex.pricesdb.constant;

import java.util.function.Function;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIRequestDataDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PricesDBServiceConstant {

  public final String RATE_NOT_FOUND_ERROR = "Operating Product Rate not found";
  private final String RATE_NOT_FOUND_MESSAGE_TEMPLATE =
      "There isn't operating product rate for BrandID: %d ProductId: %d ApplicationDate: %tF %tT";

  public static final Function<PricesDBAPIRequestDataDTO, String> RATE_NOT_FOUND_MESSAGE =
      data -> String.format(RATE_NOT_FOUND_MESSAGE_TEMPLATE, data.getBrandId(), data.getProductId(),
          data.getApplicationDate(), data.getApplicationDate());
}
