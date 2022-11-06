package appointment;

import command.appointmentcommand.AddAppointmentCommand;
import command.appointmentcommand.RemoveAppointmentCommand;
import command.petcommand.AddPetCommand;
import command.servicecommand.AddServiceCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import pet.Pet;
import service.Service;

import javax.print.DocFlavor;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class AppointmentListTest {

    @Test
    void listAppointment() {
        Appointment appointment1 =
        AppointmentList.listAppointment();
    }

    @Test
    void findAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Feed");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Meow", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "11-28", "Feed");
        addAppointmentCommand.execute();
        Appointment foundAppointment = AppointmentList.findAppointment(Appointment.idCounter);
        if (foundAppointment != null) {
            assertEquals(foundAppointment.appointmentId, Appointment.idCounter);
        } else {
            assertNull(foundAppointment);
        }
    }

    @Test
    void addAnAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Trim");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Yuhuan", "cat", true);
        addPetCommand.execute();
        int numOfAppointment = AppointmentList.appointments.size();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "2022-11-28", "Trim");
        addAppointmentCommand.execute();
        int numOfAppointmentAfterAdd = AppointmentList.appointments.size();
        assertEquals(numOfAppointmentAfterAdd - numOfAppointment, 1);
    }

    @Test
    void addNullDateAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                Pet pet = new Pet("Yuhuan", "cat", true);
                Appointment appointment = new Appointment(2001, null, "Meow meow");
                AppointmentList.addAppointment(appointment);
                });
    }

    @Test
    void addInvalidServiceAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                    Pet pet = new Pet("Yuhuan", "cat", true);
                    Appointment appointment = new Appointment(2001, new Date(), "Non exist service");
                    AppointmentList.addAppointment(appointment);
                });
    }

    @Test
    void addInvalidPetAppointmentTest() {
        assertThrows(DukeException.class,
                ()->{
                    Service service = new Service("Trim");
                    Appointment appointment = new Appointment(2000, null, "Trim");
                    AppointmentList.addAppointment(appointment);
                });
    }


    @Test
    void removeAppointment() {
        AddServiceCommand addServiceCommand = new AddServiceCommand("Brush");
        addServiceCommand.execute();
        AddPetCommand addPetCommand = new AddPetCommand("Mimi", "cat", true);
        addPetCommand.execute();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Pet.idCounter, "2022-11-28", "Brush");
        addAppointmentCommand.execute();
        int numOfAppointments = AppointmentList.appointments.size();
        RemoveAppointmentCommand removeAppointmentCommand = new RemoveAppointmentCommand(Appointment.idCounter);
        removeAppointmentCommand.execute();
        int numOfAppointmentsAfterRemove = AppointmentList.appointments.size();
        assertEquals(-1,numOfAppointmentsAfterRemove - numOfAppointments);
    }

    @Test
    void updateAppointmentStatus() {
    }

    @Test
    void viewAppointmentTasks() {
    }
}