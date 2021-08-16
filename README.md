# codefellowship2
## Build an app that allows users to create their profile on CodeFellowship.

### The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.

### An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.

### The site should allow users to create an ApplicationUser on the “sign up” page.
#### Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user. 

### The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
#### This should include a default profile picture, which is the same for every user, and their basic information. 

### add the ability for users to log in to your app. 
### When a user is logged in, the app should display the user’s username on every page (probably in the heading).
### Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
### The site should be well-styled and attractive.
### The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
### The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
### Ensure that user registration also logs users into your app automatically.

### Add a Post entity to your app.
#### A Post has a body and a createdAt timestamp.
#### A logged-in user should be able to create a Post, and a post should belong to the user that created it.

### A user’s posts should be visible on their profile page.

- rout "/" ; you can find the home page which contains some information about the website, and an acces to the users accounts.
- rout "/login" ; you can log in to your acoount.
- rout "/signup" ; you can create a new account.
- rout "/users" ; the user can see the information about user who login.
Run the project and see the result from the following link: http://localhost:8080
