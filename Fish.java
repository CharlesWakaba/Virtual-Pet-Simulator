public class Fish extends Pet implements HealthCritical {
    public Fish(String name) {
        super(name);
    }

    @Override
    public void feed() {
        if (getEnergy() <= 10) {
            System.out.println(getName() + " is too tired to eat. Rest first!");
            return;
        }
        setHunger(getHunger() - 1);
        setHealth(getHealth() + 1);
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
        setHappiness(getHappiness() + 2);
        setEnergy(getEnergy() - 1);
        System.out.println(getName() + " swims happily!");
    }

    @Override
    public void rest() {
        setEnergy(getEnergy() + 2);
    }

    @Override
    public void onLowHealth() {
        System.out.println(getName() + " (Fish) is critically low on health! Needs clean water!");
    }
}