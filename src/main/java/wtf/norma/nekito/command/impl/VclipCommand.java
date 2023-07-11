package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;

@CommandInfo(
        alias = "vclip",
        description = ":D",
        usage = ".vclip <y>",
        aliases = "v"
)
public class VclipCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length <= 0)
            throw new CommandException("Usage: &d" + getUsage());

        final double dist = Double.parseDouble(args[0]);
//            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + dist, mc.thePlayer.posZ);
        //Nie wiem czy to dobrze zrobilem, nie jestem cheat developerem jeszcze
        mc.thePlayer.setEntityBoundingBox(mc.thePlayer.getEntityBoundingBox().offset(0, dist, 0));
    }
}
