```markdown
# ShareBitesDemo Project

Welcome to the ShareBitesDemo repository. This project is a full-stack web application that utilizes Java for the backend and JavaScript (Vue.js) for the frontend.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

You will need the following tools:

- An IDE that supports Java (like IntelliJ IDEA, Eclipse, etc.)
- An IDE that supports JavaScript (like Visual Studio Code)
- MySQL Workbench or similar software for managing MySQL databases

### Installing

Follow these steps to set up your development environment:

1. Clone the repository to your local machine.

```bash
git clone https://github.com/yourusername/ShareBitesDemo.git
```

2. Open the cloned repository with a Java-supported IDE and set it up as a Maven project to import all the dependencies.

3. Open MySQL Workbench and create the necessary databases and tables as defined in the project's MySQL scripts.

4. Configure your database settings by editing the following files with your local configurations:

   - `shareBitesDemo/src/main/resources/application.properties`
   - `shareBitesDemo/src/main/resources/application.yml`

   Change the `spring.datasource.username` and `spring.datasource.password` to your local MySQL configurations.

5. Run the `ShareBitesDemoApplication` in the Java IDE to start the backend server.

6. Open another instance of your IDE for the JavaScript project located at `shareBitesDemo/ShareBitesJS/app`.

7. Navigate to the `app/src` directory in the terminal.

8. Install all the necessary npm packages.

```bash
npm install
```

9. Serve the application.

```bash
npm run serve
```

10. Access the web application by opening the link displayed in the terminal after the server starts.


## Class Diagram
![CJE-i_7TVHIBGpmkevncvdMRYmB-81SYEJ2_3Z1fkGtXiqQ9b1aY44RL8ozbs3VxXOCbxQ4A4bKj-R_F76FVXrMao0MGFLYC0mDtu_FUAUiAEbKEJ8VDVLFYnI2j](https://github.com/Zutttong/shareBitesDemo/assets/96896214/45975488-f6df-46f7-aaa4-59c9ec69f4f1)

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The backend framework
* [Vue.js](https://vuejs.org/) - The frontend framework
* [Maven](https://maven.apache.org/) - Dependency management for Java
* [npm](https://npmjs.com/) - Dependency management for JavaScript

## Contributing

Please read [CONTRIBUTING.md](https://github.com/yourusername/ShareBitesDemo/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/yourusername/ShareBitesDemo/tags). 

## Authors

* **Your Name** - *Initial work* - [yourusername](https://github.com/yourusername)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
```

Make sure to replace placeholders (like `yourusername`, `Your Name`, and other relevant fields) with actual information related to your project. This README gives a clear set of instructions for setting up the project and acknowledges the frameworks and tools used in the development.
