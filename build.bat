@echo off

rmdir /q /s build

mkdir build\lib

dir /s /b *.java > build\filenames.txt

findstr funcinterfaces build\filenames.txt > build\func.txt 

javac -Xlint:deprecation @build\func.txt -d build\classes

jar -c -f build/lib/funcinterfaces.jar -C build\classes .

jar -f build/lib/funcinterfaces.jar -d

java -p build/lib -m functinal.module/edu.chinna.kadhira.func.ExerciseOne

rem java -p build/lib -m functinal.module/edu.chinna.kadhira.Filtering

rem java -p build/lib -m functinal.module/edu.chinna.kadhira.eam.EAMPattern

rem java -p build/lib -m functinal.module/edu.chinna.kadhira.Func

