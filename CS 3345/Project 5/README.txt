Aj Shah
ajs170530
CS 3345.004
Anjum Chida
Project 5 - QuickSorter.java
IDE: IntelliJ IDEA

Zip File Contains
-README.txt
-QuickSorter.java
-Main.java

Report File Samples

Array Size = 10
FIRST_ELEMENT : PT0.0009082S
RANDOM_ELEMENT : PT0.0002486S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0001339S
MEDIAN_OF_THREE_ELEMENTS : PT0.0001031S

Array Size = 100
FIRST_ELEMENT : PT0.0011446S
RANDOM_ELEMENT : PT0.0009505S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0032065S
MEDIAN_OF_THREE_ELEMENTS : PT0.0004341S

Array Size = 1000
FIRST_ELEMENT : PT0.0043196S
RANDOM_ELEMENT : PT0.0054563S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0099464S
MEDIAN_OF_THREE_ELEMENTS : PT0.0019511S

Array Size = 100000
FIRST_ELEMENT : PT0.1533973S
RANDOM_ELEMENT : PT0.1311186S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.1451848S
MEDIAN_OF_THREE_ELEMENTS : PT0.1059701S

Which Pivot Strategy was the fastest? Why?
For both small and large array sizes, the median of three elements seemed to be the fastest strategy.This is because this strategy reduced the number of pivots and therefore saving time.

Already sorted arrays vs almost-sorted arrays - Which was fastest?
For arrays that are sorted or almost-sorted, I noticed that the first element strategy takes much longer than the other strategies. The first element strategy is best used for highly unsorted arrays.
