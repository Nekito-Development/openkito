package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.nekito;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;

@CommandInfo(
    alias = "help"
)
public class HelpCommand extends Command {

  @Override
  public void execute(String... args) throws CommandException {
    if (args.length > 0) {
      ChatHelper.printMessage("\n", false);
      Command command = nekito.INSTANCE.getCommandManager().getCommand(args[0])
          .orElseThrow(
              () -> new CommandException(String.format("Command \"%s\" not found.\n", args[0])));

      ChatHelper
          .printMessage(String.format("&5%s&f: &d%s\n", command.getAlias(), command.getUsage()));

      /*nekito.INSTANCE.getCommandManager().getCommand(args[0])
          .ifPresentOrElse(command -> ChatHelper.printMessage(
              String.format("&5%s&f: &d%s\n", command.getAlias(), command.getUsage())),
              () -> ChatHelper.printMessage(String.format("Command \"%s\" not found.\n", args[0])));*/
      return;
    }

    ChatHelper.printMessage("\n", false);
    nekito.INSTANCE.getCommandManager().getCommands().stream()
        .filter(command -> !(command instanceof HelpCommand))
        .forEach(command -> ChatHelper.printMessage(
            String.format("&5%s &f- &d%s", command.getAlias(), command.getDescription())));

    ChatHelper.printMessage("\n", false);
  }
}
