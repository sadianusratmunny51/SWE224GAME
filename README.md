# 🎮 Soaring Adventure

> **Fly. Explore. Collect. Survive. Unlock the Night.**

**Soaring Adventure** is a 2D arcade-style adventure game developed using **Java and LibGDX**. The game challenges players to navigate through multiple levels, avoid obstacles, collect valuable items, achieve target scores, and progressively unlock new stages.

The game features **three levels**, each introducing different objectives and challenges. Players begin their journey in the first level, progress through a coin-collection challenge, and eventually unlock the special **Night Mode**.

---

## ✨ Features

* 🎮 Three-level 2D adventure gameplay
* 🕊️ Player-controlled flying character
* ⌨️ Keyboard-based movement
* 🚧 Dynamic obstacles
* 💰 Coin collection system
* ⭐ Bonus item collection
* 🎒 Level 1 bag-collection objective
* 📈 Score tracking
* 🌙 Night Mode / Level 3
* 💥 Collision detection
* ❌ Game Over system
* ⏸️ Pause and resume gameplay
* 🔄 Restart functionality
* 🏠 Main menu navigation
* 📖 Introduction and Help sections
* 🏆 Level completion system
* 🔊 Background music and sound effects
* 🎨 Custom game graphics and animations

---

# 🎯 Game Objective

The main objective of **Soaring Adventure** is to successfully navigate through the game while avoiding obstacles and completing the objectives of each level.

The game progresses through three stages:

```text
                    SOARING ADVENTURE
                           │
                           ▼
                     ┌───────────┐
                     │  Level 1  │
                     └─────┬─────┘
                           │
                    Complete objective
                           │
                           ▼
                     ┌───────────┐
                     │  Level 2  │
                     └─────┬─────┘
                           │
                    Collect required
                         coins
                           │
                           ▼
                     ┌───────────┐
                     │  Level 3  │
                     │ Night Mode│
                     └───────────┘
```

---

# 🕹️ How to Play

The player controls the flying character using the keyboard.

### Controls

| Key            | Action     |
| -------------- | ---------- |
| ⬆️ Up Arrow    | Move Up    |
| ⬇️ Down Arrow  | Move Down  |
| ⬅️ Left Arrow  | Move Left  |
| ➡️ Right Arrow | Move Right |

The player must use these controls to navigate through the environment, avoid obstacles, and collect useful items.

---

# 🏆 Levels

## 🌤️ Level 1 — Start the Adventure

The first level introduces the player to the core gameplay mechanics.

The player needs to navigate through the environment while avoiding obstacles and collecting bonus items.

### Level 1 includes:

* Flying character movement
* Moving/scrolling background
* Obstacles
* Collision detection
* Score system
* Bonus items
* Bag collection objective

### Scoring

Different interactions affect the player's score.

* ⭐ Collecting a bonus item awards **500 points**
* 💥 Certain obstacle collisions reduce the score by **50 points**

After achieving the required progress and collecting the required objective, the player can proceed to the next level.

---

# 🪙 Level 2 — Coin Challenge

After completing Level 1, the player progresses to the second level.

Level 2 introduces a new objective: **collect coins and achieve the required number of coins to progress.**

### Level 2 includes:

* 🪙 Coin collection
* 🚧 Obstacles
* 📈 Score tracking
* 🕊️ Player movement
* 🎯 Coin-based progression

The player must collect the required number of coins to unlock the final level.

Once the required number of coins has been collected, the game displays a completion message and progresses to Level 3.

---

# 🌙 Level 3 — Night Mode

The final stage introduces the game's special **Night Mode**.

After successfully completing the previous objectives, the player reaches the final stage with a night-themed environment.

### Night Mode features:

* 🌙 Night-themed environment
* ⭐ Star-filled background
* 🎉 Completion presentation
* 🏆 Final level progression
* 🎊 Congratulations message

Successfully reaching this stage represents the completion of the main adventure.

---

# 💥 Collision & Game Over

The game includes collision detection between the player and obstacles.

Depending on the obstacle, a collision can:

* Reduce the player's score
* Affect gameplay
* Eventually result in a Game Over

When the game ends, the player is taken to the **Game Over** screen.

The game provides options to restart or leave the current game session.

---

# ⏸️ Pause & Restart

Players can pause the game during gameplay.

The pause system provides a **Game Paused** state and allows the player to continue managing their game session.

Players can also restart the game when necessary.

This makes it possible to temporarily stop gameplay without immediately losing progress.

---

# 🏠 Main Menu

The main menu acts as the entry point to the game.

The menu provides access to:

* ▶️ Play
* 📖 Introduction / Help
* ❌ Exit

The main menu also contains animated visual elements that help establish the game's atmosphere before gameplay begins.

---

# 📖 Introduction & Help

The game includes dedicated **Introduction** and **Help** screens.

The Help section explains the basic game progression and provides information about the three-level structure.

The overall progression is:

```text
Level 1
  ↓
Complete Level 1 Objective
  ↓
Level 2
  ↓
Collect Required Coins
  ↓
Level 3
  ↓
Night Mode
```

---

# 🔊 Sound & Audio

The game includes audio effects to make gameplay more interactive.

Different sounds are used for events such as:

* 🖱️ Button clicks
* 💥 Collisions
* ⭐ Bonus collection
* 🪙 Coin collection
* 🏆 Level completion
* 🎉 Game completion
* 🎵 Background music

---

# 🎨 Game Assets

The project contains custom game assets used throughout the game.

These include:

* Backgrounds
* Player/game characters
* Obstacles
* Coins
* Bonus objects
* Buttons
* Level graphics
* Game Over graphics
* Fonts
* Sound effects
* Background music

---

# 🛠️ Technology Stack

## Programming Language

* **Java**

## Game Development Framework

* **LibGDX**

## Build System

* **Gradle**

## Platform

* Desktop

## Graphics

* LibGDX 2D rendering
* Texture-based game assets
* Sprite and animation handling

## Audio

* LibGDX audio system
* Background music
* Sound effects

---

# 🏗️ Project Architecture

The project follows a modular LibGDX structure.

```text
Soaring Adventure
│
├── core
│   │
│   ├── Game Logic
│   ├── Screens
│   ├── Player Movement
│   ├── Collision Detection
│   ├── Score System
│   ├── Level Management
│   ├── Audio
│   └── UI
│
├── desktop
│   │
│   └── Desktop Launcher
│
└── assets
    │
    ├── Images
    ├── Backgrounds
    ├── Characters
    ├── Obstacles
    ├── Coins
    ├── Buttons
    ├── Fonts
    └── Sounds
```

---

# 📂 Project Structure

The main project is divided into the following components:

```text
SWE224GAME/
│
├── core/
│   └── src/
│       └── com/
│           └── mygdx/
│               └── game/
│                   ├── SoaringAdventure.java
│                   ├── MainMenuScreen.java
│                   ├── HelpScreen.java
│                   ├── GameScreen.java
│                   ├── GameScreen2.java
│                   ├── Level2Screen.java
│                   ├── Level3Screen.java
│                   ├── GameOverScreen.java
│                   ├── BonusIteam.java
│                   ├── BonusPoints.java
|                   ├── Coins.java
|                   ├── MovingObject.java
|                   ├── NightMood.java
|                   ├── Obstacle.java
|                   ├── StarIteam.java
|                   ├── TemporaryMessage.java
|                     
├── desktop/
│   └── Desktop launcher files
│
├── assets/
│   ├── Images
│   ├── Backgrounds
│   ├── Sounds
│   ├── Music
│   └── Fonts
│
├── gradle/
│
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

---

# 🚀 Getting Started

## Prerequisites

Before running the project, make sure you have:

* Java Development Kit (JDK)
* Gradle or the included Gradle Wrapper
* A Java-compatible IDE such as:

  * IntelliJ IDEA
  * Eclipse
  * Android Studio

---

## 📥 Clone the Repository

```bash
git clone https://github.com/sadianusratmunny51/SWE224GAME.git
```

Navigate into the project:

```bash
cd SWE224GAME
```

---

## ▶️ Run the Game

The project includes a Gradle wrapper, so you can use the provided Gradle commands.

On Windows:

```bash
gradlew desktop:run
```

On macOS/Linux:

```bash
./gradlew desktop:run
```

Alternatively, import the project into a compatible Java IDE and run the desktop launcher.

---

# 🎮 Gameplay Flow

The complete gameplay flow can be summarized as:

```text
                 ┌─────────────────┐
                 │   Main Menu     │
                 └────────┬────────┘
                          │
                    Press Play
                          │
                          ▼
                 ┌─────────────────┐
                 │     Level 1     │
                 │                 │
                 │ Avoid Obstacles │
                 │ Collect Bonuses │
                 │ Collect Bag     │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │     Level 2     │
                 │                 │
                 │ Collect Coins   │
                 │ Avoid Obstacles │
                 └────────┬────────┘
                          │
                   Required Coins
                          │
                          ▼
                 ┌─────────────────┐
                 │     Level 3     │
                 │   Night Mode    │
                 │                 │
                 │ Final Stage     │
                 └─────────────────┘
```

---

# 🎯 Project Goals

The main goals of **Soaring Adventure** are:

* To create an engaging 2D arcade-style game.
* To implement multiple levels with progressive difficulty/objectives.
* To implement player movement and collision detection.
* To introduce scoring and collectible mechanics.
* To provide an interactive game interface.
* To incorporate sound effects and background music.
* To demonstrate game development concepts using Java and LibGDX.


---

# 👨‍💻 Project Information

| Information      | Details           |
| ---------------- | ----------------- |
| **Project Name** | Soaring Adventure |
| **Course**       | SWE224            |
| **Project Type** | 2D Adventure Game |
| **Language**     | Java              |
| **Framework**    | LibGDX            |
| **Build System** | Gradle            |
| **Platform**     | Desktop           |

---

# 📄 License

This project was developed as an academic project for educational purposes.
