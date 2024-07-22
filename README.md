# Basic Anagram Application

## Deployment Steps

### Local

````shell
./mvnw
````
Run the above command to run the fullstack application. By default, it will use an in memory h2 database.
Defaults to the spring-boot:run maven goal.

### Elastic Beanstalk
Unlike test and local, beanstalk uses an RDS database to persist information between sessions. This means we
need to apply a profile to the package operation.

````shell
./mvnw clean package -Pbeanstalk
eb deploy
````
