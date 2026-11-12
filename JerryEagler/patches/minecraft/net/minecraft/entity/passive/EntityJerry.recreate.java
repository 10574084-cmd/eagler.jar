package net.minecraft.entity.passive;

import net.minecraft.world.World;

/**
 * First native Eaglercraft 1.8 implementation of Jerry.
 *
 * Phase 0.1 intentionally reuses EntityWolf's proven 1.8 movement,
 * health, collision, and AI systems. A dedicated model and renderer
 * will be added after the initial entity integration is validated.
 */
public class EntityJerry extends EntityWolf {

    public EntityJerry(World worldIn) {
        super(worldIn);
        this.setCustomNameTag("Jerry");
        this.setAlwaysRenderNameTag(true);
    }
}
