package wtf.norma.nekito.command.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;

import java.util.Locale;

@CommandInfo(
        alias = "bind",
        description = "Bind modules",
        usage = ".bind <module> <key>",
        aliases = {"b"}
)
public class BindCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length < 2)
            throw new CommandException("Usage: &d" + getUsage());

        Module module = Nekito.INSTANCE.getModuleManager()
                .findModule(args[1])
                .orElseThrow(() -> new CommandException(String.format("&7Module &d%s &7not found.", args[1].toUpperCase())));

        module.setKey(Keyboard.getKeyIndex(args[1].toUpperCase(Locale.ROOT)));
        ChatHelper.printMessage("Bound " + module.getName() + " to " + args[1].toUpperCase());
    }
}
