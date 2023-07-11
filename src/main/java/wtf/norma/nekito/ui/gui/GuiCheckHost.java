package wtf.norma.nekito.ui.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.helper.network.GeoHelper;
import wtf.norma.nekito.helper.network.ProtocolVersionHelper;
import wtf.norma.nekito.helper.network.checkhost.CheckHost;
import wtf.norma.nekito.helper.network.checkhost.result.CheckHostHttpResult;
import wtf.norma.nekito.helper.network.checkhost.result.CheckHostTcpResult;
import wtf.norma.nekito.helper.network.checkhost.server.CheckHostServer;
import wtf.norma.nekito.helper.render.ColorHelper;
import wtf.norma.nekito.helper.shader.GLSL;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GuiCheckHost extends GuiScreen {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.##");
    private final AtomicReference<Object> result = new AtomicReference<>();
    private final AtomicReference<GeoHelper> geoLocation = new AtomicReference<>();
    public long init;
    public GuiTextField inputIp;
    public String mode;
    public int time;
    public GuiScreen before;
    String lastAddress = "Pinging...";
    private ServerData serverData;
    private volatile String addressPort;
    private String lastType;

    public GuiCheckHost(final GuiScreen screen) {
        this.mode = "";
        this.time = 0;
        this.before = screen;
        this.serverData = new ServerData("", "", false);
    }

    @Override
    public void updateScreen() {
        this.inputIp.updateCursorCounter();
        this.buttonList.get(1).displayString = ("TCP");
        this.buttonList.get(2).displayString = ("HTTP");
    }

    @Override
    public void initGui() {
        init = System.currentTimeMillis();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width - 70, this.height - 30, 60, 20, "Back"));
        this.buttonList
                .add(new GuiButton(2, this.width - 144 + 74, 54, 60, 20, "TCP"));
        this.buttonList
                .add(new GuiButton(3, this.width - 144 + 10, 54, 60, 20, "HTTP"));
        (this.inputIp = new GuiTextField(0, this.fontRendererObj, this.width - 134, 30, 124, 20))
                .setMaxStringLength(65535);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(final GuiButton clickedButton) {
        switch (clickedButton.id) {
            case 1: {
                mc.displayGuiScreen(before);
                break;
            }
            case 2: {
                this.mode = "tcp";
                break;
            }
            case 3: {
                this.mode = "http";
                break;
            }
        }
    }

    @Override
    protected void keyTyped(final char par1, final int par2) {
        if (par2 == 28 || par2 == 156) {
            this.actionPerformed(this.buttonList.get(0));
        }
        this.inputIp.textboxKeyTyped(par1, par2);

        serverData = new ServerData(this.inputIp.getText(), this.inputIp.getText(), false);
    }

    @Override
    protected void mouseClicked(final int par1, final int par2, final int par3) throws IOException {
        super.mouseClicked(par1, par2, par3);
        this.inputIp.mouseClicked(par1, par2, par3);
    }

    @Override
    public void drawScreen(final int par1, final int par2, final float par3) {
        drawBackground();

        Gui.drawRect(8, 29, 400, 30, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
        Gui.drawRect(8, 291, 400, 290, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
        for (int i = 0; i < 26; ++i) {
            Gui.drawRect(10, 30 + i * 10, 400, 30 + (i + 1) * 10, Integer.MIN_VALUE);
            Gui.drawRect(8, 30 + i * 10, 9, 30 + (i + 1) * 10, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(399, 30 + i * 10, 400, 30 + (i + 1) * 10, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(135, 30 + i * 10, 136, 30 + (i + 1) * 10, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(240, 30 + i * 10, 241, 30 + (i + 1) * 10, ColorHelper.rainbowEffect(0L, 1.0f).getRGB());
        }

        this.inputIp.drawTextBox();
        if (this.time <= 550) {
            this.time += 5;
        } else {
            this.time = 0;
        }
        if (this.time == 550) {
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    ServerAddress serverAddress = ServerAddress.resolveAddress(serverData.serverIP);
                    String hostAddress = InetAddress.getByName(serverAddress.getIP()).getHostAddress();

                    if (!hostAddress.equals(lastType)) {
                        this.geoLocation.set(GeoHelper.request(hostAddress));
                        addressPort = (InetAddress.getByName(serverAddress.getIP()).getHostAddress() + " " + serverAddress.getPort());
                        lastAddress = hostAddress;
                    }

                    if (mode.equals("http")) {
                        this.result.set(new CheckHost(GuiCheckHost.this.inputIp.getText()).http());
                    } else if (mode.equals("tcp")) {
                        this.result.set(new CheckHost(GuiCheckHost.this.inputIp.getText()).tcp());
                    }

                    this.lastType = mode;
                } catch (Exception ignored) {
                }
            });
        }
        AtomicInteger y = new AtomicInteger(32);

        if (this.mode.isEmpty()) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Waiting...", 10.0f, 18.0f, -1);
        } else if (this.mode.equals("http") && mode.equals(lastType) && this.result.get() != null) {
            Map<CheckHostServer, CheckHostHttpResult> results = (Map<CheckHostServer, CheckHostHttpResult>) this.result.get();
            GeoHelper geoLocation = this.geoLocation.get();
            results.forEach((server, httpResult) -> {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("City/Country", 10.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Time", 150.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Code", 265.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(server.getCity() + "," + server.getCode(), 10.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(DECIMAL_FORMAT.format(httpResult.getTime()) + " seconds", 150.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(httpResult.getMessage(), 265.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/flags/" + server.getCode() + ".png"));
                Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 90, y.get(), 0.0f, 0.0f, 8, 8, 8, 8);
                y.addAndGet(10);

                int heigh = 150;
                int width = (this.width / 2) + 275;
                int adder = 12;
                int heigh1 = 100;

                long pingToServer = serverData.pingToServer;
                boolean pingNotAvailable = pingToServer < 0L;

                heigh1 += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Brand: Pinging..." : "Brand: " + serverData.gameVersion
                        .replaceAll("1.8.x, 1.9.x, 1.10.x, 1.11.x, 1.12.x, 1.13.x, 1.14.x, 1.15.x, 1.16.x, 1.17.x, 1.18.x, 1.19.x",
                                "1.8.x-1.19.x")
                        .replaceAll("1.7.x, ", "").replaceAll(
                                "PE-1.8.x, PE-1.9.x, PE-1.10.x, PE-1.11.x, PE-1.12.x, PE-1.13.x, PE-1.14.x, PE-1.15.x, PE-1.16.x, PE-1.17.x, PE-1.18.x, PE-1.19.x",
                                "PE-1.8.x - PE-1.19.x"), width, heigh1, -1);

                heigh1 += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Protocol: Pinging..." : "Protocol: " + serverData.version + " -> "
                        + ProtocolVersionHelper.translate(serverData.version), width, heigh1, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "IP: Pinging..." : "IP: " + addressPort.replace("null", "").replace("127.0.0.1 25565", "Pinging..."), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "ORG: Pinging..." : "ORG: " + geoLocation.getOrg()
                        .replaceAll("- Connecting your World!", "").replaceAll("Cloud ", "")
                        .replaceAll("- DDoS-Protected Gameservers and more", "").replaceAll("www.", "")
                        .replaceAll("Corp.", "").replaceAll("(haftungsbeschraenkt) & Co. KG", "")
                        .replaceAll("trading as Gericke KG", ""), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "AS: Pinging..." : "AS: " + geoLocation.getAs()
                        .replaceAll("Center ", "").replaceAll("oration", "")
                        .replaceAll("Waldecker trading as LUMASERV Systems", "").replaceAll("GmbH", "")
                        .replaceAll(". Inc.", "").replaceAll(", LLC", "").replaceAll("Corp.", "")
                        .replaceAll("TeleHost", "Tele").replaceAll("e-commerce", "")
                        .replaceAll("IT Services & Consulting", "").replaceAll("UG (haftungsbeschrankt)", ""), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Country: Pinging..." : "Country: " + geoLocation.getCountry(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "City: Pinging..." : "City: " + geoLocation.getCity(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Region: Pinging..." : "Region: " + geoLocation.getRegionName(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "ISP: Pinging..." : "ISP: " + geoLocation.getIsp(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Timezone: Pinging..." : "Timezone: " + geoLocation.getTimeZone(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "CountryCode: Pinging..." : "CountryCode: " + geoLocation.getCountryCode(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Proxy: Pinging..." : "Proxy: " + geoLocation.getProxy(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Reverse: Pinging..." : "Reverse: " + geoLocation.getReverse(), width, heigh, -1);
            });
        } else if (this.mode.equals("tcp") && mode.equals(lastType) && this.result.get() != null) {
            Map<CheckHostServer, CheckHostTcpResult> results = (Map<CheckHostServer, CheckHostTcpResult>) this.result.get();
            GeoHelper geoLocation = this.geoLocation.get();
            results.forEach((server, httpResult) -> {
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("City/Country", 10.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Time", 150.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Server", 265.0f, 18.0f, -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(server.getCity() + "," + server.getCode(), 10.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(DECIMAL_FORMAT.format(httpResult.getTime()) + " seconds", 150.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(httpResult.getIpAddress(), 265.0f, (float) y.get(), -1);
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/flags/" + server.getCode() + ".png"));
                Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 90, y.get(), 0.0f, 0.0f, 8, 8, 8, 8);
                y.addAndGet(10);

                int heigh = 150;
                int width = (this.width / 2) + 275;
                int adder = 12;
                int heigh1 = 100;

                long pingToServer = serverData.pingToServer;
                boolean pingNotAvailable = pingToServer < 0L;

                heigh1 += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Brand: Pinging..." : "Brand: " + serverData.gameVersion
                        .replaceAll("1.8.x, 1.9.x, 1.10.x, 1.11.x, 1.12.x, 1.13.x, 1.14.x, 1.15.x, 1.16.x, 1.17.x, 1.18.x, 1.19.x",
                                "1.8.x-1.19.x")
                        .replaceAll("1.7.x, ", "").replaceAll(
                                "PE-1.8.x, PE-1.9.x, PE-1.10.x, PE-1.11.x, PE-1.12.x, PE-1.13.x, PE-1.14.x, PE-1.15.x, PE-1.16.x, PE-1.17.x, PE-1.18.x, PE-1.19.x",
                                "PE-1.8.x - PE-1.19.x"), width, heigh1, -1);

                heigh1 += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Protocol: Pinging..." : "Protocol: " + serverData.version + " -> "
                        + ProtocolVersionHelper.translate(serverData.version), width, heigh1, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "IP: Pinging..." : "IP: " + addressPort.replace("null", "").replace("127.0.0.1 25565", "Pinging..."), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "ORG: Pinging..." : "ORG: " + geoLocation.getOrg()
                        .replaceAll("- Connecting your World!", "").replaceAll("Cloud ", "")
                        .replaceAll("- DDoS-Protected Gameservers and more", "").replaceAll("www.", "")
                        .replaceAll("Corp.", "").replaceAll("(haftungsbeschraenkt) & Co. KG", "")
                        .replaceAll("trading as Gericke KG", ""), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "AS: Pinging..." : "AS: " + geoLocation.getAs()
                        .replaceAll("Center ", "").replaceAll("oration", "")
                        .replaceAll("Waldecker trading as LUMASERV Systems", "").replaceAll("GmbH", "")
                        .replaceAll(". Inc.", "").replaceAll(", LLC", "").replaceAll("Corp.", "")
                        .replaceAll("TeleHost", "Tele").replaceAll("e-commerce", "")
                        .replaceAll("IT Services & Consulting", "").replaceAll("UG (haftungsbeschrankt)", ""), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Country: Pinging..." : "Country: " + geoLocation.getCountry(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "City: Pinging..." : "City: " + geoLocation.getCity(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Region: Pinging..." : "Region: " + geoLocation.getRegionName(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "ISP: Pinging..." : "ISP: " + geoLocation.getIsp(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Timezone: Pinging..." : "Timezone: " + geoLocation.getTimeZone(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "CountryCode: Pinging..." : "CountryCode: " + geoLocation.getCountryCode(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Proxy: Pinging..." : "Proxy: " + geoLocation.getProxy(), width, heigh, -1);

                heigh += adder;
                this.mc.fontRendererObj.drawStringWithShadow(pingNotAvailable ? "Reverse: Pinging..." : "Reverse: " + geoLocation.getReverse(), width, heigh, -1);
            });
        }

        this.mc.fontRendererObj.drawString("Server IP", this.width - 100, 20, -1);
        super.drawScreen(par1, par2, par3);
    }

    public void drawBackground() {
        GL11.glPushMatrix();
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
            GLSL.MAIN_MENU.useShader(width, height, 0, 0, (System.currentTimeMillis() - init) / 1000f);


            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2f(0, 0);
                GL11.glVertex2f(0, height);
                GL11.glVertex2f(width, height);
                GL11.glVertex2f(width, 0);
                GL11.glEnd();
            }
            GL20.glUseProgram(0);
        }
        GL11.glPopMatrix();
    }


}
