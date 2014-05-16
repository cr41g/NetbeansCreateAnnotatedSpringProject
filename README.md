NetbeansCreateAnnotatedSpringProject
====================================
The application is used to help create a Spring MVC project in NetBeans 8+.

The main purpose is to create a Java Config based application rather than the xml Config which NetBeans creates by default.

It also deletes the unessasary redirect.jsp Netbeans creates, as well as the now redundant xml files.

The app rewrites the web.xml file to allow for Java Config and adds the packages you supply, as well as 3 main java files (AppConfig, MvcConfig and a MainController) with minimal code, but enough to run your Spring app straight away.

To use you can download the .jar file from the dist folder.

Double click the jar file to run the app.

A form will be displayed which you use to select your NetBeans Directory, type your project name (this must already exist as a Web Application and had Spring MVC Framework selected.)
Type the 2 packages you require, one for the Config files and one for the Controllers.
Click Create
