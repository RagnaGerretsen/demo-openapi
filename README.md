# Demo OpenAPI 
This is a demo project to demo openapi. 
It shows how to use OpenApi for an incoming request API and a client API.
There is also an example of a client API without using OpenApi - 
with a very bad error that could never happen with OpenAPI.

A quick guide on how to use OpenApi:
- Add the swagger dependencies you see in this pom.xml to your own pom
- Add OpenApi plugin
- Create a yaml file (see for example demo.yml or drink.yml)
- In your pom add the execution for your yaml file(s) - 
  make sure to note the differences between controller and client API executions
- Create a ClientConfiguration class for each client yaml (this can usually be left empty)
- In your terminal write mvn clean install
- For controller API:
  - Implement the generated API in your controller class
  - Override the generated API (if using Intellij you can generate the override by right clicking -> generate)
  - Fill with your own code!
- For client API:
  - Initialise your generated API client class 
    (if using Spring boot I recommend using @RequiredArgsConstructor)
  - To use the endpoint simply call the generated method 
    (with the OperationId you used) from the API client class


