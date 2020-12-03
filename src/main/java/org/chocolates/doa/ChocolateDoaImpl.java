package org.chocolates.doa;

import org.chocolates.mapper.ChocolateMapper;
import org.chocolates.model.Chocolate;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
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

    static Jdbi getJdbiDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }

        return Jdbi.create(defualtJdbcUrl);

    }
}
