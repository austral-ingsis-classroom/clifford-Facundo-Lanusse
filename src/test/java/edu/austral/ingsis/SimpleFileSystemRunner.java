package edu.austral.ingsis;

import edu.austral.ingsis.clifford.system.FileSystem;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.List;

public class SimpleFileSystemRunner implements FileSystemRunner {
  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystem fs = new InMemoryFileSystem(); // tu sistema
    return commands.stream().map(fs::run).toList();
  }
}
