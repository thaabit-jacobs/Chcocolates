package org.chocolates.doa;

import org.chocolates.mapper.ChocolateMapper;
import org.chocolates.model.Chocolate;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class ChocolateDoaImpl implements ChocolateDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/chocs", "thaabit", "1234");

    public ChocolateDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new ChocolateMapper());
    }

    public ChocolateDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new ChocolateMapper());
    }

    @Override
    public boolean insertChocolate(Chocolate choc) {
        return jdbi.withExtension(ChocolateDoa.class, doa -> doa.insertChocolate(choc));
    }

    @Override
    public List<Chocolate> selectAllChocolate() {
        return jdbi.withExtension(ChocolateDoa.class, doa -> doa.selectAllChocolate());
    }

    @Override
    public Chocolate selectChocolate(int id) {
        return jdbi.withExtension(ChocolateDoa.class, doa -> doa.selectChocolate(id));
    }

    @Override
    public boolean updateChocolate(Chocolate choc) {
        return jdbi.withExtension(ChocolateDoa.class, doa -> doa.updateChocolate(choc));
    }

    @Override
    public boolean deleteChocolate(int id) {
        return jdbi.withExtension(ChocolateDoa.class, doa -> doa.deleteChocolate(id));
    }
}
