package manager.task;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.*;

public interface TaskManager {
    Task getTaskById(int id);
    Epic getEpicById(int id);
    Subtask getSubtaskById(int id);
    List<Task> getAllTasks();
    List<Epic> getAllEpics();
    List<Subtask> getAllSubtasks();
    List<Subtask> getAllSubtasksByEpicId(int id);
    List<Task> getHistory();
    int generateId();
    int createTask(Task task);
    int createEpic(Epic epic);
    int createSubtask(Subtask subtask);
    void deleteTaskById(int id);
    void deleteEpicById(int id);
    void deleteSubtaskById(int id);
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateStatusEpic(Epic epic);
    void updateSubtask(Subtask subtask);
}