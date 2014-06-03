package com.createspringannotationconfig;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Craig
 */
public class FormActions {
    
    private String project;    
    private final String USER_PATH;
    private final String SRC_PATH = "src" + File.separator + "java";
    private final String WEB_PATH = "web" + File.separator + "WEB-INF";
    
    public FormActions(){
        this.USER_PATH = "";
    }
    
    public FormActions(String netbeans, String project) {
        this.USER_PATH = netbeans + File.separator;
        this.project = project;
    }
    
    public void doCreateWebXml(String configPackage, String controllerPackage) {
        String f = new File(".").getAbsolutePath();
        //Path source = Paths.get(f + File.separator + "src" + File.separator + "webxml.txt");
        Path destination = Paths.get(USER_PATH + project + File.separator + WEB_PATH + File.separator + "web.xml");
        String memoryFile = "";
        try (InputStream in = FormActions.class.getClassLoader().getResourceAsStream("webxml.txt");
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                memoryFile += line + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        String[] packages = {configPackage + ".AppConfig", configPackage + ".MvcConfig", controllerPackage};
        for (int i = 0; i < 3; i++) {            
            memoryFile = memoryFile.replace("{" + i + "}", packages[i]);
        }
        byte data[] = memoryFile.getBytes();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(destination))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    public boolean packageExists(String p) {
        String path = buildFolderPath(p);
        File file = new File(USER_PATH + project + File.separator + SRC_PATH + path);
        return file.exists();
    }
    
    public void doCreatePackage(String p) {
        String path = buildFolderPath(p);
        Path dir = Paths.get(USER_PATH + project + File.separator + SRC_PATH + path);
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
        
    public void doCreateAppConfigClass(String p) {
        String f = new File(".").getAbsolutePath();
        //Path source = Paths.get(f + File.separator + "src" + File.separator + "appconfig.txt");
        String path = buildFolderPath(p);
        Path destination = Paths.get(USER_PATH + project + File.separator + SRC_PATH + path + File.separator + "AppConfig.java");        
        String memoryFile = "";
        try (InputStream in = FormActions.class.getClassLoader().getResourceAsStream("appconfig.txt");
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                memoryFile += line + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }          
        memoryFile = memoryFile.replace("{0}", p);
        byte data[] = memoryFile.getBytes();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(destination))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    public void doCreateMvcConfigClass(String p) {
        String f = new File(".").getAbsolutePath();
        //Path source = Paths.get(f + File.separator + "src" + File.separator + "mvcconfig.txt");
        String path = buildFolderPath(p);
        Path destination = Paths.get(USER_PATH + project+ File.separator + SRC_PATH + path+ File.separator + "MvcConfig.java");
        String memoryFile = "";
        try (InputStream in = FormActions.class.getClassLoader().getResourceAsStream("mvcconfig.txt");
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                memoryFile += line + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }          
        memoryFile = memoryFile.replace("{0}", p);
        byte data[] = memoryFile.getBytes();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(destination))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    public void doCreateControllerClass(String p) {
        String f = new File(".").getAbsolutePath();
        Path source = Paths.get(f+ File.separator + "src" + File.separator + "maincontroller.txt");
        String path = buildFolderPath(p);
        Path destination = Paths.get(USER_PATH + project + File.separator + SRC_PATH + path + File.separator + "MainController.java");
        String memoryFile = "";
        try (InputStream in = FormActions.class.getClassLoader().getResourceAsStream("maincontroller.txt");
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                memoryFile += line + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }          
        memoryFile = memoryFile.replace("{0}", p);
        byte data[] = memoryFile.getBytes();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(destination))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    public void deleteSpringXmlFiles() {
        File file = null;
        file = new File(USER_PATH + project + File.separator + WEB_PATH + File.separator + "applicationContext.xml");
        file.delete();
        file = new File(USER_PATH + project + File.separator + WEB_PATH + File.separator + "dispatcher-servlet.xml");
        file.delete();
    }
 
    public void deleteRedirect() {
        File file = new File(USER_PATH + project + File.separator + "web" + File.separator + "redirect.jsp");
        file.delete();
    }
    
    private String[] splitPackage(String p) {
         String[] folders = p.split("\\.");
         return folders;
    }
    
    private String buildFolderPath(String p) {
        String[] folders = splitPackage(p);
        String path = "";
        for (String f : folders) {
            path += File.separator + f;
        }
        return path;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
