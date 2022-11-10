package manager.history;

import tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int HISTORY_LIMIT = 10;
    List<Task> historyTasksViews = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            historyTasksViews.add(task);
            if (historyTasksViews.size() >= HISTORY_LIMIT) {
                historyTasksViews.remove(0);
            }
        } else {
            System.out.println("Таск не найден");
        }
    }

    @Override
    public List<Task> getHistory() {
        for (int i = 0; i < historyTasksViews.size(); i++) {
            System.out.println((i+1) + ". " + historyTasksViews.get (i));
        }
        return historyTasksViews;
    }
}