# Daily Programmer 314-Hard

[Link](https://www.reddit.com/r/dailyprogrammer/comments/6arlw4/20170512_chalenge_314_hard_finding_point_nemo/)

### My Solution
    File: runner.groovy


### Main Issue

    This line failed to read in the text file correct. When split with .split("") to identify the map, it would oddly stop about 75% through a line and separate those into a different array. The only way to get around was to use the second method of reading the text line by line.
`def totalInput = inputFile.getText("UTF-8").split("\n")`

```
def totalInput = []
inputFile.eachLine{ line ->
    totalInput << line
}
```
