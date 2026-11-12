# Preparar la decompilacion privada de Eagler 1.8.8

Este repositorio contiene el parche independiente de Jerry y herramientas para preparar el proceso. No contiene el codigo Java decompilado de Minecraft ni los recursos originales.

## Archivos de entrada requeridos

El paquete Eagler suministrado incluye BuildTools y el directorio `mcp918/`, pero este directorio solo trae `assetsIndexTransformer.json` y este README de MCP. Para ejecutar `init`, el propio archivo `mcp918/readme.txt` solicita que el usuario aporte:

- `mcp918/mcp918.zip`: Mod Coder Pack para Minecraft 1.8.8.
- `mcp918/1.8.8.jar`: JAR de Minecraft 1.8.8.
- `mcp918/1.8.json`: indice de assets de Minecraft 1.8.

Consigue esos archivos solo de una fuente a la que tengas acceso y derecho de uso. No los subas a este repositorio publico ni a Pages.

## Ejecutar en una copia de trabajo privada

1. Descarga y extrae el archivo fuente Eagler autorizado.
2. Coloca los tres archivos anteriores dentro de su carpeta `mcp918/`.
3. Copia este repositorio o su script `scripts/decompile-eagler-private.sh` dentro de tu entorno de trabajo.
4. Ejecuta desde una terminal con Java 11 o superior:

```sh
bash scripts/decompile-eagler-private.sh /ruta/a/eagler-main
```

El script valida los archivos y ejecuta el comando oficial `java -jar buildtools/BuildTools.jar init` dentro de la carpeta de trabajo que indiques. BuildTools prepara sus archivos temporales localmente.

## Limites

- El script no copia ni sube el codigo decompilado a GitHub.
- No crea un cliente Pages ni publica un artefacto.
- La autorizacion para portar el mod Jerry no concede por si sola derechos sobre Minecraft, sus recursos o el cliente Eagler.
- Antes de publicar el cliente, verifica por separado que tienes permiso para distribuir cada componente incluido.

El siguiente paso tecnico, cuando los archivos privados esten disponibles, es comprobar que BuildTools termina `init` correctamente y luego que el parche Jerry se aplica al arbol de fuentes real. No se debe declarar un cliente jugable hasta completar una compilacion completa y una prueba de ejecucion.
