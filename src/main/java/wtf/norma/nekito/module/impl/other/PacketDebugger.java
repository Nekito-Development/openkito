package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.server.*;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.event.impl.packet.PacketEvent;
import wtf.norma.nekito.event.impl.render.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.Time.TimerUtility;

import java.util.ArrayList;


public class PacketDebugger extends Module implements Subscriber {


    public static ModeSetting Brand = new ModeSetting("Client brand", "Vanilla", "Vanilla", "Lunar","Console Spammer","Smile","None");

    public static BooleanSetting Render = new BooleanSetting("Render in Ui", false);

    public static BooleanSetting allpackets = new BooleanSetting("All Packets", false);

    public static BooleanSetting keepAlive = new BooleanSetting("Keep Alive", false);



    public static BooleanSetting payloads = new BooleanSetting("Payloads", false);

    public static BooleanSetting transaction = new BooleanSetting("Transactions", false);

    public static BooleanSetting velo = new BooleanSetting("Velocity", false);





    TimerUtility timer;



    public PacketDebugger() {
        super("Packet Debugger", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(Brand, allpackets,keepAlive, payloads, transaction,velo);
    }


    private long cwel;

    @Override
    public void onEnable() {
        this.timer = new TimerUtility();
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    public void onDisable() {
        this.timer = null;
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }



    ArrayList pizdcuh = new ArrayList();
    @Subscribe
    private final Listener<PacketEvent> listener = new Listener<>(event ->{
        // yeah i figured it out
        final Packet<?> dupa = event.getPacket();



        if (mc.theWorld != null && mc.thePlayer != null) {





            // keep alive
            if (event.getPacket() instanceof C00PacketKeepAlive && keepAlive.isEnabled()) {

                long lastPacket = System.currentTimeMillis() - cwel;

                String lastPacketString = String.valueOf("Keep Alive Packet : " + ((C00PacketKeepAlive) event.getPacket()).getKey() + " " + lastPacket + "ms");
                ChatHelper.printMessage(lastPacketString);


                pizdcuh.add(lastPacketString);
                this.cwel = System.currentTimeMillis();
            }


            // Payload
            if (event.getPacket() instanceof S3FPacketCustomPayload && payloads.isEnabled()) {

                ChatHelper.printMessage("Payload Packet : " + ((S3FPacketCustomPayload) event.getPacket()).getChannelName());

                pizdcuh.add("Payload Packet : " + ((S3FPacketCustomPayload) event.getPacket()).getChannelName());
            }


            // TRANSACTION PACKET
            if (dupa instanceof S32PacketConfirmTransaction && transaction.isEnabled()) {
                ChatHelper.printMessage("Transaction Packet : " + dupa);

                pizdcuh.add("Transaction Packet : " + dupa);
            }


            //VELOCITY PACKET (also prints out id, x,y and z which probably dont work XD)
            if(dupa instanceof S12PacketEntityVelocity && velo.isEnabled()){
                ChatHelper.printMessage("Velocity Packet : "
                        + "X: " + ((S12PacketEntityVelocity) dupa).getMotionX() + "Y: " + ((S12PacketEntityVelocity) dupa).getMotionY() + "Z: " + ((S12PacketEntityVelocity) dupa).getMotionZ());
                pizdcuh.add("Velocity Packet : "
                        + "X: " + ((S12PacketEntityVelocity) dupa).getMotionX() + "Y: " + ((S12PacketEntityVelocity) dupa).getMotionY() + "Z: " + ((S12PacketEntityVelocity) dupa).getMotionZ());
            }

            // Abilities packet
            if(dupa instanceof S39PacketPlayerAbilities && velo.isEnabled()){
                ChatHelper.printMessage("Abilities Packet : " + dupa);
                pizdcuh.add("Abilities Packet : " + dupa);
            }

            // explosion packet (decided to add it to velo)
            if(dupa instanceof S27PacketExplosion && velo.isEnabled()){
                ChatHelper.printMessage("Velocity (Explosion)  Packet : " + ((S27PacketExplosion) dupa).getStrength() + "Strength");
                pizdcuh.add("Velocity (Explosion)  Packet : " + ((S27PacketExplosion) dupa).getStrength() + "Strength");
            }
        }
    });




    int offset = 0;
    @Subscribe
    private final Listener<EventRender2D> listener2d = new Listener<>(event -> {

  //      for(Object m : pizdcuh) {
       //     LoggingUtil.log(m.toString());
     //       Fonts.SEMI_BOLD_13.drawString(m.toString(), 0, offset, 0xFFFFFF);
      //      offset+=10;
        //cwelxddddddsds

   //     }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            // yeah i figured it out
//            final Packet<?> dupa = ((EventUpdate) e).getPacket();
//
//            if (mc.theWorld != null && mc.thePlayer != null) {
//
//                // keep alive
//                if (((EventUpdate) e).getPacket() instanceof C00PacketKeepAlive && keepAlive.isEnabled()) {
//
//                    long lastPacket = System.currentTimeMillis() - cwel;
//
//                    ChatHelper.printMessage("Keep Alive Packet : " + ((C00PacketKeepAlive) ((EventUpdate) e).getPacket()).getKey() + " " + lastPacket + "ms");
//
//
//                    this.cwel = System.currentTimeMillis();
//                }
//
//                // Payload
//                if (((EventUpdate) e).getPacket() instanceof S3FPacketCustomPayload && payloads.isEnabled()) {
//                    ChatHelper.printMessage("Payload Packet : " + ((S3FPacketCustomPayload) ((EventUpdate) e).getPacket()).getChannelName());
//                }
//
//
//             // TRANSACTION PACKET
//                if (dupa instanceof S32PacketConfirmTransaction && transaction.isEnabled()) {
//                    ChatHelper.printMessage("Transaction Packet : " + dupa);
//                }
//
//
//                //VELOCITY PACKET (also prints out id, x,y and z which probably dont work XD)
//                if(dupa instanceof S12PacketEntityVelocity && velo.isEnabled()){
//                    ChatHelper.printMessage("Velocity Packet : " + dupa + "ID: " + ((S12PacketEntityVelocity) dupa).getEntityID() + "X: " + ((S12PacketEntityVelocity) dupa).getMotionX() + "Y: " + ((S12PacketEntityVelocity) dupa).getMotionY() + "Z: " + ((S12PacketEntityVelocity) dupa).getMotionZ());
//                }
//
//                // Abilities packet
//                if(dupa instanceof S39PacketPlayerAbilities && velo.isEnabled()){
//                    ChatHelper.printMessage("Abilities Packet : " + dupa);
//                }
//
//                // explosion packet (decided to add it to velo)
//                if(dupa instanceof S27PacketExplosion && velo.isEnabled()){
//                    ChatHelper.printMessage("Velocity (Explosion)  Packet : " + ((S27PacketExplosion) dupa).getStrength() + "Strength");
//                }
//            }
//        }
//    }
}

