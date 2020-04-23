
##SPRINT 1- Dinosaur

Engine, Primary: Jeff, Secondary: Sophia

Player, Primary: Sophia, Secondary: Luke

Data, Primary: Justin and Luke, Secondary: Jeff

Screens, Primary: Luke, Secondary: Justin

Features to implement in this sprint: StartScreen, EndScreen (with highscores), GameSelectionScreen, GameConfiguration, Camera, Score, Character, Obstacle, Game, Coin

###Example use cases for Sprint 1:

User launches the program: This starts all interaction with the program. It should lead to a landing screen in an appropriate language. The player can select several options, like a tutorial of the game, credits, start the game, view the high scores, or design their own levels. Each of these options leads to other screens. The HomeScreen can be its own class. The LevelBuilder can be its own class. The HighScore can be its own class. The credits can be contained within the HomeScreen. This will be launched from the main method.

Jumping: when the user presses space, the character will move upwards vertically a set amount. Because the character implements the Gravity interface, the character should fall back towards the ground after reaching the apex of the jump. If the character collides with an object while jumping, the normal rules of collision will be applied.

Home Screen/Game Selection Screen. The user will be presented with three game buttons, including “Flappy Bird”, “Jetpack Joyride”, and “Dinosaur”. The user will click on one of the options, and then an instance of the selected game will be launched.

## SPRINT 2 - Jetpack Joyride / Flappy Bird

Engine, Primary: Jeff, Secondary: Sophia

Player, Primary: Sophia, Secondary: Luke

Data, Primary: Justin and Luke, Secondary: Jeff

Screens, Primary: Luke, Secondary: Justin

Features to implement in this sprint: Credits, Tutorial, PowerUps (Coin, DeluxeCoin, Slower), Enemies (StandardEnemy), GUI Editor, High Scores, More Levels

###Example use cases for Sprint 2:

PowerUp: when the user runs into a specific powerup object, the user's character will obtain some special power such as slowed time, invincibility, or decreased size. The powerup will be applied for a period of 15 seconds.

The user selects the tutorial: This is much like the normal levels except there’s text or pop up windows to explain different features and the key controls used to navigate the game. (See the wireframe). This Tutorial class will be separate from the levels to ensure as much customization as possible, and the tutorial text will be stored in a properties file.

Bonus Round. If the user successfully plays the game for a minimum of 120 seconds, the user will be given an extra opportunity to gain points via coin collection. After dying, the user will be taken to a final “Bonus Round” wherein the user will have a limited time to collect any number of coins available. The user’s final score will be a tally of their total time playing the game in addition to the number of points achieved from the coins they collect through the bonus round.