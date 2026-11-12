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


## Findings from core bytecode signature audit (2026-11-12)

The signature-only audit identified the original mod's main architecture:

- Main entity `com.pkfl.jerry.entity.companion` extends Minecraft 1.20.1 `TamableAnimal` and implements the mod's custom GeoEntity animation interface.
- Its synchronized state is extensive: activity, expression, stage, phase, attention, listening, crawling, voice response, scene pose, chest hiding/rummaging, puppy behavior, warning targets, and other story state.
- Renderer `com.pkfl.jerry.client.canine` extends a bundled GeoEntityRenderer and contains additional rendering paths for reactions, feints, chest-food behavior, and visibility.
- The main mod class uses Forge DeferredRegister objects for entities, blocks, and block entities.
- Core classes embed a notice stating that copying, modifying, or porting requires written permission. The user has confirmed permission in this conversation; retain the rights-holder authorization before using original source or assets in a distributed build.
- Many mesh and texture resources are held in private binary asset paths rather than loose PNG/JSON model files.

This confirms that 0.1.0 is a native compatibility stub, not a source-level port of Jerry's original AI or renderer. It reuses Eagler 1.8's wolf behavior and render fallback so the new entity can be registered and tested first.

## Revised implementation milestones

1. **0.1.0 — registered entity stub:** source patch and Java syntax smoke test completed. Jerry currently appears as a named wolf.
2. **0.2 — native 1.8 model and renderer:** create independent Eagler 1.8 model/renderer classes and an authorized or original texture. Do not depend on the mod's modern Geo renderer.
3. **0.3 — dog behavior:** implement a selected subset of behavior using 1.8 AI/task APIs: idle, follow, look-at-player, sit/stay, and simple interactions.
4. **0.4 — optional content:** add sounds and more advanced behavior after resource compatibility and rights are checked.
5. **0.5 — full client integration:** requires the actual Eagler BuildTools workspace inputs (Minecraft 1.8/MCP initialization artifacts and resources), which are not present in the supplied ZIP. Build and test on that complete workspace.

The browser output is a modified Eagler client build, not a Forge-style plugin JAR. The current deliverable is a BuildTools source-patch ZIP, not a playable client or an installable `.eagler` add-on.


## 0.2.0 milestone — native model and renderer prototype

GitHub Actions run 43292374914 completed successfully and uploaded artifact `JerryEagler-source-patch-v0.2.0`.

Included in the verified package:
- `EntityJerry.recreate.java`: new entity class extending the 1.8 wolf implementation.
- `ModelJerry.recreate.java`: native 1.8 model subclass with small pose/idle adjustments.
- `RenderJerry.recreate.java`: native `RenderLiving<EntityJerry>` renderer, using the wolf texture as a temporary placeholder.
- `EntityList.edit.java`: entity name `JerryEagler`, numeric ID 102.
- `RenderManager.edit.java`: renderer map insertion after the vanilla wolf renderer entry.

Validation completed:
- Eagler source ZIP SHA-256 checked.
- New Java classes compile in a syntax smoke test using minimal 1.8 API stubs.
- The downloaded package was inspected: the `RenderManager` patch and validation report contain normal newline characters and the expected files.
- This is not a full Eagler/Minecraft compilation. The supplied source archive omits the decompiled Minecraft 1.8.8/MCP workspace needed to apply the context-restricted RenderManager hunk against the exact target source.

Current visual/gameplay state:
- Jerry has a dedicated renderer and model class in the patch overlay.
- The texture remains the vanilla wolf texture; the model still starts from the 1.8 wolf model.
- Entity movement and AI still inherit from `EntityWolf`.
- No playable browser client or `.eagler` runtime add-on has been built yet.
