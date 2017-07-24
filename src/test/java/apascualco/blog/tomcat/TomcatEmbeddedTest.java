package apascualco.blog.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class TomcatEmbeddedTest {

    @Test
    public void test_con_loop_infinito() throws IOException, LifecycleException {
        String contextoAplicacion = "showcase-5.3";
        String fileName = contextoAplicacion + ".war";
        URL url = getClass().getClassLoader().getResource(fileName);
        if(url == null) {
            throw new FileNotFoundException("No se ha encontrado ell archivo " + fileName);
        }
        String fileAbsolutePath = url.getFile();
        if(fileAbsolutePath == null || fileAbsolutePath.isEmpty()) {
            throw new FileNotFoundException("No se ha encontrado ell archivo " + fileName);
        }
        File desplegable = new File(fileAbsolutePath);
        if(!desplegable.exists() || !desplegable.isFile()) {
            throw new FileNotFoundException();
        }
        String directorioDeTrabajo = desplegable.getParent();
        System.out.println("Iniciando servidor");
        Tomcat tomcat = Embedded.iniciarTomcat(contextoAplicacion, directorioDeTrabajo, desplegable);
        tomcat.start();

        /**
         * http://localhost:8080/showcase-5.3/
         */
        for(;;);
    }

}
