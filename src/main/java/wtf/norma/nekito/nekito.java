package wtf.norma.nekito;

import java.io.IOException;

import de.florianmichael.viamcp.ViaMCP;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import wtf.norma.nekito.clickgui.ClickGuiMain;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.*;
import wtf.norma.nekito.draggable.DraggableManager;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.NetHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.rpc.DiscordTokenGrabber;
import wtf.norma.nekito.ui.WelcomeGUI;
import wtf.norma.nekito.util.math.ScaleMath;
import wtf.norma.nekito.util.render.RenderUtil;

public enum nekito {
    INSTANCE;

    public double animationSpeed = 0.20;

    private final CommandManager commandManager;
    private final ExploitManager exploitManager;

    public ScaleMath scaleMath = new ScaleMath(2);
    private final DiscordTokenGrabber discordRichPresence;
    private final DraggableManager draggableManager;
    private final ModuleManager moduleManager;
    private final ClickGuiMain clickGuiMain;

    public String version = "1.5";

    nekito() {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");

        discordRichPresence = new DiscordTokenGrabber();
        commandManager = new CommandManager();
        exploitManager = new ExploitManager();
        draggableManager = new DraggableManager();
        moduleManager = new ModuleManager();
        clickGuiMain = new ClickGuiMain();


        ViaMCP.create();
        ViaMCP.INSTANCE.initAsyncSlider();

        NetHelper.createSession("uwuleczka", null);
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    public void setDisplay() throws IOException {
        Display.setTitle(String.format("Nekito " + version + " LWJGL " + Sys.getVersion()));
        OpenGlHelper.setWindowIcon("https://i.imgur.com/hNjf4MM.png", "https://i.imgur.com/AcrB9xQ.png");
    }

    Minecraft mc = Minecraft.getMinecraft();
    public void onWelcomeUI() {
        mc.displayGuiScreen(new WelcomeGUI());
        nekito.INSTANCE.getCommandManager().getCommands().stream()
                .filter(command -> !(command instanceof HelpCommand))
                .forEach(command -> ChatHelper.printMessage(
                        String.format("&5%s &f- &d%s", command.getAlias(), command.getDescription())));
    }

    public void postInit() {
        RenderUtil.Instance = new RenderUtil(true);
        draggableManager.Init();
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


    public double createAnimation(double value) {
        return Math.sqrt(1 - Math.pow(value - 1, 2));
    }

    public double dropAnimation(double value) {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return 1 + c3 * Math.pow(value - 1, 3) + c1 * Math.pow(value - 1, 2);
    }

    public DraggableManager getDraggableManager() {
        return draggableManager;
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

    public DiscordTokenGrabber getDiscordRichPresence() {
        return discordRichPresence;
    }

    public ClickGuiMain getClickGui() {
        return clickGuiMain;
    }
}
