package com.att.tlv.training.test.answers.junit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

/**
 * Add JUnit rules so that a message is printed to the console ONCE
 * before the tests, and another message after EACH test.
 * 
 */
public class JunitRulesTestAnswer {
    
    @ClassRule
    public static final TestRule before = new BeforeLoggingRule("Running set up before class...");
    @Rule
    public final TestRule after = new AfterLoggingRule("Running tear down after test...");
    
    // Bonus: like @Before and @After methods, execution order of multiple rules is not predictable.
    // Solution: RuleChain!
    @Rule
    public TestRule chain = RuleChain.outerRule(new BeforeLoggingRule(">>>>> Running set up #1 before test..."))
                                     .around(new BeforeLoggingRule(">>>>> Running set up #2 before test..."));

    @Test
    public void test1() {
        System.out.println("Running test1!");
    }

    @Test
    public void test2() {
        System.out.println("Running test2!");
    }
}

abstract class LoggingRule extends ExternalResource {

    private final String message;

    public LoggingRule(String message) {
        this.message = message;
    }
    
    protected void log() {
        System.out.println(message);
    }
}

class BeforeLoggingRule extends LoggingRule {

    public BeforeLoggingRule(String message) {
        super(message);
    }
    
    @Override
    protected void before() {
        log();
    }
}

class AfterLoggingRule extends LoggingRule {
    
    public AfterLoggingRule(String message) {
        super(message);
    }
    
    @Override
    protected void after() {
        log();
    }
}
