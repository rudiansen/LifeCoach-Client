# LifeCoach Client

This repository contains a web service client which invoke SOAP-based web services deployed on the following URL https://lifecoach-181499.herokuapp.com/ws/people?wsdl. It shows some examples of how to consuming SOAP-based web services methods.

## How does it work?

The client works simply by invoking all public methods exposed by the server. All the outputs of the invocations will be printed on the terminal and logged in a new created file client-server.log.

## How to run the program?

To run the program, clone this repository on your local machine by following below steps:

1. `git clone https://github.com/rudiansen/LifeCoach-Client.git`
2. `cd LifeCoach-Client`
3. `ant execute.client`

By executing above commands, you will see the results of the program both in your terminal and in a new created file client-server.log.

PS: To be able to execute this command, you are expected to have ANT set on your machine.

