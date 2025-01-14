package duke.command;

import duke.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;


/**
 * Represents a command to mark a task as done in the task list.
 * The MarkCommand is responsible for marking a specific task as completed.
 */
public class MarkCommand extends Command {

    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the MarkCommand.
     * This method marks the task at the specified index as done and updates the task list.
     * If the index is invalid, an error message is displayed.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        try {
            Task taskToMark = taskList.getTask(taskIndex);
            taskToMark.markAsDone();
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
            return ui.displayMarked(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage("Invalid task index.");
        }
    }
}





