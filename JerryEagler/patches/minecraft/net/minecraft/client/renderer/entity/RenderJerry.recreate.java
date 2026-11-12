package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelJerry;
import net.minecraft.entity.passive.EntityJerry;
import net.minecraft.util.ResourceLocation;

/**
 * Native Minecraft 1.8 renderer for EntityJerry.
 * The wolf texture is provisional until a separately authorized or original texture is supplied.
 */
public class RenderJerry extends RenderLiving<EntityJerry> {

    private static final ResourceLocation JERRY_TEXTURE =
            new ResourceLocation("textures/entity/wolf/wolf.png");

    public RenderJerry(RenderManager renderManagerIn, ModelJerry model, float shadowSize) {
        super(renderManagerIn, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityJerry entity) {
        return JERRY_TEXTURE;
    }
}
