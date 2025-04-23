import tasks.DateTimeTasks
import tasks.FlowDecoratorIONIOTasks
import tasks.SingletonEnumTasks

fun main() {
    while (true) {
        println("\nМеню с задачами. Для выхода напишите 0:" +
                "\n1. DateTime \n2. FlowDecoratorIONIO \n3. Singleton_Enum \n0. Выход")
        when (readlnOrNull()?.toIntOrNull()) {
            0 -> return
            1 -> runDateTimeTasks()
            2 -> runFlowDecoratorIONIOTasks()
            3 -> runSingletonEnumTasks()
            else -> println("Некорректное значение.")
        }
    }
}

fun runDateTimeTasks() {
    while (true) {
        println("\n" +
            """
            Выберите задачу DateTime (1-15). Для выхода напишите 0:
            1. Основы LocalDate и LocalTime
            2. Сравнение дат
            3. Сколько дней до Нового года?
            4. Проверка високосного года
            5. Подсчет выходных за месяц
            6. Расчет времени выполнения метода
            7. Форматирование и парсинг даты
            8. Конвертация между часовыми поясами
            9. Вычисление возраста по дате рождения
            10. Создание календаря на месяц
            11. Генерация случайной даты в диапазоне
            12. Расчет времени до заданной даты
            13. Вычисление количества рабочих часов
            14. Конвертация даты в строку с учетом локали
            15. Определение дня недели по дате
            0. Назад
            """.trimIndent()
        )
        when (readlnOrNull()?.toIntOrNull()) {
            0 -> return
            1 -> DateTimeTasks.task1()
            2 -> DateTimeTasks.runTask2()
            3 -> DateTimeTasks.task3()
            4 -> DateTimeTasks.runTask4()
            5 -> DateTimeTasks.runTask5()
            6 -> DateTimeTasks.task6()
            7 -> DateTimeTasks.runTask7()
            8 -> DateTimeTasks.runTask8()
            9 -> DateTimeTasks.runTask9()
            10 -> DateTimeTasks.runTask10()
            11 -> DateTimeTasks.runTask11()
            12 -> DateTimeTasks.runTask12()
            13 -> DateTimeTasks.runTask13()
            14 -> DateTimeTasks.runTask14()
            15 -> DateTimeTasks.runTask15()
            else -> println("Некорректное значение.")
        }
    }
}

fun runFlowDecoratorIONIOTasks() {
    while (true) {
        println("\n" +
            """
            Выберите задачу FlowDecoratorIONIO (1-5). Для выхода напишите 0:
            1. Работа с потоками ввода-вывода
            2. Реализация паттерна Декоратор
            3. Сравнение производительности IO и NIO
            4. Программа с использованием Java NIO
            5. Асинхронное чтение файла с использованием NIO.
            0. Назад
            """.trimIndent()
        )
        when (readlnOrNull()?.toIntOrNull()) {
            0 -> return
            1 -> FlowDecoratorIONIOTasks.task1()
            2 -> FlowDecoratorIONIOTasks.task2()
            3 -> FlowDecoratorIONIOTasks.task3()
            4 -> FlowDecoratorIONIOTasks.task4()
            5 -> FlowDecoratorIONIOTasks.task5()
            else -> println("Некорректное значение.")
        }
    }
}

fun runSingletonEnumTasks() {
    while (true) {
        println("\n" +
            """
            Выберите задачу Singleton_Enum (1-4). Для выхода напишите 0:
            1. Создание класса базы данных
            2. Логирование в системе
            3. Реализация статусов заказа
            4. Сезоны года
            0. Назад
            """.trimIndent()
        )
        when (readlnOrNull()?.toIntOrNull()) {
            0 -> return
            1 -> SingletonEnumTasks.task1()
            2 -> SingletonEnumTasks.task2()
            3 -> SingletonEnumTasks.task3()
            4 -> SingletonEnumTasks.task4()
            else -> println("Некорректное значение.")
        }
    }
}
