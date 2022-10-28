# ride_hailing


To run the projecto you should:
1. Clone the repocitory in your local environment, in the location that you prefer with de command:
  git clone https://github.com/daniela1409/ride_hailing.git

2. You open the project with Eclipse, Netbeans, Intellij or Sprongboot tools. These IDEs are special IDEs to java applications. 

3. Before you run the project, you should create the database, from the database manage that you prefer and run the next lines of code:
      CREATE SCHEMA `db_ride_hailing`;
      INSERT INTO db_ride_hailing.pay_methods (id, customer_email, status, token, type, create_at) VALUES(42564, 'danielaja7@gmail.com','', 'tok_test_27257_a410674e110b554dfedece325C140f1d', 'CARD', NOW());
      INSERT INTO db_ride_hailing.users (firstname, lastname, email, phone, role, status, pay_method_id,  birthday_date, create_at) VALUES('Daniel', 'Jaramillo', 'danielja7@gmail.com', '3014510118', 'rider', '', 42564, '2017-08-01', '2017-08-01');
      INSERT INTO db_ride_hailing.users (firstname, lastname, email, phone, role, status, birthday_date, last_service, create_at) VALUES('Daniela', 'Osorio', 'jedagos1409@gmail.com', '3014510118', 'driver', 'free', '2017-08-01', NOW(),  '2017-08-01');
   With these lines of code, you will create the database and add the first test data.
   
4. With these test data, you can start to test the Api:

    4.1. Init the ride:
        url: http://localhost:8080/api/v1/ride/init/1       "1" is the user id with role "rider". Here you can add just users with role rider
        Method: POST
        Body: {
            "latitude": "12",
            "longitude": "13"
        }
        
    4.2. Finish the ride:
        url: http://localhost:8080/api/v1/ride/finish/2/1    "2" is the uder id and "1" is the ride id (The last endpoint return the ride id that you use here
        Method: PUT
        Body: {
            "installments": 2 ,       //installments used for the user with role rider
            "reference": "sllakcfvfgggg",      //This number should change each time that you want finish the ride 
            "km": "1000",             //km of travel
            "minutes": 20             //minutes of travel
          }
          
     4.3. Get transaction status
        url: http://localhost:8080/api/v1/status/transaction/127257-1666990682-68430   "127257-1666990682-68430" is the transaction number
        Method: GET
        
     4.4. Create pay method
        4.4.1. You create the tokenized card, with the next end point:
            url: https://sandbox.wompi.co/v1/tokens/cards
            method: POST
            Body:             
              {
                "number": "4242424242424242",
                "exp_month": "06",
                "exp_year": "29",
                "cvc": "123",
                "card_holder": "Daniela Osorio"
              }
           From Postman, in Authorization/Beare Token, page that "pub_test_fbD6vui1TdX520upLAFRFtZ1q6FUc8qT", without quotation marks

           You cann't change just the number. 

        4.4.2. You can already create the pay method:
            url: http://localhost:8080/api/v1/1      "1" user id of a user with role rider
            method: POST
            body: {
              "type": "CARD",
              "token": "tok_test_27257_921229cCB248990C26DbcC4b1b5b5683",    This is the id that return of the last endpoint
              "customer_email": "pepito_perez@example.com"
            }
            
        
IMPORTANT THINGS
1. You should configure your local database in the file application properties, with your credentials:
    spring.datasource.username=
    spring.datasource.password=
2. There is a user with a pay method in the database, but if you want test with other values, you should tokenize a card and after you can create a pay method with the en point "Create pay method"
