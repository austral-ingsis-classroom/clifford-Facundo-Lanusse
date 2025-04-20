package edu.austral.ingsis.clifford.result;

import java.util.function.Function;

public final class Success<T> implements Result<T> {

  private final T value;

  public Success(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  @Override
  public <R> R fold(Function<T, R> onSuccess, Function<String, R> onFailure) {
    return onSuccess.apply(value);
  }

  @Override
  public boolean isSuccess() {
    return false;
  }

  @Override
  public boolean isFailure() {
    return false;
  }
}
