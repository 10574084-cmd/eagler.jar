# eagler.jar

## JerryEagler

Development and compatibility notes for the independent JerryEagler port are tracked in [JERRY_PORT_PLAN.md](JERRY_PORT_PLAN.md).

## Eagler 1.8.8 private decompilation setup

This public repository does not contain the decompiled Minecraft source or its private MCP inputs. See [DECOMPILACION.md](DECOMPILACION.md) for the required local files and the safe BuildTools preparation script.

Run the script from a private working copy with:

```sh
bash scripts/decompile-eagler-private.sh /path/to/eagler-main
```

Do not commit private game/MCP inputs or generated decompiled source. This repository's `.gitignore` excludes the expected local input and output paths.
