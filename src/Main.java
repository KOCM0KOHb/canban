import manager.Managers;
import manager.task.TaskManager;
import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getInMemoryTaskManager(Managers.getDefaultHistory());
        List<Task> history = taskManager.getHistory();

        System.out.println("-> Task <-");
        System.out.println("-> Создадим Таск");
        taskManager.createTask(new Task("Описание-1", "Task-1", Status.NEW));
        taskManager.createTask(new Task("Описание-2", "Task-2", Status.NEW));
        taskManager.printTasks();
        System.out.println("-> Получим все Task");
        List<Task> taskList = taskManager.getAllTasks();
        System.out.println(taskList);
        System.out.println("-> Получение Таска по id");
        Task task = taskManager.getTaskById(1);
        System.out.println(task);
        System.out.println("-> Обновим Таск");
        task.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task);
        System.out.println(task);
        System.out.println();

        System.out.println("-> Эпик <-");
        System.out.println("-> Создадим Epic");
        taskManager.createEpic(new Epic("Описание-1", "Epic-1", Status.NEW));
        taskManager.createEpic(new Epic("Описание-2", "Epic-2", Status.NEW));
        taskManager.printEpics();
        System.out.println("-> Получим все Эпики");
        List<Epic> epics = taskManager.getAllEpics();
        System.out.println(epics);
        System.out.println("-> Получим Эпик по id");
        Epic epic = taskManager.getEpicById(3);
        System.out.println(epic);
        System.out.println("-> Обновим Эпик");
        epic.setStatus(Status.IN_PROGRESS);
        taskManager.updateEpic(epic);
        Epic epic3 = taskManager.getEpicById(3);
        System.out.println(epic3);
        System.out.println();

        System.out.println("-> Сабтаск <-");
        System.out.println("Создадим Subtask");
        taskManager.createSubtask(new Subtask("Описание-1", "Subtask-1", Status.NEW, 3));
        taskManager.createSubtask(new Subtask("Описание-2", "Subtask-2", Status.NEW, 3));
        taskManager.createSubtask(new Subtask("Описание-3", "Subtask-3", Status.NEW, 4));
        taskManager.createSubtask(new Subtask("Описание-4", "Subtask-4", Status.NEW, 4));
        taskManager.printSubtasks();
        System.out.println("-> Получим все Сабтаски по epic id");
        List<Subtask> subtasksByEpicId = taskManager.getAllSubtasksByEpicId(3);
        System.out.println(subtasksByEpicId);
        System.out.println("-> Получим все Сабтаски");
        List<Subtask> subtasks = taskManager.getAllSubtasks();
        System.out.println(subtasks);
        System.out.println("-> Получим Сабтаск по id");
        Subtask subtask = taskManager.getSubtaskById(5);
        System.out.println(subtask);
        System.out.println("-> Обновим Сабтаск");
        subtask.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask);
        System.out.println(subtask);
        System.out.println();

//        System.out.println("-> Удаление <-");
//        System.out.println("-> Удаление Таска по id");
//        taskManager.deleteTaskById(1);
//        System.out.println(taskList);
//        System.out.println("-> Удалим все Таски");
//        taskManager.deleteAllTasks();
//        taskManager.printTasks();
//        System.out.println("-> Удаление Сабтаска по id");
//        taskManager.deleteSubtaskById(5);
//        taskManager.printSubtasks();
//        System.out.println("-> Удалим все Сабтаски");
//        taskManager.deleteAllSubtasks();
//        taskManager.printSubtasks();
//        System.out.println("-> Удаление Эпика по id");
//        taskManager.deleteEpicById(4);
//        taskManager.printEpics();
//        System.out.println("-> Удалим все Эпики");
//        taskManager.deleteAllEpics();
//        taskManager.printEpics();

        System.out.println("-> Потыкаем в ранее созданные записи ещё <-");
        taskManager.getTaskById(1);
        taskManager.getEpicById(3);
        taskManager.getTaskById(2);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);
        taskManager.getEpicById(4);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);
        taskManager.getSubtaskById(7);
        taskManager.getSubtaskById(8);
        taskManager.getSubtaskById(7);
        taskManager.getTaskById(2);

        System.out.println("-> Посмотрим историю просмотров <-");
        System.out.println(history);
    }
}