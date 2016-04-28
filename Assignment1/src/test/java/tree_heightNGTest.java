/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
public class tree_heightNGTest {
    
    public tree_heightNGTest() {
    }

    @Test
    public void testRun() throws Exception {
        File resourcesDirectory = new File("src/test/resources/tree_height");
        final Stream<File> fileStream = Arrays.stream(resourcesDirectory.listFiles());
        Map<String, String> fileContents = fileStream.collect(Collectors.toMap(File::getName, file -> {
            try {
                return new String(Files.readAllBytes(file.toPath())).trim();
            } catch (IOException ex) {
                Logger.getLogger(tree_heightNGTest.class.getName()).log(Level.SEVERE, null, ex);
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
                    tree_height instance = new tree_height();
                    // convert String into InputStream
                    InputStream is = new ByteArrayInputStream(text.getBytes());
                    Integer result;
                    try {
                        result = instance.computeHeight(is);
                    } catch (IOException ex) {
                        Logger.getLogger(tree_heightNGTest.class.getName()).log(Level.SEVERE, null, ex);
                        result = null;
                    }
                    assertEquals(result, Integer.valueOf(expResult));
                });
    }
}
