package wtf.norma.nekito;

import de.florianmichael.viamcp.ViaMCP;
import net.arikia.dev.drpc.DiscordRPC;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.bus.PubSub;
import wtf.norma.nekito.clickgui.ClickGuiMain;
import wtf.norma.nekito.command.CommandManager;
import wtf.norma.nekito.command.impl.*;
import wtf.norma.nekito.draggable.Draggable;
import wtf.norma.nekito.draggable.DraggableManager;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.KeyEvent;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.exploit.impl.creative.AnvilExploit;
import wtf.norma.nekito.exploit.impl.flood.AttackExploit;
import wtf.norma.nekito.exploit.impl.nbt.*;
import wtf.norma.nekito.exploit.impl.other.*;
import wtf.norma.nekito.helper.network.NetHelper;
import wtf.norma.nekito.helper.render.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.module.impl.movement.FlyModule;
import wtf.norma.nekito.module.impl.movement.SprintModule;
import wtf.norma.nekito.module.impl.visuals.*;
import wtf.norma.nekito.module.impl.visuals.draggable.*;
import wtf.norma.nekito.rpc.DiscordTokenGrabber;

import java.io.IOException;

public enum Nekito {
    INSTANCE;

    private static final String VERSION = "1.5";

    private final PubSub<Event> eventBus;
    private final CommandManager commandManager;
    private final ExploitManager exploitManager;
    private final DiscordTokenGrabber discordRichPresence;
    private final DraggableManager draggableManager;
    private final ModuleManager moduleManager;
    private final ClickGuiMain clickGuiMain;

    Nekito() {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");

        this.eventBus = new PubSub<>();
        discordRichPresence = new DiscordTokenGrabber();
        commandManager = new CommandManager(
                new BindCommand(),
                new ExploitCommand(),
                new FakeGamemodeCommand(),
                new HclipCommand(),
                new HelpCommand(),
                new OnlineCommand(),
                new ToggleCommand(),
                new ValueCommand(),
                new VclipCommand()
        );
        exploitManager = new ExploitManager(
                new AnvilExploit(),

                new AttackExploit(),

                new BookExploit(),
                new CipaExploit(),
                new CwelExploit(),
                new ExploitFixerExploit(),
                new KuszoExploit(),
                new OnePacketExploit(),
                new PedalExploit(),

                new ChunkLoadExploit(),
                new FaweExploit(),
                new MultiVerseExploit(),
                new MultiVerseMvExploit(),
                new NoCheatPlusExploit(),
                new SkriptExploit(),
                new SpamExploit(),
                new WorldEditExploit()
        );
        moduleManager = new ModuleManager(
                new CustomHotBarModule(),
                new ModuleListModule(),
                new ScoreBoardModule(),
                new ServerInfoModule(),
                new WatermarkModule(),

                new CapeModule(),
                new ClickGuiModule(),
                new CustomButtonsModule(),
                new EarsModule(),
                new FullBrightModule(),
                new ItemPhysicsModule(),
                new NoWeatherModule(),
                new WingsModule(),

                new FlyModule(),
                new SprintModule()
        );
        moduleManager.registerValues();
        draggableManager = new DraggableManager(moduleManager.getModules().stream().filter(module -> module instanceof Draggable).toArray(Draggable[]::new));

        clickGuiMain = new ClickGuiMain();
        eventBus.subscribe(this);

        ViaMCP.create();
        ViaMCP.INSTANCE.initAsyncSlider();

        NetHelper.createSession("uwuleczka", null);
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    public void setDisplay() throws IOException {
        Display.setTitle(String.format("Nekito " + VERSION + " LWJGL " + Sys.getVersion()));
        try {
            OpenGlHelper.setWindowIcon("https://i.imgur.com/hNjf4MM.png", "https://i.imgur.com/AcrB9xQ.png");
        } catch (Exception ignored) {
        }
    }

    //Garbage
//    public void onWelcomeUI() {
//        mc.displayGuiScreen(new WelcomeGUI());
//        Nekito.INSTANCE.getCommandManager().getCommands().stream()
//                .filter(command -> !(command instanceof HelpCommand))
//                .forEach(command -> ChatHelper.printMessage(
//                        String.format("&5%s &f- &d%s", command.getAlias(), command.getDescription())));
//    }

    public void shutDown() {
        DiscordRPC.discordShutdown();
    }

    @Handler
    public void onKey(KeyEvent keyEvent) {
        for (Module module : moduleManager.getModules()) {
            if (module.getKey() == keyEvent.getKey()) {
                module.toggle();
            }
        }
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

    public PubSub<Event> getEventBus() {
        return eventBus;
    }
}
