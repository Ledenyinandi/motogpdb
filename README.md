# MotoGPDB

## Description

This is a MotoGP Database project. It has 3 entities: Country, Rider and Grand Prix. Country has an *id* and a *name*.
Rider has an *id*, a *name*, a *bike number* and a *country*. Grand prix has an *id*, a *name*, a *country* and the *rider* who won that grand prix.

You can use the basic endpoints to find, add, update and create the entities.
There are special endpoints like `/country/id/grandprix` which gives back all the Grand Prix from a country, or `/country/id/riders` which gives back all the riders from one country.
There is also the `/grandprix/id/winningrider` endpoint which gives you the actual rider who won the given Grand Prix.

If you want to create a new entity, you don't have to give an id, just the name etc., and if there is an id field referring to another entity, you must give the id of that entity.

## How to run:

- If you downloaded the project from GitHub, you are in the project folder, and you have Maven installed on your computer, you can run it in command line with the following command: `mvn spring-boot:run`
- If you don't have Maven, you can run it in command line with the following command: `java -jar motogpdb-0.0.1-SNAPSHOT.jar`
- If you want to run it with Docker, first you have to download the image file from Dockerhub: `docker pull ledenyinandi/motogp-db`. After you can start it: `docker run -p 8080:8080 ledenyinandi/motogp-db`

## How to test it:

You can test the endpoints on Swagger: <http://localhost:8080/swagger-ui.html>