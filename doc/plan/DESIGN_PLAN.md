## Design Plan

### Introduction
The goal of this project is to write a program that can easily implement different kinds of platform games. Where the program will be most flexible will be in the data. An example where this flexibility should show is in the ease at which new levels can be configured. The primary architecture of the design should follow the Model View Controller design pattern, and use three main APIs: Engine, Data, and Player. 

Our design goals are to use interfaces and class hierarchies in a way such that the program is flexible and cleanly written. We plan to be able to have an easily extendable design that allows for different implementations of platform games (i.e. different level tilesets, enemies, rules, etc.).

### Overview

The modules that we plan to create are 

### Design Details



### Example games
Three varying games that our program should be able to emulate are Flappy Bird, Google Chrome Dinosaur game, and Jetpack Joyride.

To implement any of these games, we should be able to interchange sprites in the data folder, and still have a functional game. Furthermore, to deal with different types of obstacles / enemies, we will have obstacle or enemy classes that extend from respective abstract classes. These classes will implement the Collidable interface in their own way, which will allow us to handle the different interactions that come with these various obstacles and enemies. The game should build its levels using csv files, and so creating different tilesets that work for these different games should be no problem as well.

### Design Considerations
One design decision that was discussed was how much information we wanted to convey in our data files, specifically concerning the configuration of difficulty for the game. We ended up deciding on making it so that we would have a properties file that detailed the level of difficulty for the game. This properties file would also contain a csv file which would be the default layout of the level, with enemies included. If the properties file stated that the level of difficulty was "EASY", then the enemies in the csv file would just not be loaded. Pros of this decision would be that we would be able to reduce the amount of datafiles needed for a game, but cons for this decision would be that there would only be an "EASY" or "NORMAL" difficulty.