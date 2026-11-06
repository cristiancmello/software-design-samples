import java.io.File

fun runApp(workDir: File, input: ArrayList<String> = arrayListOf()): String {
    val process = ProcessBuilder(
        "java", "-cp", System.getProperty("java.class.path"), "org.example.MainKt"
    )
        .directory(workDir)
        .start()

    process.outputStream.bufferedWriter().use {
        input.forEach { line -> it.write(line) }
    }

    val output = process.inputStream.bufferedReader().readText()

    process.waitFor()

    return output
}