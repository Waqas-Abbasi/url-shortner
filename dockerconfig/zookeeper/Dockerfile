FROM zookeeper:3.5.7

COPY /conf /conf
COPY /data /data

EXPOSE 2181 2888 3888 9876

CMD ["bin/zkServer.sh", "start-foreground"]