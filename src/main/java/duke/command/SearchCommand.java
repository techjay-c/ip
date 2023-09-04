package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

import java.util.ArrayList;

// Solution below adapted and inspired from https://chat.openai.com/share/3bd2c2a8-57c4-480a-9055-646c61983a60
public class SearchCommand extends Command {

    private String taskToFind;

    /**
     * Constructs a SearchCommand with the specified keyword.
     *
     * @param taskToFind The keyword to search for in task descriptions.
     */
    public SearchCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    /**
     * Executes the search for tasks containing the specified keyword.
     *
     * @param taskList The TaskList containing all tasks.
     * @param ui The Ui object for user interface interactions.
     * @param storage The Storage object for data storage operations.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().contains(taskToFind)) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            ui.showErrorMessage("No such task found.");
        } else {
            ui.displaySearched(matchedTasks);
        }
    }
}