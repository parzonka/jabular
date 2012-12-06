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

import static org.junit.Assert.*;

import org.junit.Test;

public class LaTeXRendererTest {

    @Test
    public void render_printRowAndColumnLabels_noHeader() throws Exception {
	Tabular tabular = Tabular.getBuilder().setRows(2).setColumns("a", "b", "c").build();
	tabular.setValue(0, "a", "0-a");
	tabular.setValue(0, "b", "0-b");
	tabular.setValue(0, "c", "0-c");
	tabular.setValue(1, "a", "1-a");
	tabular.setValue(1, "b", "1-b");
	tabular.setValue(1, "c", "1-c");
	String actual = new LaTeXRenderer(tabular).setPrintRowLabels(true).setPrintColumnLabels(true).toString();

	System.out.println(actual);

	String expected = "" + //
		"\\begin{tabular}{l|lll}\n" + //
		"  & a & b & c \\\\ \\hline\n" + //
		"0 & 0-a & 0-b & 0-c \\\\\n" + //
		"1 & 1-a & 1-b & 1-c \\\\\n" + //
		"\\end{tabular}";

	assertEquals(expected, actual);
    }
}
