
package application;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {

    public StudentList students = new StudentList();

    public TextField firstName;
    public TextField lastName;
    public TextField numCredits;
    public TextField fundingValue;

    public RadioButton instate;
    public RadioButton outstate;
    public RadioButton international;

    public Button add;
    public Button remove;
    public Button print;

    public CheckBox funding;
    public CheckBox tristate;
    public CheckBox exchangeStudent;

    public ToggleGroup group;
    public TextArea output;

    /**
     * Prints out the students in the array-based list. Prints an error if no student is in the list.
     */
    public void print() {
        if (students.isEmpty()) {
            output.appendText("\nThere are no students in the list!\n\n");
        } else {
            output.appendText("\n");
            for (int i = 0; i < students.numStudents; i++) {
                output.appendText(students.students[i].toString() + "\n");
            }
            output.appendText("\n");
        }
    }

    /**
     * Adds students to the list. It prints an error if the student already exists in the list.
     */
    public void add() {
        int credits = Integer.parseInt(numCredits.getText());
        Student newStudent;

        if (group.getSelectedToggle().equals(international) && credits >= 9) {
            newStudent = new International(firstName.getText(), lastName.getText(), credits, exchangeStudent.isSelected());
        } else if (group.getSelectedToggle().equals(instate) && credits > 0) {
            int funds = 0;
            if (funding.isSelected()) funds = Integer.parseInt(fundingValue.getText());
            newStudent = new Instate(firstName.getText(), lastName.getText(), credits, funds);
        } else if (group.getSelectedToggle().equals(outstate) && credits > 0) {
            newStudent = new Outstate(firstName.getText(), lastName.getText(), credits, tristate.isSelected());
        } else {
            output.appendText("Error: Invalid credit amount\n");
            return;
        }

        if (students.contains(newStudent)) {
            output.appendText(firstName.getText() + " " + lastName.getText() + " is already a student.\n");
        } else {
            students.add(newStudent);
            output.appendText(firstName.getText() + " " + lastName.getText() + " has been added to the list.\n");
        }
    }

    /**
     * Removes a student from the list. Prints an error if the student does not exist in the list.
     */
    public void remove() {
        Student removedStudent = new Instate(firstName.getText(), lastName.getText(), 0, 0);
        if (!students.remove(removedStudent)) {
            output.appendText(firstName.getText() + " " + lastName.getText() + " is not a student.\n");
        } else {
            output.appendText(firstName.getText() + " " + lastName.getText() + " has been removed.\n");
        }
    }

    /**
     * Takes an the event argument and checks if print, add, or remove button is
     * clicked in order to add, remove, or print the students in the list.
     *
     * @param event Controls event of button press and decides what to do after.
     */
    public void onButtonPressed(ActionEvent event) {
        if (event.getSource().equals(print)) {
            print();
            return;
        }
        if (noErrors()) {
            if (event.getSource().equals(add)) add();
            else remove();
        }
    }

    /**
     * Takes an the event argument and disables the funding amount, tri-state, funding, and exchange student
     * radio buttons based on which button is selected.
     *
     * @param event Controls event of RadioButton press and decides what to do after.
     */
    public void onRadioPressed(ActionEvent event) {
        funding.setSelected(false);
        tristate.setSelected(false);
        exchangeStudent.setSelected(false);
        fundingValue.clear();
        if (event.getSource().equals(instate)) {
            funding.setDisable(false);
            tristate.setDisable(true);
            exchangeStudent.setDisable(true);
            fundingValue.setDisable(false);
        } else if (event.getSource().equals(outstate)) {
            funding.setDisable(true);
            tristate.setDisable(false);
            exchangeStudent.setDisable(true);
            fundingValue.setDisable(true);
        } else {
            funding.setDisable(true);
            tristate.setDisable(true);
            exchangeStudent.setDisable(false);
            fundingValue.setDisable(true);
        }
    }

    /**
     * Checks for simple errors in the GUI for numeric, alphabetic, or button/radio selection.
     *
     * @return Returns true or false as the return type for the function.
     */
    public boolean noErrors() {
        if (isNotAlpha(firstName.getText())) {
            output.appendText("Error: First name must be alphabetic and not null!\n");
            return false;
        }

        if (isNotAlpha(lastName.getText())) {
            output.appendText("Error: Last Name must be alphabetic and not null!\n");
            return false;
        }

        if (isNotNumeric(numCredits.getText())) {
            output.appendText("Error: Number of credits must be numeric and not null!\n");
            return false;
        }

        if (group.getSelectedToggle() == null) {
            output.appendText("Error: You must select a toggle: Instate, OutState, or International!\n");
            return false;
        }

        if (group.getSelectedToggle().equals(instate) && funding.isSelected() && isNotNumeric(fundingValue.getText())) {
            output.appendText("Error: Funding value must be numeric and not null!\n");
            return false;
        }

        return true;
    }

    /**
     * Checks if the string is alphabetic or not and returns true or false based on that.
     *
     * @param str Is a string variable for funding amount or credits amount.
     * @return Is the boolean variable for if string is alphabetic or not.
     */
    public boolean isNotAlpha(String str) {
        return !(!str.equals("") && str.matches("^[a-zA-Z]*$"));
    }

    /**
     * Checks if the string is numeric or not and returns true or false based on that.
     *
     * @param str Is a string variable for funding amount or credits amount.
     * @return Is the boolean variable for if string is numeric or not.
     */
    public boolean isNotNumeric(String str) {
        return !(!str.equals("") && str.matches("^[0-9]*$"));
    }

}
