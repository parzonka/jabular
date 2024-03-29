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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tabular {

    private final String[] rowLabels;
    private final String[] columnLabels;
    private final String[][] data;
    private final Map<String, Integer> rowIndex;
    private final Map<String, Integer> columnIndex;

    private Tabular(String[] rowLabels, String[] columnLabels) {
	this.rowLabels = rowLabels;
	this.columnLabels = columnLabels;
	rowIndex = createIndex(rowLabels);
	columnIndex = createIndex(columnLabels);
	data = new String[rowLabels.length][columnLabels.length];
    }

    public static Map<String, Integer> createIndex(String[] labels) {
	Map<String, Integer> result = new HashMap<String, Integer>();
	for (int i = 0; i < labels.length; i++) {
	    result.put(labels[i], i);
	}
	return result;
    }

    public static Builder getBuilder() {
	return new Builder();
    }

    public void setValue(int rowNumber, int columnNumber, String value) {
	setValue(Integer.toString(rowNumber), Integer.toString(columnNumber), value);
    }

    public void setValue(int rowNumber, String columnLabel, String value) {
	setValue(Integer.toString(rowNumber), columnLabel, value);
    }

    public void setValue(String rowLabel, int columnNumber, String value) {
	setValue(rowLabel, Integer.toString(columnNumber), value);
    }

    public void setValue(String rowLabel, String columnLabel, String value) {
	Integer[] index = getIndex(rowLabel, columnLabel);
	data[index[0]][index[1]] = value;
    }

    public String getValue(String rowLabel, String columnLabel) {
	Integer[] index = getIndex(rowLabel, columnLabel);
	return data[index[0]][index[1]];
    }

    public String getValue(int rowNumber, int columnNumber) {
	return getValue(Integer.toString(rowNumber), Integer.toString(columnNumber));
    }

    public String getValue(int rowNumber, String columnLabel) {
	Integer[] index = getIndex(Integer.toString(rowNumber), columnLabel);
	return data[index[0]][index[1]];
    }

    public String getValue(String rowLabel, int columnNumber) {
	Integer[] index = getIndex(rowLabel, Integer.toString(columnNumber));
	return data[index[0]][index[1]];
    }

    private Integer[] getIndex(String rowLabel, String columnLabel) {
	Integer[] index = { rowIndex.get(rowLabel), columnIndex.get(columnLabel) };
	if (index[0] == null) {
	    throw new IllegalArgumentException(String.format("The rowLabel [%s] is unknown. Known labels are: %s",
		    rowLabel, Arrays.toString(rowLabels)));
	}
	if (index[1] == null) {
	    throw new IllegalArgumentException(String.format("The columnLabel [%s] is unknown. Known labels are: %s",
		    columnLabel, Arrays.toString(columnLabels)));
	}
	return index;
    }

    protected String[][] getData() {
	return data;
    }

    protected String[] getRowLabels() {
	return rowLabels;
    }

    protected String[] getColumnLabels() {
	return columnLabels;
    }

    public static class Builder {

	private String[] rowLabels;
	private String[] columnLabels;

	/**
	 * Sets the row labels. Tabular values will have be set using these names.
	 *
	 * @param rowLabels
	 * @return this builder
	 */
	public Builder setRows(String... rowLabels) {
	    this.rowLabels = rowLabels;
	    return this;
	}

	/**
	 * Sets the number of rows. Tabular values will have to be set using integer in [0, ..., $numberOfRows-1].
	 *
	 * @param numberOfRows
	 * @return this builder
	 */
	public Builder setRows(final int numberOfRows) {
	    if (numberOfRows < 1)
		throw new IllegalArgumentException("Number of rows must be at least 1");
	    rowLabels = new String[numberOfRows];
	    for (int i = 0; i < numberOfRows; i++) {
		rowLabels[i] = Integer.toString(i);
	    }
	    return this;
	}

	/**
	 * Sets the column labels. Tabular values will have be set using these labels.
	 *
	 * @param columnLabels
	 * @return this builder
	 */
	public Builder setColumns(String... columnLabels) {
	    this.columnLabels = columnLabels;
	    return this;
	}

	/**
	 * Sets the number of columns. Tabular values will have to be set using strings in [0, ..., $numberOfColumns].
	 *
	 * @param numberOfColumns
	 * @return this builder
	 */
	public Builder setColumns(final int numberOfColumns) {
	    if (numberOfColumns <= 0)
		throw new IllegalArgumentException("Number of columns must be at least 1");
	    columnLabels = new String[numberOfColumns];
	    for (int i = 0; i < numberOfColumns; i++) {
		columnLabels[i] = Integer.toString(i);
	    }
	    return this;
	}

	public Tabular build() {
	    return new Tabular(rowLabels, columnLabels);
	}

    }

}
