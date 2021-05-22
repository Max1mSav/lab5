package Commands;

public interface Command {
    String getDestiny();
    String getName();
    boolean execution(String argument);
}
