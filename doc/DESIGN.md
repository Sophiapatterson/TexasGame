#Design

###Names

Sophia Patterson, Luke Evans, Justin Wu, Jeffrey Luo

##Roles

Sophia:

Luke:

Justin: My role was to implement all of the classes in the "screens" package, which included classes such as "ChangeScreen", "EndScreen", etc. These classes were in charge of creating the javafx scenes that would make up the game. For instance, these classes created the start screen from which the user could choose to go to the tutorial chooser or the game chooser, or the end screen that would be displayed after losing in any of the games. I was also in charge of implementing the tutorials for these games, which I did by using the "GameWorld" classes for each game, as well as the "Tutorial" class in the "game" package inside of the "engine" package. 

Jeffrey: My role was to implement the engine API of the game, which included abstract classes and interfaces like Collidable,
Gravity, Player, Enemy, GameWorld, and GameManager. My role included extending upon these classes to implement our smaller
individual games. 

##Design Goals

The main design goals were to make it easy to implement a new game apart from the three that we had already implemented. In terms of how different that new game could be, we made it so a new game could differ from the three already-created games with respect to how it looked (the player and enemy sprites, background, etc), what the rules of the game were, how the player interacted with the different enemies, as well as what the controls were (be it a single jump like our Dinosaur game or a constant jump like in our Jetpack and Flappy games). 

##High-Level Design
We decided to split our project into 4 main packages: data, engine, screens, and view. 
  
The data package handles the data and configuration of our games. It is responsible for fetching files from the data folder, 
and processing the files into formats suitable for the engine. 
  
The engine package handles how the games work. It includes interfaces that are required for most
scrolling games, such as Collidable, Scrolling, and Gravity. It also includes abstract classes like Enemy and Player
that can be extended upon in individual games.  

The screens package handles the user experience and user interface , and generates the correct GameWorld or screen depending 
on the buttons that the user clicks.  

The view package handles the front-end for all the game elements in the engine. To allow communication between our engine and 
view classes, we utilized binding.

##Assumptions or Decisions


##How to Add New Feature
