# Quiz Application

This repository contains a **Quiz Application** built using **Spring Boot** and a **Microservices Architecture**.

## Features

- **Microservices Communication**:  
  Communication between microservices is enabled using **OpenFeign** and **RestTemplate**.
  
- **API Gateway**:  
  An **API Gateway** has been implemented to provide a unified entry point for the application and ensure **load balancing** for efficient traffic management.
  
## Architecture Overview

The application is composed of the following microservices:

1. **Quiz-Service**: Handles quiz creation, management, and retrieval.
2. **User-Service**: Manages user profiles and authentication.
3. **Score-Service**: Tracks and manages quiz scores for each user.
4. **API Gateway**: Central entry point for routing requests to the appropriate microservices.
5. **Load Balancer**: Ensures even distribution of requests across multiple instances of microservices.

## Technologies Used

- **Spring Boot**
- **Spring Cloud (OpenFeign, API Gateway)**
- **Microservices Architecture**
- **RestTemplate**
- **Docker** 
- **Load Balancer** 

## Getting Started

To get started with the application, clone the repository and run the microservices individually.

```bash
git clone https://github.com/yourusername/quiz-application.git
cd quiz-application
