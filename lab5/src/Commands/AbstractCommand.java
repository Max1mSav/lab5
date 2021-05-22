package Commands;

public abstract class AbstractCommand implements Command {
    private String name;
    private String task;

    public AbstractCommand(String name, String destiny) {
        this.name = name;
        this.task = task;
    }

    /**
     * @return Name and usage way of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of the command.
     */
    public String getDestiny() {
        return destiny;
    }
}
