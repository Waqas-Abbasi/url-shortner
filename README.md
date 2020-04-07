Url Shortner Built with Distributed Technologies.

System Design:
![System Design](https://i.imgur.com/Ezxz9SY.png)

- Redis - Used for Caching Short URLs mapped to Long URLs (Useful for when someone opens a Short URL)

- Zookeeper - Used for generating Unique Counters for Base62 Algorithm. Each instance of URL-Shortner-Service recieves an ID range of 100,000

- Cassandra Schema:
![Cassandra Schema](https://i.imgur.com/TwwC7YC.png)
 
System uses gRPC and Protobuff to achieve communication between client and server.

Demo:

![Demo Gif](https://i.ibb.co/x5Wh4fF/DEMO.gif)

![Demo 1](https://i.imgur.com/RkUrFaN.png)

![Demo 2](https://i.imgur.com/h8b6gMT.png)