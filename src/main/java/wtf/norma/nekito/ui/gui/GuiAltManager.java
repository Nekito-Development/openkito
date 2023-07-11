package wtf.norma.nekito.ui.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.helper.network.NetHelper;
import wtf.norma.nekito.helper.shader.GLSL;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public final class GuiAltManager extends GuiScreen {
    private final GuiScreen previousScreen;
    public long init;
    //private GuiTextField password;
    private GuiTextField username;

    public GuiAltManager(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                mc.displayGuiScreen(previousScreen);
                break;
            case 0:
                Executors.newSingleThreadExecutor().execute(() -> {
                    if (username.getText().isEmpty())
                        return;

                    NetHelper.createSession(username.getText(), null);
                });
                break;
            case 3:
            case 6:
                Executors.newSingleThreadExecutor().execute(() -> NetHelper.createSession("Uohhhhhhhhh" + ThreadLocalRandom.current().nextInt(Short.MAX_VALUE), null));
                break;
        }
    }

    @Override
    public void drawScreen(int x, int y2, float z) {
        final FontRenderer font = mc.fontRendererObj;

        drawBackground();
        username.drawTextBox();

        drawCenteredString(font, "Account Login", (int) (width / 2F), 20, -1);
        drawCenteredString(font, String.format("Logged as: %s%s", EnumChatFormatting.GREEN, mc.session.username), (int) (width / 2f), 29, -1);
//        drawCenteredString(font, thread == null ? (crackedStatus == null ? EnumChatFormatting.GRAY + "Idle" : EnumChatFormatting.GREEN + crackedStatus) : thread.getStatus(),
//                (int) (width / 2f),
//                29,
//                -1);

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
    protected void keyTyped(char character, int key) throws IOException {
        super.keyTyped(character, key);
//        if (character == '\t') {
//            if (!username.isFocused() /*&& !password.isFocused()*/) {
//                username.setFocused(true);
//            } else {
//                username.setFocused(password.isFocused());
//                password.setFocused(!username.isFocused());
//            }
//        }
        if (character == '\r') {
            actionPerformed(buttonList.get(0));
        }
        username.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y2, int button) throws IOException {
        super.mouseClicked(x, y2, button);
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
