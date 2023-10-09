package com.neoris.inditex.pricesdb.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtils {

  @Getter
  protected static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.setDateFormat(
        new StdDateFormat().withTimeZone(TimeZone.getTimeZone("UTC")).withColonInTimeZone(true));
    mapper.registerModule(new JavaTimeModule().addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(formatter)));
  }

  /**
   * Method used to deserialize an object from String;
   *
   * @param <T> the type of the value being boxed
   * @param clazz class that indicates the return type of the method
   * @param value object Path of the file to load
   * @return a string or byte[] of the file loaded
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   */
  public <T> T readValueFromString(final Class<T> clazz, final String value)
      throws JsonProcessingException {
    return mapper.readValue(value, clazz);
  }

  /**
   * Method used to serialize and object as {@link String} with default pretty printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  public String writeValueAsString(final Object obj) throws JsonProcessingException {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
  }

  /**
   * Method used to serialize and object as {@link String} with flat printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  public String writeValueAsFlatString(final Object obj) throws JsonProcessingException {
    return mapper.writeValueAsString(obj);
  }

  /**
   * Method used to serialize and object as {@link byte} array with default pretty printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  public byte[] writeValueAsBytes(final Object obj) throws JsonProcessingException {
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(obj);
  }

  /**
   * Method used to serialize and object as {@link byte} array with flat printer
   *
   * @param obj to serialize
   * @throws JsonProcessingException Intermediate base class for all problems encountered when
   *         processing (parsing, generating) JSON content that are not pure I/O problems
   * @return object serialized
   */
  public byte[] writeValueAsFlatBytes(final Object obj) throws JsonProcessingException {
    return mapper.writeValueAsBytes(obj);
  }

}
