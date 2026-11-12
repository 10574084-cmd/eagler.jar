# JerryEagler — port scope

## Verified input

The GitHub Actions inspection run successfully downloaded the Jerry release asset and verified the published SHA-256 checksum. A private CFR pass completed and produced a sanitized structural audit (class names, resource paths, mixin configuration, and integration indicators only).

- JAR: 456,711,004 bytes
- Bytecode classes: 808
- Decompiled class files counted by the audit: 543
- Loader: Forge / JavaFML
- Minecraft target: 1.20.1
- Mod id: `jerry`
- Declared license: `All Rights Reserved`

## Main integration surface identified

- Mod registration class: `com.pkfl.jerry.jerry`
- Main canine entity class: `com.pkfl.jerry.entity.companion`
- Canine renderer: `com.pkfl.jerry.client.canine`
- The canine renderer extends a Geo/GeckoLib entity renderer.
- Additional entity classes include `cub`, `decoy`, `ferry`, `gibbet`, `hound`, `hull`, `performer`, `remains`, `remnant`, `rift`, and `tom`.
- Mixin config lists five common mixins and two client mixins: `transport`, `seed`, `transit`, `daylight`, `chunks`, `lid`, and `blood`.
- The JAR also bundles a large AI package, an internal Geo/Gecko-style animation implementation, 45 sound files, voice archives, dimension/world data, shader programs, and binary mesh/model resources.

## Eaglercraft 1.8 implementation route

The supplied Eagler repository is a patch/build-tools repository. Its README explicitly says the decompiled Minecraft 1.8 source tree and original Minecraft resources are not included in the ZIP.

The supported patch pipeline has a `.recreate.java` mechanism for adding new Java files. Eagler's existing patches also show the required integration points:

1. Add an independently implemented 1.8-compatible entity class via a new-source patch.
2. Add a renderer and model implemented with Minecraft 1.8's model/render classes. Do not depend on Forge 1.20.1 or the mod's modern Geo renderer.
3. Register the entity in Eagler's `EntityList` mapping.
4. Register its renderer in `RenderManager`.
5. Add an authorized texture/sound set or create replacement assets from scratch.
6. Compile against the Eagler workspace and test spawn, saving/loading, rendering, and AI.

## Scope decision

A full port of all 808 classes is not realistic as a direct conversion. Eaglercraft 1.8 has a different client/server architecture and cannot load the Forge 1.20.1 Mixin/renderer/registry APIs.

The technically realistic first target is a limited core entity: a Jerry-style canine with a 1.8-native model, animation, movement/idle/follow behavior, basic interactions, and test spawning. Complex story dimensions, custom post-processing shaders, voice recognition/native libraries, cinematic systems, and the other entities should be excluded from phase one.

## Rights gate

The mod metadata declares `All Rights Reserved`. Do not copy its decompiled source, meshes, voice recordings, or sounds into a redistributable build without explicit permission from the rights holders. A fresh implementation with original/authorized model, texture, and sound assets is the alternative.
