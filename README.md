Url Shortner Built with Distributed Technologies.

System Design:
![System Design](https://i.imgur.com/Ezxz9SY.png)

- Redis - Used for Caching Short URLs mapped to Long URLs (Useful for when someone opens a Short URL)

- Zookeeper - Used for generating Unique Counters for Base62 Algorithm. Each instance of URL-Shortner-Service recieves an ID range of 100,000

- Cassandra Schema:
![Cassandra Schema](https://i.imgur.com/TwwC7YC.png)