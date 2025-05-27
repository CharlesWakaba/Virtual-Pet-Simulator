public abstract class Pet {
    private String name;
    private int hunger = 50;
    private int happiness = 50;
    private int health = 50;
    private int energy = 50;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    protected void setHunger(int hunger) {
        this.hunger = Math.max(0, Math.min(100, hunger));
    }

    protected void setHappiness(int happiness) {
        this.happiness = Math.max(0, Math.min(70, happiness));
    }

    protected void setHealth(int health) {
        this.health = Math.max(0, Math.min(100, health));
    }

    protected void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(70, energy));
    }

    public void feed() {
        if (energy <= 10) {
            System.out.println(name + " is too tired to eat. Rest first!");
            return;
        }
        setHunger(hunger - 10);
        setHealth(health + 5);
        checkHealth();
    }

    public void play() {
        if (hunger <= 20) {
            System.out.println(name + " is too hungry to play. Feed first!");
            return;
        }
        if (energy <= 10) {
            System.out.println(name + " is too tired to play. Rest first!");
            return;
        }
        setHappiness(happiness + 10);
        setEnergy(energy - 10);
        checkHealth();
    }

    public void rest() {
        setEnergy(energy + 20);
        setHealth(health + 5);
        checkHealth();
    }

    public void resetAttributes() {
        hunger = 50;
        happiness = 50;
        health = 50;
        energy = 50;
    }

    private void checkHealth() {
        if (health <= 20 && this instanceof HealthCritical) {
            ((HealthCritical) this).onLowHealth();
        }
    }
}