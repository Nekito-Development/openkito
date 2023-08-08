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
        alias = "hclip",
        description = ":D",
        usage = ".hclip [key]",
        aliases = {"h"}
)
public class HclipCommand extends Command  {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            final double dist = Double.parseDouble(args[0]);
            final String direction = dist > 0 ? "forward" : "back"; // UNUSED POLSKA??????????????????

            final double rotation = Math.toRadians(mc.thePlayer.rotationYaw);

            final double x = Math.sin(rotation) * dist;
            final double z = Math.cos(rotation) * dist;

            mc.thePlayer.setPosition(mc.thePlayer.posX - x, mc.thePlayer.posY, mc.thePlayer.posZ + z);
        }
    }
}
