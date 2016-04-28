/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class check_bracketsNGTest {
    
    public check_bracketsNGTest() {
    }

    @Test
    public void testCheck_for_unbalanced_brackets() {
        File resourcesDirectory = new File("src/test/resources/check_brackets_in_code");
        final Stream<File> fileStream = Arrays.stream(resourcesDirectory.listFiles());
        Map<String, String> fileContents = fileStream.collect(Collectors.toMap(File::getName, file -> {
            try {
                return new String(Files.readAllBytes(file.toPath())).trim();
            } catch (IOException ex) {
                Logger.getLogger(check_bracketsNGTest.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }));
        check_test_files(fileContents);
    }

    static void check_test_files(Map<String, String> fileContents) {
        fileContents.entrySet().stream()
                .filter((entry) -> (!entry.getKey().endsWith(".a")))
                .forEach((Map.Entry<String, String> entry) -> {
                    System.out.println("Applying input from " + entry.getKey());
                    final String text = entry.getValue();
                    final String expResult = fileContents.get(entry.getKey() + ".a");
                    final String result = check_brackets.check_for_unbalanced_brackets(text);
                    assertEquals(result, expResult);
                });
    }
    
}
