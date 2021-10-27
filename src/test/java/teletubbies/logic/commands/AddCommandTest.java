package teletubbies.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teletubbies.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import teletubbies.commons.core.GuiSettings;
import teletubbies.commons.core.Range;
import teletubbies.commons.core.UserProfile;
import teletubbies.commons.exceptions.IllegalValueException;
import teletubbies.logic.commands.exceptions.CommandException;
import teletubbies.model.AddressBook;
import teletubbies.model.Model;
import teletubbies.model.ReadOnlyAddressBook;
import teletubbies.model.ReadOnlyUserPrefs;
import teletubbies.model.person.Person;
import teletubbies.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateName_throwsCommandException() {
        Person alice = new PersonBuilder().withPhone("88888888").build();
        Person alice2 = new PersonBuilder().withPhone("99999999").build();
        AddCommand addCommand = new AddCommand(alice);
        ModelStub modelStub = new ModelStubWithPerson(alice2);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePhoneNumber_throwsCommandException() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addCommand = new AddCommand(alice);
        ModelStub modelStub = new ModelStubWithPerson(bob);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE_NUMBER, () ->
            addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserProfile(UserProfile userProfile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UserProfile getUserProfile() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UserProfile.Role getUserRole() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasName(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPhoneNumber(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Person> getPersonsFromRange(Range range) throws IllegalValueException {
            return null;
        }

        @Override
        public void updateExportList(List<Person> filteredPersonList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAwaitingExportConfirmation() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AddressBook getExportAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cancelPendingExport() { }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void mergePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCommandInput(String textInput) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String> getChronologicallyAscendingHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String> getChronologicallyDescendingHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasName(Person person) {
            requireNonNull(person);
            return this.person.isSameName(person);
        }

        @Override
        public boolean hasPhoneNumber(Person person) {
            requireNonNull(person);
            return this.person.isSamePhoneNumber(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasName(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameName);
        }

        @Override
        public boolean hasPhoneNumber(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePhoneNumber);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
