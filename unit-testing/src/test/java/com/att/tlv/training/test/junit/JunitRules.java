package com.att.tlv.training.test.junit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class JunitRules {
    
    // MUST be public and static!
    @ClassRule
    public static TemporaryFolder TEMP_FOLDER = new TemporaryFolder();
    // MUST be public!
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Test
    public void test1() {
        System.out.println("Class Rule: " + TEMP_FOLDER.getRoot());
        System.out.println("Test Rule: " + tempFolder.getRoot());
    }
    
    @Test
    public void test2() {
        System.out.println("Class Rule: " + TEMP_FOLDER.getRoot());
        System.out.println("Test Rule: " + tempFolder.getRoot());
    }
}