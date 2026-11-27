cls
echo Borrando clases viejas.
if exist out rmdir /s /q out
mkdir out
echo Compilando.
dir /s /b *.java > sources.txt
javac -d out @sources.txt
del sources.txt
echo Ejecutando.
java -cp out Main
echo Listo!