package org.chocolates;

import org.chocolates.controller.ChocolateController;
import org.chocolates.service.ChocolateService;

public class App
{
    public static void main( String[] args )
    {
        new ChocolateController(ChocolateService.getInstance());
    }
}
