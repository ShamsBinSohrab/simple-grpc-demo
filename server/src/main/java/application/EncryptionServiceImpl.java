package application;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.logging.log4j.util.Base64Util;
import org.shams.practice.grpc.encryption.EncryptionRequest;
import org.shams.practice.grpc.encryption.EncryptionResponse;
import org.shams.practice.grpc.encryption.EncryptionServiceGrpc;

@GrpcService
class EncryptionServiceImpl extends EncryptionServiceGrpc.EncryptionServiceImplBase {

  @Override
  public void encrypt(EncryptionRequest request,
      StreamObserver<EncryptionResponse> responseObserver) {
    var encryptedData = encryptData(request.getData());
    var response = EncryptionResponse.newBuilder()
        .setEncryptedData(encryptedData)
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private String encryptData(String data) {
    return Base64Util.encode(data);
  }
}
