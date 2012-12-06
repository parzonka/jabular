/*
 * Copyright (c) 2012 Mateusz Parzonka, Eric Bodden
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

    @Test
    public void getBuilder() throws Exception {
	Tabular tabular = Tabular.getBuilder().setColumns(1).setRows(1).build();
	tabular.setValue("0", "0", "Foo");
	assertEquals("Foo", tabular.getValue("0", "0"));
    }

}
