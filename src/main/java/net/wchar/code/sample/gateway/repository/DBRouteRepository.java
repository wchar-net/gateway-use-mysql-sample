package net.wchar.code.sample.gateway.repository;

import net.wchar.code.sample.gateway.domain.DBRouteDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wchar.net
 */
@Repository
public interface DBRouteRepository extends ReactiveCrudRepository<DBRouteDomain, Long> {

}
