package edu.austral.ingsis.clifford.result;

import java.util.function.Function;

public final class Failure<T> implements Result<T> {

  private final String error;

  public Failure(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public <R> R fold(Function<T, R> onSuccess, Function<String, R> onFailure) {
    return onFailure.apply(error);
  }

  @Override
  public boolean isSuccess() {
    return false;
  }

  @Override
  public boolean isFailure() {
    return true;
  }
}
