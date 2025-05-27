import java.util.ArrayList;

public class GameManager {
    private static final ArrayList<Pet> availablePets = new ArrayList<>();

    public static void checkPetHealth(User user) {
        ArrayList<Pet> toRemove = new ArrayList<>();
        for (Pet p : user.getPets()) {
            if (p.getHealth() <= 0) {
                System.out.println(p.getName() + " has died!");
                p.resetAttributes();
                availablePets.add(p);
                toRemove.add(p);
            }
        }
        if (!toRemove.isEmpty()) {
            user.getPets().removeAll(toRemove);
        }
    }

    public static void displayAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No available pets at the moment.");
        } else {
            System.out.println("Available pets:");
            for (int i = 0; i < availablePets.size(); i++) {
                Pet pet = availablePets.get(i);
                System.out.println((i + 1) + ". " + pet.getName() + " (" + pet.getClass().getSimpleName() + ")");
            }
        }
    }

    public static ArrayList<Pet> getAvailablePets() {
        return availablePets;
    }

    public static Pet adoptPet(int index) {
        if (index >= 0 && index < availablePets.size()) {
            Pet pet = availablePets.remove(index);
            return pet;
        }
        return null;
    }
}