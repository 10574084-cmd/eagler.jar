# Eaglercraft entity patch integration audit\n\nThis report inspects only the user-provided Eaglercraft source archive.\n- Files in ZIP: 3103\n\n## Relevant entity and renderer patch paths\n\n- eagler-main/patches/minecraft/net/minecraft/client/model/ModelOcelot.edit.java\n- eagler-main/patches/minecraft/net/minecraft/client/model/ModelWolf.edit.java\n- eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderManager.edit.java\n- eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderOcelot.edit.java\n- eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderWolf.edit.java\n- eagler-main/patches/minecraft/net/minecraft/entity/EntityList.edit.java\n- eagler-main/patches/minecraft/net/minecraft/entity/passive/EntityOcelot.edit.java\n- eagler-main/patches/minecraft/net/minecraft/entity/passive/EntityWolf.edit.java\n\n## Patch file formats\n\n- .edit.java: 1358\n  - eagler-main/patches/minecraft/net/minecraft/block/Block.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockAir.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockAnvil.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBanner.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBarrier.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBasePressurePlate.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBeacon.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBed.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBookshelf.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBreakable.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBrewingStand.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockBush.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockButton.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockButtonStone.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockButtonWood.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockCactus.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockCake.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockCarpet.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockCarrot.edit.java\n  - eagler-main/patches/minecraft/net/minecraft/block/BlockCauldron.edit.java\n- .recreate.java: 0\n- .delete: 0\n\n## Top-level README\n\n### eagler-main/README.md\n
# EaglercraftX 1.8

### Play Minecraft 1.8 in your browser, supports singleplayer and multiplayer

![EaglercraftX 1.8 Screenshot Main Menu](https://deev.is/eagler/cors/eagler-1.8-u22-titlescreen-480p.png)

### This repository contains:

 - **Utilities to decompile Minecraft 1.8 and apply patch files to it**
 - **Source code to provide the LWJGL keyboard, mouse, and OpenGL APIs in a browser**
 - **Patch files to mod the Minecraft 1.8 source code to make it browser compatible**
 - **Browser-modified portions of Minecraft 1.8's open-source dependencies**
 - **Plugins for Minecraft servers to allow the eagler client to connect to them**

### This repository does NOT contain:

 - **Any portion of the decompiled Minecraft 1.8 source code or resources**
 - **Any portion of Mod Coder Pack and it's config files**
 - **Data that can be used alone to reconstruct portions of the game's source code**

## Getting Started:

### To compile the latest version of the client, on Windows:

1. Make sure you have at least Java 11 installed and added to your PATH, it is recommended to use Java 17
2. Download (clone) this repository to your computer
3. Double click `CompileLatestClient.bat`, a GUI resembling a classic windows installer should open
4. Follow the steps shown to you in the new window to finish compiling

### To compile the latest version of the client, on Linux/macOS:

1. Make sure you have at least Java 11 installed, it is recommended to use Java 17
2. Download (clone) this repository to your computer
3. Open a terminal in the folder the repository was cloned to
4. Type `chmod +x CompileLatestClient.sh` and hit enter
5. Type `./CompileLatestClient.sh` and hit enter, a GUI resembling a classic windows installer should open
6. Follow the steps shown to you in the new window to finish compiling

## Singleplayer

As of January 2024, singleplayer and shared worlds have been added to EaglercraftX 1.8.

Worlds are saved to your browser's local storage and are available even if your device does not have an internet connection. You can also import and export worlds in EaglercraftX as EPK files to copy them between devices and send them to your friends.

You can also import and export your existing vanilla Minecraft 1.8 worlds into EaglercraftX using ZIP files if you want to try playing all your old 1.8 maps in a modern browser. The glitch that caused some chunks to become corrupt when exporting worlds as vanilla in Eaglercraft 1.5.2 no longer happens in EaglercraftX 1.8, its perfect now. Beware that the inventories of LAN world players are not saved when the world is converted to vanilla, and pets (dogs, cats, horses, etc) might sometimes forget their owners due to the UUID changes.

## Shared Worlds

**This feature used to be known as "LAN Worlds" but has been renamed to "Shared Worlds" to avoid confusion**

If you would like to invite other players to join your singleplayer world and play the game together, use the "Invite" button in the pause menu. You can configure gamemode and cheats for the other players joining your world, you can also decide if you would like to hide your world from other people on your wifi network or advertise your world to them. If hidden is "off" then other people on your same wifi network will see your world listed on their game's "Multiplayer" screen with all of their servers like how sharing LAN worlds behave in vanilla Minecraft 1.8.

Once you press "Start Shared World", EaglercraftX 1.8 will give you a "join code" (usually 5 letters) to share with your friends. On a different device, go the "Multiplayer" screen and press "Direct Connect" and press "Join Shared World", enter the join code given to you when you started the shared world and press "Join World". Given a few seconds, the client should successfully be able to join your shared world from any other device on the internet that also has unrestricted internet access. If it does not work, check the "Network Settings" screen and make sure you and your friends all have the same set of shared world relay URLs configured or your clients will not be able to find each other.

If you would like to host your own relay, the JAR file and instructions can be downloaded from the "Network Settings" screen in the client. EaglercraftX 1.8 uses the same "LAN world" relay server that is used by Eaglercraft 1.5.2, if you would like the relay source code find a random copy of the Eaglercraft 1.5.2 source code and it should be located in the "sp-relay" folder. The relay has not been updated since then, it has only been renamed from "LAN world relay" to "Shared world relay".

## PBR Shaders

EaglercraftX 1.8 includes a deferred physically-based renderer modeled after the GTA V rendering engine with many new improvements and a novel raytracing technique for fast realistic reflections. It can be enabled in the "Shaders" menu in the game's options screen. Shader packs in EaglercraftX are just a component of resource packs, so any custom shaders you install will be in the form of a resource pack. EaglercraftX also comes with a very well optimized built-in PBR shader pack and also a built-in PBR material texture pack to give all blocks and items in the game realistic lighting and materials that looks better than most vanilla Minecraft shader packs. The default shader and texture packs were created from scratch by lax1dude, shaders packs made for vanilla Minecraft will not work in EaglercraftX and no shaders in EaglercraftX were taken from vanilla Minecraft shader packs.

## Voice Chat

EaglercraftX 1.8 includes an integrated voice-chat service that can be used in shared worlds and also on multiplayer servers when it is enabled by the server owner. This feature also uses WebRTC like shared worlds, so be careful that you don't leak your IP address accidentally by using it on a public server. If you own a website and don't want people to use voice chat on it, edit the `eaglercraftXOpts` variable in your index.html and add `allowVoiceClient: false`.

## Resource Packs

EaglercraftX 1.8 allows you to use any vanilla Minecraft 1.8 resource pack in your browser by importing it as a zip file, resource packs are saved to your browser's local storage and are saved between page refreshes. This can be used to add the original C418 soundtrack back into the game, download and import [this pack](https://bafybeiayojww5jfyzvlmtuk7l5ufkt7nlfto7mhwmzf2vs4bvsjd5ouiuq.ipfs.nftstorage.link/?filename=Music_For_Eaglercraft.zip) to add music back to Eaglercraft. A known bug with the debug desktop runtime is that sound files in resource packs do not play, this may be fixed in the future but is not a high priority issue.

If you are creating a resource pack and want to disable the blur filter on the main menu panorama, create a file called `assets/minecraft/textures/gui/title/background/enable_blur.txt` in your pack and set it's contents to `enable_blur=0`

## Making a Server

To make a server for EaglercraftX 1.8 the recommended software to use is EaglercraftXBungee ("EaglerXBungee") which is included in this repository in the `gateway/EaglercraftXBungee` folder. This is a plugin designed to be used with BungeeCord to allow Eaglercraft players to join your BungeeCord server. It is assumed that the reader already knows what BungeeCord is and has a working server set up that is joinable via java edition. If you don't know what BungeeCord is, please research the topic yourself first before continuing. Waterfall and FlameCord have also been tested, but EaglerXBungee was natively compiled against BungeeCord.

There is an experimental velocity plugin available in `gateway/EaglercraftXVelocity` but it is still in development and not recommended for public servers, so be sure to check for updates regularly if you use it. Configuration files are basically identical to EaglercraftXBungee so its safe to just directy copy in your old EaglercraftXBungee config files to the `plugins/eaglerxvelocity` folder and they should work with a minimal number of edits if you are migrating your network from BungeeCord to Velocity.

### Installation

Obtain the latest version of the EaglerXBungee JAR file (it can be downloaded in the client from the "Multiplayer" screen) and place it in the "plugins" folder of your BungeeCord server. It's recommended to only join native Minecraft 1.8 servers through an EaglerXBungee server but plugins like ProtocolSupport have allowed some people to join newer servers too.

Configuration files and other plugin data will be written in `plugins/EaglercraftXBungee`

### Online Mode Instructions

1. Enable `online_mode` in BungeeCord's `config.yml` file and make sure it works
2. Join the BungeeCord server using Minecraft Java Edition while logged into your Microsoft account
3. Run the `/eagler` command, it will give you a temporary login code
4. Disconnect from the server, close java edition, launch EaglercraftX 1.8
5. Set your profile username to the username of your Microsoft account
6. Go to the "Multiplayer" menu, press "Direct Connect", press "Connect to Server", then enter "ws://localhost:8081/"
7. If you are using a VPS, replace "localhost" with the IP address of the VPS when you connect
8. Press "Join Server", a login screen will be displayed, enter the temporary login code into the password field
9. EaglerXBungee will log you into the server as the Microsoft account you generated the login code with

Players using EaglercraftX will be able to see the vanilla skins of players on the server using vanilla Minecraft, but players on the server using vanilla Minecraft won't be able to see the skins of players using Eaglercraft. Instead they will see the skin of the Minecraft account that was used when the Eaglercraft player originally ran the `/eagler` command.

To disable this vanilla player skin feature and stop the plugin from downloading the textures of any player heads spawned with commands, edit the EaglercraftXBungee `settings.yml` file in the `plugins/EaglercraftXBungee` folder and change `download_vanilla_skins_to_clients` to `false`. Ratelimits configured in `settings.yml` define the maximum number of times per minute a single player is allowed to trigger profile/skin lookups and also define the maximum number of times per minute the entire server is allowed to actually perform profile/skin lookups.

By default, EaglercraftXBungee will use a local SQLite database in the server's working directory to store player skins and authentication codes. SQLite will be downloaded automatically if it is not already present. If you would like to use MySQL or something else instead, EaglercraftXBungee is JDBC-based and supports any database type that you can find a driver for. You can set the path of the database, path of the driver JAR, and the name of the driver class (example: `org.sqlite.JDBC`) for storing player skins in `settings.yml` and for storing login codes and profiles in `authservice.yml`.

### Offline Mode Instructions

By setting `online_mode` to `false` in the BungeeCord `config.yml` the authentication system will be disabled and players will no longer be required to first generate a code to log in. This should only be used for testing or if you can't get the authentication system to work. EaglercraftXBungee's skin system is supposed to be able to display SkinsRestorer skins if you plan to have vanilla players on the server but it's not guaranteed.

### Built-in HTTP server

When configuring the EaglercraftXBungee `listeners.yml` file, every listener includes an `http_server` section that can be used to configure the listener to also behave like a regular HTTP server when the websocket address is entered into a browser. If this is disabled people will get the normal "404 Websocket Upgrade Failure" instead when they accidentally type your server address into their browser. `root` defines the path to the folder containing index.html and the other files you want to host, relative to the `plugins/EaglercraftXBungee` folder. This can be useful for hosting the client if the offline download doesn't work for some reason but might slow your BungeeCord server down if lots of people are loading it all the time.

### Enabling Voice Chat

Voice chat is disabled by default in EaglercraftXBungee because it is not recommended for use on public servers. To enable it, add or change `allow_voice: true` to your EaglercraftXBungee `listeners.yml` file. The main difference between Eaglercraft 1.5.2 and EaglercraftX 1.8's voice chat feature is that the "Global" channel now only includes other players on the same server as you instead of every single player connected to the same bungeecord proxy. If you would like to disable voice chat on certain servers, add the names of the servers to the `disable_voice_chat_on_servers` list in the EaglercraftXBungee `settings.yml` file. You may have to add this property to the YML file manually if you've upgraded your server from an older version of EaglercraftXBungee.

### Disabling FNAW Skins

Players are known to complain about the high-poly Five Nights At Winstons character skins making PVP harder because of the belief that they change a player's hitbox. If you would like to disable those skins in your PVP worlds you can either set `disable_fnaw_skins_everywhere: true` in your EaglercraftXBungee `settings.yml` file to disable them for all players on your whole BungeeCord proxy, or you can disable them on specific servers by adding the names of the servers to the `disable_fnaw_skins_on_servers` list also in `settings.yml` like with disabling voice chat.

## Launch Options

The EaglercraftX 1.8 client is configured primarily through a variable called `window.eaglercraftXOpts` that must be set before the client starts up.

The default eaglercraftXOpts values is this:

    const relayId = Math.floor(Math.random() * 3);
    window.eaglercraftXOpts = {
        demoMode: false,
        container: "game_frame",
        assetsURI: "assets.epk",
        localesURI: "lang/",
        worldsDB: "worlds",
        servers: [
            { addr: "ws://localhost:8081/", name: "Local test server" }
        ],
        relays: [
            { addr: "wss://relay.deev.is/", comment: "lax1dude relay #1", primary: relayId == 0 },
            { addr: "wss://relay.lax1dude.net/", comment: "lax1dude relay #2", primary: relayId == 1 },
            { addr: "wss://relay.shhnowisnottheti.me/", comment: "ayunami relay #1", primary: relayId == 2 }
        ]
    };

### List of available options

- `container:` the ID of the HTML element to create the canvas in **(required)**
- `assetsURI:` the URL of the assets.epk file **(required)**
- `localesURI:` the URL where extra .lang files can be found
- `lang`: the default language to use for the game (like "en_US")
- `joinServer`: server address to join when the game launches
- `worldsDB:` the name of the IndexedDB database to store worlds in
- `resourcePacksDB:` the name of the IndexedDB database to store resource packs in
- `demoMode:` whether to launch the game in java edition demo mode
- `servers:` a list of default servers to display on the Multiplayer screen
- `relays:` the default list of shared world relays to use for invites
- `checkShaderGLErrors:` enables more verbose opengl error logging for the shaders
- `enableDownloadOfflineButton:` whether to show a "Download Offline" button on the title screen
- `downloadOfflineButtonLink:` overrides the download link for the "Download Offline" button
- `html5CursorSupport:` enables support for showing the CSS "pointer" cursor over buttons
- `allowUpdateSvc:` enables the certificate-based update system
- `allowUpdateDL:` allows the client to download new updates it finds
- `logInvalidCerts:` print update certificates with invalid signatures to console
- `enableSignatureBadge:` show a badge on the title screen indicating if digital signature is valid
- `checkRelaysForUpdates:` proprietary feature used in offline downloads
- `allowVoiceClient:` can be used to disable the voice chat feature
- `allowFNAWSkins:` can be used to disable the high poly FNAW skins
- `localStorageNamespace:` can be used to change the prefix of the local storage keys (Default: `"_eaglercraftX"`)
- `enableMinceraft:` can be used to disable the "Minceraft" title screen
- `hooks:` can be used to define JavaScript callbacks for certain events
    * `localStorageSaved:` JavaScript callback to save local storage keys
    * `localStorageLoaded:` JavaScript callback to load local storage keys

### Using Hooks

You may want to implement some custom logic for loading/saving certain local storage keys. The eaglercraftXOpts hooks section can be used to override the client's local storage load and save functions. Currently, local storage keys are used to save game settings, the user's profile, custom servers, and shared world relays. Worlds and resource packs do not use local storage keys because modern browsers limit local storage keys to only 5 megabytes per domain which is too small for saving entire worlds and resource packs. Worlds and resource packs are saved using [IndexedDB](https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API).

    window.eaglercraftXOpts = {
        ...
        ...
        ...
        hooks: {
            localStorageSaved: function(key, data) {
                // 'key' is local storage key name as a string
                // 'data' is base64-encoded byte array as a string
                // function returns nothing
            },
            localStorageLoaded: function(key) {
                // 'key' is local storage key name as a string
                // function returns a base64-encoded byte array as a string
                // function returns null if the key does not exist
            }
        }
    }

Be aware that the client will still save the key to the browser's local storage anyway even if you define a custom save handler, and will just attempt to load the key from the browser's local storage normally if you return null, thes\n\n## EntityList / RenderManager / wolf patch excerpts\n\n### eagler-main/patches/minecraft/net/minecraft/client/model/ModelOcelot.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 3  @  2 : 5

~ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/client/model/ModelWolf.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 3  @  2 : 5

~ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderManager.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 3

> INSERT  1 : 11  @  1

+ 
+ import com.google.common.collect.Maps;
+ 
+ import net.lax1dude.eaglercraft.v1_8.EagRuntime;
+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.OpenGlHelper;
+ import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.dynamiclights.DynamicLightsStateManager;
+ import net.lax1dude.eaglercraft.v1_8.profile.RenderHighPoly;

> INSERT  3 : 4  @  3

+ import net.minecraft.client.Minecraft;

> DELETE  13  @  13 : 15

> DELETE  2  @  2 : 3

> DELETE  1  @  1 : 55

> INSERT  81 : 82  @  81

+ 	private RenderPlayer eaglerRenderer;

> CHANGE  82 : 92  @  82 : 83

~ 		this.skinMap.put("slim", new RenderPlayer(this, true, false));
~ 		this.skinMap.put("zombie", new RenderPlayer(this, false, true));
~ 		if (EagRuntime.getConfiguration().isAllowFNAWSkins()) {
~ 			this.eaglerRenderer = new RenderHighPoly(this, this.playerRenderer.getMainModel(),
~ 					this.playerRenderer.shadowSize);
~ 		} else {
~ 			this.eaglerRenderer = this.playerRenderer;
~ 		}
~ 		this.skinMap.put("eagler",
~ 				Minecraft.getMinecraft().gameSettings.enableFNAWSkins ? this.eaglerRenderer : this.playerRenderer);

> INSERT  2 : 6  @  2

+ 	public void setEnableFNAWSkins(boolean en) {
+ 		this.skinMap.put("eagler", en ? this.eaglerRenderer : this.playerRenderer);
+ 	}
+ 

> CHANGE  9 : 10  @  9 : 10

~ 			render = this.getEntityClassRenderObject((Class<? extends Entity>) parClass1.getSuperclass());

> CHANGE  6 : 7  @  6 : 7

~ 	public <T extends Entity> Render getEntityRenderObject(Entity entityIn) {

> INSERT  82 : 86  @  82

+ 		if (DynamicLightsStateManager.isInDynamicLightsPass()) {
+ 			DynamicLightsStateManager.reportForwardRenderObjectPosition2((float) (d0 - viewerPosX),
+ 					(float) (d1 - viewerPosY), (float) (d2 - viewerPosZ));
+ 		}

> INSERT  3 : 4  @  3

+ 			DeferredStateManager.setEmissionConstant(1.0f);

> CHANGE  7 : 13  @  7 : 9

~ 		try {
~ 			return this.doRenderEntity(entity, d0 - this.renderPosX, d1 - this.renderPosY, d2 - this.renderPosZ, f,
~ 					partialTicks, parFlag);
~ 		} finally {
~ 			DeferredStateManager.setEmissionConstant(0.0f);
~ 		}

> INSERT  2 : 13  @  2

+ 	public static void setupLightmapCoords(Entity entity, float partialTicks) {
+ 		int i = entity.getBrightnessForRender(partialTicks);
+ 		if (entity.isBurning()) {
+ 			DeferredStateManager.setEmissionConstant(1.0f);
+ 			i = 15728880;
+ 		}
+ 		int j = i % 65536;
+ 		int k = i / 65536;
+ 		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
+ 	}
+ 

> CHANGE  18 : 23  @  18 : 19

~ 		try {
~ 			return this.doRenderEntity(entityIn, x, y, z, entityYaw, partialTicks, false);
~ 		} finally {
~ 			DeferredStateManager.setEmissionConstant(0.0f);
~ 		}

> INSERT  14 : 17  @  14

+ 					RenderItem.renderPosX = (float) x;
+ 					RenderItem.renderPosY = (float) y + entity.height * 0.5f;
+ 					RenderItem.renderPosZ = (float) z;

> CHANGE  14 : 16  @  14 : 15

~ 				if (this.debugBoundingBox && !entity.isInvisible() && !parFlag
~ 						&& !DeferredStateManager.isDeferredRenderer()) {

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderOcelot.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 3  @  2

+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;

> DELETE  1  @  1 : 4

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/client/renderer/entity/RenderWolf.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 3  @  2

+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;

> DELETE  1  @  1 : 4

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/entity/EntityList.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 4

> CHANGE  4 : 11  @  4 : 8

~ 
~ import com.google.common.collect.Lists;
~ import com.google.common.collect.Maps;
~ 
~ import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
~ import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
~ import net.lax1dude.eaglercraft.v1_8.minecraft.EntityConstructor;

> DELETE  66  @  66 : 68

> INSERT  4 : 6  @  4

+ 	private static final Map<String, EntityConstructor<? extends Entity>> stringToConstructorMapping = Maps
+ 			.newHashMap();

> INSERT  2 : 3  @  2

+ 	private static final Map<Integer, EntityConstructor<? extends Entity>> idToConstructorMapping = Maps.newHashMap();

> INSERT  1 : 3  @  1

+ 	private static final Map<Class<? extends Entity>, EntityConstructor<? extends Entity>> classToConstructorMapping = Maps
+ 			.newHashMap();

> CHANGE  3 : 5  @  3 : 4

~ 	private static void addMapping(Class<? extends Entity> entityClass,
~ 			EntityConstructor<? extends Entity> entityConstructor, String entityName, int id) {

> INSERT  10 : 11  @  10

+ 			stringToConstructorMapping.put(entityName, entityConstructor);

> INSERT  2 : 3  @  2

+ 			idToConstructorMapping.put(Integer.valueOf(id), entityConstructor);

> INSERT  1 : 2  @  1

+ 			classToConstructorMapping.put(entityClass, entityConstructor);

> CHANGE  4 : 6  @  4 : 5

~ 	private static void addMapping(Class<? extends Entity> entityClass,
~ 			EntityConstructor<? extends Entity> entityConstructor, String entityName, int entityID, int baseColor,

> CHANGE  1 : 2  @  1 : 2

~ 		addMapping(entityClass, entityConstructor, entityName, entityID);

> CHANGE  7 : 10  @  7 : 11

~ 			EntityConstructor<? extends Entity> constructor = stringToConstructorMapping.get(entityName);
~ 			if (constructor != null) {
~ 				entity = constructor.createEntity(worldIn);

> CHANGE  2 : 3  @  2 : 3

~ 			logger.error("Could not create entity", exception);

> INSERT  5 : 28  @  5

+ 	public static Entity createEntityByClass(Class<? extends Entity> entityClass, World worldIn) {
+ 		Entity entity = null;
+ 
+ 		try {
+ 			EntityConstructor<? extends Entity> constructor = classToConstructorMapping.get(entityClass);
+ 			if (constructor != null) {
+ 				entity = constructor.createEntity(worldIn);
+ 			}
+ 		} catch (Exception exception) {
+ 			logger.error("Could not create entity", exception);
+ 		}
+ 
+ 		return entity;
+ 	}
+ 
+ 	public static Entity createEntityByClassUnsafe(Class<? extends Entity> entityClass, World worldIn) {
+ 		EntityConstructor<? extends Entity> constructor = classToConstructorMapping.get(entityClass);
+ 		if (constructor != null) {
+ 			return constructor.createEntity(worldIn);
+ 		}
+ 		return null;
+ 	}
+ 

> CHANGE  8 : 11  @  8 : 12

~ 			EntityConstructor<? extends Entity> constructor = stringToConstructorMapping.get(nbt.getString("id"));
~ 			if (constructor != null) {
~ 				entity = constructor.createEntity(worldIn);

> CHANGE  2 : 3  @  2 : 3

~ 			logger.error("Could not create entity", exception);

> CHANGE  15 : 18  @  15 : 19

~ 			EntityConstructor<? extends Entity> constructor = getConstructorFromID(entityID);
~ 			if (constructor != null) {
~ 				entity = constructor.createEntity(worldIn);

> CHANGE  2 : 3  @  2 : 3

~ 			logger.error("Could not create entity", exception);

> INSERT  18 : 22  @  18

+ 	public static EntityConstructor<? extends Entity> getConstructorFromID(int entityID) {
+ 		return idToConstructorMapping.get(Integer.valueOf(entityID));
+ 	}
+ 

> CHANGE  17 : 18  @  17 : 18

~ 		Set<String> set = stringToClassMapping.keySet();

> CHANGE  29 : 97  @  29 : 91

~ 		addMapping(EntityItem.class, EntityItem::new, "Item", 1);
~ 		addMapping(EntityXPOrb.class, EntityXPOrb::new, "XPOrb", 2);
~ 		addMapping(EntityEgg.class, EntityEgg::new, "ThrownEgg", 7);
~ 		addMapping(EntityLeashKnot.class, EntityLeashKnot::new, "LeashKnot", 8);
~ 		addMapping(EntityPainting.class, EntityPainting::new, "Painting", 9);
~ 		addMapping(EntityArrow.class, EntityArrow::new, "Arrow", 10);
~ 		addMapping(EntitySnowball.class, EntitySnowball::new, "Snowball", 11);
~ 		addMapping(EntityLargeFireball.class, EntityLargeFireball::new, "Fireball", 12);
~ 		addMapping(EntitySmallFireball.class, EntitySmallFireball::new, "SmallFireball", 13);
~ 		addMapping(EntityEnderPearl.class, EntityEnderPearl::new, "ThrownEnderpearl", 14);
~ 		addMapping(EntityEnderEye.class, EntityEnderEye::new, "EyeOfEnderSignal", 15);
~ 		addMapping(EntityPotion.class, EntityPotion::new, "ThrownPotion", 16);
~ 		addMapping(EntityExpBottle.class, EntityExpBottle::new, "ThrownExpBottle", 17);
~ 		addMapping(EntityItemFrame.class, EntityItemFrame::new, "ItemFrame", 18);
~ 		addMapping(EntityWitherSkull.class, EntityWitherSkull::new, "WitherSkull", 19);
~ 		addMapping(EntityTNTPrimed.class, EntityTNTPrimed::new, "PrimedTnt", 20);
~ 		addMapping(EntityFallingBlock.class, EntityFallingBlock::new, "FallingSand", 21);
~ 		addMapping(EntityFireworkRocket.class, EntityFireworkRocket::new, "FireworksRocketEntity", 22);
~ 		addMapping(EntityArmorStand.class, EntityArmorStand::new, "ArmorStand", 30);
~ 		addMapping(EntityBoat.class, EntityBoat::new, "Boat", 41);
~ 		addMapping(EntityMinecartEmpty.class, EntityMinecartEmpty::new,
~ 				EntityMinecart.EnumMinecartType.RIDEABLE.getName(), 42);
~ 		addMapping(EntityMinecartChest.class, EntityMinecartChest::new, EntityMinecart.EnumMinecartType.CHEST.getName(),
~ 				43);
~ 		addMapping(EntityMinecartFurnace.class, EntityMinecartFurnace::new,
~ 				EntityMinecart.EnumMinecartType.FURNACE.getName(), 44);
~ 		addMapping(EntityMinecartTNT.class, EntityMinecartTNT::new, EntityMinecart.EnumMinecartType.TNT.getName(), 45);
~ 		addMapping(EntityMinecartHopper.class, EntityMinecartHopper::new,
~ 				EntityMinecart.EnumMinecartType.HOPPER.getName(), 46);
~ 		addMapping(EntityMinecartMobSpawner.class, EntityMinecartMobSpawner::new,
~ 				EntityMinecart.EnumMinecartType.SPAWNER.getName(), 47);
~ 		addMapping(EntityMinecartCommandBlock.class, EntityMinecartCommandBlock::new,
~ 				EntityMinecart.EnumMinecartType.COMMAND_BLOCK.getName(), 40);
~ 		addMapping(EntityLiving.class, null, "Mob", 48);
~ 		addMapping(EntityMob.class, null, "Monster", 49);
~ 		addMapping(EntityCreeper.class, EntityCreeper::new, "Creeper", 50, 894731, 0);
~ 		addMapping(EntitySkeleton.class, EntitySkeleton::new, "Skeleton", 51, 12698049, 4802889);
~ 		addMapping(EntitySpider.class, EntitySpider::new, "Spider", 52, 3419431, 11013646);
~ 		addMapping(EntityGiantZombie.class, EntityGiantZombie::new, "Giant", 53);
~ 		addMapping(EntityZombie.class, EntityZombie::new, "Zombie", 54, '\uafaf', 7969893);
~ 		addMapping(EntitySlime.class, EntitySlime::new, "Slime", 55, 5349438, 8306542);
~ 		addMapping(EntityGhast.class, EntityGhast::new, "Ghast", 56, 16382457, 12369084);
~ 		addMapping(EntityPigZombie.class, EntityPigZombie::new, "PigZombie", 57, 15373203, 5009705);
~ 		addMapping(EntityEnderman.class, EntityEnderman::new, "Enderman", 58, 1447446, 0);
~ 		addMapping(EntityCaveSpider.class, EntityCaveSpider::new, "CaveSpider", 59, 803406, 11013646);
~ 		addMapping(EntitySilverfish.class, EntitySilverfish::new, "Silverfish", 60, 7237230, 3158064);
~ 		addMapping(EntityBlaze.class, EntityBlaze::new, "Blaze", 61, 16167425, 16775294);
~ 		addMapping(EntityMagmaCube.class, EntityMagmaCube::new, "LavaSlime", 62, 3407872, 16579584);
~ 		addMapping(EntityDragon.class, EntityDragon::new, "EnderDragon", 63);
~ 		addMapping(EntityWither.class, EntityWither::new, "WitherBoss", 64);
~ 		addMapping(EntityBat.class, EntityBat::new, "Bat", 65, 4996656, 986895);
~ 		addMapping(EntityWitch.class, EntityWitch::new, "Witch", 66, 3407872, 5349438);
~ 		addMapping(EntityEndermite.class, EntityEndermite::new, "Endermite", 67, 1447446, 7237230);
~ 		addMapping(EntityGuardian.class, EntityGuardian::new, "Guardian", 68, 5931634, 15826224);
~ 		addMapping(EntityPig.class, EntityPig::new, "Pig", 90, 15771042, 14377823);
~ 		addMapping(EntitySheep.class, EntitySheep::new, "Sheep", 91, 15198183, 16758197);
~ 		addMapping(EntityCow.class, EntityCow::new, "Cow", 92, 4470310, 10592673);
~ 		addMapping(EntityChicken.class, EntityChicken::new, "Chicken", 93, 10592673, 16711680);
~ 		addMapping(EntitySquid.class, EntitySquid::new, "Squid", 94, 2243405, 7375001);
~ 		addMapping(EntityWolf.class, EntityWolf::new, "Wolf", 95, 14144467, 13545366);
~ 		addMapping(EntityMooshroom.class, EntityMooshroom::new, "MushroomCow", 96, 10489616, 12040119);
~ 		addMapping(EntitySnowman.class, EntitySnowman::new, "SnowMan", 97);
~ 		addMapping(EntityOcelot.class, EntityOcelot::new, "Ozelot", 98, 15720061, 5653556);
~ 		addMapping(EntityIronGolem.class, EntityIronGolem::new, "VillagerGolem", 99);
~ 		addMapping(EntityHorse.class, EntityHorse::new, "EntityHorse", 100, 12623485, 15656192);
~ 		addMapping(EntityRabbit.class, EntityRabbit::new, "Rabbit", 101, 10051392, 7555121);
~ 		addMapping(EntityVillager.class, EntityVillager::new, "Villager", 120, 5651507, 12422002);
~ 		addMapping(EntityEnderCrystal.class, EntityEnderCrystal::new, "EnderCrystal", 200);

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/entity/passive/EntityOcelot.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  20  @  20 : 23

> EOF
\n### eagler-main/patches/minecraft/net/minecraft/entity/passive/EntityWolf.edit.java\n
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  25  @  25 : 30

> CHANGE  79 : 82  @  79 : 82

~ 		this.dataWatcher.addObject(18, Float.valueOf(this.getHealth()));
~ 		this.dataWatcher.addObject(19, Byte.valueOf((byte) 0));
~ 		this.dataWatcher.addObject(20, Byte.valueOf((byte) EnumDyeColor.RED.getMetadata()));

> EOF
\n\n## Example recreate patches\n\n\n## DiffSet implementation clues\n\n### eagler-main/buildtools/src/main/java/net/lax1dude/eaglercraft/v1_8/buildtools/task/diff/DiffSet.java\n14: import java.util.regex.Pattern;\n15: \n16: import org.apache.commons.io.FileUtils;\n17: \n18: import com.github.difflib.UnifiedDiffUtils;\n19: import com.github.difflib.patch.Patch;\n20: \n21: /**\n22:  * Copyright (c) 2022-2023 lax1dude. All Rights Reserved.\n23:  * \n24:  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND\n25:  * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED\n26:  * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\n48: 	}\n49: 	\n50: 	public static final DeleteFunction deleteFunction = new DeleteFunction();\n51: \n52: 	public final Map<String,Object> diffs;\n53: 	public final Map<String,byte[]> recreate;\n54: 	\n55: 	public DiffSet() {\n56: 		diffs = new HashMap();\n57: 		recreate = new HashMap();\n58: 	}\n59: 	\n60: 	private static final Pattern editPattern = Pattern.compile(".*\\.edit(\\.[^\\.\\/\\\\]+)?$");\n61: 	private static final Pattern replacePattern = Pattern.compile(".*\\.replace(\\.[^\\.\\/\\\\]+)?$");\n62: 	private static final Pattern deletePattern = Pattern.compile(".*\\.delete(\\.[^\\.\\/\\\\]+)?$");\n63: 	private static final Pattern recreatePattern = Pattern.compile(".*\\.recreate(\\.[^\\.\\/\\\\]+)?$");\n64: 	\n65: 	public int loadFolder(File pathIn, boolean useECR, SourceProvider ecrContextProvider) throws IOException {\n66: 		String baseAbsolutePath = pathIn.getAbsolutePath();\n67: 		int total = 0;\n68: 		\n69: 		File del = new File(pathIn, "delete.txt");\n70: 		if(del.isFile()) {\n90: 				fName = fName.substring(1);\n91: 			}\n92: 			if(editPattern.matcher(fName).matches()) {\n93: 				try {\n94: 					String nm = removeExt(fName, "edit");\n95: 					Patch<String> pth;\n96: 					if(useECR) {\n97: 						try (BufferedReader reader = new BufferedReader(\n98: 								new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {\n99: 							pth = EaglerContextRedacted.readContextRestricted(ecrContextProvider.getSource(nm), reader);\n100: 						}\n101: 					}else {\n102: 						List<String> phile = FileUtils.readLines(f, "UTF-8");\n118: 					System.err.println("ERROR: could not read '" + fName + "'!");\n119: 				}\n120: 			}else if(deletePattern.matcher(fName).matches()) {\n121: 				diffs.put(removeExt(fName, "delete"), deleteFunction);\n122: 				++total;\n123: 			}else if(recreatePattern.matcher(fName).matches()) {\n124: 				try {\n125: 					String str = removeExt(fName, "recreate");\n126: 					recreate.put(str, FileUtils.readFileToByteArray(f));\n127: 					diffs.remove(str);\n128: 					++total;\n129: 				}catch(Throwable ex) {\n130: 					System.err.println("ERROR: could not read '" + fName + "'!");\n131: 				}\n132: 			}\n133: 		}\n