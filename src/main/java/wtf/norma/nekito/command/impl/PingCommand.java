package wtf.norma.nekito.command.impl;

import net.minecraft.util.EnumChatFormatting;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.PlayerHelper;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.util.player.EntityUtility;


@CommandInfo(
        alias = "ping",
        description = "sprawdz pinga ziom",
        usage = ".ping",
        aliases = {"p"}
)
public class PingCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {

        ChatHelper.printMessage(EnumChatFormatting.WHITE + "Your Ping is: " + EnumChatFormatting.LIGHT_PURPLE + EntityUtility.getPing() + "ms");
    }


}