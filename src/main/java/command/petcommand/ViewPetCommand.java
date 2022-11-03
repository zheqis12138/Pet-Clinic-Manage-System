package command.petcommand;

import command.Command;
import pet.Pet;
import pet.PetList;


public class ViewPetCommand extends Command {

    @Override
    public void execute() {
        int index = 1;
        System.out.println("Here are the pets in your pet list:");
        for (Pet pet : PetList.pets) {
            System.out.println(String.format("%d. ID: %d  %s",index, (pet.petId), pet));
            index++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
