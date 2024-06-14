# Use the official Tomcat image from the Docker Hub
FROM tomcat:9.0

# Copy the WAR file to the webapps directory of Tomcat and rename it to ROOT.war
# COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]
