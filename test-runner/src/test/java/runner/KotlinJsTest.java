package runner;

import org.junit.runner.RunWith;
import runner.KotlinJsTestRunner.JsFile;

@RunWith(KotlinJsTestRunner.class)
@JsFile("../container/build/classes/main/lib/kotlin.js")
@JsFile("../container/build/classes/main/container_main.js")
@JsFile("../container/build/classes/test/container_test.js")
public class KotlinJsTest {

}
