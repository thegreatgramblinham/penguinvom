package MainGame;

import GameObjects.Characters.Enemies.EnemyBase;
import MainGame.Random.Randomizer;
import SectorBase.Sector;

import java.awt.*;

class EnemySpawner
{
    //Private Constants
    private final int SPAWN_ZONE_WIDTH = 20;

    //Private Variables
    private Sector _spawnSector;
    private Rectangle _spawnableArea;
    private Rectangle[] _spawnZones;

    private EnemyBase[] _spawnableEnemies;

    private Randomizer r;

    private int _spawnTimer;
    private int _spawnTimerReset;


    //Constructor
    public EnemySpawner(Sector spawnSector, EnemyBase[] spawnableEnemies,
                        Rectangle spawnableArea, int spawnTimerReset)
    {
        _spawnSector = spawnSector;
        _spawnableArea = spawnableArea;
        _spawnableEnemies = spawnableEnemies;
        _spawnTimerReset = spawnTimerReset;
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public boolean ShouldSpawn()
    {
        if(_spawnTimer < _spawnTimerReset)
        {
            _spawnTimer++;
            return false;
        }
        else
        {
            _spawnTimer = 0;
            return true;
        }
    }

    public void SpawnRandom()
    {
        //Randomly select zone
        //Rectangle zone = _spawnZones[r.nextInt(_spawnZones.length)];
        Rectangle zone = _spawnZones[1];

        //Randomly select enemy
        EnemyBase enemy = _spawnableEnemies[r.nextInt(_spawnableEnemies.length)];

        int xLoc = zone.x + r.nextInt(15);
        int yLoc = r.nextInt(zone.y, enemy.height);

        EnemyBase clonedEnemy = (EnemyBase)enemy.clone();
        clonedEnemy.NSetLocation(new Point(xLoc, yLoc));

        _spawnSector.AddObject(clonedEnemy, 2);
    }

    //Private Methods
    private void Init()
    {
        r = new Randomizer();
        InitSpawnZones();
    }

    private void InitSpawnZones()
    {
        _spawnZones = new Rectangle[2];

        //Left
        _spawnZones[0]
                = new Rectangle(
                    0,
                    _spawnableArea.y,
                    SPAWN_ZONE_WIDTH,
                    _spawnableArea.height);
        //Right
        _spawnZones[1]
                = new Rectangle(
                    _spawnableArea.x + _spawnableArea.width - SPAWN_ZONE_WIDTH,
                    _spawnableArea.y,
                    SPAWN_ZONE_WIDTH,
                    _spawnableArea.height);
    }
}
