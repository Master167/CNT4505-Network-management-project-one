# CNT4505-Network-management-project-one
CNT4505 Computer Networks and Distributed Processing Project One from Dr. Sanjay Ahuja (Fall 2016)

This client-server programming project is an iterative TCP based network administration tool. The server provides current system status as requested from the client. The client can request from the server such information as the system time, the uptime, the memory use, netstat, current users, and the running processes. The client program displays a text menu for the user. The user makes requests by selecting a menu option. 

The user will enter the server hostname as a command line argument when the client program is invoked. If there is no command line argument, then the program will print an error message and exit. The client program then enters a loop until told to quit where it will:

* Display a menu 
* Prompt the user for a command
* Test user input for command validity. If user command is invalid, inform the user and redisplay the menu.
* Send that command request to the server on the host
* Get response back from server
* Display response for user

The menu will provide the following choices to the user:
* Host current Date and Time
* Host uptime
* Host memory use
* Host Netstat
* Host current users
* Host running processes
* Quit

Have your server print out diagnostic messages about what it is doing (e.g., “accepting a new connection”, etc.)

You will be required to provide a demo to the instructor. The client-server application will be tested for compliance with all requirements listed above. 
Your code will be expected to deal with invalid user commands. You will turn in binaries and source code of your client and server programs at the time of the demo. 

Plot two graphs of the latency (mean server response time) versus number of clients for the netstat and current date and time commands.  Increase the number of clients as follows: 1, 5, 10, 20, 30, 40, 50, 60, 70, 75. 
