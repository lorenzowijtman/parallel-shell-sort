# Parallel Shell Sort
Parallel implementation of the shell sort algorithm using multithreading.

Our application is divided into two projects, one is the local version of our parallel shell sort algorithm, 
and the other is meant to simulate a serverside version. We will discuss how to run both sub projects below. 
Both projects are implemented as command line applications.

## IDEA
We developed this project in IntelliJ, though you are free to use any IDEA of choice some steps described could be
specific to IntelliJ.

### Setup
To get started, simply download this repository and save it somewhere locally on your computer. For this you could u
se the following command on any desired location assuming you already have Git installed.
  
```git clone https://github.com/lorenzowijtman/parallel-shell-sort```

To make it easier to run the projects and their main classes we recommend configuring the src folders of
both projects as 'Source' folders in your IDEA. In IntelliJ this can be done by opening the root project folder,
clicking on 'File' in the top left corner of the IDEA and opening the project structure. If you're a keyboard warrior, 
you can also use Ctrl+Alt+Shift+S.

In the project structure, mark 'parallel-shell-sort > src' and 'SortingRMI > src' as source folders.

Secondly you might want to add the Main classes to your run configurations to make it easier to run the applications.
Again in IntelliJ, go to 'Edit configurations' and add a new application.  

The main class for the local version is simply called 'Main'.  
The Main class for starting the server is called 'SortInfoServer'.  
The server uses clients to handle the sorting of data, the Main client class is called 'SortInfoClient'.

You can duplicate the run configuration of the client class a few times to run multiple clients.

## Local version
The local version can be run with the run configuration described above.  
Simply start the application by running the 'Main' run configuration and the command line application will start.  
The application will tell you how many threads are available to be used and ask you for your input on how many 
you actually want to use. Please only enter integers here as it's the expected input, a string will throw an error.  
After specifying the number of threads, the application will ask which size you want to use for the dataset. Again, 
only enter an integer if you want to avoid exceptions. The algorithm will start to run and once finished will provide 
you with a print of the sorted dataset, dataset size and time it took to sort the dataset.

## Server version
To run the server version of our algorithm, start by running the main server class you set up in your run 
configurations. This will also print some information regarding the different command line options such as testing the 
connection with clients, starting the sort algorithm and quitting the server application.

As mentioned the server relies on clients to do the sorting, so start up a few clients from the main class and you 
will see the number of connected clients in the output of the server application. If you want to test the 
connection further, simply type the ```test``` command in the server and a test message will be printed in 
the clients.  

To start sorting, run the ```sort``` command in the server, this will automatically use all the clients that are 
available/ connected. The server will then ask for the dataset site you want to use, and just as before, only enter an 
integer here. We recommend not to use a dataset with a size bigger than 5000 items and running with less than 6 
clients if you want to get the result in less than 5 minutes. This application is not meant to be the most optimized 
solution but instead be used to understand the workings and methodology of parallelizing an application this way.  

When the dataset is sorted the server will print the sorted dataset, size of the dataset and time it took to
sort it. from here on you can either close the server and clients or turn off a client by typing anything into it's 
terminal. Simply closing the terminal will close the client but won't tell the server that the client closed, thus not 
closing the client properly as described will result in an error.

That's all you need to know to run the applications!

