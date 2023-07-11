package net.minecraft.client.multiplayer;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {

    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
    private static final Logger logger = LogManager.getLogger();
    private NetworkManager networkManager;
    private boolean cancel;
    private final GuiScreen previousGuiScreen;


    public static boolean ServerIP = true;
    public static boolean SRV = true;
    public static boolean Netty = true;
    public static boolean Resolving = true;
    public static boolean Connecting = true;
    public static boolean sendingloginpackets;
    public static boolean waitingforresponse;
    public static boolean verifyingsession;
    public static boolean encrypting;
    public static boolean sucess;
    public static String kickedMessage = "";
    public static boolean kicked = false;




    public GuiConnecting(GuiScreen p_i1181_1_, Minecraft mcIn, ServerData p_i1181_3_) {

        this.mc = mcIn;
        this.previousGuiScreen = p_i1181_1_;
        ServerAddress serveraddress = ServerAddress.func_78860_a(p_i1181_3_.serverIP);

        mcIn.loadWorld(null);
        mcIn.setServerData(p_i1181_3_);

        this.connect(serveraddress.getIP(), serveraddress.getPort());
    }

    public GuiConnecting(GuiScreen p_i1182_1_, Minecraft mcIn, String hostName, int port) {
        this.mc = mcIn;
        this.previousGuiScreen = p_i1182_1_;
        mcIn.loadWorld(null);
        this.connect(hostName, port);
    }

    private void connect(final String ip, final int port) {
        logger.info("Connecting to " + ip + ", " + port);
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet()) {
            public void run() {
                InetAddress inetaddress = null;
                try {
                    if (GuiConnecting.this.cancel) {
                        return;
                    }

                    inetaddress = InetAddress.getByName(ip);
                    GuiConnecting.this.networkManager = NetworkManager.func_181124_a(inetaddress, port, GuiConnecting.this.mc.gameSettings.func_181148_f());
                    GuiConnecting.this.networkManager.setNetHandler(new NetHandlerLoginClient(GuiConnecting.this.networkManager, GuiConnecting.this.mc, GuiConnecting.this.previousGuiScreen));
                    GuiConnecting.this.networkManager.sendPacket(new C00Handshake(47, ip, port, EnumConnectionState.LOGIN));
                    GuiConnecting.this.networkManager.sendPacket(new C00PacketLoginStart(GuiConnecting.this.mc.getSession().getProfile()));
                } catch (UnknownHostException unknownhostexception) {
                    if (GuiConnecting.this.cancel) {
                        return;
                    }

                    GuiConnecting.logger.error("Couldn't connect to server",
                        unknownhostexception);
                    GuiConnecting.this.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.this.previousGuiScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", "Unknown host")));
                } catch (Exception exception) {
                    if (GuiConnecting.this.cancel) {
                        return;
                    }

                    GuiConnecting.logger.error("Couldn't connect to server", exception);
                    String s = exception.toString();

                    if (inetaddress != null) {
                        String s1 = inetaddress.toString() + ":" + port;
                        s = s.replaceAll(s1, "");
                    }

                    GuiConnecting.this.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.this.previousGuiScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason",
                        s)));
                }
            }
        }).start();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        if (this.networkManager != null) {
            if (this.networkManager.isChannelOpen()) {
                this.networkManager.processReceivedPackets();
            } else {
                this.networkManager.checkDisconnected();
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl
     * Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is
     * displayed and when the window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.cancel = true;

            if (this.networkManager != null) {
                this.networkManager.closeChannel(new ChatComponentText("Aborted"));
            }

            this.mc.displayGuiScreen(this.previousGuiScreen);
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        String s2;

        this.drawDefaultBackground();

        int var4 = height / 4 + 120 + 12;

        Gui.drawRect(width / 2 - 100, var4 - 130, width / 2 + 100, var4 - 20, -16777216);



        switch ((int) (Minecraft.getSystemTime() / 300L % 4L)) {

            default: {
                s2 = "\u00a77_";
                break;
            }
            case 1:

            case 3: {
                s2 = "";
                break;
            }
            case 2: {
                s2 = "\u00a77_";
            }
        }

        int yPos = var4 - 86;
        this.drawString(this.fontRendererObj, "\u00a7c" + kickedMessage, 5, 5, Color.RED.darker().getRGB());

        //author bettercraft
        if (ServerIP) {
            this.drawString(this.fontRendererObj,
                    "Conneting to " + Minecraft.getMinecraft().getCurrentServerData().serverIP+ "...", width / 2 - 95 - 1,
                    var4 - 126, Color.WHITE.getRGB());

            if (SRV) {
                this.drawString(this.fontRendererObj, "Resolving SRV...", width / 2 - 95 - 1, var4 - 116,
                        Color.RED.darker().getRGB());

                if (Netty) {
                    this.drawString(this.fontRendererObj, "Starting Netty Connection...", width / 2 - 95 - 1,
                            var4 - 106, Color.RED.darker().getRGB());

                    if (Resolving) {
                        this.drawString(this.fontRendererObj, "Resolving IP...", width / 2 - 95 - 1, var4 - 96,
                                Color.RED.darker().getRGB());

                        if (Connecting) {
                            this.drawString(this.fontRendererObj, "Connecting...", width / 2 - 95 - 1, var4 - 86,
                                    Color.YELLOW.darker().getRGB());

                            if (sendingloginpackets) {
                                this.drawString(this.fontRendererObj, "Sending Login Packets...", width / 2 - 95 - 1,
                                        var4 - 76, Color.YELLOW.darker().getRGB());
                                yPos = var4 - 76;

                                if (waitingforresponse) {
                                    this.drawString(this.fontRendererObj, "Waiting for response...", width / 2 - 95 - 1,
                                            var4 - 66, Color.YELLOW.darker().getRGB());
                                    yPos = var4 - 66;

                                    if (verifyingsession) {
                                        this.drawString(this.fontRendererObj, "Verifying Session...",
                                                width / 2 - 95 - 1, var4 - 56, Color.GREEN.darker().getRGB());
                                        this.drawString(this.fontRendererObj, "Encrypting...", width / 2 - 95 - 1,
                                                var4 - 46, Color.GREEN.darker().getRGB());
                                        yPos = var4 - 46;

                                        if (sucess) {
                                            this.drawString(this.fontRendererObj, "Success!", width / 2 - 95 - 1,
                                                    var4 - 36, Color.GREEN.darker().getRGB());
                                            yPos = var4 - 36;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.drawString(this.fontRendererObj, s2, width / 2 - 95 - 1, yPos + 6, Color.GREEN.darker().getRGB());

                super.drawScreen(mouseX, mouseY, partialTicks);
            }
        }
    }
}
