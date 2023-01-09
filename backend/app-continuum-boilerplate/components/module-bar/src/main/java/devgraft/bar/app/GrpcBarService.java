package devgraft.bar.app;

import devgraft.simple.lib.SimpleGrpc;
import devgraft.simple.lib.SimpleServiceProto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcBarService {
    @GrpcClient("test")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public String sendMessage(final String name) {
        try {
            final SimpleServiceProto.SimpleReply response = this.simpleStub.callMethod(SimpleServiceProto.SimpleRequest.newBuilder().setMessage(name).build());
            return response.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
