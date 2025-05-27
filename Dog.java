public class Dog extends Pet implements HealthCritical {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void feed() {
        if (getEnergy() <= 10) {
            System.out.println(getName() + " is too tired to eat. Rest first!");
            return;
        }
        setHunger(getHunger() - 20);
        setHealth(getHealth() + 5);
    }

    @Override
    public void play() {
        if (getHunger() <= 20) {
            System.out.println(getName() + " is too hungry to play. Feed first!");
            return;
        }
        if (getEnergy() <= 10) {
            System.out.println(getName() + " is too tired to play. Rest first!");
            return;
        }
        setHappiness(getHappiness() + 10);
        setEnergy(getEnergy() - 10);
        setHealth(getHealth() - 10); // Added for testing
        System.out.println(getName() + " barks happily!");
    }

    @Override
    public void rest() {
        setEnergy(getEnergy() + 15);
        setHealth(getHealth() + 10);
    }

    @Override
    public void onLowHealth() {
        System.out.println(getName() + " (Dog) is critically low on health! Needs immediate care!");
    }
}