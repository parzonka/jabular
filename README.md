# jabular

Export tabular data from Java to LaTeX.

##Usage

Here a toy example demonstrating the Java API:
(In the real world you will not enter the data manually in your Java, but transfer it from some data structure or fill it by running some algorithm.)

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

Which can now be set in LaTeX:

![hybrids png](https://github.com/parzonka/jabular/wiki/image/hybrids.png)

##Maven

Snapshot:
```xml
<dependency>
	<groupId>com.github.parzonka</groupId>
	<artifactId>jabular</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```
Snapshot repository:
```xml
<repositories>
	<repository>
		<id>parzonka.github.com-snapshots</id>
		<url>http://parzonka.github.com/m2/snapshots</url>
	</repository>
</repositories>
```
