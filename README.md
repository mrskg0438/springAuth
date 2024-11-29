
<p>This Project is Role-Based-Access-Control(RBAC) System built using Spring Boot and MongoDB. It includes user authentication and role-based access controlwith distinct user roles such as Admin, User, and Moderator. The system leverages Spring Security for secure API endpoints and password encryption using BCrypt. MongoDB ensures secure data storage with unique constraints and error handling for duplicate entries.</p>

<h4>I have created 4 main Controllers</h2>
<ol>
  <li>AuthController: It contains two APIs for one for SignUp and Second one for Signin. User Can Signup with their name, email, password and also can assing role to their account by default it takes a User as Role.
  Signin api generate jwt token if user exists in database.
  </li>
  <li>
    AdminController: It contains api for listing all user and has autorization with admin role. It means only ADMIN can see all user List
  </li>
  <li>
    UserController: It contains api for getting   user's details and has autorization with admin, user, moderator role.
  </li>
  <li>
    AdminController: It contains api for listing all user and moderator and it has autorization with admin and moderator role.
  </li>
  
</ol>

<h4>I have used Spring Security for authenticating and authorizing by user's role</h4>



