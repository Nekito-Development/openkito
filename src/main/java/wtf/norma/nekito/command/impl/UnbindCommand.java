package wtf.norma.nekito.command.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.ModuleManager;

@CommandInfo(
        alias = "unbind",
        description = "Unbind modules",
        usage = ".bind [key]",
        aliases = {"ub"}
)
public class UnbindCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 1) {
            ModuleManager.modules.forEach(module -> {
                if (args[0].equalsIgnoreCase(module.getName())) {

                    module.setKeybind(Keyboard.getKeyIndex(null));
                    ChatHelper.printMessage("Unbinded " + module.getName()); // cry abt it

                }
            });
        } else {
            ChatHelper.printMessage("Wrong arguments!");
        }
    }
}

