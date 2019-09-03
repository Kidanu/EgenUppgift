import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class UppgiftA_VG {
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource service = client.resource(UriBuilder.fromUri("http://date.jsontest.com/").build()); //Bygger adressen till APIet

        System.out.println(service.path("resPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));


    }
}
