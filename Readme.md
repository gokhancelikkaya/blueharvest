
  
# Blue Harvest Assignment    
 The  project consists of 3 backend sub projects and 1 UI project .    
    
 - Account Service    
 - Transaction Service    
 - Eureka Naming Service    
 - Vue.js Frontend application    
    
## Requirements    
    
- JDK 1.8+    
 - Gradle    
 - Npm 6+ (for UI part)    
    
    
## Services    
 The server consists from 3 services.     
    
 - **Transaction Service:** Responsible from creating and listing transactions of an account. By default settings runs on port 8000.    
 - **Account Service:** Responsible from creating and listing customers and accounts. Also sends request to transaction service for transaction related operations. By default settings runs on port 8080.    
 - **Eureka Naming Service:** Responsible from discovering services and allows to connect them to each other via naming. By default settings runs on port 8761.    
    
Basic flow of the application is illustrated below:    
![Service Architecture](asset/serviceArchitecture.png/?raw=true "Service Architecture")    
    
## Endpoints  
  
Swagger dependency is configured to the main service (account service) to display available endpoints. After launching the project you may see on http://localhost:8080/swagger-ui.html  
  
## Running the application  
  ### Server Side  
On the main directory of the project following commands are used to run services.  
  
    ./gradlew eureka-naming:bootRun
    ./gradlew transaction:bootRun    
    ./gradlew account:bootRun  
  

**Note:** Account service requires transaction service for transaction related operations. `account-service` still runs even if the `transaction-service` is not up yet, but transaction related operations fails until `transaction-service` is up. In such case it may still take a few seconds to run transaction related operations after `transaction-service` is up and running. 
  
### UI Side  

A very simple user interface is developed to allow testing easier. Inside the `ui` directory, run following command to run frontend.   
  
    npm install npm run dev  

The command will run the UI on http://localhost:3000/


## Screenshots
![Create New Customer or Account](asset/NewCustomerAccount.png/?raw=true "Create New Customer or Account")

![Transactions](asset/Transactions.png/?raw=true "Transactions")    
