public class Main {
    public static void main(String[] args) {

        String str = new String("New String");
        String str1 = new String("New String");

        System.out.println(str1 == str);
        System.out.println(str.hashCode());
        System.out.println(str1.hashCode());
        System.out.println(str.equals(str1));


       /* Dragon dragon = new Dragon("Big Dragon", Status.ENEMY,
                35, 15, 60);
        System.out.printf(dragon.toString());
        dragon.damageObject(20);
        dragon.displayInfo();
        dragon.damageObject(47);
        dragon.displayInfo();

        Dragon dragon2 = new Dragon("Dragon", Status.ENEMY, 35, 15, 60);
        Dragon drag3 = new Dragon("Big Dragon", Status.FRIEND, 35, 15, 60);
        if(dragon2.equals(dragon)){
            System.out.printf("\n\nDragon0 and Dragon2 are equal");
        } else System.out.printf("\n\nDragon0 and Dragon2 are not equal");

        if(drag3.equals(dragon)){
            System.out.printf("\n\nDragon0 and Dragon3 are equal");
        } else System.out.printf("\n\nDragon0 and Dragon3 are not equal");



        Dog dog = new Dog();
        dog.displayInfo();
        dog.getHeald(15);
        dog.displayInfo();
        dog.damageObject(17);
        dog.displayInfo();
        dog.getHeald(7);
        dog.displayInfo();
        dog.getHeald(20);
        dog.displayInfo();
        Dog d1 = new Dog();
        System.out.printf("\nHash code: %d",dog.hashCode());
        System.out.printf("\nHash code d1: %d",d1.hashCode());
        if(dog.equals(dragon)){
            System.out.printf("\n\nare equal");
        } else System.out.printf("\n\nare not equal");*/
    }
}

/*
* Задание: сделать программу, в которой будет использоваться наследование и абстрактные классы с интерфейсами.
* Сделать геттеры-сеттеры, переопределить hashCode equals и toString.
* Минимум по 2 метода в классе. Минимум по 2 поля в классе.
*  Сделать конструктор без параметров и со всеми полями класса.
* Задание сделать на одну из тем:

 */

//мобы в игре - животные, могут быть другом, нейтральным или врагом,
// класс защиты, урон, количество жизней

enum Status{
    FRIEND, //помощник игрока
    ENEMY, //враг
    NEUTRAL //объект/нпси
}
abstract class GameObject{
    private final int id;
    private static int counter = 1;
    public final String name;
    protected final Status status;
    GameObject(){
        id = counter++;
        name = "Undefined";
        status = Status.NEUTRAL;
    }
    GameObject(String name, Status status){
        id = counter++;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n\nName: "+this.name+"\nStatus: "+status + "\nID: " + id;
    }

    void displayInfo(){
        System.out.printf("\n\nName: %s\nId: %d\nStatus: %s", this.name, this.id, this.status);
    }
    abstract int[] getCoordinates(int x, int y);

    @Override
    public int hashCode() {
        return 31 * name.hashCode();
    }
}
interface GetDamage{
    void damageObject(int damage);
}

interface GetHeald{
    void getHeald(int healthPoints);
}
class Dragon extends GameObject implements GetDamage{
    public int armorClass;
    public int damage;
    public int healthPoints;
    Dragon(){
        super();
        armorClass = 50;
        damage = 20;
        healthPoints = 70;
    }
    Dragon(String name, Status status, int armorClass, int damage, int healthPoints){
        super(name, status);
        this.armorClass = armorClass;
        this.damage = damage;
        this.healthPoints = healthPoints;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dragon)) return false;

        Dragon dragon = (Dragon) obj;
        return this.name.equals(dragon.name);
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.printf("\nArmor Class: %d\nDamage: %d\nHealth: %d", this.armorClass,
                this.damage, this.healthPoints);
    }

    @Override
    int[] getCoordinates(int x, int y) {
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    @Override
    public void damageObject(int damage) {
        if(damage > this.armorClass){
            this.healthPoints -= damage;
            System.out.printf("\n\nYou got %d damage\n\n", damage);
        }
        else{
            System.out.printf("\n\nThe damage misses");
        }
    }
}

class Dog extends GameObject implements GetDamage, GetHeald{
    public int armorClass;
    public int damage;
    public int healthPoints;

    Dog(){
        super("Dog", Status.FRIEND);
        armorClass = 15;
        damage = 10;
        healthPoints = 20;
    }
    @Override
    int[] getCoordinates(int x, int y) {
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.printf("\nArmor Class: %d\nDamage: %d\nHealth: %d", this.armorClass,
                this.damage, this.healthPoints);
    }

    @Override
    public void damageObject(int damage) {
        if(damage > this.armorClass){
            this.healthPoints -= damage;
            System.out.printf("\n\nYou got %d damage\n\n", damage);
        }
        else{
            System.out.printf("The damage misses");
        }
    }

    @Override
    public void getHeald(int healthPoints) {
        if(this.healthPoints < 20){
            this.healthPoints += healthPoints;
            if(this.healthPoints > 20){
                this.healthPoints = 20;
            }
        }
    }

}