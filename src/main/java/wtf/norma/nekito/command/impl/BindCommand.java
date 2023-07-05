package wtf.norma.nekito.command.impl;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.PlayerHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.nekito;

import java.util.Locale;

@CommandInfo(
        alias = "bind",
        description = "Bind modules",
        usage = ".bind [key]",
        aliases = {"b"}
)
public class BindCommand extends Command  {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length == 2) {
            nekito.INSTANCE.getModuleManager().modules.forEach(module -> {
                if (args[0].equalsIgnoreCase(module.getName())) {

                    module.setKeybind(Keyboard.getKeyIndex(args[1].toUpperCase(Locale.ROOT)));
                    ChatHelper.printMessage("Bound " + module.getName()  + " to " + args[1].toUpperCase());
                }
            });
        } else {
            ChatHelper.printMessage("Wrong arguments!");
        }
    }
}
