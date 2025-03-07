package implementsample;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import saasus.sdk.util.apiserver.ApiServer;

/**
 * SaaSus API Server Listener
 * Starts API server when the web application is deployed
 */
@WebListener
public class ApiServerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ApiServer.start(8083);
            System.out.println("SaaSus API Server started on port 8083");
            System.out.println("Available endpoints:");
            System.out
                    .println("GET http://localhost:8083/implementsample.controller.InventoryController$InventoryItem");
        } catch (Exception e) {
            System.err.println("Failed to start SaaSus API Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}