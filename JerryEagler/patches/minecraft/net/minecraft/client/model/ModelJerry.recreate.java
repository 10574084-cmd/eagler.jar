package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Independent Minecraft 1.8 model pass for Jerry.
 * Uses the vanilla wolf texture as a temporary placeholder.
 */
public class ModelJerry extends ModelWolf {

    public ModelJerry() {
        super();
        this.wolfHeadMain.setRotationPoint(-1.0F, 13.25F, -7.35F);
        this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.25F);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
            float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.wolfHeadMain.rotateAngleZ = MathHelper.sin(ageInTicks * 0.075F) * 0.025F;
        this.wolfTail.rotateAngleX += MathHelper.sin(ageInTicks * 0.18F) * 0.10F;
    }
}
