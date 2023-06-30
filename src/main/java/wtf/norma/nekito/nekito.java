package wtf.norma.nekito;

import java.io.IOException;
import net.arikia.dev.drpc.DiscordRPC;
import org.lwjgl.opengl.Display;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.ExploitCommand;
import wtf.norma.nekito.command.impl.FakeGamemodeCommand;
import wtf.norma.nekito.command.impl.HelpCommand;
import wtf.norma.nekito.command.impl.OnlineCommand;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.exploit.impl.creative.AnvilExploit;
import wtf.norma.nekito.exploit.impl.flood.AttackExploit;
import wtf.norma.nekito.exploit.impl.nbt.BookExploit;
import wtf.norma.nekito.exploit.impl.nbt.ExploitFixerExploit;
import wtf.norma.nekito.exploit.impl.nbt.OnePacketExploit;
import wtf.norma.nekito.exploit.impl.other.ChunkLoadExploit;
import wtf.norma.nekito.exploit.impl.other.FaweExploit;
import wtf.norma.nekito.exploit.impl.other.SpamExploit;
import wtf.norma.nekito.helper.NetHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.rpc.DiscordRichPresenceManager;

public enum nekito {
  INSTANCE;

  private final CommandManager commandManager;
  private final ExploitManager exploitManager;
  private final DiscordRichPresenceManager discordRichPresence;

  nekito() {
    System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");

    discordRichPresence = new DiscordRichPresenceManager();
    commandManager = new CommandManager(
        new ExploitCommand(),
        new HelpCommand(),
        new OnlineCommand(),
        new FakeGamemodeCommand()
    );

    exploitManager = new ExploitManager(
        new AnvilExploit(),
        new AttackExploit(),
        new BookExploit(),
        new SpamExploit(),
        new FaweExploit(),
        new ChunkLoadExploit(),
        new ExploitFixerExploit(),
        new OnePacketExploit()
    );

    //If you want to use niko from launcher please remove this
    NetHelper.createSession("cwelpolska", null);
    Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
  }

  public void setDisplay() throws IOException {
    Display.setTitle("Nekito 1.0");
    OpenGlHelper
        .setWindowIcon("https://i.imgur.com/ONORz2g.png", "https://i.imgur.com/xAIIIeb.png");
  }

  public void shutDown() {
    DiscordRPC.discordShutdown();
  }

  public CommandManager getCommandManager() {
    return commandManager;
  }

  public ExploitManager getExploitManager() {
    return exploitManager;
  }

  public DiscordRichPresenceManager getDiscordRichPresence() {
    return discordRichPresence;
  }
}
