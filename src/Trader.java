public class Trader implements Seller {

    int potionPrice = 5; // Цена зелья

    // Покупка зелья лечения
    @Override
    public void sellPotion(Hero player) {
        if (player.gold >= potionPrice) {
            player.gold -= potionPrice;
            player.health += 20;
            System.out.println("Здоровье восстановлено на +20!");
        } else {
            System.out.println("Не хватает золота!");
        }
    }
}
