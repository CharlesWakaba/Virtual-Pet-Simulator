import java.util.ArrayList;

public class User {
    private final String username;
    private final ArrayList<Pet> pets;

    public User(String username) {
        this.username = username;
        this.pets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        if (pets.size() < 3) {
            pets.add(pet);
            System.out.println(pet.getName() + " has been adopted!");
        } else {
            System.out.println("Cannot add " + pet.getName() + ". Maximum 3 pets allowed.");
        }
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        System.out.println(pet.getName() + " has been released!");
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
}