package runner;

import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;


public class KotlinJsTestRunner extends ParentRunner<String> {

    @Repeatable(JsFiles.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface JsFile {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface JsFiles {
        JsFile[] value();
    }

    private NashornTestRunner nashornTestRunner;

    public KotlinJsTestRunner(Class<?> klass) throws Throwable {
        super(klass);

        JsFiles tmp = getTestClass().getAnnotation(JsFiles.class);
        List<String> jsFiles = new ArrayList<>();
        for (JsFile jsFile : tmp.value()) {
            jsFiles.add(jsFile.value());
        }

        nashornTestRunner = new NashornTestRunner(jsFiles);
    }

    @Override
    protected List<String> getChildren() {
        return nashornTestRunner.getTestNames();
    }

    @Override
    protected Description describeChild(String child) {
        System.out.println(child);
        String[] tmp = child.split("\\.");
        return Description.createTestDescription(tmp[0], tmp[1]);
    }

    @Override
    protected void runChild(String child, RunNotifier notifier) {
        Description description = describeChild(child);

        notifier.fireTestStarted(description);
        try {
            nashornTestRunner.runTestCase(child);
        } catch (AssumptionViolatedException e) {
            notifier.fireTestAssumptionFailed(new Failure(description, e));
        } catch (Throwable e) {
            notifier.fireTestFailure(new Failure(description, e));
        } finally {
            notifier.fireTestFinished(description);
        }
    }

}


