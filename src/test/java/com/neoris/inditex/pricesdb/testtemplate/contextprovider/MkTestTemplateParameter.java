package com.neoris.inditex.pricesdb.testtemplate.contextprovider;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MkTestTemplateParameter {

  private final Method test;
  private final String path;

}
