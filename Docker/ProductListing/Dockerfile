FROM ubuntu:14.04


RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get install -y wget

USER root

#install Java
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer
RUN sudo apt-get install unzip
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#install Tomcat
RUN wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.24/bin/apache-tomcat-8.5.24.tar.gz -O /tmp/tomcat.tgz
RUN mkdir /tmp/unzip/
WORKDIR /tmp/unzip/
RUN tar -xvf /tmp/tomcat.tgz
RUN mv /tmp/unzip/apache-tomcat-8.5.24 /opt/tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin
EXPOSE 8080
EXPOSE 8081
EXPOSE 8009
WORKDIR /opt/tomcat

#add war , liquibasejar
RUN mkdir /opt/tomcat/webapps/product-mapping/
ADD ./application.war /opt/tomcat/webapps/product-mapping/

#extract war in tomact webapps and replace application.properties
WORKDIR /opt/tomcat/webapps/product-mapping/
RUN jar -xvf application.war
ADD ./application.properties /opt/tomcat/webapps/product-mapping/WEB-INF/classes/


# Launch Tomcat
CMD [ "/opt/tomcat/bin/catalina.sh", "run"]