package illager.guardillagers.client.render.layer;

import illager.guardillagers.client.render.RenderGuardIllager;
import illager.guardillagers.entity.EntityGuardIllager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;

public class LayerHeldItemGuard implements LayerRenderer<EntityGuardIllager> {
    private final RenderGuardIllager guardRenderer;

    public LayerHeldItemGuard(RenderGuardIllager guardRendererIn) {
        this.guardRenderer = guardRendererIn;
    }

    public void render(EntityGuardIllager entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItemOffhand();
        if (!itemstack.isEmpty()) {
            GlStateManager.color3f(1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            if (this.guardRenderer.getMainModel().isChild) {
                GlStateManager.translatef(0.0F, 0.625F, 0.0F);
                GlStateManager.rotatef(-20.0F, -1.0F, 0.0F, 0.0F);
                float f = 0.5F;
                GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            }

            this.guardRenderer.getMainModel().crossHand().postRender(0.0625F);
            GlStateManager.translatef(-0.0625F, 0.53125F, 0.21875F);
            Item item = itemstack.getItem();
            Minecraft minecraft = Minecraft.getInstance();
            if (Block.getBlockFromItem(item).getDefaultState().getRenderType() == EnumBlockRenderType.ENTITYBLOCK_ANIMATED) {
                GlStateManager.translatef(0.0F, -0.2875F, -0.46F);
                GlStateManager.rotatef(30.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                float f1 = 0.375F;
                GlStateManager.scalef(0.375F, -0.375F, 0.375F);
            } else if (item == Items.BOW) {
                GlStateManager.translatef(0.0F, -0.2875F, -0.46F);
                GlStateManager.rotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                float f2 = 0.625F;
                GlStateManager.scalef(0.625F, -0.625F, 0.625F);
                GlStateManager.rotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            } else {
                GlStateManager.translatef(0.0F, -0.2875F, -0.46F);
                float f3 = 0.875F;
                GlStateManager.scalef(0.875F, 0.875F, 0.95F);
                GlStateManager.rotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(-60.0F, 1.0F, 0.0F, 0.0F);
            }

            GlStateManager.rotatef(-15.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(40.0F, 0.0F, 0.0F, 1.0F);
            minecraft.getFirstPersonRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}