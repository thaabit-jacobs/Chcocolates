package org.chocolates.service;

import org.chocolates.doa.ChocolateDoa;
import org.chocolates.doa.ChocolateDoaImpl;
import org.chocolates.model.Chocolate;

public class ChocolateService {

    private final ChocolateDoa chocolateDoa = new ChocolateDoaImpl();
    private static final ChocolateService instance = new ChocolateService();

    private ChocolateService(){}

    public static ChocolateService getInstance(){
        return instance;
    }

    public boolean insertChocolate(Chocolate choc){
       return  chocolateDoa.insertChocolate(choc);
    }

    public boolean deleteChocolate(int id){
        return  chocolateDoa.deleteChocolate(id);
    }
}
