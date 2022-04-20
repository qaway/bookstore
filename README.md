# Bookstore project

Spring Boot service for practice with REST API testing course.

# Table of contents
1. [Dependencies](#dependencies)
2. [How to build](#build)
3. [How to start](#start)
4. [How to stop](#stop)

# Dependencies <a name="dependencies"></a>
- Java 11
- Maven 3.6.3
- Docker for easy start

# How to build <a name="build"></a>
Run from the project root directory command:

```shell
$ mvn clean package -Pdocker
```

# How to start <a name="start"></a>
Run from the project root directory command:

```shell
$ docker-compose up -d
```

Example Output:

```
Creating network "bookstore_default" with the default driver
Creating bookstore_app_1 ... done
```

# How to stop <a name="stop"></a>
Run from the project root directory command:

```shell
$ docker-compose down
```

Example Output:

```
Stopping bookstore_app_1 ... done
Removing bookstore_app_1 ... done
Removing network bookstore_default
```
