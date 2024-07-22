# My CPSC 210 Personal Project

## What the Application does
The application that I am planning to make is a **game application**. The specific game that I am planning to make is a 
rhythm-based game where the user would have to time keyboard inputs based on a sequence shown on the 
graphical interface. The sequence is sometimes accompanied by music, which can be done through 
the use of Java's MidiSynth. This game would challenge a player's sense of rhythm by allowing the 
user to interact with keyboard presses. This game will have pre-defined lanes from which shapes
will fall down from, and when a shape overlaps with a stationary indicator, the user would have to
press the corresponding key. If timed properly, the shape would disappear and the user would gain points.
Missed shapes would cost the user one of their lives, and when this count reaches 0, the game ends.

## Who can use this Application?
Since this application is a game, it's main purpose is entertainment, and thus it is suitable for any
user - especially those who would like to challenge their sense of rhythm.

## My Interest in this Project
My interest in this specific project -
designing a rhythm game - stems from my interest in music and video games as this application incorporates
aspects from both subjects. This is also the first project I have ever created by myself, so I am extremely excited and 
interested as this will be my first step towards creating more projects in the future. All the knowledge and design
choices I make for this project are lessons and skills that I will be able to bring to the future, which
makes this a great learning opportunity.

## User Stories
As a user, I want to be able to:
- view a list of commands to know what keys correspond to which indicators
- visually observe when I am supposed to press a key
- keep track of my current score
- know how many missed shapes are left until I lose and the level finishes
- determine how many notes I want to play with (adds this number of notes to a list of notes)
- add my score to a list of previous scores and view this list
- access a main menu to choose what to do next
- know what the highest level I have beaten so far is
- keep track of my score on each level

**Data Persistence User Stories**
- As a user, before I want to exit the application, I want to be able to save the current state of the level
- As a user, I want the option to load my progress from an earlier session

## Phase 4: Task 2
Example of EventLog printed to the console:

Thu Nov 25 19:18:48 PST 2021\
Game progress saved\
Thu Nov 25 19:18:49 PST 2021\
Game progress loaded\
Thu Nov 25 19:18:53 PST 2021\
Indicator added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Indicator added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Indicator added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Indicator added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Note added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Note added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Note added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Note added to the current level\
Thu Nov 25 19:18:53 PST 2021\
Note added to the current level\
Thu Nov 25 19:18:54 PST 2021\
Score decreased\
Thu Nov 25 19:18:54 PST 2021\
Score decreased\
Thu Nov 25 19:18:55 PST 2021\
Score decreased\
Thu Nov 25 19:18:55 PST 2021\
Score decreased\
Thu Nov 25 19:18:56 PST 2021\
Active note removed from a well-timed key press\
Thu Nov 25 19:18:56 PST 2021\
Score increased\
Thu Nov 25 19:18:57 PST 2021\
Active note removed from a well-timed key press\
Thu Nov 25 19:18:57 PST 2021\
Score increased\
Thu Nov 25 19:18:58 PST 2021\
Counter decreased by 1\
Thu Nov 25 19:18:58 PST 2021\
Active note removed because it is out of bounds\
Thu Nov 25 19:18:59 PST 2021\
Counter decreased by 1\
Thu Nov 25 19:18:59 PST 2021\
Active note removed because it is out of bounds\
Thu Nov 25 19:18:59 PST 2021\
Counter decreased by 1\
Thu Nov 25 19:18:59 PST 2021\
Active note removed because it is out of bounds\
Thu Nov 25 19:18:59 PST 2021\
All notes removed because the game is over





