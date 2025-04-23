package tasks

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.time.measureTime
import kotlin.random.Random
import java.time.format.TextStyle
import java.util.Locale

object DateTimeTasks {
    fun task1(){
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()
        val formattedDateTime = "${currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))} ${currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))}"
        println(formattedDateTime)
    }

    fun task2(date1: LocalDate, date2: LocalDate){
        println(
            when{
                date1.isAfter(date2) -> "Первая дата больше второй"
                date1.isBefore(date2) -> "Вторая дата больше первой"
                else -> "Даты равны"
            }
        )
    }

    fun task3(){
        val currentDate = LocalDate.now()
        val newYear = LocalDate.of(currentDate.year + 1, 1, 1)
        val daysUntilNewYear = ChronoUnit.DAYS.between(currentDate, newYear)
        println("До Нового года осталось $daysUntilNewYear дней")
    }

    fun task4(year: Int) : Boolean{
        return Year.of(year).isLeap
    }

    fun task5(yearMonth: YearMonth) : Int{
        val weekends = (1..yearMonth.lengthOfMonth())
            .count{ day ->
                val date = yearMonth.atDay(day)
                date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
            }
        return weekends
    }

    fun task6(){
        val time = measureTime {
            for (i in 1..1000000) {
            }
        }
        println("Время выполнения: ${time.inWholeMilliseconds} мс")
    }

    fun task7(dateStr: String){
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(dateStr, formatter)
        val newDate = date.plusDays(10)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        println("Дата + 10 дней: ${newDate.format(outputFormatter)}")
    }

    fun task8(dateTime: LocalDateTime): LocalDateTime {
        return dateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Moscow")).toLocalDateTime()
    }

    fun task9(birthDate: LocalDate) : Int {
        return Period.between(birthDate, LocalDate.now()).years
    }

    fun task10(yearMonth: YearMonth){
        for (day in 1..yearMonth.lengthOfMonth()){
            val date = yearMonth.atDay(day)
            val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
            println("${date.dayOfMonth} - ${if (isWeekend) "Выходной" else "Рабочий день"}")
        }
    }

    fun task11(date1: LocalDate, date2: LocalDate) : LocalDate{
        val daysBetween = ChronoUnit.DAYS.between(date1, date2)
        return date1.plusDays(Random.nextLong(daysBetween + 1))
    }

    fun task12(dateTime: LocalDateTime) {
        val now = LocalDateTime.now()
        val hours = ChronoUnit.HOURS.between(now, dateTime)
        val minutes = ChronoUnit.MINUTES.between(now, dateTime) % 60
        val seconds = ChronoUnit.SECONDS.between(now, dateTime) % 60
        println("Осталось: $hours часов, $minutes минут, $seconds секунд")
    }

    fun task13(start: LocalDateTime, end: LocalDateTime) : Long{
        var workingHours = 0L
        var current = start
        while(current.isBefore(end)){
            if (current.dayOfWeek != DayOfWeek.SATURDAY && current.dayOfWeek != DayOfWeek.SUNDAY){
                workingHours++
            }
            current = current.plusHours(1)
        }
        return workingHours
    }

    fun task14(date: LocalDate, locale: Locale): String{
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", locale))
    }

    fun task15(date: LocalDate): String{
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
    }

    // для задач с вводом данных:

    fun runTask2(){
        print("Введите первую дату в формате yyyy-MM-dd: ")
        val date1 = LocalDate.parse(readLine())
        print("Введите вторую дату в формате yyyy-MM-dd: ")
        val date2 = LocalDate.parse(readLine())
        task2(date1, date2)
    }

    fun runTask4(){
        print("Введите год: ")
        val year = readLine()?.toIntOrNull() ?: run {
            println("Неверный ввод")
            return
        }
        println("Високосный год: ${task4(year)}")
    }

    fun runTask5(){
        print("Введите год и месяц (yyyy-MM): ")
        val ymStr = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        val ym = YearMonth.parse(ymStr)
        println("Количество выходных: ${task5(ym)}")
    }

    fun runTask7(){
        print("Введите дату в формате dd-MM-yyyy: ")
        val dateStr = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        task7(dateStr)
    }

    fun runTask8(){
        print("Введите дату и время в формате yyyy-MM-dd HH:mm:ss (UTC): ")
        val input = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(input, formatter)
        val result = task8(dateTime)
        println("Время в Москве: $result")
    }

    fun runTask9(){
        print("Введите дату рождения в формате yyyy-MM-dd: ")
        val birthDate = LocalDate.parse(readLine())
        println("Возраст: ${task9(birthDate)} лет")
    }

    fun runTask10(){
        print("Введите год и месяц (yyyy-MM): ")
        val ymStr = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        val ym = YearMonth.parse(ymStr)
        task10(ym)
    }

    fun runTask11(){
        print("Введите начальную дату в формате yyyy-MM-dd: ")
        val date1 = LocalDate.parse(readLine())
        print("Введите конечную дату в формате yyyy-MM-dd: ")
        val date2 = LocalDate.parse(readLine())
        println("Случайная дата между: ${task11(date1, date2)}")
    }

    fun runTask12(){
        print("Введите дату и время события в формате yyyy-MM-dd HH:mm:ss: ")
        val input = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(input, formatter)
        task12(dateTime)
    }

    fun runTask13(){
        print("Введите начало периода в формате yyyy-MM-dd HH:mm:ss: ")
        val startStr = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        print("Введите конец периода в формате yyyy-MM-dd HH:mm:ss: ")
        val endStr = readLine() ?: run {
            println("Неверный ввод")
            return
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val start = LocalDateTime.parse(startStr, formatter)
        val end = LocalDateTime.parse(endStr, formatter)
        println("Рабочих часов: ${task13(start, end)}")
    }

    fun runTask14(){
        print("Введите дату в формате yyyy-MM-dd: ")
        val date = LocalDate.parse(readLine())
        print("Введите локаль (например: ru, en): ")
        val localeStr = readLine() ?: "ru"
        val locale = Locale(localeStr)
        println("Дата с учётом локали: ${task14(date, locale)}")
    }

    fun runTask15(){
        print("Введите дату в формате yyyy-MM-dd: ")
        val date = LocalDate.parse(readLine())
        println("День недели: ${task15(date)}")
    }
}