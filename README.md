# pet-store
Pet store assignment of middleware architecture course
1. How to build or deploy API
    API should create endpoint and according to the request we should take parameters on body or in the path. Then do the logical part and return response

3. 
    Get All Pets
        curl --location --request GET 'localhost:8080/v1/pets'
        
    Get Pets By Name
        curl --location --request GET 'localhost:8080/v1/pets/name/Boola'
        
    Get Pet By Id
        curl --location --request GET 'localhost:8080/v1/pets/1'
        
    Get Pets By Age
        curl --location --request GET 'localhost:8080/v1/pets/age/6'
        
    Add New Pet
        curl --location --request POST 'localhost:8080/v1/pets/add pet' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "petAge": 7,
            "petId": 5,
            "petName": "Roxy",
            "petType": "Dog"
        }'
        
    Delete Pet By Id
        curl --location --request GET 'localhost:8080/v1/pets/delete pet/1'
     
