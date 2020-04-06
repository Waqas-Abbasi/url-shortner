package com.waqasabbasi;

import com.waqasabbasi.helloworld.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestClient {
    public static void main(String[] args) {


        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("192.168.64.7", 30002)
                .usePlaintext()
                .build();

        URLShortnerServiceGrpc.URLShortnerServiceBlockingStub urlShortnerServiceStub = URLShortnerServiceGrpc.newBlockingStub(managedChannel);

        ShortURLRequest request = ShortURLRequest.newBuilder()
                .setLongUrl("www.google.com")
                .build();

        ShortURLResponse response = urlShortnerServiceStub.getShortURL(request);

        LongUrlRequest longUrlRequest = LongUrlRequest.newBuilder()
                .setShortUrl(response.getShortUrl())
                .build();

        LongUrlResponse longUrlResponse = urlShortnerServiceStub.getLongUrl(longUrlRequest);
//
        System.out.println(response.getShortUrl());
        System.out.println(longUrlResponse.getLongUrl());

        managedChannel.shutdown();
    }
}
