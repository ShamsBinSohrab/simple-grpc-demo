package application;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.shams.practice.grpc.encryption.EncryptionRequest;
import org.shams.practice.grpc.encryption.EncryptionServiceGrpc.EncryptionServiceBlockingStub;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @GrpcClient("local-grpc-server")
  private EncryptionServiceBlockingStub encryptionServiceBlockingStub;

  public User save(User user) {
    var request = EncryptionRequest.newBuilder().setData(user.password()).build();
    var response = encryptionServiceBlockingStub.encrypt(request);
    return new User(user.username(), response.getEncryptedData());
  }
}
