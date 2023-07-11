package wtf.norma.nekito.ui.botting;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

import java.io.IOException;


public final class BotterUI extends GuiScreen {
    private final GuiScreen previousScreen;
    private GuiTextField PORT;
    private GuiTextField ip;
    private Botter thread;
    private String crackedStatus;


    public BotterUI(GuiScreen previousScreen) {
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
                    thread = new Botter(ip.getText(), PORT.getText());
                    Botter.start(ip.getText(), PORT.getText());
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

        //Gui.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), new Color(50, 50, 50).getRGB());
        drawDefaultBackground();
        ip.drawTextBox();
        PORT.drawTextBox();
        drawCenteredString(font, "Botter", (int) (width / 2F), 20, -1);
        if (ip.getText().isEmpty()) {
            font.drawStringWithShadow("IP", width / 2F - 96, 66, -7829368);
        }
        if (PORT.getText().isEmpty()) {
            font.drawStringWithShadow("PORT", width / 2F - 96, 106, -7829368);
        }
        super.drawScreen(x, y2, z);
    }

    @Override
    public void initGui() {
        int var3 = height / 4 + 24;
        buttonList.add(new GuiButton(0, width / 2 - 100, var3 + 72 + 12, "Sent"));
        buttonList.add(new GuiButton(1, width / 2 - 100, var3 + 72 + 12 + 24, I18n.format("gui.cancel")));


        ip = new GuiTextField(var3, mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
        PORT = new GuiTextField(var3, mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
        ip.setFocused(true);
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
            if (!ip.isFocused() && !PORT.isFocused()) {
                ip.setFocused(true);
            } else {
                ip.setFocused(PORT.isFocused());
                PORT.setFocused(!ip.isFocused());
            }
        }
        if (character == '\r') {
            actionPerformed(buttonList.get(0));
        }
        ip.textboxKeyTyped(character, key);
        PORT.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y2, int button) {
        try {
            super.mouseClicked(x, y2, button);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ip.mouseClicked(x, y2, button);
        PORT.mouseClicked(x, y2, button);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        ip.updateCursorCounter();
        PORT.updateCursorCounter();
    }
}
