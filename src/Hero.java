import java.util.Random;

public class Hero implements Fight {
    Random random = new Random();
    String name;
    int health, experience, gold, agility, strength;

    public Hero(String name, int agility, int strength) {
        this.name = name;
        this.health = 100;
        this.experience = 0;
        this.gold = 0;
        this.agility = agility;
        this.strength = strength;
    }

    // Метод атаки через интерфейс Fight
    @Override
    public int attack() {
        if (agility * 3 > random.nextInt(100)) {
            int damage = strength; // Успешная атака

            // Проверка на критический удар (15%)
            if (random.nextInt(100) < 15) {

                damage *= 2; //Удваивает урон при критическом ударе
                System.out.println("Критичиский урон! Урон: " + damage);
            }

            return damage;

        } else {
            // Промах
            return 0;
        }
    }

    // Метод получения урона через интерфейс Fight
    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
