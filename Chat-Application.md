# 1. What is Web socket
![Screenshot from 2022-06-14 11-02-34](https://user-images.githubusercontent.com/42698268/173755193-164d9f43-8f45-4eb5-835b-33c1bbfb5d3d.png)

but in case of http, the connection is closed once a request is served by a server and there is even a time constraint to open a new connection request again, that's why web socket is more preferrable in case of by directional communication development

# 2. Web socket and spring architecture

![Screenshot from 2022-06-14 11-14-39](https://user-images.githubusercontent.com/42698268/173756056-a65139b2-76dc-4b3d-a152-100b326a501a.png)
* From web socket client section we send the data by specifying the URL
* The request will come to the request channel 
* Then it will go the simple broker based on the URL or we can say the message broker
* The message broker helps us to route the data to the corresponding response channel
* From response channel whichever server is available will get the data
* Normally in this architecture, we are going to send the text data over the channel
* To handle the text data over the request channel and response channel,we need to depend on another protocol called STOMP protocol

# 3. We have web socket protocol for communication purpose then why need STOMP protocol?

![Screenshot from 2022-06-14 11-16-59](https://user-images.githubusercontent.com/42698268/173760499-ab8c26d9-a770-4b99-9bd1-e3c84e68ed55.png)

# SERVER SIDE APPLICATION
# 4. Now we'll go to the eclipse and create a server side application and then we'll create a client side application
## A. In pom.xml we added only websocket dependency, to achieve full web socketfeatures using stomp we need to add one more dependency

```java
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-reactor-netty</artifactId>
</dependency>
```
## B. Now create required packages such as configuration, controller and model

![Screenshot from 2022-06-15 12-47-44](https://user-images.githubusercontent.com/42698268/173769513-d60d80bb-a0ae-4c8f-80ad-6e188c0a7aa6.png)

## C. Now go to the configuration package and enable the stomp protocol and message broker 
* Create a class named ws.config
* Now annotate it with @Configuration
* Now enable web socket server using @EnableWebSocketMessageBroker
* Now implement your class to WebSocketMessageBrokerConfigurer
* Now you have to write two methods, one for registering STOMP end-point and another for message broker
* In case of STOMP END points, we need to provide a prefix URL


![Screenshot from 2022-06-15 13-44-49](https://user-images.githubusercontent.com/42698268/173777731-5f4f9c6b-e69c-4dc8-b388-4246ee2f97c7.png)


* We are using withSockJS() here, the advantage of using withSockJS here is that whenever the web socket connection is disconnected or the web socket connection cannot be established then that time connection will be downgraded to http and the communication between client and server still continue
* For configureMessageBroker() we need to provide URL
* We need to specify the url here, the same url we need to configure in our client side

![Screenshot from 2022-06-15 13-57-32](https://user-images.githubusercontent.com/42698268/173780733-c67021fd-c8fb-4d00-b7b6-161617fa11ad.png)

* Now we are done with the configuration

# 5. Now create model
* Create a class called ChatMessage
* Define Content and Sender
* Create one more enum to check whether the sender has joined the chat or left the chat 
* So define CHAT, LEAVE and JOIN
* And define getters and setters for defined fields

# 6. Now create controller
* Annotate your class with @Controller
* First the user need to be registered on the portal, then only you will be able to chat, so first of all add the user to the chat
* Give the request body chatMessage and annotate it with @Payload
* For websocket to capture the request, we use annotation @payload  
* And we also need to capture the username who is going to chat now, to do that we need to use a class SimpleMessageHeaderAccessor
* Now get the session attribute and in the session attribute, put the name of the user who is goint to register now, and get the username from the request body using chatMessage.getSender()
* Now return the chatMessage
* and annotate this method with @MessageMapping, this annotation will help us to map the same url from client to our server
* So specify the path
* and one more annotation which is @SendTo(), this annotation will help  us to specify the queue, i.e request channel and response channel based on the url
* Now specify it with something like @SendTo("/topic/public"), which we had mentioned in our config file
* Now define one more method to continue the chat called sendMessage()
* Now we are done with server side details
* Now we need to add the client side details

# CLIENT SIDE DETAILS



