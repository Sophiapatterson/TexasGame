## Use Cases

#### Jeffrey's use cases
1. Starting the tutorial: the user will click on a button on the main screen titled “Start tutorial”. This will run a pre-built level with instructions on the scene that describe the controls of the game, the obstacles of the game, as well as the objective of the game. The user can click a button to go back to the main screen, or complete the tutorial to begin the first level. 

2. Jumping: when the user presses space, the character will move upwards vertically a set amount. Because the character implements the Gravity interface, the character should fall back towards the ground after reaching the apex of the jump. Furthermore, the character will be able to be controlled while in the air (right and left). If the character collides with an object while jumping, the full jump will not be done, and normal rules of collision will be applied.

3. Moving left and right: when the user presses A or D, or Left arrow or Right arrow, the character will move a set speed towards the left or right. Depending on if the character hits a collidable object, such as a wall, the rules of collision will be applied and the character may not necessarily move.

4. Timer running out: If the timer hits zero while the user is still playing the level, the user loses the game. The user will be sent to a new screen with prompts to either restart or quit the game.

5. Finishing the game: Upon beating the last level, the user will be presented with a high score screen with the top 5 scores and the names of the people that achieved those scores. There will also be a button to go back to the main screen, or play the game again. If the user is among the top 5 scores, they will be prompted to enter a name to display their score. 

#### Justin's use cases

1. The user changes the game difficulty: The player will be able to choose from a number of difficulties during a level or before he/she decides to start a level. The difficulty will be increased by increasing the speed at which the enemies move, decreasing the character’s movement speed and jump distance, making the character smaller, among many other things. This could be its own class.

2. The user’s character loses all lives: Whenever the character loses all of its lives, the game will be over and the player will have to start from the beginning if he/she decides to keep playing. This would stop the game and display a screen with options of either quitting the game or restarting. This could be its own class.

3. The user’s character collides with a powerup: Whenever the character collides with a powerup, the powerup will be applied to the character depending on what the powerup’s effect is. We will most likely add a number of powerups, such as one that makes the character grow in size or invincible for a certain amount of time. This could be a method that would be a part of the Engine API as well, maybe as part of the Collidable class.

4. The user’s character moves toward the edge of the screen: If the character moves towards the left edge of the screen, it shouldn’t be able to move past it, as that’s the “beginning” of the scene. Regarding the right edge of the screen, for most levels it would be the case that if the character reaches the right edge of the screen, then the player has cleared that level. This could be a method or two that would be part of the Engine API.

5. The user’s character collides with an enemy: Depending on which side of the enemy the character collides with, there can be two results. If the character collides with the left or right sides of the enemy, then the character will lose a life. However, if the character collides with the top side of the enemy, then the enemy will be defeated by the character and the character will gain a certain amount of points for defeating it. This could be a method that would be a part of the Engine API as well, maybe as part of the Collidable class. 

###Luke's use cases
1. User launches the program. This starts all interaction with the program. It should lead to a landing screen in an appropriate language. The player can select several options, like a tutorial of the game, credits, start the game, view the high scores, or design their own levels. Each of these options leads to other screens. The HomeScreen can be its own class. The LevelBuilder can be its own class. The HighScore can be its own class. The credits can be contained within the HomeScreen. This will be launched from the main method. 

2. The user views the credits. This is done by selecting a button on the home screen. The credits for the game can appear in a pop-up. The text will be contained through a properties file to ensure easy translation. It will mention the authors, the class, link to github, etc. Closing the window returns the user to the home screen.

3. The user starts level one. This makes the actual game appear in a new window. The level starts with a landing screen that has a play button along with instructions. Starting the game will make the character appear in the center of a grid landscape.

4. The user selects the tutorial. This is much like the normal levels except there’s text or pop up windows to explain different features and the key controls used to navigate the game. (See the wireframe). This Tutorial class will be separate from the levels to ensure as much customization as possible, and the tutorial text will be stored in a properties file.

5. The user views the high score. This can be navigated to from the home screen that will display the data. Or it can be viewed from the WinScreen. This high score screen can be its own class. It will display scores from all users. These scores are their own class. The scores are exported to JSON data after a player completes all default levels.