package runner

import java.io.FileReader
import java.io.InputStreamReader
import javax.script.Invocable
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class NashornTestRunner(jsFiles: MutableList<String>) {
    val nashorn = ScriptEngineManager().getEngineByName("nashorn")

    init {
        nashorn.eval(InputStreamReader(javaClass.getResourceAsStream("/qunitfake.js")))
        jsFiles.forEach {
            nashorn.eval(FileReader(it))
        }
    }

    val invocableNashorn = nashorn as Invocable

    val testNames: List<String> get() {
        val names = invocableNashorn.invokeFunction("getTestNames") as Array<*>
        return names.map { it.toString() }
    }

    fun runTestCase(testName: String) {
        println(invocableNashorn.invokeFunction("runTest", testName))
    }

}
