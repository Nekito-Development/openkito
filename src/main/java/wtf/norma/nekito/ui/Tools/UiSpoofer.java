package wtf.norma.nekito.ui.Tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.util.player.UUIDFetcherUtils;


import java.awt.*;
import java.io.IOException;
import java.util.UUID;

public class UiSpoofer extends GuiScreen {
    public static String FakeIp = null;
    public static String FakeUUID = null;
    public static String renderText = "";
    private GuiTextField field_1;
    private GuiTextField field_2;
    private GuiTextField field_3;

    private GuiScreen before;

    public UiSpoofer(GuiScreen before) {
        this.before = before;
    }

    @Override
    public void initGui() {
        renderText = "";

        this.buttonList.add(new GuiButton(1, width / 2 - 30, height / 2 - 90, 60, 20, "Spoof"));
        this.buttonList.add(new GuiButton(0, width / 2 + 5, height / 2 + 70, 40, 20, "Back"));
        this.buttonList.add(new GuiButton(3, width / 2 + 5, height / 2 + 40, 70, 20, "RandomUUID"));
        this.buttonList.add(new GuiButton(4, width / 2 - 75, height / 2 + 40, 70, 20, "CurrentUUID"));
        this.buttonList.add(new GuiButton(5, width / 2 - 45, height / 2 + 70, 40, 20, "Clear"));

        this.field_1 = new GuiTextField(100, mc.fontRendererObj, width / 2 - 100, height / 2 - 20, 200, 20);
        this.field_1.setMaxStringLength(100);
        this.field_1.setText("FakeIp");
        this.field_1.setText(FakeIp != null ? FakeIp : "");

        this.field_2 = new GuiTextField(100, mc.fontRendererObj, width / 2 - 100, height / 2 - 20 + 35, 200, 20);
        this.field_2.setMaxStringLength(100);
        this.field_2.setText("FakeUUID");
        this.field_2.setText(FakeUUID != null ? FakeUUID : "");

        this.field_3 = new GuiTextField(100, mc.fontRendererObj, width / 2 - 100, height / 2 - 20 - 35, 200, 20);
        this.field_3.setMaxStringLength(100);
        this.field_3.setText("CrackedName");
        this.field_3.setText(mc.getSession().getUsername());
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                mc.displayGuiScreen(before);
                break;
            }
            case 1: {
                FakeIp = !this.field_1.getText().trim().isEmpty() ? this.field_1.getText().trim() : null;
                FakeUUID = !this.field_2.getText().trim().isEmpty() ? this.field_2.getText().trim() : null;
                if (!this.field_3.getText().trim().isEmpty()) {
                    Minecraft.getMinecraft().login(this.field_3.getText().trim());
                }
                renderText = "Successful";
            }
            default: {
                break;
            }
            case 3: {
                this.field_2.setText("" + UUID.randomUUID());
                break;
            }
            case 4: {
                if (this.field_3.getText().trim().isEmpty()) {
                    this.field_2.setText(UUIDFetcherUtils.getUUID(mc.getSession().username) != null ? UUIDFetcherUtils.getUUID(mc.getSession().username).toString() : "Error");
                    break;
                }
                this.field_2.setText(UUIDFetcherUtils.getUUID(this.field_3.getText().trim()) != null ? UUIDFetcherUtils.getUUID(this.field_3.getText().trim()).toString() : "Error");
                break;
            }
            case 5: {
                this.field_1.setText("");
                this.field_2.setText("");
                this.field_3.setText("");
                FakeIp = null;
                FakeUUID = null;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.field_3.drawTextBox();
        this.field_1.drawTextBox();
        this.field_2.drawTextBox();
        UiSpoofer.drawCenteredString(mc.fontRendererObj, "Fake IP", width / 2, height / 2 - 20 - 10, Color.WHITE.hashCode());
        UiSpoofer.drawCenteredString(mc.fontRendererObj, "Cracked Username", width / 2, height / 2 - 20 - 45, Color.WHITE.hashCode());
        UiSpoofer.drawCenteredString(mc.fontRendererObj, "Fake UUID", width / 2, height / 2 - 20 + 25, Color.WHITE.hashCode());
        GL11.glPushMatrix();
        GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
        GL11.glScaled((double)4.0, (double)4.0, (double)4.0);
        UiSpoofer.drawCenteredString(mc.fontRendererObj, renderText, width / 8, height / 4 - mc.fontRendererObj.FONT_HEIGHT, 0);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.field_1.mouseClicked(mouseX, mouseY, mouseButton);
        this.field_2.mouseClicked(mouseX, mouseY, mouseButton);
        this.field_3.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 15) {
            if (this.field_1.isFocused()) {
                this.field_1.setFocused(false);
                this.field_3.setFocused(true);
                return;
            }
            if (this.field_2.isFocused()) {
                this.field_2.setFocused(false);
                this.field_1.setFocused(true);
                return;
            }
            if (this.field_3.isFocused()) {
                this.field_3.setFocused(false);
                this.field_2.setFocused(true);
                return;
            }
            this.field_1.setFocused(true);
        }
        this.field_1.textboxKeyTyped(typedChar, keyCode);
        this.field_2.textboxKeyTyped(typedChar, keyCode);
        this.field_3.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }
}

