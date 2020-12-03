package org.chocolates.service;

import org.chocolates.doa.ChocolateDoaImpl;
import org.chocolates.model.Chocolate;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChocolateDoaImplTest {

    private ChocolateDoaImpl chocolateDoa = new ChocolateDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/chocstest", "thaabit", "1234"));
    private Chocolate chocolateOne = new Chocolate(1, "Tex", 5);

    @Test
    void shouldReturnTrueWhenChocolateAddedToDbAndDeleted(){
        assertTrue(chocolateDoa.deleteChocolate(1));
        assertTrue(chocolateDoa.insertChocolate(chocolateOne));
    }

    @Test
    void shouldReturnListOfCoclates(){
        assertTrue(chocolateDoa.selectAllChocolate() instanceof List);
    }

    @Test
    void shouldReturnAChocolateForValidId(){
        chocolateDoa.deleteChocolate(1);
        chocolateDoa.insertChocolate(chocolateOne);
        assertEquals(1, chocolateDoa.selectChocolate(1).getId());
    }

    @Test
    void shouldReturnTrueAbdUpdateChoclateQty(){
        chocolateDoa.deleteChocolate(1);
        chocolateDoa.insertChocolate(chocolateOne);
        chocolateOne.setQty(10);
        chocolateDoa.updateChocolate(chocolateOne);
        assertEquals(10, chocolateDoa.selectChocolate(1).getQty());
    }

}
