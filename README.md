# Play Java
A mini game written in Java, to practice OOP.

## Idea

"Fantastic Four" will be a upgraded version of the classis "Connect Four" game. Two players can each in return select a column where they want to drop a chip in there represented colour. By using a "Super Power Chip" a player will be empowered to overwrite a occupied field on the board. 

## Concept

![Fantastic_Four_Class_Diagram_Brainstorm](/image1.png "Fantastic Four Class Diagram Brainstorming")

![Fantastic_Four_Rules_Brainstorm](/image2.png "Fantastic Four Rules Brainstorming")

![Updated_Concept](/PuFProjekt.jpg "Updated Concept")

![Updated_fxml](/FantasticFour.jpg "Updated fxml")

## Rules of the Game

Two players can each in return select a column where they want to drop a chip in there represented colour. The chip will be placed in the last empty row of that column. If a field in a column is taken, the following chip will be placed in the row above this field in the same column. If one player uses a Super Power Chip, she can overwrite a occupied field.

## GUI Mockup

Using Scene Builder a first GUI design is proposed. Based on this [FXML file](./FantasticFour_v1.fxml) the GUI mockup looks like this: <br>

![GUI_Design_v1](/image3.png "Fantastic Four GUI Design from Scene Builder")

## Open Questions

How or when do players get a Super Power Chip? (game design related question) <br>
Can a Player select a random colour other then red or blue before the game starts? (UI related question)


