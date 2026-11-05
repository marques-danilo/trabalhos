//package aula.ada.configuration;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//
//import org.eclipse.microprofile.health.HealthCheck;
//import org.eclipse.microprofile.health.HealthCheckResponse;
//import org.eclipse.microprofile.health.Readiness;
//@Readiness
//@ApplicationScoped
//public class HealthCheck implements HealthCheck {
//
//    @Inject
//    DatabaseService databaseService;
//
//    @Override
//    public HealthCheckResponse call() {
//
//        if (databaseService.isConnected()) {
//            return HealthCheckResponse.up("Database");
//        }
//
//        return HealthCheckResponse.down("Database");
//    }
//}
//}
