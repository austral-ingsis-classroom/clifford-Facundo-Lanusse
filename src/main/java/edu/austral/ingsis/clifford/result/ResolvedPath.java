package edu.austral.ingsis.clifford.result;

import edu.austral.ingsis.clifford.elements.Directory;

import java.util.List;

public record ResolvedPath(Directory directory, List<String> path) {}
