import spark.Request;
import spark.Response;
import spark.Session;


public class ServiceUtils {
    public ServiceUtils() {
        super();
    }

    public boolean hasUserLoggedIn(Request request, Response response) {
        Session session = request.session(true);
        return session.attribute("user") != null;
    }

}