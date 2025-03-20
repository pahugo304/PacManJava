package main.java.fr.ynov.pacman.domain.entity;

public abstract class Enemy extends Entity
{
    protected EnemyState state;

    public Enemy(int x, int y, int speed) {
        super(x, y, speed);
        this.state = EnemyState.CHASE;
    }

    public abstract void changeState(EnemyState newState);
    public abstract void chase(Pacman target);
}
