package edu.austral.ingsis.clifford.result;

import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public record CommandResult(String output, InMemoryFileSystem fileSystem) {}
