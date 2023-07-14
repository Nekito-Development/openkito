package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.util.Animations.EasingHelper;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class GuiChest extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;

    /**
     * window height is calculated with these values; the more rows, the heigher
     */
    private int inventoryRows;

    private float progress = 0.0f;

    private long lastMS = 0L;


    public GuiChest(IInventory upperInv, IInventory lowerInv)
    {
        super(new ContainerChest(upperInv, lowerInv, Minecraft.getMinecraft().thePlayer));
        this.upperChestInventory = upperInv;
        this.lowerChestInventory = lowerInv;
        this.lastMS = System.currentTimeMillis();
        this.progress = 0.0f;
        this.allowUserInput = false;
        int i = 222;
        int j = i - 108;
        this.inventoryRows = lowerInv.getSizeInventory() / 9;
        this.ySize = j + this.inventoryRows * 18;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */

    public static double animation;

    public static double phase;
    public static double createAnimation(double phase) {
        return 1.0 - Math.pow(1.0 - phase, 3.0);
    }
    private static ResourceLocation ANIME_GIRL;


    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

        ScaledResolution sr = new ScaledResolution(this.mc);

        this.progress = this.progress >= 1.0f ? 1.0f : (float)(System.currentTimeMillis() - this.lastMS) / 850.0f;
        // dzialalo kiedys ale teraz n dziala i chuj Xddddddddd
        double trueAnim = EasingHelper.easeOutQuart(this.progress);
        GL11.glTranslated((1.0 - trueAnim) * ((double)this.width / 2.0), (1.0 - trueAnim) * ((double)this.height / 2.0), 0.0);
        GL11.glScaled(trueAnim, trueAnim, trueAnim);
        GL11.glScaled(trueAnim, trueAnim, trueAnim);

        GL11.glColor4d(animation, animation, animation, animation);
        ANIME_GIRL = new ResourceLocation("nekito/uwu/babaxd.png");

        mc.getTextureManager().bindTexture(ANIME_GIRL);
        RenderUtility.drawImage(ANIME_GIRL, (float)((double)sr.getScaledWidth() - 350.0 * animation), sr.getScaledHeight() - 370, 400.0f, 400.0f, Color.WHITE);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}
