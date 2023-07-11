package wtf.norma.nekito.command;

import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandManager {

    private static final String PREFIX = ".";

    private final List<Command> commands = new ArrayList<>();

    public CommandManager(Command... commands) {
        register(commands);
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public boolean handleCommand(String message) {
        if (/*message.isBlank() ||*/ message.isEmpty()) {
            return false;
        }

        String[] args = message.substring(1).split(" ");
        try {
            findCommand(args[0])
                    .orElseThrow(() -> new CommandException(String
                            .format("Command \"%s\" not found. Use \".help\" to see command list.", args[0])))
                    .execute(Arrays.copyOfRange(args, 1, args.length));
        } catch (CommandException e) {
            ChatHelper.printMessage(e.getMessage());
        }

    /*getCommand(args[0]).ifPresentOrElse(command -> {
      try {
        command.execute(Arrays.copyOfRange(args, 1, args.length));
      } catch (CommandException e) {
        ChatHelper.printMessage(e.getMessage());
      }
    }, () -> ChatHelper.printMessage(
        String.format("Command \"%s\" not found. Use \".help\" to see command list.", args[0])));*/

        return true;
    }

    public void register(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    public void unregister(Command... commands) {
        this.commands.removeAll(Arrays.asList(commands));
    }

    public Optional<Command> findCommand(String alias) {
        return commands.stream().filter(command -> command.is(alias)).findFirst();
    }

    public List<Command> getCommands() {
        return commands;
    }
}
