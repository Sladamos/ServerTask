# Internship backend task

## Description

This project is a simple local server implemented in Java with Spring framework. It is an internship task. The main goal is to query data from Narodowy Bank Polski's public APIs and return relevant information from them.

## How to start

To start the server without docker, run one of these commands:

- on Windows System
```
mvnw.cmd spring-boot:run
```
- on Unix System
```
./mvnw spring-boot:run
```

To start the server in a docker container, run sequence of comands (Windows System as example):

```
mvnw.cmd clean package
docker build -t [image_id/image_tag] .
docker run -d -p [host_port:container_port] --name [container_name] [image_id/image_tag]
```

## Endpoints

Server provides a separate endpoint for each operation:

1. Given a date (formatted YYYY-MM-DD) and a currency code, provide its average exchange rate.

```
URL path: nbp/exchanges/{currency code}/{properly formatted date}
```

2. Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value.

```
URL path: nbp/extremes/{currency code}/{number of last quotations}
```
3. Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate.

```
URL path: nbp/differences/{currency code}/{number of last quotations}
```

## Examples
- Firstly launch server  
```
mvnw.cmd spring-boot:run
```
- To query operation 1, run this command (which should have the value 5.2768 as the returning information):
```
curl http://localhost:8080/nbp/exchanges/GBP/2023-01-02
```
- To query operation 2, run this command (which should have the value 5.2768 as the returning information):
```
curl http://localhost:8080/nbp/extremes/GBP/12
```
- To query operation 3, run this command (which should have the value 0.107 as the returning information):
```
curl http://localhost:8080/nbp/differences/GBP/12
```  