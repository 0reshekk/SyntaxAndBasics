package tasks

import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.concurrent.Future

object FlowDecoratorIONIOTasks {
    fun task1(){
        val inputFile= "register_input.txt"
        val outputFile= "register_output.txt"

        try{
            BufferedReader(FileReader(inputFile)).use { reader ->
                BufferedWriter(FileWriter(outputFile)).use { writer ->
                    var line: String?
                    while(reader.readLine().also {line = it} != null){
                        writer.write(line!!.uppercase())
                        writer.newLine()
                    }
                }
            }
            println("Успешная запись в $outputFile")
        } catch (e: FileNotFoundException){
            println("Файл не найден: ${e.message}")
        } catch (e: IOException){
            println("Ошибка ввода-вывода: ${e.message}")
        }
    }

    interface TextProcessor{
        fun process(text: String): String
    }

    class SimpleTextProcessor: TextProcessor{
        override fun process(text: String): String = text
    }

    class UpperCaseDecorator(private val processor: TextProcessor): TextProcessor{
        override fun process(text: String): String = processor.process(text).uppercase()
    }

    class TrimDecorator(private val processor: TextProcessor): TextProcessor{
        override fun process(text: String): String = processor.process(text).trim()
    }

    class ReplaceDecorator(private val processor: TextProcessor): TextProcessor{
        override fun process(text: String): String = processor.process(text).replace(' ', '_')
    }

    fun task2(){
        val processor: TextProcessor = ReplaceDecorator(UpperCaseDecorator(TrimDecorator(SimpleTextProcessor())))
        val result = processor.process(" Test text: Hello world ")
        println(result)
    }

    fun task3(){
        val largeFile = "REALLY_large_file.txt"
        val ioOutputFile ="io_output.txt"
        val nioOutputFile ="nio_output.txt"
        try{
            val startIO = System.currentTimeMillis()
            BufferedReader(FileReader(largeFile)).use { reader ->
                BufferedWriter(FileWriter(ioOutputFile)).use {writer ->
                    var line: String?
                    while(reader.readLine().also {line = it} != null){
                        writer.write(line)
                        writer.newLine()
                    }
                }
            }
            val endIO = System.currentTimeMillis()
            val startNIO = System.currentTimeMillis()
            FileChannel.open(Paths.get(largeFile)).use { inputChannel ->
                FileChannel.open(Paths.get(nioOutputFile), StandardOpenOption.CREATE, StandardOpenOption.WRITE).use { outputChannel ->
                    val buffer = ByteBuffer.allocate(1024)
                    while (inputChannel.read(buffer) > 0){
                        buffer.flip()
                        outputChannel.write(buffer)
                        buffer.clear()
                    }
                }
            }
            val endNIO = System.currentTimeMillis()
            println("Время чтения IO: ${endIO - startIO} мс")
            println("Время чтения NIO: ${endNIO - startNIO} мс")
        } catch (e: FileNotFoundException){
            println("Файл не найден: ${e.message}")
        } catch (e: IOException){
            println("Ошибка ввода-вывода: ${e.message}")
        }
    }

    fun task4(){
        val sourceFile = "source.txt"
        val destFile = "copied.txt"
        try {
            FileInputStream(sourceFile).channel.use { srcChannel ->
                FileOutputStream(destFile).channel.use { destChannel ->
                    srcChannel.transferTo(0, srcChannel.size(), destChannel)
                }
            }
            println("Файл успешно скопирован")
        } catch (e: Exception){
            println("Ошибка при копировании файла: ${e.message}")
        }
    }

    fun task5(){
        val filePath = "REALLY_large_file.txt"
        try {
            val fileChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ)
            val buffer = ByteBuffer.allocate(128)
            var position = 0L
            var bytesRead: Int
            do {
                val future: Future<Int> = fileChannel.read(buffer, position)
                bytesRead = future.get()
                if (bytesRead > 0) {
                    buffer.flip()
                    val data = ByteArray(bytesRead)
                    buffer.get(data)
                    println(String(data))
                    buffer.clear()
                    position += bytesRead
                }
            } while (bytesRead > 0)
            fileChannel.close()
        } catch (e: Exception){
            println("Ошибка при чтении файла: ${e.message}")
        }
    }
}