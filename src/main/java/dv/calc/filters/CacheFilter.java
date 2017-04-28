package dv.calc.filters;

import dv.calc.rest.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filter component which provides caching of calculation results.
 *
 * @author Denys Volchkov
 */
@Provider
@PreMatching
@Cached
public class CacheFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String CACHE_USED = "cache_used";

    // Simple cache implementation - String request path as key, Result object as value
    private static ConcurrentHashMap<String, Result> cache = new ConcurrentHashMap<>();

    private Logger log = LoggerFactory.getLogger(CacheFilter.class);

    /**
     * Request filter method - responds with cached result if found.
     *
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String requestPath = requestContext.getUriInfo().getPath();

        Result cachedResult = cache.get(requestPath);
        if (cachedResult != null) {
            log.info("{} - responding with cached result: {}", requestPath, cachedResult);

            requestContext.setProperty(CACHE_USED, Boolean.TRUE);

            requestContext.abortWith(
                    Response.ok(cachedResult).type(MediaType.APPLICATION_JSON).build());
        } else {
            log.info("{} - no result in cache", requestPath);
        }
    }

    /**
     * Response filter method - puts calculation result in cache.
     *
     * @param requestContext
     * @param responseContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (requestContext.getProperty(CACHE_USED) == null && responseContext.getEntity() instanceof Result) {
            Result result = (Result) responseContext.getEntity();
            String requestPath = requestContext.getUriInfo().getPath();

            log.info("{} - caching result: {}", requestPath, result);

            cache.putIfAbsent(requestPath, result);
        }
    }
}
