package manager.history;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int HISTORY_LIMITS = 10;
    List<Task> historyTasksViews = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            if (historyTasksViews.size() >= HISTORY_LIMITS) {
                historyTasksViews.remove(0);
                historyTasksViews.add(task);
            } else {
                historyTasksViews.add(task);
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