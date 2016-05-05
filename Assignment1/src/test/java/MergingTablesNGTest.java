/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class MergingTablesNGTest {
    
    @Test
    public void testMerge_5() {
        List<MergingTables.Table> tables = IntStream.of(1, 1, 1, 1, 1)
                .mapToObj(i -> new MergingTables.Table(i)).collect(Collectors.toList());

        tables.get(2).merge(tables.get(4));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 2);

        tables.get(1).merge(tables.get(3));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 2);

        tables.get(0).merge(tables.get(3));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 3);

        tables.get(4).merge(tables.get(3));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 5);

        tables.get(4).merge(tables.get(2));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 5);
    }
    
    @Test
    public void testMerge_6() {
        List<MergingTables.Table> tables = IntStream.of(10, 0, 5, 0, 3, 3)
                .mapToObj(i -> new MergingTables.Table(i)).collect(Collectors.toList());

        tables.get(5).merge(tables.get(5));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 10);

        tables.get(5).merge(tables.get(4));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 10);

        tables.get(4).merge(tables.get(3));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 10);

        tables.get(3).merge(tables.get(2));
        assertEquals(MergingTables.Table.maximumNumberOfRows, 11);

    }
    
}
