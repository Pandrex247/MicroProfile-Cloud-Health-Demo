package fish.payara.examples.microprofile.health.cloud.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

/**
 *
 * @author Andrew Pielage <andrew.pielage@payara.fish>
 */
@Liveness
@ApplicationScoped
public class FragileHealthExample implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("fragile");
        
        if (up()) {
            return responseBuilder.withData("CoinFlip", "Heads").up().build();
        }
        
        return responseBuilder.withData("CoinFlip", "Tails").down().build();
    }
    
    private boolean up() {
        int up = new Random().nextInt(10);
        return up < 6 ? true : false;
    }
}
