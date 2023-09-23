<h1 align="center">
  <br>
  MARS ROVER GAME
  <br>
</h1>

<h4> 
Mars Rover Game, es una version adaptada de <a href="https://kata-log.rocks/mars-rover-kata" target="_blank"> Mars Rover Cata</a>. 
Este proyecto a sido parte de una entrega final para aprobar y rendir un examen final del BootCamp BackEnd Java en <b><a href="http://atl.academy" target="_blank"> ATL Academy</a></b> brindado por Lucas Moy.
<br>
En pos de que el proyecto tenga mas valor y utilidad, es que se ha implementado como un juego simple cuya finalidad es:
 de trasladar al objeto rover a una meta final, sin chocar con diversos objetos marcianos que se encuentran ene l camino, a traves de 2 movimientos: adelante y atras, y 2 giros: hacia la izquierda o hacia la derecha.
</h4>

## UML Diagram 

<p> </p>
![UML Diagram of Mars Rover Game] (https://github.com/Melisayunis/MarsRoverGame/blob/master/Mars%20Rover%20UML%20Diagram.png)

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
<p> The game in the browser.</p>
<li> To open the game in the browser, you will need to open Visual Studio Code, add the project tothe IDE, sourch the file <i>\Mars Rover\MarsRover\src\main\resources\static\index.html</i> and start Go Live for run live server.</li>
<li> And now you should see this image:</li>
![MarsRoverGameIniti] (https://github.com/Melisayunis/MarsRoverGame/blob/master/MarsRoverGameInit.png)
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
![]()
</li>

<li> You can play again by pressiong the <i>Play Again!</i> button.
![]()
</li>

</ul>

<><>
