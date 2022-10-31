package manager;

import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.*;

public class Manager {
    private static int id = 0;
    // Спасибо за проверку.Исправил основную ошибку, остальное доделаю при работе над 4 спринтом
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public int generateId() {
        return ++id;
    }

    public void createTask(Task task) {
        int newTaskId = generateId();
        task.setId(newTaskId);
        tasks.put(newTaskId, task);
    }

    public void createEpic(Epic epic) {
        int newEpicId = generateId();
        epic.setId(newEpicId);
        epics.put(newEpicId, epic);
    }

    public void createSubtask(Subtask subtask) {
        int newSubtaskId = generateId();
        subtask.setId(newSubtaskId);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            subtasks.put(newSubtaskId, subtask);
            epic.addSubtaskIds(newSubtaskId);
            updateStatusEpic(epic);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Таск не найден");
        }
    }

    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            epic.getSubtaskIds().remove((Integer) subtask.getId());
            updateStatusEpic(epic);
            subtasks.remove(id);
        } else {
            System.out.println("Сабтаск не найден");
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateStatusEpic(epic);
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public List<Task> getAllTasks() {
        if (tasks.size() == 0) {
            System.out.println("Лист Тасков пустой");
            return Collections.emptyList();
        }
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        if (epics.size() == 0) {
            System.out.println("Лист Эпиков пустой");
            return Collections.emptyList();
        }
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        if (subtasks.size() == 0) {
            System.out.println("Лист Сабтасков пустой");
            return Collections.emptyList();
        }
        return new ArrayList<>(subtasks.values());
    }

    public List<Subtask> getAllSubtasksByEpicId(int id) {
        if (epics.containsKey(id)) {
            List<Subtask> subtasksNew = new ArrayList<>();
            Epic epic = epics.get(id);
            for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
                subtasksNew.add(subtasks.get(epic.getSubtaskIds().get(i)));
            }
            return subtasksNew;
        } else {
            System.out.println("Эпик не найден");
            return Collections.emptyList();
        }
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else {
            System.out.println("Таск не найден");
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateStatusEpic(epic);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    private void updateStatusEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            if (epic.getSubtaskIds().size() == 0) {
                epic.setStatus(Status.NEW);
            } else {
                List<Subtask> subtasksNew = new ArrayList<>();
                boolean statusDone = true;
                boolean statusNew = true;
                boolean statusInProgress = true;

                for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
                    subtasksNew.add(subtasks.get(epic.getSubtaskIds().get(i)));
                }

                for (Subtask subtask : subtasksNew) {
                    statusDone &= (subtask.getStatus() == Status.DONE);
                    statusNew &= (subtask.getStatus() == Status.NEW);
                    statusInProgress &= (subtask.getStatus() == Status.IN_PROGRESS);
                }
                /*int countDone = 0;
                int countNew = 0;

                for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
                    subtasksNew.add(subtasks.get(epic.getSubtaskIds().get(i)));
                }

                for (Subtask subtask : subtasksNew) {
                    if (subtask.getStatus() == Status.DONE) {
                        countDone++;
                    }
                    if (subtask.getStatus() == Status.NEW) {
                        countNew++;
                    }
                    if (subtask.getStatus() == Status.IN_PROGRESS) {
                        epic.setStatus(Status.IN_PROGRESS);
                        return;
                    }
                }

                if (countDone == epic.getSubtaskIds().size()) {
                    epic.setStatus(Status.DONE);
                } else if (countNew == epic.getSubtaskIds().size()) {
                    epic.setStatus(Status.NEW);
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }*/
            }
        } else {
            System.out.println("Эпик не найден");
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            updateStatusEpic(epic);
        } else {
            System.out.println("Сабтаск не найден");
        }
    }

    public void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("Лист Тасков пустой");
            return;
        }
        for (Task task : tasks.values()) {
            System.out.println("Task{" +
                    "description='" + task.getDescription() + '\'' +
                    ", id=" + task.getId() +
                    ", name='" + task.getName() + '\'' +
                    ", status=" + task.getStatus() +
                    '}');
        }
    }

    public void printEpics() {
        if (epics.size() == 0) {
            System.out.println("Лист Эпиков пустой");
            return;
        }
        for (Epic epic : epics.values()) {
            System.out.println("Epic{" +
                    "subtasksIds=" + epic.getSubtaskIds() +
                    ", description='" + epic.getDescription() + '\'' +
                    ", id=" + epic.getId() +
                    ", name='" + epic.getName() + '\'' +
                    ", status=" + epic.getStatus() +
                    '}');
        }
    }

    public void printSubtasks() {
        if (subtasks.size() == 0) {
            System.out.println("Лист Сабтасков пустой");
            return;
        }
        for (Subtask subtask : subtasks.values()) {
            System.out.println("Subtask{" +
                    "epicId=" + subtask.getEpicId() +
                    ", description='" + subtask.getDescription() + '\'' +
                    ", id=" + subtask.getId() +
                    ", name='" + subtask.getName() + '\'' +
                    ", status=" + subtask.getStatus() +
                    '}');
        }
    }
}