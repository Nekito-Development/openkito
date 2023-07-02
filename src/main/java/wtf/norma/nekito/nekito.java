package wtf.norma.nekito;

import java.io.IOException;

import de.florianmichael.viamcp.ViaMCP;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import wtf.norma.nekito.clickgui.ClickGuiMain;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.ExploitCommand;
import wtf.norma.nekito.command.impl.FakeGamemodeCommand;
import wtf.norma.nekito.command.impl.HelpCommand;
import wtf.norma.nekito.command.impl.OnlineCommand;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.exploit.impl.creative.AnvilExploit;
import wtf.norma.nekito.exploit.impl.flood.*;
import wtf.norma.nekito.exploit.impl.nbt.BookExploit;
import wtf.norma.nekito.exploit.impl.nbt.ExploitFixerExploit;
import wtf.norma.nekito.exploit.impl.nbt.OnePacketExploit;
import wtf.norma.nekito.exploit.impl.other.*;
import wtf.norma.nekito.helper.NetHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.rpc.DiscordRichPresenceManager;
import wtf.norma.nekito.util.math.ScaleMath;

public enum nekito {
    INSTANCE;

    private final CommandManager commandManager;
    private final ExploitManager exploitManager;

    public ScaleMath scaleMath = new ScaleMath(2);
   // private final DiscordRichPresenceManager discordRichPresence;
    private final ModuleManager moduleManager;

    private final ClickGuiMain clickGuiMain;

    nekito() {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");


       /// discordRichPresence = new DiscordRichPresenceManager();

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
                new CIPA(),
                new SkriptCrasher(),
                new ChunkLoadExploit(),
                new MVCExploit(),
                new CwelExploit(),
                new KuszkoExploit(),
                new PedalExploit(),
                new ExploitFixerExploit(),
                new OnePacketExploit()
        );

        moduleManager = new ModuleManager();
        clickGuiMain = new ClickGuiMain();

        ViaMCP.create();
        ViaMCP.INSTANCE.initAsyncSlider();

        //If you want to use niko from launcher please remove this
        NetHelper.createSession("cwelpolska", null);
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    public void setDisplay() throws IOException {
        Display.setTitle("Nekito 1.0");
        OpenGlHelper.setWindowIcon("https://i.imgur.com/hNjf4MM.png", "https://i.imgur.com/AcrB9xQ.png");
    }

    public void shutDown() {
        DiscordRPC.discordShutdown();
    }

    public void onKey(int key) {
        for (Module module : moduleManager.modules) {
            if (module.getKeybind() != 0 && key == module.getKeybind()) {
                module.toggle();
            }
        }
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ExploitManager getExploitManager() {
        return exploitManager;
    }

   // public DiscordRichPresenceManager getDiscordRichPresence() {
   //     return discordRichPresence;
 //   }

    public ClickGuiMain getClickGui() {
        return clickGuiMain;
    }
}
