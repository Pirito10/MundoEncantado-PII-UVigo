# Mundo Encantado
Mundo Encantado is a **Role-Playing Game** developed as part of the course "[Programación II](https://secretaria.uvigo.gal/docnet-nuevo/guia_docent/index.php?centre=305&ensenyament=V05G301V01&assignatura=V05G301V01110&any_academic=2019_20)" in the Telecommunications Engineering Degree at the Universidad de Vigo (2019 - 2020).

## About The Project
This project simulates an enchanted world where players compete by managing creatures in a magical environment. The game incorporates concepts of object-oriented programming such as inheritance, polymorphism, and encapsulation, which were key learning objectives of the course.

The project features:
- A turn-based game system where players use creatures to battle.
- Modular design for extensibility.
- Text-based input/output using predefined files for creatures, players, and gameplay.

## How To Run
### Compilation
To compile all Java classes and generate the `.class` files, run:
```bash
javac -d bin src/*.java
```
This command creates the compiled files inside the `bin/` directory.

### Execution
Once compiled, you can run the game in one of two modes: **Normal Mode** or **Instructions Mode**.

#### Normal Mode
```bash
java -cp bin MundoEncantado -j f_jugadores -c f_criaturas [-r f_reparto] [-o f_partida]
```
| Option | Optional | Description |
|--------|----------|-------------|
| `-j f_jugadores` | No | Specifies the file containing player data |
| `-c f_criaturas` | No | Specifies the file containing creature data |
| `-r f_reparto` | Yes | Specifies the file containing the allocation of creatures to players  |
| `-o f_partida` | Yes | Specifies the file where the results of the game will be written |
##### Example
```bash
java -cp bin MundoEncantado -j test/jugadores.txt -c test/criaturas.txt -r test/reparto.txt -o output.txt
```
---
#### Instructions Mode
```bash
java -cp bin MundoEncantado -i f_instrucciones [-o f_salida]
```
| Option | Optional | Description |
|--------|----------|-------------|
| `-i f_instrucciones` | No | Specifies the file containing instructions for the game execution |
| `-o f_salida` | Yes | Specifies the file where the results of the game will be written |
##### Example
```bash
java -cp bin MundoEncantado -i test/instrucciones.txt -o output.txt
```

# About The Code
Refer to [**Especificaciones.pdf**](Especificaciones.pdf) for an in-depth explanation of the project, how the game works, the multiple ways of running the game, example files to use, and more.

_The current version is 1.0.0, so changes related to version 2.0.0 are not implemented._
