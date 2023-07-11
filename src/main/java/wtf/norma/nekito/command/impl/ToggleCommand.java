package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;

@CommandInfo(
        alias = "toggle",
        aliases = {"t"},
        usage = ".toggle <module>"
)
public class ToggleCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length <= 0)
            throw new CommandException("Usage: %d" + getUsage());


        Module module = Nekito.INSTANCE.getModuleManager().findModule(args[0]).orElseThrow(() ->
                new CommandException(String.format("&7Module &d%s &7not found.", args[0].toUpperCase())));

        module.toggle();
        ChatHelper.printMessage("&7Module &d" + args[0].toUpperCase() + " &7has been " + (module.isEnabled() ? "enabled" : "disabled") + "."
        );
    }
}
