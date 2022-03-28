#The House of Technologist - TechBook - README
===

## Table of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)
4. [Schema](#Schema)
5. [Built With](#-TechBooks-is-buildt with)
6. [Authors](#-Authors)
7. [Running Locally](#-Running-Locally)
8. [Phone Emulator](#-Phone-Emulator)

## Overview
### Description
[<img src=".gif" width = "600">]()


TechBook is a free social networking platform that enables its users( tech professionals) to upload and share their GIFs and photos with other users.  Techbook users can choose to share information either publicly or privately. Anything shared publicly can be seen by any other user, whereas privately shared content can only be accessed by the specified set of people.

We designed a simpler version where a user can share photos and follow other users. The ‘News Feed’ for each user will consist of top photos or gifs of all the people the user follows.
 
The app displays a list of conference events and allows the user to filter these events by 
types and by topics (Android, Firebase, etc.). Users can see details about
events, and they can star events that interest them. Conference attendees can
reserve events to guarantee a seat.

# Development Environment

### Evaluation
- **Category:** Social Networking / Technology 

- **Mobile:** Mobile is essential for easy posting. Users will use their phone's built-in camera to take photos and upload them to their feed. 

- **Features:**  The app displays a list of conference events near the user.  Users can see details about events, such as name, date, time, and location.  In addition, they can star events that interest them.  Future features will include allowing users to filter these events by event types and by topics(Android, Bitcoin, etc).   
 
- **Story:** Allows users to answer questions such as, "What events are near me? How can I promote myself? Or Where can I find tech talent"? Users will be able to create a profile and upload their content to either share publicly or privately.  Users will be able to search local technology theme events and save them to their accounts for easy access and use. They'll also be able to "likes" posts and receive messages on their own posts, for positive validations 


- **Scope** Our application will allow users to post on the live feed.  This will allow users to upload photos or gifs of their projects and content.  We believe that the best version of the app will provide a rich user experience to engage and interact with other users. 

## Product Spec 

### 1. User Stories 

#### Required Must-have Stories  

- [X] [ 1 ] User can register a new account
- [X] [ 2 ] User can login
- [x] [ 3 ] User sees app icon in home screen and styled bottom navigation view.
  - [x] User sees app icon on the home screen.
  - [X] User sees styled bottom navigation view.
- [x] [ 4 ] User can see a feed of recent user-generated photos
  - [X] Create posts feed fragment. (i.e. User Feed)
  - [x] User can see a feed of recent user-generated photos.
- [x] [ 7 ] List of user's "favorited" post returned.
  - [X] Create "favorited" post fragment. (i.e. Saved Post)
  - [ ] List of user's "favorited" post returned.
- [ ] [ 8 ] User can take a picture 
- [x] [ 10 ] User can search for event
  - [x] API returns a list of local events and popular posts that users can make.
  - [ ] Implement Search toolbar
- [ ] [ 13 ] User can filter search based on location. This is a simple, return of API data
- [x] [ 23 ] Users can see a personal feed of what they’ve posted
  - [X] Create profile fragment.
  - [x] User can see a personal feed of what they’ve posted


### 2. 🎥 Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https:// title='Video Walkthrough' width='' alt='Video Walkthrough' />


### 3. Screen Archetypes 
Screens
Splash
Register
Login
Home
Eventlist
Contact
Upload
Likes



#### Login Screen
* Required Stories
    * [ 2 ] User can login

#### Register Screen
* Required Stories
    * [ 1 ] User can register a new account

#### User Feed - Stream Screen
* Required Stories
    * [ 4 ] User can see a feed of recent user-generated photos and gif

* Optional
    * [ 24 ] User can like post
    * [ 5 ] User feed is filled with most popular content
    * [ 39 ] User can see a number of overall likes for each personal post
    * [ 40 ] User can see number of likes for each post from their tech friends (i.e. vouching from ppl they know)
    * [ 43 ] Businesses can post ads 


#### Search Screen - Stream Screen
* Required Stories
    * [ 7 ] API returns a list of events near  the user

#### EventsList Screen - Stream Screen
* Required Stories
    * [ 13 ] User can filter the search based on zip code, address, or state. This is a simple, return of API data


#### Creation Screen
* Required Stories
    * [ 8 ] User can take a picture

#### Profile Screen
* Required Stories
    * [ 23 ] Users can see a personal feed of what they’ve posted


### 4. Navigation 

#### Tab Navigation (Tab to Screen)

* Bottom Bar Navigation
    * Home Screen - Stream Screen
    * EventList Screen - Stream Screen
    * UpLoad Screen - Stream Screen
* Likes screen
    * Profile Screen

* Top Bar Navigation
    * Creation Screen


#### Flow Navigation (Screen to Screen)

**Login Screen**
* -> User Feed

**Register Screen**
* -> User Feed

**User Feed - Stream Screen**
* -> None, but future version might go to the Recipe Screen by tapping an image

**Search Screen - Stream Screen**
* -> Advanced Search Screen

**Creation Screen**
* -> Profile Screen (for now because the personal feed is a required story)
* -> Future versions may go to the User Feed (if we integrate stretch stories with the User Feed)

**Profile Screen**
* -> None
* -> Future versions may go to the Search Screen (If we maintain a persistent list of ingredients)
*# Product Spec

## Wireframes
![wireframe](jpg)



## Schema
### Models
#### User

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | username      | String   | unique username for the user |
   | password      | String   | password for user login |
   | profilePic    | File     | image for user profile |
   | createdAt     | DateTime | date when user is created (default field) |
   | updatedAt     | DateTime | date when user is last updated (default field) |
   
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| post author |
   | image         | File     | image that user posts |
   | description   | String   | post caption by author | 
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   

   

### Networking
#### List of network requests by screen
  - Login/Register Screen
      - (Read/GET) Query logged in user object
      - (Create/POST) Create a new user 
  - User Feed (Stream) Screen
      - (Read/GET) Query most recent posts
      - (Create/POST) Create a new "fond" on a post
      - (Delete/DEL) Delete a "fond" from a previously "fond"-ed post
  - Photo Capture Screen
      - (Create/POST) Create a new post object
  - Search (stream) Screen
      - No Parse interaction for this page     
  - Advanced Search (stream) Screen
      - No Parse interaction for this page
  - Saved Recipes (Stream) Screen
      - (Read/GET) Query user's favorited recipe
  - Profile Screen
      - (Read/GET) Query user's posts
      
#### Existing API Endpoints
##### 
- Base URL - [https://api.spoonacular.com](https://api.spoonacular.com)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    |/recipes/random | get random list of recipes
    `GET`    | /recipes/complexSearch?query={query} | return recipes by natural language search query
 

## 🔨 TechBook  is built with

  - [Retrofit](https://square.github.io/retrofit/)

## 😋 Authors
-- [Robin B.](https://github.com/hyperiusblake)
  - [Ana P.](https://github.com/kayabliss)
  - [Cory G.](https://github.com/mlapresta)
   - [Latifah P. ](https://github.com/latifahpresident)

## 🖥 Running Locally
  - Clone the repository: 
  - Open the project in the Android Studio
  -
  - Run the emulator.
  
## 📱 Phone Emulator
  - This application has been developed on a Pixel 5 XL emulator
