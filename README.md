# user-registration-with-geoapi
API microservice using spring boot to simulate user registration

Business Logic & Tech Stack:

- Expose REST API to accept a payload of username, password, and IP address.
- All parameters must not be blank (!= empty and null). Return error messages if not valid
- Password need to be greater than 8 characters, containing at least 1 number, 1 Captialized letter, 1 special character in this set “_ # $ % .” Return error messages if not valid
- Call this end point to get geolocation for the provided IP:IP-API.com - Geolocation API - Documentation - JSON. If the IP is not in Canada, return error message that user is not elligible to register
- When all validation is passed, return a random uuid and a welcome message with his username and his City Name (resolved using ip-geolocation api)
- The API need to have OpenAPI specification, no matter what your approach is code first or design first.
- Project must use maven or gradle to build. Generate a spring boot project here: Spring Initializr 
- Need to have JUnit Tests

## How to run the app?
Clone the repo and run the command
`./mvnw spring-boot:run`

After that run following `curl` commands

<img width="1028" alt="image" src="https://user-images.githubusercontent.com/37356432/149896228-86dcb3cf-dac5-4bbc-909c-ccae4765181d.png">

<img width="1023" alt="image" src="https://user-images.githubusercontent.com/37356432/149896336-f9682b89-0cdd-41d4-a54e-c96ef888d210.png">
