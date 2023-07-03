package wtf.norma.nekito.ui.checkhost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.ui.checkhost.results.CheckHostHttpResult;
import wtf.norma.nekito.ui.checkhost.results.CheckHostTcpResult;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.other.GeoUtils;
import wtf.norma.nekito.util.other.ProtocolVersionUtils;
import wtf.norma.nekito.util.shader.GLSL;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.Map;

// thanks bettercraft for this

public class GuiCheckHost extends GuiScreen {

    // EDITED CLIENT GUI MODS SERVERPINGER
    private ServerData serverData;
    // EDITED CLIENT GUI MODS SERVERPINGER

    // EDITED CLIENT GUI MODS BRAND
    String lastAddress = "Pinging...";
    private volatile String addressPort;
    public long init;
    // EDITED CLIENT GUI MODS GEO

    // EDITED CLIENT GUI MODS GEO

    public GuiTextField inputIp;
    public String mode;
    public CheckResult<Map<CheckHostServer, CheckHostHttpResult>> httpResult;
    public CheckResult<Map<CheckHostServer, CheckHostTcpResult>> tcpResult;
    public int time;

    public GuiScreen before;

    public GuiCheckHost(final GuiScreen screen) {
        this.mode = "";
        this.time = 0;
        this.before = screen;
        this.serverData = new ServerData("", "", false);
    }

    @Override
    public void updateScreen() {
        this.inputIp.updateCursorCounter();
        this.buttonList.get(1).displayString = ((this.mode == "tcp") ? "TCP" : "TCP");
        this.buttonList.get(2).displayString = ((this.mode == "http") ? "HTTP" : "HTTP");
    }

    @Override
    public void initGui() {
        init = System.currentTimeMillis();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width - 70, this.height - 30, 60, 20, "Back"));
        this.buttonList
                .add(new GuiButton(2, this.width - 144 + 74, 54, 60, 20, (this.mode == "tcp") ? "TCP" : "TCP"));
        this.buttonList
                .add(new GuiButton(3, this.width - 144 + 10, 54, 60, 20, (this.mode == "http") ? "HTTP" : "HTTP"));
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

        Gui.drawRect(8, 29, 400, 30, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
        Gui.drawRect(8, 291, 400, 290, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
        for (int i = 0; i < 26; ++i) {
            Gui.drawRect(10, 30 + i * 10, 400, 30 + (i + 1) * 10, Integer.MIN_VALUE);
            Gui.drawRect(8, 30 + i * 10, 9, 30 + (i + 1) * 10, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(399, 30 + i * 10, 400, 30 + (i + 1) * 10, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(135, 30 + i * 10, 136, 30 + (i + 1) * 10, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
            Gui.drawRect(240, 30 + i * 10, 241, 30 + (i + 1) * 10, ColorUtility.rainbowEffect(0L, 1.0f).getRGB());
        }

        this.inputIp.drawTextBox();
        if (this.time <= 550) {
            this.time += 5;
        } else {
            this.time = 0;
        }
        if (this.time == 550) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String mode;
                    switch (mode = GuiCheckHost.this.mode) {
                        case "tcp": {
                            if (GuiCheckHost.this.inputIp.getText().isEmpty()) {
                                return;
                            }
                            try {
                                GuiCheckHost.this.tcpResult = CheckHostAPI
                                        .createTcpRequest(GuiCheckHost.this.inputIp.getText(), 100);
                            } catch (IOException ex) {
                            }
                            break;
                        }

                        case "http": {
                            if (GuiCheckHost.this.inputIp.getText().isEmpty()) {
                                return;
                            }
                            try {
                                GuiCheckHost.this.httpResult = CheckHostAPI.createHttpRequest(GuiCheckHost.this.inputIp.getText(), 100);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            }).start();
        }
        int y = 32;

        if (this.mode.isEmpty()) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Waiting...", 10.0f, 18.0f, -1);


        } else if (this.mode.equals("http") && this.httpResult != null) {
            for (final CheckHostServer r : this.httpResult.getServers()) {
                final CheckHostHttpResult endResult = this.httpResult.getResult().get(r);
                if (endResult != null) {
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("City/Country", 10.0f, 18.0f,
                            -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Time", 150.0f, 18.0f, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Code", 265.0f, 18.0f, -1);
                    final DecimalFormat fm = new DecimalFormat("00.##");
                    final String pingFormat = fm.format(endResult.getPing());
                    Minecraft.getMinecraft().fontRendererObj
                            .drawStringWithShadow("" + r.getCity() + "," + r.getCountryCode(), 10.0f, (float) y, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("" + pingFormat + " seconds",
                            150.0f, (float) y, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("" + endResult.getStatus(), 265.0f,
                            (float) y, -1);
                    final String file = "textures/gui/flags/" + r.getCountryCode() + ".png";
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(file));
                    Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 90, y, 0.0f, 0.0f, 8, 8, 8,
                            8);
                    y += 10;

                    try {
                        ServerData sd = serverData;
                        String version = sd.gameVersion;
                        String protocolVersion = "" + sd.version;
                        String ping = "" + sd.pingToServer;

                        new Thread(() -> {
                            try {
                                ServerAddress serveradress = ServerAddress.resolveAddress(sd.serverIP);
                                String adress = InetAddress.getByName(serveradress.getIP()).getHostAddress();
                                if (!lastAddress.equals(adress)) {
                                    lastAddress = adress;
                                    new GeoUtils(adress);
                                    addressPort = (InetAddress.getByName(serveradress.getIP()).getHostAddress() + " "
                                            + serveradress.getPort());

                                    return;
                                }
                            } catch (Exception ignored) {
                            }
                        }, "PingThread-").start();

                        int heigh = 150;
                        int width = (this.width / 2) + 275;
                        int adder = 12;

                        int heigh1 = 100;

                        //RenderUtils.drawBorderedRect(width - 5, heigh - 50, width * 3.25, 450,
                        //ColorUtils.rainbowEffect(0L, 1.0f).getRGB(), Integer.MIN_VALUE);
                        if (version.equalsIgnoreCase("1.12.2") && sd.pingToServer < 0L) {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Brand: Pinging...", width, heigh1, -1);
                        } else {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Brand: " + version
                                            .replaceAll("1.8.x, 1.9.x, 1.10.x, 1.11.x, 1.12.x, 1.13.x, 1.14.x, 1.15.x, 1.16.x, 1.17.x, 1.18.x, 1.19.x",
                                                    "1.8.x-1.19.x")
                                            .replaceAll("1.7.x, ", "").replaceAll(
                                                    "PE-1.8.x, PE-1.9.x, PE-1.10.x, PE-1.11.x, PE-1.12.x, PE-1.13.x, PE-1.14.x, PE-1.15.x, PE-1.16.x, PE-1.17.x, PE-1.18.x, PE-1.19.x",
                                                    "PE-1.8.x - PE-1.19.x"),
                                    width, heigh1, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Protocol: Pinging...", width, heigh1, -1);
                        } else {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Protocol: " + protocolVersion + " -> "
                                    + ProtocolVersionUtils.getInstance().getKnownAs(sd.version), width, heigh1, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("IP: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("IP: " + addressPort.replace("null", "").replace("127.0.0.1 25565", "Pinging..."), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ORG: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ORG: " + GeoUtils.getInstance().getORG()
                                    .replaceAll("- Connecting your World!", "").replaceAll("Cloud ", "")
                                    .replaceAll("- DDoS-Protected Gameservers and more", "").replaceAll("www.", "")
                                    .replaceAll("Corp.", "").replaceAll("(haftungsbeschraenkt) & Co. KG", "")
                                    .replaceAll("trading as Gericke KG", ""), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("AS: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("AS: " + GeoUtils.getInstance().getAS()
                                            .replaceAll("Center ", "").replaceAll("oration", "")
                                            .replaceAll("Waldecker trading as LUMASERV Systems", "").replaceAll("GmbH", "")
                                            .replaceAll(". Inc.", "").replaceAll(", LLC", "").replaceAll("Corp.", "")
                                            .replaceAll("TeleHost", "Tele").replaceAll("e-commerce", "")
                                            .replaceAll("IT Services & Consulting", "").replaceAll("UG (haftungsbeschrankt)", ""),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Country: Pinging,,,", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Country: " + GeoUtils.getInstance().getCOUNTRY(),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("City: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("City: " + GeoUtils.getInstance().getCITY(), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Region: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "Region: " + GeoUtils.getInstance().getREGIONNAME(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ISP: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ISP: " + GeoUtils.getInstance().getISP(), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Timezone: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "Timezone: " + GeoUtils.getInstance().getTIMEZONE(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("CountryCode: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "CountryCode: " + GeoUtils.getInstance().getCOUNTRYCODE(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Proxy: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Proxy: " + GeoUtils.getInstance().getPROXY(),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Reverse: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Reverse: " + GeoUtils.getInstance().getREVERSE(),
                                    width, heigh, -1);
                        }
                    }

                    catch (Throwable ignored) {
                        // empty catch block
                    }
                    // EDITED CLIENT GUI MODS GEO
                }
            }
        } else if (this.mode.equals("tcp") && this.tcpResult != null) {
            for (final CheckHostServer r : this.tcpResult.getServers()) {
                final CheckHostTcpResult endResult3 = this.tcpResult.getResult().get(r);
                if (endResult3 != null) {
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("City/Country", 10.0f, 18.0f,
                            -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Time", 150.0f, 18.0f, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Server", 265.0f, 18.0f, -1);
                    final DecimalFormat fm = new DecimalFormat("00.##");
                    final String pingFormat = fm.format(endResult3.getPing());
                    Minecraft.getMinecraft().fontRendererObj
                            .drawStringWithShadow("" + r.getCity() + "," + r.getCountryCode(), 10.0f, (float) y, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("" + pingFormat + " seconds",
                            150.0f, (float) y, -1);
                    Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("" + r.getName(), 265.0f, (float) y,
                            -1);
                    final String file = "textures/gui/flags/" + r.getCountryCode() + ".png";
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(file));
                    Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 90, y, 0.0f, 0.0f, 8, 8, 8,
                            8);
                    y += 10;
                    try {
                        ServerData sd = serverData;
                        String version = sd.gameVersion;
                        String protocolVersion = "" + sd.version;
                        String ping = "" + sd.pingToServer;

                        new Thread(() -> {
                            try {
                                ServerAddress serveradress = ServerAddress.resolveAddress(sd.serverIP);
                                String adress = InetAddress.getByName(serveradress.getIP()).getHostAddress();
                                if (!lastAddress.equals(adress)) {
                                    lastAddress = adress;
                                    new GeoUtils(adress);
                                    addressPort = (InetAddress.getByName(serveradress.getIP()).getHostAddress() + " "
                                            + serveradress.getPort());

                                    return;
                                }
                            } catch (Exception ignored) {
                            }
                        }, "PingThread-").start();

                        int heigh = 150;
                        int width = (this.width / 2) + 275;
                        int adder = 12;

                        int heigh1 = 100;

						/*
						RenderUtils.drawBorderedRect(width - 5, heigh - 50, width * 1.15, 300,
								ColorUtils.rainbowEffect(0L, 1.0f).getRGB(), Integer.MIN_VALUE);
								*/
                        if (version.equalsIgnoreCase("1.12.2") && sd.pingToServer < 0L) {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Brand: Pinging...", width, heigh1, -1);
                        } else {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Brand: " + version
                                            .replaceAll("1.8.x, 1.9.x, 1.10.x, 1.11.x, 1.12.x, 1.13.x, 1.14.x, 1.15.x, 1.16.x",
                                                    "1.8.x-1.16.x")
                                            .replaceAll("1.7.x, ", "").replaceAll(
                                                    "PE-1.8.x, PE-1.9.x, PE-1.10.x, PE-1.11.x, PE-1.12.x, PE-1.13.x, PE-1.14.x, PE-1.15.x, PE-1.16.x",
                                                    "PE-1.8.x - PE-1.16.x"),
                                    width, heigh1, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Protocol: Pinging...", width, heigh1, -1);
                        } else {
                            heigh1 += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Protocol: " + protocolVersion + " -> "
                                    + ProtocolVersionUtils.getInstance().getKnownAs(sd.version), width, heigh1, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("IP: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("IP: " + addressPort.replace("null", "").replace("127.0.0.1 25565", "Pinging..."), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ORG: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ORG: " + GeoUtils.getInstance().getORG()
                                    .replaceAll("- Connecting your World!", "").replaceAll("Cloud ", "")
                                    .replaceAll("- DDoS-Protected Gameservers and more", "").replaceAll("www.", "")
                                    .replaceAll("Corp.", "").replaceAll("(haftungsbeschraenkt) & Co. KG", "")
                                    .replaceAll("trading as Gericke KG", ""), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("AS: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("AS: " + GeoUtils.getInstance().getAS()
                                            .replaceAll("Center ", "").replaceAll("oration", "")
                                            .replaceAll("Waldecker trading as LUMASERV Systems", "").replaceAll("GmbH", "")
                                            .replaceAll(". Inc.", "").replaceAll(", LLC", "").replaceAll("Corp.", "")
                                            .replaceAll("TeleHost", "Tele").replaceAll("e-commerce", "")
                                            .replaceAll("IT Services & Consulting", "").replaceAll("UG (haftungsbeschrankt)", ""),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Country: Pinging,,,", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Country: " + GeoUtils.getInstance().getCOUNTRY(),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("City: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("City: " + GeoUtils.getInstance().getCITY(), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Region: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "Region: " + GeoUtils.getInstance().getREGIONNAME(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ISP: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("ISP: " + GeoUtils.getInstance().getISP(), width,
                                    heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Timezone: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "Timezone: " + GeoUtils.getInstance().getTIMEZONE(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("CountryCode: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow(
                                    "CountryCode: " + GeoUtils.getInstance().getCOUNTRYCODE(), width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Proxy: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Proxy: " + GeoUtils.getInstance().getPROXY(),
                                    width, heigh, -1);
                        }

                        if (sd.pingToServer < 0L) {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Reverse: Pinging...", width, heigh, -1);
                        } else {
                            heigh += adder;
                            this.mc.fontRendererObj.drawStringWithShadow("Reverse: " + GeoUtils.getInstance().getREVERSE(),
                                    width, heigh, -1);
                        }
                    }

                    catch (Throwable ignored) {
                        // empty catch block
                    }
                    // EDITED CLIENT GUI MODS GEO
                }
            }
        }

        this.mc.fontRendererObj.drawString("Server IP", this.width - 100, 20, -1);
        super.drawScreen(par1, par2, par3);
    }

    public void drawBackground() {
        GL11.glPushMatrix();
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
            GLSL.MAINMENU.useShader(width,height, 0, 0, (System.currentTimeMillis() - init) / 1000f);


            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2f(0,0);
                GL11.glVertex2f(0,height);
                GL11.glVertex2f(width,height);
                GL11.glVertex2f(width,0);
                GL11.glEnd();
            }
            GL20.glUseProgram(0);
        }
        GL11.glPopMatrix();
    }
}
