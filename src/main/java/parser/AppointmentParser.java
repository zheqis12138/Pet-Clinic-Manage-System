package parser;

import command.Command;
import command.EmptyCommand;
import command.appointmentcommand.AddAppointmentCommand;
import command.appointmentcommand.RemoveAppointmentCommand;
import command.appointmentcommand.ViewAppointmentCommand;
import exception.DukeException;

public class AppointmentParser {
    private int lengthOfSignature;
    private Parser parser;

    public AppointmentParser(Parser parser, int lengthOfSignature) {
        this.parser = parser;
        this.lengthOfSignature = lengthOfSignature;
    }

    public Command parseAppointment(String input) {
        try {
            if (!input.contains(" ")) {
                if (input.equals("view")) {
                    return new ViewAppointmentCommand();
                }
                throw new DukeException();
            }

            String type = input.substring(0,input.indexOf(" "));
            String statement = input.substring(input.indexOf(" "));
            switch (type) {
            case AddAppointmentCommand.COMMAND_WORD:
                return prepareAddAppointment(statement);
            case RemoveAppointmentCommand.COMMAND_WORD:
                return prepareRemoveAppointment(statement);
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("Sorry, unrecognized appointment operation.");
            return new EmptyCommand();
        }
    }


    public Command prepareRemoveAppointment(String input) {
        try {
            int index = parser.indexOfInput(input);
            return new RemoveAppointmentCommand(index);
        } catch (DukeException e) {
            System.out.println("Sorry, index entered invalid for removing an appointment");
            return new EmptyCommand();
        }
    }

    public Command prepareAddAppointment(String input) {
        try {
            int s = input.indexOf(" s/");
            int p = input.indexOf(" p/");
            int d = input.indexOf(" d/");

            if(!input.substring(0,s).isEmpty()){
                throw new DukeException();
            }

            if (s > p || p > d || s == -1 || p == -1 || d == -1) {
                throw new DukeException();
            }

            String service = input.substring(s + lengthOfSignature, p);
            String petName = input.substring(p + lengthOfSignature, d);
            String appointmentDate = input.substring(d + lengthOfSignature);
            return new AddAppointmentCommand(petName, appointmentDate, service);
        } catch (DukeException e) {
            System.out.println("Sorry, format of parameters entered for adding an appointment is invalid");
            return new EmptyCommand();
        }
    }

}
