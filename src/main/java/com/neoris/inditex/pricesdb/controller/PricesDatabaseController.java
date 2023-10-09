package com.neoris.inditex.pricesdb.controller;

import org.springframework.http.ResponseEntity;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIRequestDataDTO;
import com.neoris.inditex.pricesdb.dto.api.PricesDBAPIResponseDTO;
import com.neoris.inditex.pricesdb.dto.api.response.PricesDBAPIErrorResponseDTO;
import com.neoris.inditex.pricesdb.dto.database.RateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "Prices database general controller", name = "PricesDatabaseController")
public interface PricesDatabaseController {

  @ApiResponses(value = {

      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = RateDTO.class))),

      @ApiResponse(responseCode = "504",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PricesDBAPIErrorResponseDTO.class))),

      @ApiResponse(responseCode = "503",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PricesDBAPIErrorResponseDTO.class))),

      @ApiResponse(responseCode = "500",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PricesDBAPIErrorResponseDTO.class))),

      @ApiResponse(responseCode = "404",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = PricesDBAPIErrorResponseDTO.class))),

      @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = PricesDBAPIErrorResponseDTO.class)))

  })

  @Operation(summary = "Get operating product rate from Prices database",
      operationId = "get-operating-product-rate",
      description = "Get the operating rate information from Prices database")
  public ResponseEntity<PricesDBAPIResponseDTO> getSimilarProducts(
      final PricesDBAPIRequestDataDTO data);
}
