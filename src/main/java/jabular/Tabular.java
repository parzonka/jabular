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

import java.util.HashMap;
import java.util.Map;

public class Tabular {

    private final Map<Object, Integer> rowIndex;
    private final Map<Object, Integer> columnIndex;
    private final String[][] data;

    private Tabular(Map<Object, Integer> rowIndex, Map<Object, Integer> columnIndex) {
	this.rowIndex = rowIndex;
	this.columnIndex = columnIndex;
	data = new String[rowIndex.size()][columnIndex.size()];
    }

    public static TabularBuilder getBuilder() {
	return new TabularBuilder();
    }

    public void setValue(String rowLabel, String columnLabel, String value) {
	Integer[] index = getIndex(rowLabel, columnLabel);
	data[index[0]][index[1]] = value;
    }

    public String getValue(String rowLabel, String columnLabel) {
	Integer[] index = getIndex(rowLabel, columnLabel);
	return data[index[0]][index[1]];
    }

    private Integer[] getIndex(String rowLabel, String columnLabel) {
	Integer[] index = { rowIndex.get(rowLabel), columnIndex.get(columnLabel)};
	if (index[0] == null) {
	    throw new IllegalArgumentException(String.format("The rowLabel [%s] is unknown. Known labels are: %s",
		    rowLabel, rowIndex.toString()));
	}
	if (index[1] == null) {
	    throw new IllegalArgumentException(String.format("The columnLabel [%s] is unknown. Known labels are: %s",
		    columnLabel, columnIndex.toString()));
	}
	return index;
    }

    public static class TabularBuilder {

	private Map<Object, Integer> rowIndex;
	private Map<Object, Integer> columnIndex;

	/**
	 * Sets the row labels. Tabular values will have be set using these names.
	 *
	 * @param rowLabels
	 * @return this builder
	 */
	public TabularBuilder setRows(String... rowLabels) {
	    rowIndex = new HashMap<Object, Integer>();
	    for (int i = 0; i < rowLabels.length; i++) {
		rowIndex.put(rowLabels[i], i);
	    }
	    return this;
	}

	/**
	 * Sets the number of rows. Tabular values will have to be set using strings in ["1", ..., "$numberOfRows"].
	 *
	 * @param numberOfRows
	 * @return this builder
	 */
	public TabularBuilder setRows(final int numberOfRows) {
	    if (numberOfRows < 1)
		throw new IllegalArgumentException("Number of rows must be at least 1");
	    columnIndex = new HashMap<Object, Integer>();
	    for (int i = 0; i < numberOfRows; i++) {
		rowIndex.put(i, i);
	    }
	    return this;
	}

	/**
	 * Sets the column labels. Tabular values will have be set using these labels.
	 *
	 * @param rowLabels
	 * @return this builder
	 */
	public TabularBuilder setColumns(String... columnLabels) {
	    columnIndex = new HashMap<Object, Integer>();
	    for (int i = 0; i < columnLabels.length; i++) {
		columnIndex.put(columnLabels[i], i);
	    }
	    return this;
	}

	/**
	 * Sets the number of columns. Tabular values will have to be set using strings in ["1", ...,
	 * "$numberOfColumns"].
	 *
	 * @param numberOfColumns
	 * @return this builder
	 */
	public TabularBuilder setColumns(final int numberOfColumns) {
	    if (numberOfColumns <= 0)
		throw new IllegalArgumentException("Number of columns must be at least 1");
	    columnIndex = new HashMap<Object, Integer>();
	    for (int i = 0; i < numberOfColumns; i++) {
		columnIndex.put(i, i);
	    }
	    return this;
	}

	public Tabular build() {
	    return new Tabular(rowIndex, columnIndex);
	}

    }

}
