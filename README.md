# CatMap



## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview


### Description
Allows users to search and view course schedules. Building location of classes can then be displayed on a map. Users can create accounts to save their course schedules.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Education
- **Mobile:** This app is primarily developed for mobile, but has potential to be used on other platforms. Mobile has more possibilities for navigation and expanded features, however.
- **Story:** Users can create and login to an account. They can search for their classes and have it displayed on a map.
- **Market:** UC Merced population and any individual navigating to UC Merced
- **Habit:** It could be used often during the beginning of the semester to locate classes. Visitors can use to navigate around the campus when touring.
- **Scope:** First we want to connect classes to their map location. Next we could improve the details, such as displaying floor numbers, accurate location within the building, and office hours. If successful and polished enough, we could try and integrate officially with the university.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logs in to access their account. New user can create an account. [*]
*  User can search for a course, have the location displayed on a map with a marker.
* User has profile page with saved classes.
* View all classes by semester, major and more [*].

**Optional Nice-to-have Stories**


* Forget Password (password reset)
* Details of location such as floor number, class location within the building
* Highlight building entrances



### 2. Screen Archetypes

* Login
   * User can login using their password and username. [*]
* Register
   * User signs up. 
* Home Screen
   * Display the campus map, and allow user to search a class to display its location.
* MyClasses Screen
  * Display user's saved classes
* AllClasses
  *  Display all course by semester, search by class category or text search. [*]

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* MyClasses
* AllClasses
* Profile

**Flow Navigation** (Screen to Screen)

* Login -> Register (if a new user) -> Home
* Home -> Search Bar
* MyClasses -> Main section -> Lab/Discussion




## Wireframes

![](https://i.imgur.com/Rac29eD.jpg)

### [BONUS] Digital Wireframes & Mockups

![](https://i.imgur.com/cZMmWjd.png)

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]

### Models
[Add table of models]
Classes

| **Property** | **Type** | **Description** |
|--------------|:--------:|-----------------|
| subject      |string    | name of subject |
| crn          |number    | course request number|
| course_id    |number    | name of course section (CSE-031-01)|
| course_name  |string    |   name of the course(Calculus 1)               |
| instructor_name   |string    |  name of the instructor               |
| course_type |     String     |   Lecture/ Discussion/ Lab              |
|time|Day time|time of the class|


### Networking
- Map/Home Screen
    - (Read/GET) Query signed-in user's classes
- MyClasses Screen
    - (Read/GET) Query signed-in user's classes
- Course Catalog Screen
    - (Read/GET) Query all courses
- Login Screen
    - (Create/POST) Allow user sign-in
    
        public void loginUser() {
        ParseUser.logInInBackground("<userName>", "<password>", (user, e) -> {
        if (user != null) {
            // Hooray! The user is logged in.
        } else {
            // Login failed. Look at the ParseException to see what happened.
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        });
        }
- Sign-up Screen
    - (Create/POST) Create a new account
        user.signUpInBackground(e -> {
        if (e == null) {
            // Here you should tell the user to verify the e-mail
            ParseUser.logOut();
        } else {
            // Sign up didn't succeed. Look at the ParseException
            // to figure out what went wrong
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });

- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
    
    
Milestone 1: Login-Parse-Register View
    
    
![](https://i.imgur.com/95GQCQU.gif)
