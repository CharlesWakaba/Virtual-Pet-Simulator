import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetGame {
    public static void main(String[] args) {
        List<User> users = createDummyData();
        GameManager.getAvailablePets().addAll(createAvailablePets());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Virtual Pet Simulator!");
            displayAllUsersAndPets(users);

            System.out.print("\nEnter your username: ");
            String username = scanner.nextLine();
            User currentUser = users.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .orElse(null);

            if (currentUser == null) {
                System.out.println("User not found. Exiting.");
                return;
            }

            System.out.println("Welcome, " + currentUser.getUsername() + "!");
            boolean running = true;
            while (running) {
                displayUserPets(currentUser);
                System.out.print("\nChoose a pet (enter the number, or 0 to continue without selecting): ");
                int petIndex;
                try {
                    petIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid input. Exiting.");
                    scanner.nextLine();
                    return;
                }

                Pet myPet = null;
                if (petIndex >= 0 && petIndex < currentUser.getPets().size()) {
                    myPet = currentUser.getPets().get(petIndex);
                }

                boolean petMenu = true;
                while (petMenu && (myPet != null || petIndex == -1)) {
                    System.out.println("\nWhat would you like to do" + (myPet != null ? " with " + myPet.getName() : "") + "?");
                    System.out.println("1. Feed");
                    System.out.println("2. Play");
                    System.out.println("3. Rest");
                    System.out.println("4. Show Stats");
                    System.out.println("5. Adopt a Pet");
                    System.out.println("6. Release a Pet");
                    System.out.println("7. Show Available Pets");
                    System.out.println("8. Choose Another Pet");
                    System.out.println("9. Exit");
                    System.out.print("Choose an option: ");

                    int choice;
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Try again.");
                        scanner.nextLine();
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            if (myPet == null) {
                                System.out.println("Please select a pet first!");
                            } else {
                                myPet.feed();
                                GameManager.checkPetHealth(currentUser);
                                if (!currentUser.getPets().contains(myPet)) {
                                    myPet = null;
                                    petMenu = false;
                                }
                            }
                            break;
                        case 2:
                            if (myPet == null) {
                                System.out.println("Please select a pet first!");
                            } else {
                                myPet.play();
                                GameManager.checkPetHealth(currentUser);
                                if (!currentUser.getPets().contains(myPet)) {
                                    myPet = null;
                                    petMenu = false;
                                }
                            }
                            break;
                        case 3:
                            if (myPet == null) {
                                System.out.println("Please select a pet first!");
                            } else {
                                myPet.rest();
                                GameManager.checkPetHealth(currentUser);
                                if (!currentUser.getPets().contains(myPet)) {
                                    myPet = null;
                                    petMenu = false;
                                }
                            }
                            break;
                        case 4:
                            if (myPet == null) {
                                System.out.println("Please select a pet first!");
                            } else {
                                printStats(myPet);
                            }
                            break;
                        case 5:
                            GameManager.displayAvailablePets();
                            if (!GameManager.getAvailablePets().isEmpty()) {
                                System.out.print("Enter the number of the pet to adopt: ");
                                try {
                                    int adoptIndex = scanner.nextInt() - 1;
                                    scanner.nextLine();
                                    Pet adoptedPet = GameManager.adoptPet(adoptIndex);
                                    if (adoptedPet != null) {
                                        currentUser.addPet(adoptedPet);
                                    } else {
                                        System.out.println("Invalid pet number.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 6:
                            if (currentUser.getPets().isEmpty()) {
                                System.out.println("You have no pets to release!");
                            } else {
                                displayUserPets(currentUser);
                                System.out.print("Enter the number of the pet to release: ");
                                try {
                                    int releaseIndex = scanner.nextInt() - 1;
                                    scanner.nextLine();
                                    if (releaseIndex >= 0 && releaseIndex < currentUser.getPets().size()) {
                                        Pet releasedPet = currentUser.getPets().get(releaseIndex);
                                        currentUser.removePet(releasedPet);
                                        releasedPet.resetAttributes();
                                        GameManager.getAvailablePets().add(releasedPet);
                                        if (myPet == releasedPet) {
                                            myPet = null;
                                            petMenu = false;
                                        }
                                    } else {
                                        System.out.println("Invalid pet number.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 7:
                            GameManager.displayAvailablePets();
                            break;
                        case 8:
                            petMenu = false;
                            break;
                        case 9:
                            System.out.println("Goodbye!");
                            petMenu = false;
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }
        }
    }

    private static void printStats(Pet pet) {
        System.out.println("\n--- " + pet.getName() + "'s Stats ---");
        System.out.println("Type: " + pet.getClass().getSimpleName());
        System.out.println("Hunger: " + pet.getHunger());
        System.out.println("Happiness: " + pet.getHappiness());
        System.out.println("Health: " + pet.getHealth());
        System.out.println("Energy: " + pet.getEnergy());
    }

    private static void displayAllUsersAndPets(List<User> users) {
        System.out.println("\nAll Users and Their Pets:");
        for (User user : users) {
            System.out.println("User: " + user.getUsername() + " (" + user.getPets().size() + " pets)");
            for (Pet pet : user.getPets()) {
                System.out.println("  - " + pet.getName() + " (" + pet.getClass().getSimpleName() + "): " +
                        "Hunger=" + pet.getHunger() + ", Happiness=" + pet.getHappiness() +
                        ", Health=" + pet.getHealth() + ", Energy=" + pet.getEnergy());
            }
        }
    }

    private static void displayUserPets(User user) {
        System.out.println("\nYour Pets:");
        if (user.getPets().isEmpty()) {
            System.out.println("No pets assigned.");
        } else {
            for (int i = 0; i < user.getPets().size(); i++) {
                Pet pet = user.getPets().get(i);
                System.out.println((i + 1) + ". " + pet.getName() + " (" + pet.getClass().getSimpleName() + ")");
            }
        }
    }

    private static List<User> createDummyData() {
        List<User> users = new ArrayList<>();
        String[] userNames = {"marijam", "marinam", "elenaj", "kirej", "macom"};
        String[] petNames = {"Bono", "Max", "Bella", "Charlie", "Lucy", "Daisy", "Molly", "Bailey", "Coco", "Lola",
                "Rocky", "Milo", "Ruby", "Oscar", "Toby", "Luna", "Zoe", "Jack", "Sadie", "Chloe"};

        int petIndex = 0;
        for (String userName : userNames) {
            User user = new User(userName);
            for (int i = 0; i < 3; i++) {
                Pet pet;
                int type = petIndex % 4;
                if (type == 0) {
                    pet = new Dog(petNames[petIndex]);
                } else if (type == 1) {
                    pet = new Cat(petNames[petIndex]);
                } else if (type == 2) {
                    pet = new Fish(petNames[petIndex]);
                } else {
                    pet = new Bird(petNames[petIndex]);
                }
                user.addPet(pet);
                petIndex++;
            }
            users.add(user);
        }
        return users;
    }

    private static List<Pet> createAvailablePets() {
        List<Pet> pets = new ArrayList<>();
        String[] extraPetNames = {"Buddy", "Shadow", "Pepper", "Angel", "Tucker"};
        for (int i = 0; i < 5; i++) {
            Pet pet = new Fish(extraPetNames[i]);
            pets.add(pet);
        }
        return pets;
    }
}