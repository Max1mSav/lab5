package Commands;

/**
 * Command interface.
 */

public interface Command {
    String getName();
    String getInfo();
    boolean execute(String argument);
}
