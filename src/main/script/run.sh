#!/bin/sh
BASEDIR="$(dirname "$0")"

cd "$BASEDIR"

LIBRARY="$BASEDIR/lib"
JAR="$BASEDIR/lib/Vokabeltrainer.jar"

echo "BASEDIR:" $BASEDIR
echo "LIBRARY:" $LIBRARY
echo "JAR:" $JAR


java -Djava.library.path="$LIBRARY" -jar "$JAR"
