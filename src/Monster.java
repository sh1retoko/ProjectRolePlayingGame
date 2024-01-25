import java.util.Random;

public class Monster implements Fight {
    Random random = new Random();
    String type;
    int health, strength;

    public Monster(String type, int strength) {
        this.type = type;
        this.health = 20;
        this.strength = strength;
    }

    @Override
    public int attack() {
        return random.nextInt(strength) + 1;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
