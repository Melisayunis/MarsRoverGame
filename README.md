<h1 align="center">
  <br> MARS ROVER GAME <br>
</h1>

<h4> 
Mars Rover Game, it is an adapted version of <a href="https://kata-log.rocks/mars-rover-kata" target="_blank"> Mars Rover Cata</a>, where an API was developed that translates commands sent from Earth into instructions that the rover understands. 
As indicated in Cata, edge wrapping and obstacle detection have been implemented. As well as the movement and turn format of the rover.

This proyect is the final delivery to pass and take a final exam of the BackEnd Java BootCamp at <b><a href="http://atl.academy" target="_blank"> ATL Academy</a></b> provided by Lucas Moy.
<br>
In order for the project to have more value and usefulness, it has been implemented as a simple game whose purpose is:
 Through movements and turns, move the rover across the map of Mars towards a final goal, without colliding with various Martian objects that are along the way. Taking into account where the rover is looking, you can make 2 movements: forward and backward; and 2 turns: left or right. You will win if you reach the finish line.
  
</h4>

## UML Diagram 

<p> Below is the diagram of relationship between the project classes:</p>

![UML Diagram of Mars Rover Game](https://github.com/Melisayunis/MarsRoverGame/blob/master/Mars%20Rover%20UML%20Diagram.png)

## EXECUTION

<ul style = "list-style-type:circle">
<p> Data base conection.</p>

<li> Open XAMPP app's and conect the ports: Apache and MySQL. </li>

<li> Open the next page <a href="localhost/phpmyadmin/" target="_blank"> localhost/phpmyadmin/</a> in your browser. </li>

<li> Create a local data base with name <b>roverbd</b>. </li>

<li> Checked if <i>application.properties</i> file's is correctly with the data of your daba base.</li>

<li> If everything is correct you can run Springboot in your IDE.
Follow these steps: Maven -> MarsRover -> Plugins -> spring-boot -> spring-boot:run 
<br> And now wait for spring to start. </li>
<br>
<p> Play the game in the browser.</p>
<li> To open the game in the browser, you will need to open Visual Studio Code, add the project tothe IDE, sourch the file <i>\Mars Rover\MarsRover\src\main\resources\static\index.html</i> and start Go Live for run live server.</li>
<li> And now you should see this image:</li>

![MarsRoverGameIniti](https://github.com/Melisayunis/MarsRoverGame/blob/master/MarsRoverGameInit.png)

<li> Start to play! Choose the Map size and the difficult and click to Play button. Now will be positioned randomly the Rover, Object Martian and the Finish Line. For example, this would be a view if you choose middle size and medium difficult: 
  
  ![PlayMiddleMediumGame](https://github.com/Melisayunis/MarsRoverGame/blob/master/PlayMiddleMediumGame.png) 

You can observe and distinguish the Rover because it a dark background , and the finish line it has a light background. </li>

<li> Move the rover with the buttons <i>Forward</i> and <i>Backward</i>. </li>

<li> Turn the rover with the buttons <i>Left</i> and <i>Right</i>. </li>

<li> <b> But pay attention to the direction the rover's yellow arrow points to move and turn. </b> 

  ![RoverDirections](https://github.com/Melisayunis/MarsRoverGame/blob/master/Rover%20Directions.png)
  
Also be careful with Martian objects, you don't want to crash!
![RoverCrash](https://github.com/Melisayunis/MarsRoverGame/blob/master/RoverCrash.png)
</li>

<li> You finally win when the rover lands on top of the finish line. Congratulations!

  ![YouWonWellDone](https://github.com/Melisayunis/MarsRoverGame/blob/master/YouWonPlayAgain.png)
</li>

<li> You can play again by pressiong the <i>Play Again! </i> button.</li>
</ul>

<br>
<footer> Thank you! By Asilem! </footer>
