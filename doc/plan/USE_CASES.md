## Use Cases

#### Jeffrey's use cases
1. Starting the tutorial: the user will click on a button on the main screen titled “Start tutorial”. This will run a pre-built level with instructions on the scene that describe the controls of the game, the obstacles of the game, as well as the objective of the game. The user can click a button to go back to the main screen, or complete the tutorial to begin the first level. 

2. Jumping: when the user presses space, the character will move upwards vertically a set amount. Because the character implements the Gravity interface, the character should fall back towards the ground after reaching the apex of the jump. If the character collides with an object while jumping, the normal rules of collision will be applied.

3. PowerUp: when the user runs into a specific powerup object, the user's character will obtain some special power such as slowed time, invincibility, or decreased size. The powerup will be applied for a period of 15 seconds. 

4. Timer running out: If the timer hits zero while the user is in the bonus level, the user finishes the game. The user will be sent to the final screen, where they can view high scores and restart the game.

5. Finishing the game: Upon beating the last level, the user will be presented with a high score screen with the top 5 scores and the names of the people that achieved those scores. There will also be a button to go back to the main screen, or play the game again. If the user is among the top 5 scores, they will be prompted to enter a name to display their score. 

#### Justin's use cases

1. The user changes the game difficulty: The player will be able to choose from a number of difficulties during a level or before he/she decides to start a level. The difficulty will be increased by increasing the speed at which the enemies move, decreasing the character’s movement speed and jump distance, making the character smaller, among many other things. This could be its own class.

2. Pause/Play. The user selects “pause” during any level of the game. All game movements will be paused so the user may take a break. When the user selects “play”, all game movements will resume as usual.

3. The user collides with the top of the screen: The jump will not be fully completed, and the character will fall towards the ground with the usual gravitational speed. 

4. The user's character collides with an obstacle: Whenever the character collides with an obstacle, the player loses the game. This could be a method that would be part of the Engine API, maybe as part of the Collidable class.

5. The user’s character collides with an enemy: Enemies are similar to obstacles except for the fact that enemies may move. On collision, the player loses game similar to hitting an obstacle.

###Luke's use cases
1. User launches the program. This starts all interaction with the program. It should lead to a landing screen in an appropriate language. The player can select several options, like a tutorial of the game, credits, start the game, view the high scores, or design their own levels. Each of these options leads to other screens. The HomeScreen can be its own class. The LevelBuilder can be its own class. The HighScore can be its own class. The credits can be contained within the HomeScreen. This will be launched from the main method. 

2. The user views the credits. This is done by selecting a button on the home screen. The credits for the game can appear in a pop-up. The text will be contained through a properties file to ensure easy translation. It will mention the authors, the class, link to github, etc. Closing the window returns the user to the home screen.

3. The user starts level one. This makes the actual game appear in a new window. The level starts with a landing screen that has a play button along with instructions. Starting the game will make the character appear in the center of a grid landscape.

4. The user selects the tutorial. This is much like the normal levels except there’s text or pop up windows to explain different features and the key controls used to navigate the game. (See the wireframe). This Tutorial class will be separate from the levels to ensure as much customization as possible, and the tutorial text will be stored in a properties file.

5. The user views the high score. This can be navigated to from the home screen that will display the data. Or it can be viewed from the WinScreen. This high score screen can be its own class. It will display scores from all users. These scores are their own class. The scores are exported to JSON data after a player completes all default levels.

## Sophia's use cases

1. Settings Button. The user will be playing the game. The user will click the “settings” button and will be presented with the option to “return home”, “switch game”, or “return to game”. 

2. Home Screen/Game Selection Screen. The user will be presented with three game buttons, including “Flappy Bird”, “Jetpack Joyride”, and “Dinosaur”. The user will click on one of the options, and then an instance of the selected game will be launched.

3. Coin System. The user will collect coins throughout the duration of any given game. A user also collects coins after the completion of a bonus round. This coin tally is synonymous with the user’s score, which will be recorded at the end of the game if the user is among the top 5 scorers. 

4. Dark Mode. The player selects a button called “dark mode”. All aspects of the graphic user interface, including the screen background, text, and theme, will change to a darker tint. The user can change back to the original “light mode” if desired.

5. Bonus Round. If the user successfully plays the game for a minimum of 120 seconds, the user will be given an extra opportunity to gain points via coin collection. After dying, the user will be taken to a final “Bonus Round” wherein the user will have a limited time to collect any number of coins available. The user’s final score will be a tally of their total time playing the game in addition to the number of points achieved from the coins they collect through the bonus round. 
