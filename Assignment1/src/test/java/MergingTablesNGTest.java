/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class MergingTablesNGTest {
    
    @Test
    public void testMerge() {
        List<MergingTables.Table> tables = IntStream.of(1, 1, 1, 1, 1)
                .mapToObj(i -> new MergingTables.Table(i)).collect(Collectors.toList());

        tables.get(2).merge(tables.get(4));
        assertEquals(MergingTables.Table.getMaximumNumberOfRows(), 2);

        tables.get(1).merge(tables.get(3));
        assertEquals(MergingTables.Table.getMaximumNumberOfRows(), 2);

        tables.get(0).merge(tables.get(3));
        assertEquals(MergingTables.Table.getMaximumNumberOfRows(), 3);

        tables.get(4).merge(tables.get(3));
        assertEquals(MergingTables.Table.getMaximumNumberOfRows(), 5);

        tables.get(4).merge(tables.get(2));
        assertEquals(MergingTables.Table.getMaximumNumberOfRows(), 5);
    }
    
}
