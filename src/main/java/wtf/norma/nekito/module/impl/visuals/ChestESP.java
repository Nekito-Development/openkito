package wtf.norma.nekito.module.impl.visuals;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.render.EventRender3D;
import wtf.norma.nekito.module.impl.other.Stealer;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.render.RenderUtility;

// author: omikron
public class ChestESP extends Module implements Subscriber {

    public static BooleanSetting colorMode = new BooleanSetting("Client Color", true);


    public ChestESP() {
        super("ChestESP", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventRender3D> listener = new Listener<>(event -> {
        mc.theWorld.loadedTileEntityList.stream().forEach(tileEntity -> {
            if (tileEntity instanceof TileEntityChest) {
                final TileEntityLockable storage = (TileEntityLockable) tileEntity;
                this.pierdolnijespnaczest(storage, storage.getPos().getX(), storage.getPos().getY(), storage.getPos().getZ());
            }
        });
    });

//    public void onEvent(Event event) {
//        if (event instanceof EventRender3D) {
//            for (final Object o : mc.theWorld.loadedTileEntityList) {
//                if (o instanceof TileEntityChest) {
//                    final TileEntityLockable storage = (TileEntityLockable) o;
//                    this.pierdolnijespnaczest(storage, storage.getPos().getX(), storage.getPos().getY(), storage.getPos().getZ());
//                }
//            }
//        }
//    }

    public void pierdolnijespnaczest(TileEntityLockable storage, double x, double y, double z) {
        assert !storage.isLocked();
        TileEntityChest chest = (TileEntityChest) storage;
        Vec3 vec = new Vec3(0, 0, 0);
        Vec3 vec2 = new Vec3(0, 0, 0);
        if (chest.adjacentChestZNeg != null) {
            vec = new Vec3(x + 0.0625, y, z - 0.9375);
            vec2 = new Vec3(x + 0.9375, y + 0.875, z + 0.9375);
        } else if (chest.adjacentChestXNeg != null) {
            vec = new Vec3(x + 0.9375, y, z + 0.0625);
            vec2 = new Vec3(x - 0.9375, y + 0.875, z + 0.9375);
        } else if (chest.adjacentChestXPos == null && chest.adjacentChestZPos == null) {
            vec = new Vec3(x + 0.0625, y, z + 0.0625);
            vec2 = new Vec3(x + 0.9375, y + 0.875, z + 0.9375);
        } else {
            return;
        }
        GL11.glPushMatrix();
        RenderUtility.pre3D();
        mc.entityRenderer.setupCameraTransform(mc.timer.renderPartialTicks, 2);
        if (chest.getChestType() == 1) {
            GL11.glColor4d(0.7, 0.1, 0.1, 0.5);
        } else if (chest.isEmpty && Nekito.INSTANCE.getModuleManager().getModule(Stealer.class).isToggled()) {
            GL11.glColor4d(0.4, 0.2, 0.2, 0.5);
        }
        RenderUtility.drawSelectionBoundingBox(new AxisAlignedBB(vec.xCoord - RenderManager.renderPosX, vec.yCoord - RenderManager.renderPosY, vec.zCoord - RenderManager.renderPosZ, vec2.xCoord - RenderManager.renderPosX, vec2.yCoord - RenderManager.renderPosY, vec2.zCoord - RenderManager.renderPosZ));
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        RenderUtility.post3D();
        GL11.glPopMatrix();
    }

}
