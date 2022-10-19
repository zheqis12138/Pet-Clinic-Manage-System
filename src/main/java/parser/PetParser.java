package parser;

import command.*;
import petExceptions.*;

public class PetParser {
    private int lengthOfSignature;
    private Parser parser;

    public PetParser(Parser parser, int lengthOfSignature){
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }


    public Command parsePet(String input) throws NotEnoughParameterException{
        if(!input.contains(" ")){
            if(input.equals("view")){
                return new ViewPetCommand();
            }
            throw new NotEnoughParameterException();
        }

        String type = input.substring(0,input.indexOf(" "));
        String statement = input.substring(input.indexOf(" "));
        switch(type) {
        case AddPetCommand.COMMAND_WORD:
            return prepareAddPet(statement);
        case RemovePetCommand.COMMAND_WORD:
            return prepareRemovePet(statement);
        default:
            System.out.println("Error: unrecognized pet operation");
            return new EndCommand();
        }
    }

    public Command prepareAddPet(String input){
        int startOfN = input.indexOf(" n/");
        int startOfS = input.indexOf(" s/");

        if(startOfN > startOfS || startOfN == -1|| startOfS == -1){
            //System.out.println("Error: format of parameters entered for adding a pet is invalid");
            return new PetIncorrectCommand("Invalid input! Please follow the stipulated format: pet add n/name s/species");
        }


        String name = input.substring(startOfN + lengthOfSignature, startOfS);
        String species = input.substring(startOfS + lengthOfSignature);
        return new AddPetCommand(name, species, true);
    }


    public Command prepareRemovePet(String input){
        int index = parser.indexOfRemove(input);
        if(index == -1){
            System.out.println("input invalid");
            return new PetIncorrectCommand("Invalid Input ! Please follow the stipulated format: pet remove i/ID");
        }
        
        return new RemovePetCommand(index);
    }

}
