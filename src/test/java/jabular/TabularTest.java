/*
 * Copyright (c) 2012 - 2013 Mateusz Parzonka
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Mateusz Parzonka - initial API and implementation
 */
package jabular;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TabularTest {

    @Test(expected=IllegalArgumentException.class)
    public void setValue_unknownLabel() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue("42", "0", "Foo");
    }

    @Test(expected=IllegalArgumentException.class)
    public void getValue_unknownLabel() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue("0", "0", "Foo");
	tabular.getValue("42", "0");
    }

    @Test
    public void getValue_tabularWithNumberLabels() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue("0", "0", "Foo");
	assertEquals("Foo", tabular.getValue("0", "0"));
    }

    @Test
    public void getValue_tabularWithStringLabels() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns("a", "b", "c").setRows(2).build();
	tabular.setValue("0", "a", "0-a");
	tabular.setValue("0", "b", "0-b");
	tabular.setValue("0", "c", "0-c");
	tabular.setValue("1", "a", "1-a");
	tabular.setValue("1", "b", "1-b");
	tabular.setValue("1", "c", "1-c");
	assertEquals("0-a", tabular.getValue("0", "a"));
	assertEquals("0-b", tabular.getValue("0", "b"));
	assertEquals("0-c", tabular.getValue("0", "c"));
	assertEquals("1-a", tabular.getValue("1", "a"));
	assertEquals("1-b", tabular.getValue("1", "b"));
	assertEquals("1-c", tabular.getValue("1", "c"));
    }

    @Test
    public void getValue_int_String() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue(0, "0", "Foo");
	assertEquals("Foo", tabular.getValue(0, "0"));
    }

    @Test
    public void getValue_String_int() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue("0", 0, "Foo");
	assertEquals("Foo", tabular.getValue("0", 0));
    }

    @Test
    public void getValue_int_int() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue(0, 0, "Foo");
	assertEquals("Foo", tabular.getValue(0, 0));
    }


}
