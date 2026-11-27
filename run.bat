@echo off
echo Compilando...

if exist out rmdir /s /q out
mkdir out

dir /s /b *.java > sources.txt
javac -d out @sources.txt
if errorlevel 1 (
    echo.
    echo ERROR: no compilo. Revisa los mensajes de arriba.
    del sources.txt
    pause
    exit /b 1
)
del sources.txt

echo Listo. Ejecutando...
echo.
java -cp out Main
pause