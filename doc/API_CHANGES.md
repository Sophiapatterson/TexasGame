#API Changes

##Changes to Controllable API
The APIs we determined to be central to the game during the initial design sprint
were mostly unchanged. The Collidable and Gravity interfaces were implemented as
described. However, the Controllable API was not a part of our project. This was 
because of significant design decisions made around in the first week of the project
that made it unnecessary. Originally, we'd planned on creating a video game like
Super Mario, where the playable character could move across the screen but ultimately
decided to create a more diverse array of scrolling games where the player did not
move horizontally. Because of this, the Controllable API's functionality, like 
moveRight() and moveLeft() were not implemented.

##Other APIs
The final game included a robust class hierarchy with several distinct closed classes
and interfaces that could be considered APIs but were not fully brainstormed during
the initial design phase, as were Collidable and Gravity. See the Javadoc comments
on these classes for more information about their functionality.