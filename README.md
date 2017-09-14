# tickets1-exercise
this repository holds a small exercise in testing, dependency injection and spring configuration.

running the main function of the project will run a command line application that asks the user 3 questions:
 a. Please choose the name of the show:
 b. Pleasee choose the desired date:
 c. number of tickets:
 
 The user can choose to enter whatever string he wants as the name of the show and choose whatever date and the amount of tickets he wants.
 The application than calculates and generates a report with the following data:
 a. The name of the show.
 b. The desired date.
 c. the number of tickets.
 d. The last cancelation date.
 The application prints the report to the screen and also saves it on the file system on a specific path. 
 
 The last cancelation date is calculated as follows:
 If the date of the show had already passes- exception is thrown and printed to the screen.
 A customer can cancel his order within a week from the date of purchase unless the customer buys a ticket to a show that is due less
 than a week from the moment of of purchase (in the latter case, cancellation is not possible).
 
 
whoops, this project was not written using TDD and has no automated tests :@.
Your mission is (high level):
1. Write one unit test (that passes) to the application (choose whatever test case you want)
2. Note that everytime the test runs, there is a side effect -  a file is written on the file system. redesign and refactor the application
   using dependency injection to avoid the side effect when running the test
3. Configure all objects creation with Spring using Java configuration classes

And being more specific in case anyone wants more specifications:
1. Write one unit test (that passes) to the application (choose whatever test case you want)
2. Extract the WindowsFileWriter to an interface and use dependency injection in the TicketService class.
3. Use a mock in the tests so that the file won’t be written in the file system when running the tests.
4. Configure everything with Spring.
5. Read the report path from a properties file instead of using hard coded value. 


Enjoy:)
