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
Using this program successfully relies on several assumptions. To run an instance of the generic
(data-driven) game, the properties file must contain all 36 key/value pairs with correct spelling on the keys. 
For proper functionality of the leaderboard, a user should not enter their name containing a "-" or
the parsing of the name may be disrupted. CSV level files are parsed robustly to overcome formatting errors,
but a user should follow this format to ensure their files are used correctly in the game:
the first line should only have one integer that represents the total length of the level, 
the following lines should be comma separated.


##How to Add New Feature
To add new features to our project, you would add a new class to the “game” package under the “engine” package if it’s something that isn’t already there. For example, if the new feature is a new enemy or a new powerup, then a new class under this package wouldn’t be necessary, as those classes already exist and the class for the new feature could just extend this class. If a new class would be required, this class would contain methods that would deal with its behavior in game, be it its x and y coordinates, whether or not it collides with the user, if it adds to the player’s score, etc. Then, if the feature is supposed to be viewable inside the game, a new class would have to be created inside of the “view” package if the new feature isn’t an enemy or a powerup. This class would extend the abstract class “View”, and it would have to do with the new feature’s JavaFX images, such as its width and height, as well as its x and y coordinates inside of the game. This class would also bind the new feature’s view to the new feature’s backend object. Finally, this feature would be added to your desired GameWorld by adding the feature’s backend objects to the GameWorld’s Group, as this would show the feature while playing the game. 