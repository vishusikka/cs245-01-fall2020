Vishu Sikka
CS 245-01
September 13, 2020

Time Complexity:
Best Case: all the indices have decreasing order and it is ditectable by comparing the first ans second character of each column in this case the run time will be O(n)
Worst Case: we would need to go through all the indices to detect wether or not they are valid and in this case the time complexity is O(n^2)

Space Complexity:
Best Case: no indices needs to be deleted and we don't add anything to our list. O(1)
Worst Case: all indices need to be deleted and we need to add them to our list O(n)
