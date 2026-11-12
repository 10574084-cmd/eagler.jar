# JerryEagler prototype — 0.2

This folder contains a native Minecraft 1.8/Eaglercraft source patch.

## Included

- A new `EntityJerry` class, created through Eagler BuildTools' `.recreate.java` mechanism.
- Entity registration as `JerryEagler`, using numeric ID `102`.
- A new `ModelJerry` class based on the Minecraft 1.8 wolf model with small independent pose adjustments.
- A new `RenderJerry` class using the Minecraft 1.8 living-entity renderer.
- A RenderManager map entry for the dedicated Jerry renderer.

## Current limitations

- The model still uses the vanilla wolf texture as a placeholder. It is not the original Jerry appearance.
- Movement, health, collision, and most AI still come from Eagler 1.8's `EntityWolf`.
- The original Forge 1.20.1 Geo renderer, mixins, and custom animation engine are not loaded.
- No original voice recordings, meshes, textures, dimensions, shaders, or cinematic assets are included.
- The RenderManager insertion is anchored to the vanilla 1.8 constructor's wolf renderer registration. A complete application against the target Eagler 1.8.8 workspace is still required to verify the exact base-source context.
- The workflow syntax-checks the new Java classes against minimal 1.8 API stubs. It does not compile the full Minecraft client or generate a playable build.

## Target

This is a BuildTools patch overlay for a modified Eaglercraft client. It is not a Forge mod JAR and is not an installable browser add-on.
