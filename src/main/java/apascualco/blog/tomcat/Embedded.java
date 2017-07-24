package apascualco.blog.tomcat;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

class Embedded {

    static Tomcat iniciarTomcat(String contextoAplicacion, String directorioDeTrabajo, File desplegable) {
        System.out.println("Directorio de trabajo : " + directorioDeTrabajo);
        System.out.println("Creando servidor");
        Tomcat tomcatEmbedded = new Tomcat();
        Embedded.configuracionBaseToncat(tomcatEmbedded, directorioDeTrabajo);

        System.out.println("Añadiendo aplicacion");
        tomcatEmbedded.addWebapp(tomcatEmbedded.getHost(), "/".concat(contextoAplicacion), desplegable.getAbsolutePath());
        Embedded.agregarUsuariosYRoles(tomcatEmbedded);
        return tomcatEmbedded;
    }

    private static void configuracionBaseToncat(Tomcat tomcatEmbedded, String directorioDeTrabajo) {
        tomcatEmbedded.setPort(8080);
        tomcatEmbedded.setBaseDir(directorioDeTrabajo);
        tomcatEmbedded.getHost().setAppBase(directorioDeTrabajo);
        tomcatEmbedded.getHost().setAutoDeploy(true);
        tomcatEmbedded.getHost().setDeployOnStartup(true);
    }

    private static void agregarUsuariosYRoles(Tomcat tomcatEmbedded){
        System.out.println("Usuarios y roles");
        tomcatEmbedded.addUser("admin", "admin");
        tomcatEmbedded.addUser("user", "user");
        tomcatEmbedded.addRole("admin", "admin");
        tomcatEmbedded.addRole("admin", "user");
        tomcatEmbedded.addRole("user", "user");
    }
}
