#  Banking Application (Spring Boot + JWT Security)

A secure and scalable backend Banking Application built using Spring Boot. This project implements JWT-based authentication and Spring Security to protect APIs and manage user access.

---

##  Features

- User Authentication (Register/Login)
- JWT Token-based Security
- Role-based Access Control (if applicable)
- Account Management
- Loan Request Handling
- Secure REST APIs
- Layered Architecture (Controller, Service, Repository)
- DTO-based request handling

---

##  Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate / JPA
- MySQL
- Maven

---

##  Security Features

- Custom UserDetailsService implementation
- JWT Token Provider for secure authentication
- JWT Authentication Filter
- Security Configuration using Spring Security
- Stateless session management

---

##  Project Structure

src/main/java
│
├── controller
├── service
├── repository
├── dto
│   ├── AccountRequest
│   ├── AuthRequest
│   ├── LoanRequest
│
├── security
│   ├── JwtTokenProvider
│   ├── JwtAuthenticationFilter
│   ├── CustomUserDetailsService
│   ├── SecurityConfig
│
└── BankingApplication.java

---

##  API Flow

Client → Controller → Service → Repository → Database

Authentication Flow:
Login → JWT Token Generation → Token Validation → Access Protected APIs

---

##  Learning Outcomes

- Spring Boot backend development
- JWT authentication and authorization
- Spring Security configuration
- DTO-based clean architecture
- Secure REST API design
- Real-world backend system structure

---

##  Future Improvements

- Frontend integration (React/Angular)
- Transaction history module
- Email notifications
- Advanced role-based system (Admin/User)
- Microservices architecture

---

##  Author

Yumna Saeed  
