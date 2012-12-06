# jabular

Export tabular data from Java to LaTeX.

##Usage

```java
Tabular tabular = Tabular.getBuilder().setRows(2).setColumns("a", "b", "c").build();
tabular.setValue(0, "a", "0-a");
tabular.setValue(0, "b", "0-b");
tabular.setValue(0, "c", "0-c");
tabular.setValue(1, "a", "1-a");
tabular.setValue(1, "b", "1-b");
tabular.setValue(1, "c", "1-c");
System.out.println(new LaTeXRenderer(tabular).setPrintRowLabels(true).setPrintColumnLabels(true).toString());
```
Will result to:

```latex
\begin{tabular}{l|lll}
  & a & b & c \\ \hline
0 & 0-a & 0-b & 0-c \\
1 & 1-a & 1-b & 1-c \\
\end{tabular}
```