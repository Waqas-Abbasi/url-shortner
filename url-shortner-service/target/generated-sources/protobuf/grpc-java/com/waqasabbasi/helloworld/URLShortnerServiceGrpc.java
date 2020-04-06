package com.waqasabbasi.helloworld;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.28.0)",
    comments = "Source: urlshortner.proto")
public final class URLShortnerServiceGrpc {

  private URLShortnerServiceGrpc() {}

  public static final String SERVICE_NAME = "com.waqasabbasi.helloworld.URLShortnerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.ShortURLRequest,
      com.waqasabbasi.helloworld.ShortURLResponse> getGetShortURLMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetShortURL",
      requestType = com.waqasabbasi.helloworld.ShortURLRequest.class,
      responseType = com.waqasabbasi.helloworld.ShortURLResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.ShortURLRequest,
      com.waqasabbasi.helloworld.ShortURLResponse> getGetShortURLMethod() {
    io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.ShortURLRequest, com.waqasabbasi.helloworld.ShortURLResponse> getGetShortURLMethod;
    if ((getGetShortURLMethod = URLShortnerServiceGrpc.getGetShortURLMethod) == null) {
      synchronized (URLShortnerServiceGrpc.class) {
        if ((getGetShortURLMethod = URLShortnerServiceGrpc.getGetShortURLMethod) == null) {
          URLShortnerServiceGrpc.getGetShortURLMethod = getGetShortURLMethod =
              io.grpc.MethodDescriptor.<com.waqasabbasi.helloworld.ShortURLRequest, com.waqasabbasi.helloworld.ShortURLResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetShortURL"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.waqasabbasi.helloworld.ShortURLRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.waqasabbasi.helloworld.ShortURLResponse.getDefaultInstance()))
              .setSchemaDescriptor(new URLShortnerServiceMethodDescriptorSupplier("GetShortURL"))
              .build();
        }
      }
    }
    return getGetShortURLMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.LongUrlRequest,
      com.waqasabbasi.helloworld.LongUrlResponse> getGetLongUrlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLongUrl",
      requestType = com.waqasabbasi.helloworld.LongUrlRequest.class,
      responseType = com.waqasabbasi.helloworld.LongUrlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.LongUrlRequest,
      com.waqasabbasi.helloworld.LongUrlResponse> getGetLongUrlMethod() {
    io.grpc.MethodDescriptor<com.waqasabbasi.helloworld.LongUrlRequest, com.waqasabbasi.helloworld.LongUrlResponse> getGetLongUrlMethod;
    if ((getGetLongUrlMethod = URLShortnerServiceGrpc.getGetLongUrlMethod) == null) {
      synchronized (URLShortnerServiceGrpc.class) {
        if ((getGetLongUrlMethod = URLShortnerServiceGrpc.getGetLongUrlMethod) == null) {
          URLShortnerServiceGrpc.getGetLongUrlMethod = getGetLongUrlMethod =
              io.grpc.MethodDescriptor.<com.waqasabbasi.helloworld.LongUrlRequest, com.waqasabbasi.helloworld.LongUrlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLongUrl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.waqasabbasi.helloworld.LongUrlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.waqasabbasi.helloworld.LongUrlResponse.getDefaultInstance()))
              .setSchemaDescriptor(new URLShortnerServiceMethodDescriptorSupplier("GetLongUrl"))
              .build();
        }
      }
    }
    return getGetLongUrlMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static URLShortnerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceStub>() {
        @java.lang.Override
        public URLShortnerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new URLShortnerServiceStub(channel, callOptions);
        }
      };
    return URLShortnerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static URLShortnerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceBlockingStub>() {
        @java.lang.Override
        public URLShortnerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new URLShortnerServiceBlockingStub(channel, callOptions);
        }
      };
    return URLShortnerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static URLShortnerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<URLShortnerServiceFutureStub>() {
        @java.lang.Override
        public URLShortnerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new URLShortnerServiceFutureStub(channel, callOptions);
        }
      };
    return URLShortnerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class URLShortnerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getShortURL(com.waqasabbasi.helloworld.ShortURLRequest request,
        io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.ShortURLResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetShortURLMethod(), responseObserver);
    }

    /**
     */
    public void getLongUrl(com.waqasabbasi.helloworld.LongUrlRequest request,
        io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.LongUrlResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLongUrlMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetShortURLMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.waqasabbasi.helloworld.ShortURLRequest,
                com.waqasabbasi.helloworld.ShortURLResponse>(
                  this, METHODID_GET_SHORT_URL)))
          .addMethod(
            getGetLongUrlMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.waqasabbasi.helloworld.LongUrlRequest,
                com.waqasabbasi.helloworld.LongUrlResponse>(
                  this, METHODID_GET_LONG_URL)))
          .build();
    }
  }

  /**
   */
  public static final class URLShortnerServiceStub extends io.grpc.stub.AbstractAsyncStub<URLShortnerServiceStub> {
    private URLShortnerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected URLShortnerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new URLShortnerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getShortURL(com.waqasabbasi.helloworld.ShortURLRequest request,
        io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.ShortURLResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetShortURLMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLongUrl(com.waqasabbasi.helloworld.LongUrlRequest request,
        io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.LongUrlResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLongUrlMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class URLShortnerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<URLShortnerServiceBlockingStub> {
    private URLShortnerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected URLShortnerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new URLShortnerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.waqasabbasi.helloworld.ShortURLResponse getShortURL(com.waqasabbasi.helloworld.ShortURLRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetShortURLMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.waqasabbasi.helloworld.LongUrlResponse getLongUrl(com.waqasabbasi.helloworld.LongUrlRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetLongUrlMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class URLShortnerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<URLShortnerServiceFutureStub> {
    private URLShortnerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected URLShortnerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new URLShortnerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.waqasabbasi.helloworld.ShortURLResponse> getShortURL(
        com.waqasabbasi.helloworld.ShortURLRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetShortURLMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.waqasabbasi.helloworld.LongUrlResponse> getLongUrl(
        com.waqasabbasi.helloworld.LongUrlRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLongUrlMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SHORT_URL = 0;
  private static final int METHODID_GET_LONG_URL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final URLShortnerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(URLShortnerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SHORT_URL:
          serviceImpl.getShortURL((com.waqasabbasi.helloworld.ShortURLRequest) request,
              (io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.ShortURLResponse>) responseObserver);
          break;
        case METHODID_GET_LONG_URL:
          serviceImpl.getLongUrl((com.waqasabbasi.helloworld.LongUrlRequest) request,
              (io.grpc.stub.StreamObserver<com.waqasabbasi.helloworld.LongUrlResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class URLShortnerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    URLShortnerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.waqasabbasi.helloworld.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("URLShortnerService");
    }
  }

  private static final class URLShortnerServiceFileDescriptorSupplier
      extends URLShortnerServiceBaseDescriptorSupplier {
    URLShortnerServiceFileDescriptorSupplier() {}
  }

  private static final class URLShortnerServiceMethodDescriptorSupplier
      extends URLShortnerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    URLShortnerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (URLShortnerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new URLShortnerServiceFileDescriptorSupplier())
              .addMethod(getGetShortURLMethod())
              .addMethod(getGetLongUrlMethod())
              .build();
        }
      }
    }
    return result;
  }
}
