# Event manager

## Introduction

This project is a recruitment task to Synerise.

### Assumptions

While creating the project I made few assumptions:
- from the task description phrase "unique event" is most important - I assume when user send same eventId multiple times, it won't be stored
- timestamp of the event is not updated when the same entry is already stored 

## Running application

Clone and import this project into intellij as maven project. Create Spring Boot run configuration with selected EventManagementApplication as main class. Run with green play button.

## REST

### Registrating new event

Endpoint
```
POST
/register/event
```
Body
```
{
	"eventId": "event1",
	"userId": "user1"
}
```
Response
When event is saved - true, else false

### Get amount of unique users per event
Endpoint
```
GET
GET /views/<eventId>?n=<N>
eventId - id of event
N - minutes in past when event occured
```
Response
```
{
"event2": 5
}
```

### Get amount of unique users for all events
Endpoint
```
GET
GET /views/all?n=<N>
eventId - id of event
N - minutes in past when event occured
```
Response
```
{
"event1": 5,
"event2": 7,
}
```
