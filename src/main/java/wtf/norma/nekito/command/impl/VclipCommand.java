package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;

@CommandInfo(
        alias = "vclip",
        description = ":D",
        usage = ".vclip [key]",
        aliases = {"v"}
)
public class VclipCommand extends Command  {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 0) {
            final double dist = Double.parseDouble(args[0]);
            final String direction = dist > 0 ? "up" : "down";

            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + dist, mc.thePlayer.posZ);
        }
    }
}
