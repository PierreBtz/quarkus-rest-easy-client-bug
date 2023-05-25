package eu.pierrebeitz.bug;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@ApplicationScoped
@RegisterRestClient(configKey = "client")
public interface Client {

    @POST
    @Path("/api")
    void call(MultipartBody data);

    class MultipartBody {

        @RestForm("file")
        @PartType(MediaType.APPLICATION_OCTET_STREAM)
        private final InputStream file;

        public MultipartBody(InputStream file) {
            this.file = file;
        }

        public InputStream getFile() {
            return file;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MultipartBody that = (MultipartBody) o;
            try {
                return IOUtils.contentEquals(file, that.file);
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(file);
        }
    }
}