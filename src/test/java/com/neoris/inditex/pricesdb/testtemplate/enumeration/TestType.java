package com.neoris.inditex.pricesdb.testtemplate.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestType {

  DEFAULT(false, false),

  BASIC(false, false),

  LOAD_FILE(true, false),

  FILTER_RESULT(false, true),

  LOAD_AND_FILTER(true, true);

  public final boolean loadFile;
  public final boolean filter;

}
