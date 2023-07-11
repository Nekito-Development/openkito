package wtf.norma.nekito.command.impl;

import wtf.norma.nekito.command.Command;
import wtf.norma.nekito.command.CommandInfo;
import wtf.norma.nekito.exception.CommandException;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.player.PlayerHelper;

@CommandInfo(
        alias = "online",
        description = ":D",
        usage = ".online [method]",
        aliases = {"players", "realplayers"}
)
public class OnlineCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        Type type = args.length > 0 ? Type.valueOf(args[0].toUpperCase()) : Type.PLAYER_DATA;
        int onlinePlayers = -1;

        switch (type) {
            case PLAYER_DATA:
                onlinePlayers = PlayerHelper.getOnlinePlayer().size();
                break;
            case TAB_COMPLETE:
                //TODO: LOL I'M LAZY AS FUK
                break;
        }

        ChatHelper.printMessage("Online players: &d" + onlinePlayers);
    }

    enum Type {
        TAB_COMPLETE, PLAYER_DATA
    }
}
