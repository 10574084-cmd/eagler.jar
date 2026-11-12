# JerryEagler prototype — 0.1

This folder contains an initial Minecraft 1.8/Eaglercraft source patch.

## Included

- A new `EntityJerry` class, created through Eagler BuildTools' `.recreate.java` patch mechanism.
- The entity is registered under the name `JerryEagler` with unused legacy entity ID `102`.
- It inherits the existing 1.8 wolf implementation for movement, AI, health, collision, and rendering fallback.
- The entity displays the name tag `Jerry`.

## Current limitations

- This prototype deliberately uses the vanilla wolf renderer and texture. It is a compatibility milestone, not the final visual port.
- No original Jerry meshes, textures, voices, shader effects, dimensions, or cinematic assets are included yet.
- Forge 1.20.1 classes and mixins are not loaded. The entity is compiled into a modified Eaglercraft 1.8 client.
- Eaglercraft does not natively install Forge JAR files as browser mods. A completed distribution will be a custom Eaglercraft client build, not a normal Forge JAR.

## Apply

The preparation workflow downloads the user-provided Eaglercraft source ZIP, copies this folder's patch into `patches/minecraft`, and adds the registry entry to Eagler's existing EntityList context patch. It checks the patch structure and Java syntax in CI.

A full Eagler client build still requires the Minecraft 1.8/MCP workspace inputs that the upstream Eagler source archive does not contain. This prototype workflow does not claim to compile a playable client.

## Spawn

After the source patch is integrated into a successfully built client, try:

```
/summon JerryEagler
```
