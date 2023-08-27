package wtf.norma.nekito;

import de.florianmichael.viamcp.ViaMCP;
import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.HelpCommand;
import wtf.norma.nekito.draggable.DraggableManager;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.newevent.impl.input.EventKey;
import wtf.norma.nekito.rpc.DiscordTokenGrabber;
import wtf.norma.nekito.ui.clickgui.ClickGuiMain;
import wtf.norma.nekito.ui.crashgui.CrashGuiMain;
import wtf.norma.nekito.util.math.ScaleMath;
import wtf.norma.nekito.util.other.LoggingUtil;
import wtf.norma.nekito.util.render.RenderUtil;

import java.io.IOException;

public enum Nekito implements Subscriber {
    INSTANCE;

    public static final EventBus EVENT_BUS = EventManager.builder()
            .setName("nekito/root")
            .setSuperListeners()
            .build();

    Minecraft mc = Minecraft.getMinecraft();
    public double animationSpeed = 0.20;

    @Getter
    private final CommandManager commandManager;
    @Getter
    private final ExploitManager exploitManager;

    public ScaleMath scaleMath = new ScaleMath(2);
    @Getter
    private final DiscordTokenGrabber discordRichPresence;
    @Getter
    private final DraggableManager draggableManager;


    @Getter
    private final ModuleManager moduleManager;
    @Getter
    private final ClickGuiMain clickGuiMain;
    @Getter
    private final CrashGuiMain crashGuiMain;



  //  public ConfigManager configManager;


    public static double deltaTime() {
        return Minecraft.getDebugFPS() > 0 ? (1.0000 / Minecraft.getDebugFPS()) : 1;
    }

    public static String name = "Nekito";

    public static String version = "2.0";


    public boolean isStarting = false;

    Nekito() {
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
        Nekito.INSTANCE.getCommandManager().getCommands().stream()
                .filter(command -> !(command instanceof HelpCommand))
                .forEach(command -> ChatHelper.printMessage(
                        String.format("&5%s &f- &d%s", command.getAlias(), command.getDescription())));
    }



    public void postInit() {
//        Me being a fucking retard forgot to subscribe
        EVENT_BUS.subscribe(this);
        RenderUtil.Instance = new RenderUtil(true);
        draggableManager.Init();
    }

    public void shutDown() {
        EVENT_BUS.unsubscribe(this);
    // nekito.INSTANCE.configManager.saveConfig("default");
     DiscordRPC.discordShutdown();
    }

    //OLD ASS PIECE OF CODE
//    public void onKey(int key) {
//        for (Module module : ModuleManager.modules) {
//            if (module.getKeybind() != 0 && key == module.getKeybind()) {
//                module.toggle();
//            }
//        }
//    }

    @Subscribe
    private final Listener<EventKey> keyListener = new Listener<>(eventKey -> {
        ModuleManager.modules.stream().forEach(module -> {
            if (module.getKeybind() != 0 && eventKey.getKey() == module.getKeybind()) module.toggle();
        });
    });


    public double createAnimation(double value) {
        return Math.sqrt(1 - Math.pow(value - 1, 2));
    }

    public double dropAnimation(double value) {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return 1 + c3 * Math.pow(value - 1, 3) + c1 * Math.pow(value - 1, 2);
    }


}
