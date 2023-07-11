package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;

@CommandInfo(
        alias = "hclip",
        description = ":D",
        usage = ".hclip <value>",
        aliases = {"h"}
)
public class HclipCommand extends Command {

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length <= 0)
            throw new CommandException("Usage: &d" + getUsage());

        final double dist = Double.parseDouble(args[0]);
        final double rotation = Math.toRadians(mc.thePlayer.rotationYaw);

        final double x = Math.sin(rotation) * dist;
        final double z = Math.cos(rotation) * dist;

//        mc.thePlayer.setPosition(mc.thePlayer.posX - x, mc.thePlayer.posY, mc.thePlayer.posZ + z);
        //Nie wiem czy to dobrze zrobilem, nie jestem cheat developerem jeszcze
        mc.thePlayer.setEntityBoundingBox(mc.thePlayer.getEntityBoundingBox().offset(-x, dist, z));
    }
}
