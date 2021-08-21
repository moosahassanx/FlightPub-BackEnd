/*
    UserController.java class
        - Rest controller that handles all the incoming front-end requests regarding users
        - Password hashing works but will ultimately be re-done on the front-end due to using HTTP
        - Ideally will have an Error handling controller instead of handling errors in each method
 */
package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.Services.Discovery;
import com.seng3150.flightpub.models.User;
import com.seng3150.flightpub.repository.UserRepository;
import com.seng3150.flightpub.Services.Discovery;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {

    // Variables used for hashing password
    // This works but isn't implemented in the prototype submission
    private static final Random RND = new SecureRandom();
    private static final int KEY = 256;
    private static final int ITERATIONS = 1000;

    // Access to the user database service
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Checks if the username input exists, if not returns ERROR status code
    // If exists, gets their last booked location, maps it to the locations lat and long
    // Returns the lat and long to frontend to display on map
    @RequestMapping("/checkExists")
    public ResponseEntity<?> checkUserExistsByUsername(@RequestParam (required = false, name = "username") String username) throws IOException {

        if(!userRepository.existsByUserName(username)) {
            return new ResponseEntity<>("Cant find " + username, HttpStatus.BAD_REQUEST);
        }

        // Constructs the map to send back with the usernames last visited lat/lng
        HashMap<String, Map<String,Double[]>> returnJson = new HashMap<>();
        HashMap<String,Double[]> locationLatLong = new HashMap<>();
        Discovery d = new Discovery();

        // Gets the last location from discovery helper class
        // adds the lat and long to the map to send back
        List<String> location = userRepository.getVisitedLocations(1);
        Double[] d3 = d.countryLatLng.get(location.get(0));

        // Splits the username on @ symbol, due to javascript being funny
        // having symbols in key names
        String[] usernameSplit = username.split("@");
        locationLatLong.put(usernameSplit[0], d3);

        returnJson.put("username", locationLatLong);

        return new ResponseEntity<>(returnJson, HttpStatus.OK);
    }

    // If a user is found, sends back OK response with the List<User> json response
    // Else if no user exists with the given details sends back HTTP.Not_found response
    @RequestMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody HashMap<String,String> jsonData) {
        // getting user details by using findByUserNameAndPasswordHash() method
        System.out.println("=======================================================================================");
        System.out.println("QUERYING FOR " + jsonData.get("userName") + " WITH PASSWORD " + jsonData.get("password"));

        // matching registered bcrypt password with input plaintext password
        int strength = 10;      // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

        List<User> data1 = userRepository.getDetailsByUserName1(jsonData.get("userName"));
        System.out.println("RETURNED: " + data1 + "\n\n");

        System.out.println("INPUT PASSW: " + jsonData.get("password"));
        System.out.println("RETRIEVED P: " + data1.get(0).getPasswordHash());
        System.out.println("VERIFICATION: " + bCryptPasswordEncoder.matches(jsonData.get("password"), data1.get(0).getPasswordHash()));
        boolean successfulLogin = bCryptPasswordEncoder.matches(jsonData.get("password"), data1.get(0).getPasswordHash());

        // successfully found the user with correct credentials
        if(successfulLogin == true) {
            return new ResponseEntity<>(data1, HttpStatus.OK);
        }

        // response message depending on the outcome
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    // send back the entire users list
    @RequestMapping("/getUsers")
    public List<User> getUserDetails() {
        // legit 2 lines cuz what more do you want?
        List<User> usersList = userRepository.getAllUsers();
        return usersList;
    }

    // send back all the users in the list that requested for promotion
    @RequestMapping("/getUserRequests")
    public List<User> getUserRequests() {
        // legit 2 lines cuz what more do you want?
        List<User> usersList = userRepository.getAllUserRequests();
        return usersList;
    }
    
    // Register user, mapped as form data to a map<Key,Data>
    // Pulls data from body request, Checks if this email already exists
    // If a user exists send back HTTP.Conflict else create the user model and save to the db
    // And return HTTP.OK response
    @RequestMapping(value="/registerUser",
                    method= RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody MultiValueMap<String, String> formData) {


            // Grabbing data from the form encoded data
            String user_name =  formData.getFirst("email");
            String first_name =  formData.getFirst("firstName");
            String last_name =  formData.getFirst("lastName");
            String password_hash =  formData.getFirst("hPwd");
            String phone_number = formData.getFirst("phoneNumber");
            String address = formData.getFirst("address");

            // Before hashing password check if the email already exists
            if(userRepository.existsByUserName(user_name)) {
                return new ResponseEntity<>( HttpStatus.CONFLICT);
            }

            // Getting users salt to hash their password
            byte[] salt = getSalt();

            // hash registration password and set it as the new one
            int strength = 10;      // work factor of bcrypt
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bCryptPasswordEncoder.encode(password_hash);

            // Saving user to the database
            userRepository.save(new User(user_name, first_name, last_name, address, phone_number, encodedPassword, salt));

            // Returning HTTP status
            return new ResponseEntity<>( HttpStatus.OK);
    }

    // Change password, takes in the json data as a map<Key,Data>
    // saves new password to the users table
    @RequestMapping(value = "/changePassword",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeUserPassword(@RequestBody HashMap<String, String> jsonData) {
        userRepository.updateUserPassword(jsonData.get("userName"), jsonData.get("password"));
    }

    // Change details, takes in the json data as map<Key,Data>
    // Pulls data from body request, gets the updated details and
    // updates the users table - Sends back HTTP.OK if table was updated correctly
    @RequestMapping(value = "/changeDetails",
                    method = RequestMethod.PUT)
    public ResponseEntity<?> changeUserDetails(@RequestBody HashMap<String, String> jsonData) {

        userRepository.updateUserDetails(jsonData.get("userName"),
                                         jsonData.get("firstName"),
                                         jsonData.get("lastName"),
                                         jsonData.get("phoneNumber"),
                                         jsonData.get("address"));

        return new ResponseEntity<>("Details changed successfully", HttpStatus.OK);
    }

    // promote the user depending on the username passed as a parameter
    @Transactional
    @RequestMapping(value = "/promoteUser",
            method = RequestMethod.PUT)
    public ResponseEntity<?> promoteUser(@RequestBody HashMap<String, String> jsonData) {

        System.out.println("===========================================================");

        String requestingFor = userRepository.getUserRequestingFor(jsonData.get("userName"));
        System.out.println("THIS USER IS REQUESTING FOR: " + requestingFor);

        System.out.println("ATTEMPTING TO PROMOTE USER: " + jsonData.get("userName") + " TO " + requestingFor);

        userRepository.promoteUser(requestingFor, jsonData.get("userName"));

        return new ResponseEntity<>("User " + jsonData.get("userName") + " successfully promoted", HttpStatus.OK);
    }

    // a user has requested for a higher position (store it for further approval by administrators)
    @Transactional
    @RequestMapping(value = "/addToRequestList",
            method = RequestMethod.PUT)
    public ResponseEntity<?> addToRequestList(@RequestBody HashMap<String, String> jsonData) {

        System.out.println("============================================================");

        System.out.println("ATTEMPTING TO ADD USER TO REQUEST LIST");

        System.out.println("userName: " + jsonData.get("userName"));
        System.out.println("why: " + jsonData.get("why"));
        System.out.println("referencing: " + jsonData.get("referencing"));
        System.out.println("experience: " + jsonData.get("experience"));
        System.out.println("requesting_for: " + jsonData.get("requesting_for"));

        userRepository.addUserToRequestList(jsonData.get("userName"), jsonData.get("why"), jsonData.get("referencing"), jsonData.get("experience"), jsonData.get("requesting_for"));

        return new ResponseEntity<>("User " + jsonData.get("userName") + " successfully added to list", HttpStatus.OK);
    }

    // Get user details for account management settings
    // Returns back a JSON map with the user details.
    // Ideally will use a DTO with the variables needed instead of mapping to a user
    @RequestMapping("/getDetails")
    public Map<String,Object> getUserDetails(@RequestParam("userName") String userName) {

        // Creates map to map the user to a json object
        // User wont be invalid due to them already being logged in
        Map<String, Object> getDetails = new HashMap<>();
        User user = userRepository.getDetailsByUserName(userName);

        // Add user details to the map with the required key
        getDetails.put("id", user.getUserId());
        getDetails.put("userName", user.getUserName());
        getDetails.put("firstName", user.getFirstName());
        getDetails.put("lastName", user.getLastName());
        getDetails.put("phoneNumber", user.getPhoneNumber());
        getDetails.put("address", user.getAddress());

        return getDetails;
    }

    // Password hashing private methods
    // Won't use these in the backend, but left in for submission for clarification
    private byte[] getSalt() {

        byte[] d = new byte[16];
        RND.nextBytes(d);

        return d;
    }
    private String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return new String(hash);

    }

    // remove the user from the database completely
    @Transactional
    @RequestMapping(value = "/removeUser",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeUser(@RequestBody HashMap<String, String> jsonData) {
        System.out.println("===============================================================");
        System.out.println("ATTEMPTING TO REMOVE USER " + jsonData.get("userName") + " FROM DB");

        userRepository.removeUser(jsonData.get("userName"));

        return new ResponseEntity<>("User" + jsonData.get("userName") + "successfully removed", HttpStatus.OK);
    }




}
