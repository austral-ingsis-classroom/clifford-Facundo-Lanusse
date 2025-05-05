package edu.austral.ingsis.clifford.elements;

// chequear siempre las calses abstractas
public abstract sealed class FileSystemElements permits Directory, File {

  private final String name;

  public FileSystemElements(String name) {
    /*
    me aseguro de que nadie pueda iniciar un file element con un null o blank
    respeto principio de encapsulamiento xq en un futuro alguien puede estar usando otros componentes
    enotnces con mi fileElement me aseguro que nunca se puede y no dependa de otros ese chequeo
    */
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract boolean isDirectory();
}
