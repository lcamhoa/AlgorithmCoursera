/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
public class process_packagesNGTest {
    
    @Test
    public void testRun() throws Exception {
        File resourcesDirectory = new File("src/test/resources/network_packet_processing_simulation");
        final Stream<File> fileStream = Arrays.stream(resourcesDirectory.listFiles());
        Map<String, String> fileContents = fileStream.collect(Collectors.toMap(File::getName, file -> {
            try {
                return new String(Files.readAllBytes(file.toPath())).trim();
            } catch (IOException ex) {
                Logger.getLogger(process_packagesNGTest.class.getName()).log(Level.SEVERE, null, ex);
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
                    // convert String into InputStream
                    InputStream is = new ByteArrayInputStream(text.getBytes());
                    String result;
                    try {
                        ByteArrayOutputStream bas = new ByteArrayOutputStream();
                        PrintStream os = new PrintStream(bas);
                        process_packages.simulatePackageFlow(is, os);
                        result = bas.toString();
                    } catch (IOException ex) {
                        Logger.getLogger(process_packagesNGTest.class.getName()).log(Level.SEVERE, null, ex);
                        result = null;
                    }
                    assertEquals(result, expResult);
                });
    }
   
}
