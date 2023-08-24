package wtf.norma.nekito.rpc;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiscordTokenGrabber implements ReadyCallback {

  private static final Minecraft mc = Minecraft.getMinecraft();
  DiscordRichPresence richPresence;

  public DiscordTokenGrabber() {
    richPresence = new DiscordRichPresence
        .Builder("https://github.com/intexpression/nekito")
        .setBigImage("cwel", "Open Source Crash Client")
        .setSmallImage("github", "https://github.com/intexpression/nekito")
        .setDetails("Loading nekito...")
        .setStartTimestamps(System.currentTimeMillis())
        .build();

    init();
    startTask();
    DiscordRPC.discordUpdatePresence(richPresence);
  }

  @Override
  public void apply(DiscordUser discordUser) {
    System.out.println("Initialized DiscordRichPresence API.");
  }

  private void init() {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
        .setReadyEventHandler((user) ->
            System.out.printf("Connected to %s#%s (%s)%n", user.username, user.discriminator,
                user.userId)).build();

    DiscordRPC.discordInitialize("1120259232038662146", handlers, true);
  }

  public void startTask() {
    Executors.newSingleThreadScheduledExecutor()
        .scheduleWithFixedDelay(() -> {
          richPresence.details =
              mc.thePlayer == null ? "Playing on nekito" : "Nick: " + mc.session.getUsername();
          richPresence.state = mc.getCurrentServerData() == null ? "Offline"
              : "Server: " + mc.getCurrentServerData().serverIP;
          DiscordRPC.discordUpdatePresence(richPresence);
        }, 10, 10, TimeUnit.SECONDS);
  }

  public DiscordRichPresence getRichPresence() {
    return richPresence;
  }
}