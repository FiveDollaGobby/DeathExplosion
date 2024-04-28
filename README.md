# DeathExplosion 
is a highly customizable Spigot plugin designed to enhance the gameplay experience on Minecraft servers by adding dynamic and visually striking effects to player deaths. When a player dies, they explode, creating a visual and physical impact in the game world.
 
## Features:
Configurable Explosions: Control the power of the explosion and whether it breaks 	blocks around the death site.

Customizable Particle Effects: Each explosion can trigger a unique particle effect, 	adding a dramatic flair to player deaths. Server administrators can configure the default particle effect, and players can select their personal preferences if granted permission.

Permission-Based Settings: Integration with server permissions allows fine-grained control over who can trigger explosions and customize their particle effects. This ensures that the feature can be reserved for certain players or events.

GUI for Particle Selection: A user-friendly GUI lets players choose their explosion particle effect from a variety of options, each represented by visually intuitive icons.

Configurable Particle Amounts: The density of the particle effects can be adjusted in the configuration file, allowing server admins to balance aesthetics with performance based on their server's capabilities.

![Alt Text](https://i.imgur.com/sJs1d1A.gif)
 
## How It Works:
Player Death: When a player with the appropriate permissions dies, an explosion is triggered at their location.

Explosion Effect: The explosion can break blocks and will display a particle effect, both of which are configurable through the config.yml file.

GUI Access: Players with permissions can open a GUI to select their preferred particle effect. This choice is saved to the server, making their selection persistent across sessions.

Permissions Management: Permissions control who can trigger explosions, use specific particle effects, and access the particle selection GUI.
 
## Configuration:
The config.yml file offers several settings:

explosionPower: Specifies the strength of the explosion.

breakBlocks: Determines if the explosion can break blocks.

defaultParticle: Sets the default particle effect used for explosions.

particleAmount: Controls the number of particles used in the effect.
 
## Installation and Setup:
Installation: Drop the plugin JAR file into your server's plugins directory.

Configuration: Adjust the config.yml file as needed.

Permissions: Set up permissions via your server's permission system to control access to various features.
 
 
## Permissions:
```  
  - dexplode.gui.access:
    - description: Allows access to the particle selection GUI
    
  - dexplode.explode:
    - description: Allows player to explode on death
  
  - dexplode.explode.EXPLOSION_HUGE:
    - description: Allows using EXPLOSION_HUGE particles
    
  - dexplode.explode.FLAME:
    - description: Allows using FLAME particles
    
  - dexplode.explode.SMOKE_LARGE:
    - description: Allows using SMOKE_LARGE particles
    
  - dexplode.explode.LAVA:
    - description: Allows using LAVA particles
    
  - dexplode.explode.HEART:
    - description: Allows using HEART particles
    
  - dexplode.explode.WATER_DROP:
    - description: Allows using WATER_DROP particles
    
  - dexplode.explode.VILLAGER_HAPPY:
    - description: Allows using VILLAGER_HAPPY particles
    
  - dexplode.explode.FIREWORKS_SPARK:
    - description: Allows using FIREWORKS_SPARK particles
    
  - dexplode.explode.MAGIC_CRIT:
    - description: Allows using MAGIC_CRIT particles
```


### TODO

- [ ] Explosion Damage Toggle
- [ ] Custom Explosion Shapes
- [ ] Economy Integration
- [ ] Randomized Particle Effect Option
- [ ] Visual Effect Intensity Levels
- [ ] Sound Effects
- [ ] WorldGuard Integration
- [ ] Player-Specific Settings Panel
- [ ] Logging and Analytics
- [ ] Event Triggers
