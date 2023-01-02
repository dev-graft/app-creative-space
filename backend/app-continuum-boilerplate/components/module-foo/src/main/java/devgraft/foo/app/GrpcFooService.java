package devgraft.foo.app;

import devgraft.simple.lib.SimpleGrpc;
import devgraft.simple.lib.SimpleServiceProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import static devgraft.simple.lib.SimpleServiceProto.SimpleReply;

@Slf4j
@GrpcService
public class GrpcFooService extends SimpleGrpc.SimpleImplBase {

    @Override
    public void callMethod(final SimpleServiceProto.SimpleRequest request, final StreamObserver<SimpleReply> responseObserver) {
        log.info("request message: {}", request.getMessage());
        SimpleReply reply = SimpleReply.newBuilder()
                .setMessage("My Name Foo")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
