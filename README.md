# About The Project

Mundo Encantado (Role-Playing Game) project for "[Programación II](https://secretaria.uvigo.gal/docnet-nuevo/guia_docent/index.php?centre=305&ensenyament=V05G301V01&assignatura=V05G301V01110&any_academic=2019_20)" subject in Telecommunications Engineering Degree at Universidad de Vigo (2019 - 2020)

# Building The Project

- Run the following command to compile every .java into a .class file: `javac -d ./build *.java`

  - Now you can run `java MundoEncantado [args]` inside the build directory to run the game.

  - Or you can package everything in to a single .jar file with the following command, also inside the build directory: `jar cfm MundoEncando.jar manifest.txt *`

    - And then run the game with `java -jar MundoEncando.jar [args]`

# About The Code

Refer to [**Especificaciones.pdf**](Especificaciones.pdf) for an in-depth explanation of the project, how the game works, the multiple ways of running the game, example files to use, and more.

The current version is 1.0.0, so changes related to version 2.0.0 are not implemented.
