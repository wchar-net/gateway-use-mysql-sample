package net.wchar.code.sample.gateway.config;

import lombok.AllArgsConstructor;
import net.wchar.code.sample.gateway.domain.DBRouteDomain;
import net.wchar.code.sample.gateway.repository.DBRouteRepository;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Flux;

/**
 * @author wchar.net
 */
@Configuration
public class DBRouteConfiguration {
    @Bean
    public RouteLocator routeLocator(DBRouteRepository dbRouteRepository,
                                     RouteLocatorBuilder routeLocatorBuilder) {
        return new DBRouteLocatorImpl(dbRouteRepository, routeLocatorBuilder);
    }

    @AllArgsConstructor
    private static class DBRouteLocatorImpl implements RouteLocator {

        private final DBRouteRepository dbRouteRepository;
        private final RouteLocatorBuilder routeLocatorBuilder;

        @Override
        public Flux<Route> getRoutes() {
            RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
            return dbRouteRepository.findAll()
                    .map(route -> routesBuilder.route(String.valueOf(route.getId()),
                            predicateSpec -> setPredicateSpec(route, predicateSpec)))
                    .collectList()
                    .flatMapMany(builders -> routesBuilder.build()
                            .getRoutes());
        }

        private Buildable<Route> setPredicateSpec(DBRouteDomain dbRouteDomain, PredicateSpec predicateSpec) {
            return predicateSpec.path(dbRouteDomain.getLocation()).and().method(
                    HttpMethod.GET,
                    HttpMethod.POST,
                    HttpMethod.PUT,
                    HttpMethod.DELETE,
                    HttpMethod.PATCH,
                    HttpMethod.HEAD,
                    HttpMethod.OPTIONS,
                    HttpMethod.TRACE
            ).filters(f -> f.stripPrefix(dbRouteDomain.getStripPrefix())).uri(dbRouteDomain.getProxyAddress());
        }
    }
}
