# Selenium SauceDemo Framework

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Headless Mode](#headless-mode)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## Introduction

This repository contains a Selenium-based framework for testing the [SauceDemo](https://www.saucedemo.com/) website. The framework is designed to be easily extendable and maintainable, providing a solid foundation for writing automated UI tests.

## Prerequisites

Before you begin, ensure you have the following installed on your local machine:

- Java Development Kit (JDK) 8 or higher
- Maven
- ChromeDriver or GeckoDriver (for Chrome and Firefox browsers respectively)

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/01TeeTheDev01/selenium-saucedemo-framework.git
    ```

2. Install the dependencies:
    ```sh
    mvn clean install
    ```

## Running Tests

To run all tests, execute the following command:
```sh
mvn clean test
```

# Headless Mode in Selenium

Headless mode is a feature that allows running a browser in a mode without a graphical user interface (GUI). This is useful for running tests in environments where a display is not available or when you want to improve the performance of your tests by avoiding the overhead of rendering the UI.

## Table of Contents
- [Introduction](#introduction)
- [Why Use Headless Mode?](#why-use-headless-mode)
- [Setting Up Headless Mode](#setting-up-headless-mode)
    - [Chrome](#chrome)
    - [Firefox](#firefox)
    - [Safari](#safari)
- [Example Configuration](#example-configuration)
- [Usage in Tests](#usage-in-tests)
- [Conclusion](#conclusion)

## Introduction

Headless mode in Selenium allows you to run tests without opening a browser window. This can significantly speed up test execution and is especially useful in CI/CD pipelines.

## Why Use Headless Mode?

- **Performance**: Tests run faster without the need to render a UI.
- **Resource Usage**: Lower CPU and memory usage.
- **CI/CD Integration**: Easier to integrate into continuous integration and deployment pipelines where a graphical environment may not be available.

## Setting Up Headless Mode

### Chrome

To run Chrome in headless mode, you need to set the `--headless` argument in the `ChromeOptions`.

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadless {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.example.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
```
### Firefox

To run Firefox in headless mode, you need to set the `--headless` argument in the `FirefoxOptions`.

```java
public class FirefoxHeadless {

    public static void main(String[] args) {
        System.setProperty("webdriver.firefox.driver", "/path/to/chromedriver");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.example.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
```

### Edge

To run Edge in headless mode, you need to set the `--headless` argument in the `EdgeOptions`.

```java
public class FirefoxHeadless {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "/path/to/chromedriver");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        WebDriver driver = new EdgeDriver(options);
        driver.get("https://www.example.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
```

### Safari

Safari does not support headless mode directly through the SafariDriver in Selenium. SafariDriver, unlike ChromeDriver or GeckoDriver, does not have a headless mode because Apple has not yet provided such functionality for Safari.


# Troubleshooting Java Issues

This document provides troubleshooting tips and solutions for common issues encountered while working with Java applications and development environments.

## Table of Contents
- [Common Java Issues](#common-java-issues)
    - [ClassNotFoundException](#classnotfoundexception)
    - [NullPointerException](#nullpointerexception)
    - [Compilation Errors](#compilation-errors)
    - [Maven Dependency Issues](#maven-dependency-issues)
- [Debugging Tips](#debugging-tips)


## Common Java Issues

### ClassNotFoundException

#### Description

`ClassNotFoundException` occurs when the JVM tries to load a class but cannot find it in the classpath.


#### Solution

1. **Check Classpath**: Verify if the required JAR file containing the class is included in the classpath.
2. **Dependency Issues**: Ensure that the correct version of the dependency is specified in `pom.xml` or `build.gradle`.
3. **IDE Configuration**: If using an IDE like IntelliJ or Eclipse, refresh dependencies or reimport the project to resolve classpath issues.


### NullPointerException

#### Description

`NullPointerException` (NPE) is thrown when attempting to use an object reference that has not been initialized (i.e., is `null`).


#### Solution

1. **Null Check**: Always perform null checks (`if (obj != null)`) before accessing methods or properties of an object.
2. **Debugging**: Use debugging tools in your IDE to trace the source of the `null` reference.
3. **Review Code**: Review the code to ensure proper initialization of objects and handling of `null` values.


### Compilation Errors

#### Description

Compilation errors occur when the compiler encounters syntax errors or issues with the code.


#### Solution

1. **Review Error Messages**: Read the error messages provided by the compiler to identify the line and nature of the error.
2. **Syntax Correction**: Fix syntax errors such as missing semicolons, parentheses, or incorrect variable declarations.
3. **IDE Features**: Utilize IDE features like auto-suggestion and code formatting to correct errors.


### Maven Dependency Issues

#### Description

Maven dependency issues can arise due to conflicts between different versions of libraries or missing dependencies.


#### Solution

1. **Dependency Tree**: Use `mvn dependency:tree` to inspect the dependency tree and identify conflicts.
2. **Exclusions**: Exclude transitive dependencies that are causing conflicts in `pom.xml`.
3. **Update Versions**: Ensure that all dependencies are updated to compatible versions and resolve version conflicts.


## Debugging Tips

- **Use Logging**: Incorporate logging statements (`System.out.println` or logging frameworks like Log4j) to output variable values and trace program flow.
- **Debugger**: Utilize the debugger in your IDE to step through code execution and inspect variable states.
- **Unit Testing**: Write unit tests to isolate and verify the functionality of individual components and methods.


## Conclusion

By following the troubleshooting tips and solutions provided in this document, you can effectively diagnose and resolve common Java-related issues encountered during development. Continuous learning and leveraging debugging tools are key to improving your troubleshooting skills.


# Contributing

We welcome contributions to improve the project! Whether you want to report a bug, suggest a feature, or submit a patch, please follow these guidelines to contribute effectively.


## Ways to Contribute

### Reporting Bugs

If you encounter a bug or unexpected behavior, please check the existing issues on GitHub to see if it has already been reported. If not, please open a new issue with the following details:
- Description of the issue
- Steps to reproduce the bug
- Expected behavior
- Any relevant error messages or screenshots


### Suggesting Enhancements

You can suggest enhancements or new features by opening an issue on GitHub. Please provide a clear and detailed description of your suggestion, including its potential benefits and use cases.


### Submitting Pull Requests

If you'd like to contribute code to the project:
1. Fork the repository and create your branch from `main`.
2. Make your changes and ensure the code adheres to the project's coding style and guidelines.
3. Write tests to validate your changes, if applicable.
4. Ensure your commits are clear and describe the purpose of your changes.
5. Open a pull request, referencing any relevant issues or discussions.


## Attribution

Contributors who submit significant improvements will be credited in the project's `CONTRIBUTORS.md` file.


## Contact

If you have any questions or need further assistance, feel free to reach out to us through GitHub issues.

Thank you for contributing to the project!