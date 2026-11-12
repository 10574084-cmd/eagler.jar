#!/usr/bin/env bash
set -euo pipefail

usage() {
  echo "Uso: bash scripts/decompile-eagler-private.sh /ruta/a/eagler-main" >&2
  exit 2
}

SOURCE_ROOT="${1:-}"
[[ -n "$SOURCE_ROOT" ]] || usage
[[ -d "$SOURCE_ROOT" ]] || { echo "No existe la carpeta: $SOURCE_ROOT" >&2; exit 2; }

SOURCE_ROOT="$(cd "$SOURCE_ROOT" && pwd)"
BUILD_TOOLS="$SOURCE_ROOT/buildtools/BuildTools.jar"
MCP_DIR="$SOURCE_ROOT/mcp918"

[[ -s "$BUILD_TOOLS" ]] || { echo "Falta buildtools/BuildTools.jar" >&2; exit 1; }
[[ -d "$MCP_DIR" ]] || { echo "Falta el directorio mcp918/" >&2; exit 1; }

required=(
  "$MCP_DIR/mcp918.zip"
  "$MCP_DIR/1.8.8.jar"
  "$MCP_DIR/1.8.json"
)
missing=0
for file in "${required[@]}"; do
  if [[ ! -s "$file" ]]; then
    echo "Falta el archivo requerido: $file" >&2
    missing=1
  fi
done
if [[ "$missing" -ne 0 ]]; then
  echo "No se inicio la decompilacion. Consulta DECOMPILACION.md." >&2
  exit 1
fi

command -v java >/dev/null 2>&1 || { echo "Java no esta instalado o no esta en PATH." >&2; exit 1; }
java -version

echo "Iniciando BuildTools init en una carpeta de trabajo privada:"
echo "$SOURCE_ROOT"
echo "Los archivos generados se quedaran dentro del entorno de trabajo y no se subiran a GitHub."
cd "$SOURCE_ROOT"
exec java -jar "$BUILD_TOOLS" init
