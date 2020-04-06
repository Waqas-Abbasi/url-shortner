FROM cassandra:3.11.6

COPY /conf /var/lib/cassandra
COPY /data /var/lib/cassandra

EXPOSE 7000 9042 7199

CMD ["cassandra", "-f"]

#docker run --network testnetwork --name cassandra -v /tmp/cassandra-data:/var/lib/cassandra -d cassandra:3.11.6
#docker run -it --network testnetwork  --rm cassandra cqlsh cassandra