package com.waqasabbasi.server;

import io.grpc.ServerBuilder;
import io.grpc.Server;

public class URLShortnerServer {
    public static void main(String[] args) {
        try {
            int port = 8080;

            //Initialise UrlShortner Service
            URLShortnerServiceImpl urlShortnerService = new URLShortnerServiceImpl();

            //Add shutdown hook to terminate all open connections
            Runtime.getRuntime().addShutdownHook(new Thread(urlShortnerService::terminate));

            //Create a Server using UrlShortner Service
            Server server = ServerBuilder.forPort(port)
                    .addService(urlShortnerService)
                    .build();

            server.start();

            System.out.println("Succesfully Started on Port: " + port);

            server.awaitTermination();

        } catch (Exception e) {
            e.printStackTrace();

            //Terminate everything if there is an error
            System.exit(0);
        }
    }
}
