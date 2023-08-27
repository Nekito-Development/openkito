package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;


/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 */

public class eleczkamode extends Module implements Subscriber {


    // nudzilo mi sie ok?

    public eleczkamode() {
        super("eleczkamode", Category.OTHER, Keyboard.KEY_NONE);
    }

    @Override // insane shitcode polska.pl cry abt it
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);

        //   ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        // modules
        Nekito.INSTANCE.getModuleManager().getModule(Speed.class).name = "onichan supido";
        Nekito.INSTANCE.getModuleManager().getModule(KillAura.class).name = "KillUwUra";
        Nekito.INSTANCE.getModuleManager().getModule(TargetStrafe.class).name = "Tauwued strowow";
        Nekito.INSTANCE.getModuleManager().getModule(Velocity.class).name = "Vewocity";
        Nekito.INSTANCE.getModuleManager().getModule(HitBox.class).name = "Hitbowox";
        Nekito.INSTANCE.getModuleManager().getModule(AutoClicker.class).name = "AuwutoCiker";
        Nekito.INSTANCE.getModuleManager().getModule(AimBot.class).name = "AuwBiot";
        Nekito.INSTANCE.getModuleManager().getModule(Reach.class).name = "Rewch";
        Nekito.INSTANCE.getModuleManager().getModule(Sprint.class).name = "Spwint";
        Nekito.INSTANCE.getModuleManager().getModule(Fly.class).name = "Fluwt";
        Nekito.INSTANCE.getModuleManager().getModule(NoSlowDown.class).name = "NOwOsluwDown";
        Nekito.INSTANCE.getModuleManager().getModule(AirJump.class).name = "AirUwUmp";
        Nekito.INSTANCE.getModuleManager().getModule(Stealer.class).name = "Steuwuer";
        Nekito.INSTANCE.getModuleManager().getModule(ClickGUI.class).name = "ClickUwUi";
        Nekito.INSTANCE.getModuleManager().getModule(Cape.class).name = "Cauwpe";
        Nekito.INSTANCE.getModuleManager().getModule(ItemPhysics.class).name = "IwtemPhysic";
        Nekito.INSTANCE.getModuleManager().getModule(FullBright.class).name = "Fuwbrit";
        Nekito.INSTANCE.getModuleManager().getModule(CustomButtons.class).name = "CuwustomBuwttons";
        Nekito.INSTANCE.getModuleManager().getModule(CustomModel.class).name = "CuwstommOwOdel";
        Nekito.INSTANCE.getModuleManager().getModule(ServerInfo.class).name = "SeuwuerInfu";
        Nekito.INSTANCE.getModuleManager().getModule(InventorySettings.class).name = "Gwui setuwnii";
        Nekito.INSTANCE.getModuleManager().getModule(bandytakamera.class).name = "gorocamuwera";
        Nekito.INSTANCE.getModuleManager().getModule(WorldColor.class).name = "WourdCowolowr";

        Nekito.INSTANCE.getModuleManager().getModule(Watermark.class).name = "WauterMaurk";
        Nekito.INSTANCE.getModuleManager().getModule(Watermark2.class).name = "XauwuesWauterMaurk";
        Nekito.INSTANCE.getModuleManager().getModule(Arraylist.class).name = "AuwrList";
        Nekito.INSTANCE.getModuleManager().getModule(TargetHUD.class).name = "Tawwget HUD";
        Nekito.INSTANCE.getModuleManager().getModule(NoWeather.class).name = "No Wauther";
        Nekito.INSTANCE.getModuleManager().getModule(CustomHotbar.class).name = "CuwustomHoutbar";
        Nekito.INSTANCE.getModuleManager().getModule(TimeChanger.class).name = "TimeChawwger";
        Nekito.INSTANCE.getModuleManager().getModule(Wings.class).name = "Awwings";
        Nekito.INSTANCE.getModuleManager().getModule(Ears.class).name = "Ewars";
        Nekito.INSTANCE.getModuleManager().getModule(AntiVoid.class).name = "Awti Vowoit";
        Nekito.INSTANCE.getModuleManager().getModule(HELIUMDDOS.class).name = "ekkore oni chan uwu";
        Nekito.INSTANCE.getModuleManager().getModule(eleczkamode.class).name = "eweczka mowt";
        Nekito.INSTANCE.getModuleManager().getModule(NoClip.class).name = "NowCliwp";
        Nekito.INSTANCE.getModuleManager().getModule(Timer.class).name = "Timwmer";
        Nekito.INSTANCE.getModuleManager().getModule(Criticals.class).name = "Criwtakals";
        Nekito.INSTANCE.getModuleManager().getModule(FogColor.class).name = "Fowo Colowwr";
        Nekito.INSTANCE.getModuleManager().getModule(Tickbase.class).name = "TiwwBawse";
        Nekito.INSTANCE.getModuleManager().getModule(NoFall.class).name = "No Fawll";
        Nekito.INSTANCE.getModuleManager().getModule(FreeCam.class).name = "FrewwCaam";
        Nekito.INSTANCE.getModuleManager().getModule(FastWorldLoad.class).name = "FawstWowoldLoaw";
        Nekito.INSTANCE.getModuleManager().getModule(Strafe.class).name = "Straawwfe";
        Nekito.INSTANCE.getModuleManager().getModule(LagDetector.class).name = "LagoDetectoro";
        Nekito.INSTANCE.getModuleManager().getModule(TriggerBot.class).name = "TiwwerBowt";


        //categories
        Category.COMBAT.name = "COwObat";
        Category.LEGIT.name = "Lewgit";
        Category.MOVEMENT.name = "Moveuwment";
        Category.VISUALS.name = "Viewuals";
        Category.OTHER.name = "Othwwer";
        Category.CRASHERS.name = "Crashuewrs Liswt";


        //exploits

        super.onEnable();
    }

    @Override
    public void onDisable() {  // insane shitcode polska.pl cry abt it
        Nekito.EVENT_BUS.unsubscribe(this);
        Nekito.INSTANCE.getModuleManager().getModule(Speed.class).name = "Player Speed"; // insane
        Nekito.INSTANCE.getModuleManager().getModule(KillAura.class).name = "KillAura"; // insane
        Nekito.INSTANCE.getModuleManager().getModule(Criticals.class).name = "Criticals";
        Nekito.INSTANCE.getModuleManager().getModule(TargetStrafe.class).name = "Target Strafe";
        Nekito.INSTANCE.getModuleManager().getModule(Velocity.class).name = "Velocity";
        Nekito.INSTANCE.getModuleManager().getModule(HitBox.class).name = "HitBox";
        Nekito.INSTANCE.getModuleManager().getModule(AutoClicker.class).name = "AutoClicker";
        Nekito.INSTANCE.getModuleManager().getModule(AimBot.class).name = "AimBot";
        Nekito.INSTANCE.getModuleManager().getModule(Reach.class).name = "Reach";
        Nekito.INSTANCE.getModuleManager().getModule(Sprint.class).name = "Sprint";
        Nekito.INSTANCE.getModuleManager().getModule(Fly.class).name = "Fly";
        Nekito.INSTANCE.getModuleManager().getModule(TargetHUD.class).name = "Target HUD";
        Nekito.INSTANCE.getModuleManager().getModule(NoSlowDown.class).name = "No Slow Down";
        Nekito.INSTANCE.getModuleManager().getModule(AirJump.class).name = "AirJump";
        Nekito.INSTANCE.getModuleManager().getModule(Stealer.class).name = "romanian simulator";
        Nekito.INSTANCE.getModuleManager().getModule(ClickGUI.class).name = "ClickGUI";
        Nekito.INSTANCE.getModuleManager().getModule(Cape.class).name = "Cape";
        Nekito.INSTANCE.getModuleManager().getModule(ItemPhysics.class).name = "ItemPhysics";
        Nekito.INSTANCE.getModuleManager().getModule(FullBright.class).name = "FullBright";
        Nekito.INSTANCE.getModuleManager().getModule(CustomButtons.class).name = "CustomButtons";
        Nekito.INSTANCE.getModuleManager().getModule(CustomModel.class).name = "CustomModel";
        Nekito.INSTANCE.getModuleManager().getModule(ServerInfo.class).name = "ServerInfo";
        Nekito.INSTANCE.getModuleManager().getModule(InventorySettings.class).name = "Gui Settings";
        Nekito.INSTANCE.getModuleManager().getModule(bandytakamera.class).name = "bandytakamera";
        Nekito.INSTANCE.getModuleManager().getModule(WorldColor.class).name = "WorldColor";
        Nekito.INSTANCE.getModuleManager().getModule(Watermark.class).name = "Watermark";
        Nekito.INSTANCE.getModuleManager().getModule(Watermark2.class).name = "cfx watermark ";
        Nekito.INSTANCE.getModuleManager().getModule(Arraylist.class).name = "Arraylist";
        Nekito.INSTANCE.getModuleManager().getModule(NoWeather.class).name = "No Weather";
        Nekito.INSTANCE.getModuleManager().getModule(CustomHotbar.class).name = "CustomHotbar";
        Nekito.INSTANCE.getModuleManager().getModule(TimeChanger.class).name = "Time Changer";
        Nekito.INSTANCE.getModuleManager().getModule(Wings.class).name = "Wings";
        Nekito.INSTANCE.getModuleManager().getModule(Ears.class).name = "Ears";
        Nekito.INSTANCE.getModuleManager().getModule(AntiVoid.class).name = "Anti Void";
        Nekito.INSTANCE.getModuleManager().getModule(HELIUMDDOS.class).name = "helium auth disabler";
        Nekito.INSTANCE.getModuleManager().getModule(eleczkamode.class).name = "eleczkamode";
        Nekito.INSTANCE.getModuleManager().getModule(NoClip.class).name = "No Clip";
        Nekito.INSTANCE.getModuleManager().getModule(Timer.class).name = "Timer";
        Nekito.INSTANCE.getModuleManager().getModule(FogColor.class).name = "Fog Color";
        Nekito.INSTANCE.getModuleManager().getModule(Tickbase.class).name = "Tickbase";
        Nekito.INSTANCE.getModuleManager().getModule(NoFall.class).name = "No Fall";
        Nekito.INSTANCE.getModuleManager().getModule(FreeCam.class).name = "FreeCam";
        Nekito.INSTANCE.getModuleManager().getModule(FastWorldLoad.class).name = "FastWorldLoad";
        Nekito.INSTANCE.getModuleManager().getModule(LagDetector.class).name = "Lag Detector";
        Nekito.INSTANCE.getModuleManager().getModule(TriggerBot.class).name = "TriggerBot";
        Nekito.INSTANCE.getModuleManager().getModule(Strafe.class).name = "Strafe";


        Category.COMBAT.name = "Combat";
        Category.LEGIT.name = "Legit";
        Category.MOVEMENT.name = "Movement";
        Category.VISUALS.name = "Visuals";
        Category.OTHER.name = "Other";
        Category.CRASHERS.name = "Crashers List";


        super.onDisable();
    }
    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        //TODO: implement shit idk
    });


}
