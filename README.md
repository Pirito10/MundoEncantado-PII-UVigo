# Mundo Encantado
_Mundo Encantado_ is a **Role-Playing Game** developed as part of the course "[Programación II](https://secretaria.uvigo.gal/docnet-nuevo/guia_docent/?centre=305&ensenyament=V05G301V01&assignatura=V05G301V01110&any_academic=2019_20)" in the Telecommunications Engineering Degree at the Universidad de Vigo (2019 - 2020).

## About The Project
This project simulates an enchanted world where players compete by managing creatures in a magical environment. The game incorporates concepts of object-oriented programming such as inheritance, polymorphism, and encapsulation, which were key learning objectives of the course.

The project features:
- A turn-based game system where players use creatures to battle.
- Modular design for extensibility.
- Text-based input/output using predefined files for creatures, players, and gameplay.

## How To Run
### Compilation
Make sure you have a [Java JDK](https://www.oracle.com/java/technologies/downloads/) installed on your system. Then compile all Java classes and generate the `.class` files with:
```bash
javac -d bin src/*.java
```
This command creates the compiled files inside the `bin/` directory.

### Execution
Once compiled, you can run the game in one of two modes: **Normal Mode** or **Instructions Mode**.

#### Normal Mode
```bash
java -cp bin MundoEncantado <-j f_jugadores> <-c f_criaturas> [-r f_reparto] [-o f_partida]
```
| Option | Description | Example |
|--------|-------------|---------|
| `-j f_jugadores` | File containing player data | `-j jugadores.txt` |
| `-c f_criaturas` | File containing creature data | `-c criaturas.txt` |
| `-r f_reparto` | File containing the allocation of creatures to players  | `-r reparto.txt` |
| `-o f_partida` | File where the results of the game will be written | `-o partida.txt` |
##### Example
```bash
java -cp bin MundoEncantado -j test/jugadores.txt -c test/criaturas.txt -r test/reparto.txt -o output.txt
```
---
#### Instructions Mode
```bash
java -cp bin MundoEncantado <-i f_instrucciones> [-o f_salida]
```
| Option | Description | Example |
|--------|-------------|---------|
| `-i f_instrucciones` | File containing instructions for the game execution | `-i instrucciones.txt` |
| `-o f_salida` | File where the results of the game will be written | `-o salida.txt` |
##### Example
```bash
java -cp bin MundoEncantado -i test/instrucciones.txt -o output.txt
```

## About The Code
Refer to [`Especificaciones.pdf`](docs/Especificaciones.pdf) for an in-depth explanation of the project, how the game works, the multiple ways of running the game, example files to use, and more.

_The current version is 1.0.0, so changes related to version 2.0.0 are not implemented._
