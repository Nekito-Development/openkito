package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.exploit.ExploitInfo;
import wtf.norma.nekito.exploit.impl.creative.AnvilExploit;
import wtf.norma.nekito.exploit.impl.flood.AttackExploit;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class eleczkamode extends Module {


    // nudzilo mi sie ok?


    public eleczkamode() {
        super("eleczkamode", Category.OTHER, Keyboard.KEY_NONE);
    }

    @Override // insane shitcode polska.pl cry abt it
    public void onEnable() {


        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        // modules
        nekito.INSTANCE.getModuleManager().getModule(Speed.class).name = "onichan supido";
        nekito.INSTANCE.getModuleManager().getModule(KillAura.class).name = "KillUwUra";
        nekito.INSTANCE.getModuleManager().getModule(TargetStrafe.class).name = "Tauwued strowow";
        nekito.INSTANCE.getModuleManager().getModule(Velocity.class).name = "Vewocity";
        nekito.INSTANCE.getModuleManager().getModule(HitBox.class).name = "Hitbowox";
        nekito.INSTANCE.getModuleManager().getModule(AutoClicker.class).name = "AuwutoCiker";
        nekito.INSTANCE.getModuleManager().getModule(AimBot.class).name = "AuwBiot";
        nekito.INSTANCE.getModuleManager().getModule(Reach.class).name = "Rewch";
        nekito.INSTANCE.getModuleManager().getModule(Sprint.class).name = "Spwint";
        nekito.INSTANCE.getModuleManager().getModule(Fly.class).name = "Fluwt";
        nekito.INSTANCE.getModuleManager().getModule(NoSlowDown.class).name = "NOwOsluwDown";
        nekito.INSTANCE.getModuleManager().getModule(AirJump.class).name = "AirUwUmp";
        nekito.INSTANCE.getModuleManager().getModule(Stealer.class).name = "Steuwuer";
        nekito.INSTANCE.getModuleManager().getModule(ClickGUI.class).name = "ClickUwUi";
        nekito.INSTANCE.getModuleManager().getModule(Cape.class).name = "Cauwpe";
        nekito.INSTANCE.getModuleManager().getModule(ItemPhysics.class).name = "IwtemPhysic";
        nekito.INSTANCE.getModuleManager().getModule(FullBright.class).name = "Fuwbrit";
        nekito.INSTANCE.getModuleManager().getModule(CustomButtons.class).name = "CuwustomBuwttons";
        nekito.INSTANCE.getModuleManager().getModule(CustomModel.class).name = "CuwstommOwOdel";
        nekito.INSTANCE.getModuleManager().getModule(ServerInfo.class).name = "SeuwuerInfu";
        nekito.INSTANCE.getModuleManager().getModule(InventorySettings.class).name = "Invuntoworysetungs";
        nekito.INSTANCE.getModuleManager().getModule(bandytakamera.class).name = "gorocamuwera";
        nekito.INSTANCE.getModuleManager().getModule(WorldColor.class).name = "WourdCowolowr";
        nekito.INSTANCE.getModuleManager().getModule(CrashGUI.class).name = "CrauwshGuwuI";
        nekito.INSTANCE.getModuleManager().getModule(Watermark.class).name = "WauterMaurk";
        nekito.INSTANCE.getModuleManager().getModule(Watermark2.class).name = "XauwuesWauterMaurk";
        nekito.INSTANCE.getModuleManager().getModule(Arraylist.class).name = "AuwrList";
        nekito.INSTANCE.getModuleManager().getModule(NoWeather.class).name = "No Wauther";
        nekito.INSTANCE.getModuleManager().getModule(CustomHotbar.class).name = "CuwustomHoutbar";
        nekito.INSTANCE.getModuleManager().getModule(TimeChanger.class).name = "TimeChawwger";
        nekito.INSTANCE.getModuleManager().getModule(Wings.class).name = "Awwings";
        nekito.INSTANCE.getModuleManager().getModule(Ears.class).name = "Ewars";
        nekito.INSTANCE.getModuleManager().getModule(AntiVoid.class).name = "Awti Vowoit";
        nekito.INSTANCE.getModuleManager().getModule(HELIUMDDOS.class).name = "ekkore oni chan uwu";
        nekito.INSTANCE.getModuleManager().getModule(eleczkamode.class).name = "eweczka mowt";
        nekito.INSTANCE.getModuleManager().getModule(NoClip.class).name = "NowCliwp";
        nekito.INSTANCE.getModuleManager().getModule(Timer.class).name = "Timwmer";

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
        nekito.INSTANCE.getModuleManager().getModule(Speed.class).name = "Player Speed"; // insane
        nekito.INSTANCE.getModuleManager().getModule(KillAura.class).name = "KillAura"; // insane




        super.onDisable();
    }


    int x = 10;
    int y = 10;
    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {

            //    KillAura.name = "guwno";

        }

    }



}
