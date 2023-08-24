package wtf.norma.nekito.module.impl;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.server.*;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;


public class PacketDebugger extends Module {


    public static ModeSetting Brand = new ModeSetting("Client brand", "Vanilla", "Vanilla", "Lunar","Console Spammer","Smile","None");

    public static BooleanSetting Render = new BooleanSetting("Render in Ui", false);

    public static BooleanSetting keepAlive = new BooleanSetting("Keep Alive", false);

    public static BooleanSetting payloads = new BooleanSetting("Payloads", false);

    public static BooleanSetting transaction = new BooleanSetting("Transactions", false);

    public static BooleanSetting velo = new BooleanSetting("Velocity", false);









    public PacketDebugger() {
        super("Packet Debugger", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(Brand,keepAlive, payloads, transaction,velo);
    }


    private long cwel;
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {


            // yeah i figured it out
            final Packet<?> dupa = ((EventUpdate) e).getPacket();

            if (mc.theWorld != null && mc.thePlayer != null) {

                // keep alive
                if (((EventUpdate) e).getPacket() instanceof C00PacketKeepAlive && keepAlive.isEnabled()) {

                    long lastPacket = System.currentTimeMillis() - cwel;

                    ChatHelper.printMessage("Keep Alive Packet : " + ((C00PacketKeepAlive) ((EventUpdate) e).getPacket()).getKey() + " " + lastPacket + "ms");


                    this.cwel = System.currentTimeMillis();
                }

                // Payload
                if (((EventUpdate) e).getPacket() instanceof S3FPacketCustomPayload && payloads.isEnabled()) {
                    ChatHelper.printMessage("Payload Packet : " + ((S3FPacketCustomPayload) ((EventUpdate) e).getPacket()).getChannelName());
                }


             // TRANSACTION PACKET
                if (dupa instanceof S32PacketConfirmTransaction && transaction.isEnabled()) {
                    ChatHelper.printMessage("Transaction Packet : " + dupa);
                }


                //VELOCITY PACKET (also prints out id, x,y and z which probably dont work XD)
                if(dupa instanceof S12PacketEntityVelocity && velo.isEnabled()){
                    ChatHelper.printMessage("Velocity Packet : " + dupa + "ID: " + ((S12PacketEntityVelocity) dupa).getEntityID() + "X: " + ((S12PacketEntityVelocity) dupa).getMotionX() + "Y: " + ((S12PacketEntityVelocity) dupa).getMotionY() + "Z: " + ((S12PacketEntityVelocity) dupa).getMotionZ());
                }

                // Abilities packet
                if(dupa instanceof S39PacketPlayerAbilities && velo.isEnabled()){
                    ChatHelper.printMessage("Abilities Packet : " + dupa);
                }

                // explosion packet (decided to add it to velo)
                if(dupa instanceof S27PacketExplosion && velo.isEnabled()){
                    ChatHelper.printMessage("Velocity (Explosion)  Packet : " + ((S27PacketExplosion) dupa).getStrength() + "Strength");
                }












            }
        }
    }
}

