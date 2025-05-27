public class Cat extends Pet implements HealthCritical {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void feed() {
        if (getEnergy() <= 10) {
            System.out.println(getName() + " is too tired to eat. Rest first!");
            return;
        }
        setHunger(getHunger() - 2);
        setHappiness(getHappiness() + 1);
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
        setHappiness(getHappiness() + 3);
        setEnergy(getEnergy() - 2);
        System.out.println(getName() + " purrs playfully!");
    }

    @Override
    public void rest() {
        setEnergy(getEnergy() + 3);
        setHealth(getHealth() + 1);
    }

    @Override
    public void onLowHealth() {
        System.out.println(getName() + " (Cat) is critically low on health! Needs a vet visit!");
    }
}