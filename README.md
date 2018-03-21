# Fitspo

An application for a personal trainer to follow up his/her customers. The idea is that the customer has an app on their phone which collects data
from their regular lives, and sends it to our server daily.
This data includes steps, heartrate, gps-data and more, but most likely we'll not be using any more than the step data.
What we aim to do is to provide the personal trainer with more detailed information about the day to day activity of the person they're working on,
as well as the progression.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
- Git
- JDK version 1.8
- IDE with Maven Extension, e.g Eclipse
- A clone of this repo on your local machine

### Installing with Eclipse
These instructions will give you a development env running in Eclipse
1. Download Eclipse with TDT4180 extension. This version will give you access to testfunctonalites through Maven
When installing eclipse choose advanced mode and select product tdt4180 without any projects.


Clone the project from terminal:
```
cd path/to/yourEclipseWorkspace
git clone git@gitlab.stud.iie.ntnu.no:tdt4140-2018/06.git
```

### Running locally 
Open Eclipse and the project folder.
Run tdt4140-gr1806.app.core.FitspoApp.Java and the application should start.

## Running the tests
We're using Junit 4 as our unit testing framework. 

### Unit tests
Right-click on the tdt4140-gr1806.core folder and click "Run As Maven Test". To run one specific unit test just right click it and choose "Run as JUnit test". 

### Integration tests 
For this you'll need to use Maven in the terminal, navigate to the project folder tdt4140-gr1806 and run mvn verify. 

## Deployment
1. Set up your database information in ConnectionManager in the core folder.
2. Create a .jar file for the project and run the application.

## Built With
* [Eclipse](https://www.eclipse.org/) - IDE
* [IntelliJ] (https://www.jetbrains.com/idea/) - IDE
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing
### Create a branch
1. git checkout master
2. git pull origin master to get the latest main code
3. git checkout -b name-of-my-branch (Name of branch should start with #issuenumber)

### Make the Change
1. Follow the "Running locally" instructrions
2. Save the files and check your application locally by starting FitspoApp.java

### Test the change
Unit tests and integration tests should be written
1. See "Testing instructions" for further details

### Push it
1. git add -A && git commit -m"message" ("message" should contain what you have done)
2. git push --set-upstream my-branch-name

### Create a merge request for it
Go to you branch in gitlab. Click on "merge request", choose your target and write what you have done.

### List of contributors
- Adrian Hofseth
- Åsmund Staldvik
- Tore Tefre
- Matias Ravnå Eidem
- Henriette Andersen
- Ingrid Domben
- Magnus Gaustad Eriksson


## Versioning

We use [Gitlab](https://about.gitlab.com/) for versioning.

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/06/blob/master/LICENSE) file for details

## Acknowledgments

* Our teaching assistants for great help and reviews.
* [reactjs](https://github.com/reactjs/reactjs.org) and [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) for great templates for readme.
