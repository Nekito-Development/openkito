package wtf.norma.nekito.command.impl;

import net.minecraft.command.CommandBase;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings.GameType;
import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;

@CommandInfo(
        alias = "fakegamemode",
        description = "Spoofing your gamemode (only in client)",
        usage = ".fakegamemode <mode/revert>",
        aliases = {"fakegm", "fgm"}
)
public class FakeGamemodeCommand extends Command {
    private GameType savedType;

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length <= 0) {
            throw new CommandException("Usage: &d" + getUsage());
        }

        if (savedType != null && args[0].equalsIgnoreCase("revert")) {
            mc.playerController.setGameType(savedType);
            ChatHelper.printMessage("Your client gamemode was reverted to &d" + savedType.getName());
            savedType = null;
            return;
        }

        try {
            GameType gameType = getGameModeFromCommand(args[0]);
            if (savedType == null) {
                savedType = mc.playerController.getCurrentGameType();
            }

            mc.playerController.setGameType(gameType);
            ChatHelper.printMessage("Your client gamemode was set to &d" + gameType.getName());
        } catch (Exception e) {
            throw new CommandException(getUsage());
        }
    }

    //Don't kill me it's mojang code
    private WorldSettings.GameType getGameModeFromCommand(String argument) throws NumberInvalidException {
        return !argument.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName()) && !argument
                .equalsIgnoreCase("s")
                ? (!argument.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName()) && !argument
                .equalsIgnoreCase("c")
                ? (!argument.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName()) && !argument
                .equalsIgnoreCase("a")
                ? (!argument.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.getName()) && !argument
                .equalsIgnoreCase("sp")
                ? WorldSettings.getGameTypeById(
                CommandBase.parseInt(argument, 0, WorldSettings.GameType.values().length - 2))
                : WorldSettings.GameType.SPECTATOR) : WorldSettings.GameType.ADVENTURE)
                : WorldSettings.GameType.CREATIVE) : WorldSettings.GameType.SURVIVAL;
    }
}
