package edu.austral.ingsis.clifford.result;

import java.util.function.Function;

public sealed interface Result<T> permits Success, Failure {

  boolean isSuccess();

  boolean isFailure();

  <R> R fold(Function<T, R> onSuccess, Function<String, R> onFailure);
}
