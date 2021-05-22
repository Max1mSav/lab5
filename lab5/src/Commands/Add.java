package Commands;

public class Add extends AbstractCommand {
    String argument;

    public Add(String argument) {
        this.argument = argument;
    }
    @Override
    public boolean execution(String argument) {
        return true;
    }
}
