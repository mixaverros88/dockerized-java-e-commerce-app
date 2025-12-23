# e-commerce Web Application using JEE and JSF framework
![preview image](https://raw.githubusercontent.com/mixaverros88/java-e-commerce/master/src/main/resources/e-commerce.jpg)

### :ghost: This project was created for the final examination of  [Boot Camp 3](https://www.afdemp.org/%CF%84%CE%BF-coding-bootcamp-3-%CE%BE%CE%B5%CE%BA%CE%AF%CE%BD%CE%B7%CF%83%CE%B5/)

The main idea behind this, is that we have to create a e-grocery store that sales several products from various vendors.
The grocery shop earn profits only through commissions.
### :pencil: List of requirements

## Admin Panel ##
> A Vendor can
- [x] Create account.
- [x] Add quantity on existed products.
- [x] View all the sales that he commited to the consortium.
- [x] View all the orders of his products.
- [x] View a chart with the last five days orders.

> A Admin can
- [x] Create , Update, Delete accounts.
- [x] Approval or disapproval orders and sales.
- [x] Create , Update, Delete Categories.
- [x] Create , Delete images.

## Front panel ##
> A User can 
- [x] Create account.
- [x] Reset password.
- [x] Log in.
- [x] Buy Products.
- [x] See order history.

When a user commit an order, automatically the web app send them an email with the order details and the invoice of the order in pdf format.

### :file_folder: File Details ###
web.xml : JSF 2.0 Servlet Configuration  

persistence.xml: database configurations

### :paperclip: Context params ###
The “context-param” tag is define in “web.xml” file and it provides parameters to the entire web application.
* vat: 0.24

### Navigate to the following link ###
```
[docker-machine ip]:80/java-e-commerce/
```

### :computer: Deploy instructions ###
Use the maven plug-in (wildfly-maven-plugin) for wildfly server in order to automatically deploy this project in your local server. You can find this plug-in in pom.xml file in order to modify with yours configurations, also don't forget to add in settings.xml your credentials for tomcat server.
```
<servers>
    <server>
        <id>TomcatServer</id>
        <username>admin</username>
        <password>password</password>
    </server>
</servers>
```
You can run the below command in order to deploy the artifact in your local wildfly server
```
mvn clean wildfly:deploy
```
### :scroll: Database instructions ###
You can use the dataBase.sql in order to create the poll database and insert some values.

### :passport_control: Initial admin credentials ###
Username: admin

Password: admin

# Database Diagram
![preview image](https://raw.githubusercontent.com/mixaverros88/java-e-commerce/master/src/main/resources/database_diagram.png)
