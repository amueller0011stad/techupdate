package de.ssc.chain;



import com.chain.exception.BadURLException;
import com.chain.http.Client;

/**
 * TestUtils provides a simplified api for testing.
 */
public class TestUtils {
  public static Client generateClient() throws BadURLException {
    String coreURL = System.getProperty("chain.api.url");
    String accessToken = System.getProperty("client.access.token");
    if (coreURL == null || coreURL.isEmpty()) {
      coreURL = "http://localhost:1999";
    }
    return new Client(coreURL, "client:d4c10c50b1a0f118c72985187bd54895578bb6fa87c991c4d4a8ebff6f31e48b");
  }
}
