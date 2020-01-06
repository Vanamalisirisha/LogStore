# LogStore

1. Reads a text file containing JSON objects as Events using scanner object.
2. Stores the events with same id as one Key with two log records as values in a concurrent hashmap
3. Initializes the LogAlert object according to the conditions mentioned - Initializes the Alert to true if the event takes longer than 4s.
4. Uses threads to maintain concurrency and uses concurrent hashmap to make the operations threadsafe.
5. Exception handling and Loggers for the visibility of the process going wrong.
