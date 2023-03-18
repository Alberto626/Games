# Description
A very simple TicTacToe game currently using Spring Boot. This includes Player vs Player, and Player vs BOT

With the use of Spring security, we were able to create a custom login and registration page.

In the future I plan to integrate multiple types of games instead of a single game such as Battleships.

### Stack Used
  *  Spring Security 
  * Spring Boot
* Javascript
  * Use of polling 
  * Fetch api
* Thymeleaf
  * Goes in hand with Spring
  * Send Model attributes that represent something from the backend and be able to display it in towards the front.
* HTML
* CSS
* MySQL

Created login/register system using Spring Security
![](src/main/resources/static/gifs/login.gif)

![](src/main/resources/static/gifs/register.gif)

With the use of Spring controllers, we were able to create restful apis that allowed a more dynamic and changeable frontend. This includes a very basic implementation of polling to constantly survey any changes a player makes and display it during its next rotation. 
![](src/main/resources/static/gifs/changestofront.gif)