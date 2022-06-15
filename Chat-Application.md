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
