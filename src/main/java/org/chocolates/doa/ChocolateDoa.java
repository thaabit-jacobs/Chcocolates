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

    @SqlUpdate("delete from chocolate where id=:id")
    boolean deleteChocolate(@Bind("id") int id);
}
