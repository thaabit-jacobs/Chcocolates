package org.chocolates.doa;

import org.chocolates.model.Chocolate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ChocolateDoa {
    @SqlUpdate("insert into chocolate (id, name, qty) values(:id, :name, :qty)")
    boolean insertChocolate(@BindBean Chocolate choc);

    @SqlQuery("select * from chocolate")
    List<Chocolate> selectAllChocolate();

    @SqlQuery("select * from chocolate where id=?")
    Chocolate selectChocolate(int id);

    @SqlUpdate("update chocolate set qty=:qty where id=:id")
    boolean updateChocolate(@BindBean Chocolate choc);

    @SqlUpdate("delete from chocolate where id=?")
    boolean deleteChocolate(int id);
}
