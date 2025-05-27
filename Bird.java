public class Bird extends Pet implements HealthCritical {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void feed() {
        if (getEnergy() <= 10) {
            System.out.println(getName() + " is too tired to eat. Rest first!");
            return;
        }
        setHunger(getHunger() - 5);
        setHealth(getHealth() + 2);
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
        setHappiness(getHappiness() + 15);
        setEnergy(getEnergy() - 5);
        System.out.println(getName() + " chirps joyfully!");
    }

    @Override
    public void rest() {
        setEnergy(getEnergy() + 10);
        setHealth(getHealth() + 3);
    }

    @Override
    public void onLowHealth() {
        System.out.println(getName() + " (Bird) is critically low on health! Needs a nest!");
    }
}