# jabular

Export tabular data from Java to LaTeX.

##Usage

```java
Tabular tabular = Tabular.getBuilder().setRows("Lion", "Tiger").setColumns("Jaguar", "Leopard").build();
tabular.setValue("Lion", "Jaguar", "Liguar");
tabular.setValue("Lion", "Leopard", "Lipard");
tabular.setValue("Tiger", "Jaguar", "Tiguar");
tabular.setValue("Tiger", "Leopard", "Tigard");
System.out.println(new LaTeXRenderer(tabular).setPrintRowLabels(true).setPrintColumnLabels(true).toString());
```
Will get rendered to:

```latex
\begin{tabular}{l|ll}
  & Jaguar & Leopard \\ \hline
Lion & Liguar & Lipard \\
Tiger & Tiguar & Tigard \\
\end{tabular}
```

Which can be set in LaTeX:

![hybrids png](https://github.com/parzonka/jabular/wiki/image/hybrids.png)
