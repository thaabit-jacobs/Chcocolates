package org.chocolates;

import org.chocolates.controller.ChocolateController;
import org.chocolates.service.ChocolateService;

import static spark.Spark.port;

public class App
{
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());
        new ChocolateController(ChocolateService.getInstance());
    }
}
