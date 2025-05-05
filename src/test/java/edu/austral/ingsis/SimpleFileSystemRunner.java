package edu.austral.ingsis;

import edu.austral.ingsis.clifford.commandFactory.CdFactory;
import edu.austral.ingsis.clifford.commandFactory.CommandFactory;
import edu.austral.ingsis.clifford.commandFactory.LsFactory;
import edu.austral.ingsis.clifford.commandFactory.MkdirFactory;
import edu.austral.ingsis.clifford.commandFactory.PwdFactory;
import edu.austral.ingsis.clifford.commandFactory.RmFactory;
import edu.austral.ingsis.clifford.commandFactory.TouchFactory;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.CommandParser;
import edu.austral.ingsis.clifford.system.FileSystem;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleFileSystemRunner implements FileSystemRunner {
  @Override
  public List<String> executeCommands(List<String> commands) {

    Map<String, CommandFactory> factoryMap =
        Map.of(
            "cd", new CdFactory(),
            "mkdir", new MkdirFactory(),
            "pwd", new PwdFactory(),
            "rm", new RmFactory(),
            "touch", new TouchFactory(),
            "ls", new LsFactory());

    CommandParser parser = new CommandParser(factoryMap);
    FileSystem fs = new InMemoryFileSystem(parser); // tu sistema
    List<String> results = new ArrayList<>();

    for (String command : commands) {
      var result = fs.run(command);

      if (result instanceof Success<?> success) {
        CommandResult commandResult = (CommandResult) success.getValue();
        results.add(commandResult.output());
        fs = commandResult.fileSystem(); // actualizar el file system con el nuevo estado
      } else if (result instanceof Failure failure) {
        results.add(failure.getError());
      } else {
        results.add("Unknown error");
      }
    }

    return results;
  }
}
