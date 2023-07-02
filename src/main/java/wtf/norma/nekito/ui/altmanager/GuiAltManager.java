package wtf.norma.nekito.ui.altmanager;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.RandomStringUtils;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.util.player.NameUtil;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.net.URI;

public final class GuiAltManager extends GuiScreen {
    private GuiTextField password;
    private final GuiScreen previousScreen;
    private GuiTextField username;
    private AltLoginThread thread;

    private String crackedStatus;



    // 30% of this is from sleek cuz im lazy af

    public GuiAltManager(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        try {
            switch (button.id) {
                case 1:
                    mc.displayGuiScreen(previousScreen);
                    break;

                case 0:
                    thread = new AltLoginThread(username.getText(), "");
                    thread.start();
                    break;



                case 3:
                    thread = null;
                    thread = new AltLoginThread(RandomStringUtils.random(14, true, true), "");
                    thread.start();
                    break;

                case 4:

                    break;

                case 5:

                    break;
                case 6:
                    thread = null;
                    thread = new AltLoginThread(NameUtil.generateName(), "");
                    thread.start();
                    break;

                default:
                    break;
            }
        } catch (Throwable var11) {
            //REMOVE ME LATER: throw new RuntimeException();
        }
    }

    @Override
    public void drawScreen(int x, int y2, float z) {
        final FontRenderer font = mc.fontRendererObj;
        ScaledResolution scaledResolution = new ScaledResolution(mc);


        drawDefaultBackground();
        username.drawTextBox();

        this.drawCenteredString(font, "Account Login", (int) (width / 2F), 20, -1);
        this.drawCenteredString(font, thread == null ? (crackedStatus == null ? EnumChatFormatting.GRAY + "Idle" : EnumChatFormatting.GREEN + crackedStatus) : thread.getStatus(),
                (int) (width / 2f),
                29,
                -1);
        if (username.getText().isEmpty()) {
            font.drawStringWithShadow("Username", width / 2F - 96, 66, -7829368);
        }


        super.drawScreen(x, y2, z);
    }

    @Override
    public void initGui() {
        int var3 = height / 4 + 24;
        buttonList.add(new GuiButton(0, width / 2 - 100, var3 + 72 + 12, "Login"));
        buttonList.add(new GuiButton(1, width / 2 - 100, var3 + 72 + 12 + 24, I18n.format("gui.cancel")));
        buttonList.add(new GuiButton(3, width / 2 - 100, var3 + 72 + 12 + 48 + 24, "Generate Cracked Account"));
        username = new GuiTextField(var3, mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
        username.setFocused(true);
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    protected void keyTyped(char character, int key) {
        try {
            super.keyTyped(character, key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (character == '\t') {
            if (!username.isFocused() && !password.isFocused()) {
                username.setFocused(true);
            } else {
                username.setFocused(password.isFocused());
                password.setFocused(!username.isFocused());
            }
        }
        if (character == '\r') {
            actionPerformed(buttonList.get(0));
        }
        username.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y2, int button) {
        try {
            super.mouseClicked(x, y2, button);
        } catch (IOException e) {
            e.printStackTrace();
        }
        username.mouseClicked(x, y2, button);

    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        username.updateCursorCounter();

    }
}
