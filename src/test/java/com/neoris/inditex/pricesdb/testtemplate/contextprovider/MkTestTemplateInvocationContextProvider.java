package com.neoris.inditex.pricesdb.testtemplate.contextprovider;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MkTestTemplateInvocationContextProvider
    implements TestTemplateInvocationContextProvider {

  private static final ThreadLocal<String> SOURCE_PATH;
  private static final ThreadLocal<List<Method>> TEST_METHODS;

  static {
    SOURCE_PATH = ThreadLocal.withInitial(String::new);
    TEST_METHODS = ThreadLocal.withInitial(ArrayList::new);
  }

  public static void clean() {
    SOURCE_PATH.remove();
    TEST_METHODS.remove();
  }

  public static void setSourcePath(final String sourcePath) {
    SOURCE_PATH.set(sourcePath);
  }

  private static String getSourcePath() {
    return SOURCE_PATH.get();
  }

  public static void setTestMethods(final List<Method> methods) {
    TEST_METHODS.set(methods);
  }

  private static List<Method> getTestMethods() {
    return TEST_METHODS.get();
  }

  @Override
  public boolean supportsTestTemplate(final ExtensionContext context) {
    return true;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
      final ExtensionContext context) {

    final Path templatePath = Paths.get(new File(
        Thread.currentThread().getContextClassLoader().getResource(getSourcePath()).getFile())
            .getAbsolutePath());

    try (final Stream<Path> fileStream = Files.walk(templatePath)) {
      final Set<String> testPaths =
          fileStream.map(path -> (path.toFile().isFile()) ? path.toFile().getParent() : null)
              .filter(Objects::nonNull).collect(Collectors.toSet());
      return testPaths.stream().sorted()
          .flatMap(path -> getTestMethods().stream().map(test -> this.testPathContext(test, path)));

    } catch (final Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  private TestTemplateInvocationContext testPathContext(final Method test, final String path) {
    return new TestTemplateInvocationContext() {

      @Override
      public String getDisplayName(final int invocationIndex) {
        return "Processing: [Path -> (" + path + ")] :: [Test -> (" + test.getName() + ")]";
      }

      @Override
      public List<Extension> getAdditionalExtensions() {
        return Arrays
            .asList(new MkTestTemplateParameterResolver(new MkTestTemplateParameter(test, path)));
      }
    };
  }
}
