#Design

###Names

Sophia Patterson, Luke Evans, Justin Wu, Jeffrey Luo

##Roles

Sophia: My role was to implement all aspects of the game's View. More specifically, I implemented the abstract View class, which is inherited by EnemyView, PlayerView, PowerupView, and GenericPlayerView. Via these classes, I worked to design code that could be flexibly used in any future games. I implemented binding to connect backend and frontend objects.

Luke: My role was to implement the data package API, which managed reading the CSV data for the game levels, the properties files for the leaderboard scores, and the error handling for the IO of the game. These classes include HighScores, Score, GameConfiguration, each implementation of the GameConfiguration abstract class, and the custom LevelFileException. I also implemented the generic game package, which is the highly data-driven version of our game where game rules and GUI elements are drawn from a properties file.

Justin: My role was to implement all of the classes in the "screens" package, which included classes such as "ChangeScreen", "EndScreen", etc. These classes were in charge of creating the javafx scenes that would make up the game. For instance, these classes created the start screen from which the user could choose to go to the tutorial chooser or the game chooser, or the end screen that would be displayed after losing in any of the games. I was also in charge of implementing the tutorials for these games, which I did by using the "GameWorld" classes for each game, as well as the "Tutorial" class in the "game" package inside of the "engine" package. 

Jeffrey: My role was to implement the engine API of the game, which included abstract classes and interfaces like Collidable,
Gravity, Player, Enemy, GameWorld, and GameManager. My role included extending upon these classes to implement our smaller
individual games. 

##Design Goals

The main design goals were to make it easy to implement a new game apart from the three that we had already implemented. In terms of how different that new game could be, we made it so a new game could differ from the three already-created games with respect to how it looked (the player and enemy sprites, background, etc), what the rules of the game were, how the player interacted with the different enemies, as well as what the controls were (be it a single jump like our Dinosaur game or a constant jump like in our Jetpack and Flappy games). 

##High-Level Design

##Assumptions or Decisions

##How to Add New Feature
