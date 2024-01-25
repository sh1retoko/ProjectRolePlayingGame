import java.util.Random;
import java.util.Scanner;

public class World implements Game {
    Hero player;
    Monster currentMonster;
    Trader trader = new Trader();
    Scanner scanner = new Scanner(System.in);

    public World() {
        System.out.println("Добро пожаловать в игру!");
        System.out.print("Введите имя персонажа: ");
        String playerName = scanner.nextLine();
        player = new Hero(playerName, 10, 5);
    }

    // Запуск с выбором действий
    @Override
    public void start() {
        while (true) {
            System.out.println("\nКуда желаете отправиться?");
            System.out.println("1. Посетить торговца");
            System.out.println("2. Отправиться в подземелье");
            System.out.println("3. Выход");
            System.out.print("Выбор: ");



            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    visitTrader();
                    break;
                case 2:
                    startBattle();
                    break;
                case 3:
                    System.out.println("Выход из игры");
                    System.exit(0);
                default:
                    System.out.println("Ошибка! Выберите из предоставленных действий (1, 2, 3)");
            }
        }
    }

    // Торговец
    @Override
    public void visitTrader() {
        System.out.println("\nДобро пожаловать к торговцу");
        System.out.println("1. Купить зелье здоровья");
        System.out.println("2. Покинуть торговца");
        System.out.print("Выбор: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                trader.sellPotion(player);
                break;
            case 2:
                System.out.println("Покинули торговца");
                break;
            default:
                System.out.println("Ошибка! Выберите из предоставленных действий (1, 2)");
        }
    }

    // Битва с монстрами
    @Override
    public void startBattle() {
        int random = new Random().nextInt(2);
        String monsterType = (random == 0) ? "Goblin" : "Skeleton";
        currentMonster = new Monster(monsterType, 8);

        System.out.println("На вашем пути " + monsterType);
        System.out.println();

        while (player.health > 0 && currentMonster.health > 0) {
            // Ход игрока
            int playerDamage = player.attack();
            if (playerDamage > 0) {
                currentMonster.takeDamage(playerDamage);
                System.out.println("Вам нанесли " + playerDamage + "  " + monsterType + "!");
            } else {
                System.out.println("Вы промахнулись!");
            }

            if (currentMonster.health <= 0) {
                System.out.println("\nВы победили " + monsterType + "!");
                player.experience += 10;
                player.gold += 5;

                // Выводим информацию о текущем состоянии игрока
                System.out.println("Health: " + player.health);
                System.out.println("Experience: +" + player.experience);
                System.out.println("Gold: +" + player.gold);
                System.out.println("----------------------");
                break;
            }

            // Ход монстра
            int monsterDamage = currentMonster.attack();
            player.takeDamage(monsterDamage);
            System.out.println(monsterType + " нанес " + monsterDamage + " урона вам");


            if (player.health <= 0) {
                System.out.println("Вы проиграли! Вас убил " + monsterType);
                System.exit(0);
            }

            System.out.println();

            System.out.println("Хотите ли вы сражаться с ним? (1. Да / 2. Нет)");
            int continueBattle = scanner.nextInt();
            if (continueBattle != 1) {
                System.out.println("Вы вышли из подземелья");
                break;
            }
        }
    }

}
