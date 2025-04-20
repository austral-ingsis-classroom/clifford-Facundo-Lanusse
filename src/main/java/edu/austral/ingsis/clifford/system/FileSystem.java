package edu.austral.ingsis.clifford.system;

public interface FileSystem {
  String run(String commandLine);

  String getCurrentPath();
}
