document.addEventListener('DOMContentLoaded', function () {
    var backgroundMusic = document.getElementById('background-music');
    backgroundMusic.volume = '0.3';
    backgroundMusic.play();

    const mapContainer = document.getElementById('map-container');
    mapContainer.style.display = 'none';

    const loadder = document.getElementById('loader');
    loadder.style.display = 'flex';

    const urlParams = new URLSearchParams(window.location.search);

    const levelDifficult = urlParams.get('levelDifficult');
    const heightX = urlParams.get('heightX');
    const wideY = urlParams.get('wideY');

    let roverPositionX;
    let roverPositionY;
    let roverDirection;
    let size;

    const data = {
        levelDifficult: levelDifficult,
        heightX: heightX,
        wideY: wideY
    };

    // Enviar solicitud al backend
    fetch('http://localhost:8080/api/marsmap', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(marsMap => generateMap(marsMap))
        .catch(error => {
            console.error('Error al procesar la respuesta del servidor ¿-_-?:', error);
        });


    setTimeout(function () {
        loadder.style.display = 'none';
        mapContainer.style.display = 'flex';
    }, 3000);


    function generateMap(marsMap) {
        size = marsMap.wideY;

        const gridContainer = document.getElementById('grid-container');
        gridContainer.innerHTML = ''; // Limpiar cualquier grilla anterior

        gridContainer.style.gridTemplateColumns = `repeat(${size}, 1fr)`;

        for (let i = 0; i < size * size; i++) {
            const gridItem = document.createElement('div');
            gridItem.classList.add('grid-item');

            gridContainer.appendChild(gridItem);

            const image = document.createElement('img');
            image.classList.add('grid-image');
            image.src = '/src/main/resources/static/images/fondo.png';
            image.alt = 'Fondo';
            image.title = 'Paisaje marciano';
            gridItem.appendChild(image);
        }

        const gridItems = gridContainer.querySelectorAll('.grid-item');

        if (marsMap.finishLine) {
            const finishLine = marsMap.finishLine;
            const finishX = finishLine[0] - 1;
            const finishY = finishLine[1] - 1;

            const targetGridItem = gridItems[finishX * size + finishY];
            const newImageSrc = '/src/main/resources/static/images/FINISH_LINE.png';
            const imageElement = targetGridItem.querySelector('.grid-image');
            imageElement.src = newImageSrc;
            imageElement.alt = 'FINISH_LINE';
            imageElement.title = 'Finish line';
            targetGridItem.style.backgroundColor = '#e8d3c6b0';

        } else {
            console.log(" Finish Line empty");
        }

        if (marsMap.rover) {
            const roverMarsMap = marsMap.rover;
            roverPositionX = roverMarsMap.positionX - 1;
            roverPositionY = roverMarsMap.positionY - 1;
            roverDirection = roverMarsMap.direction;

            const roverGridItem = gridItems[roverPositionX * size + roverPositionY];

            const roverImage = `/src/main/resources/static/images/ROVER_${roverDirection}.png`;
            const imageElementRover = roverGridItem.querySelector('.grid-image');
            imageElementRover.src = roverImage;
            imageElementRover.alt = `ROVER_${roverDirection}`;
            imageElementRover.title = `Rover looking at ${roverDirection}`;
            roverGridItem.style.backgroundColor = 'rgba(56, 31, 12, 0.79)';

        } else {
            console.log(" Rover empty");
        }

        if (marsMap.martianObjects) {
            marsMap.martianObjects.forEach(obj => {
                const positX = obj.positionX - 1;
                const positY = obj.positionY - 1;

                const martianObjectGridItem = gridItems[positX * size + positY];
                const martianImage = `/src/main/resources/static/images/${obj.name}.png`;
                const imageElementMartian = martianObjectGridItem.querySelector('.grid-image');
                imageElementMartian.src = martianImage;
                imageElementMartian.alt = `${obj.name}`;
                imageElementMartian.title = `${obj.name}`;
            });
        } else {
            console.log(" Martian Objects empty.");
        }

    }

    const controlButtons = document.getElementById('button-container');

    controlButtons.addEventListener('click', function (event) {
        const buttonClicked = event.target;

        if (buttonClicked.id === 'forward-button' || buttonClicked.id === 'backward-button') {
            soundMoveRover();

            const action = buttonClicked.id === 'forward-button' ? 'F' : 'B';

            fetch('http://localhost:8080/api/marsmap/moving-rover', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: action
            })
                .then(response => response.json())
                .then(newMarsMap => {
                    // me fijo si gana primero
                    isWin(newMarsMap);

                    // me fijo si chocó, sino borro el rover anterior y creo el nuevo
                    isCrashed(newMarsMap);
                })
                .catch(error => {
                    console.error('Error al mover el rover:', error);
                });

        } else if (buttonClicked.id === 'left-button' || buttonClicked.id === 'right-button') {
            soundTurnRover();

            const action = buttonClicked.id === 'left-button' ? 'L' : 'R';

            fetch('http://localhost:8080/api/marsmap/turn-rover', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: action
            })
                .then(response => response.json())
                .then(newMarsMap => {

                    cleanRoverMap();
                    putRoverMap(newMarsMap);
                })
                .catch(error => {
                    console.error('Error al girar el rover:', error);
                });
        }

        function isCrashed(newMarsMap) {
            if (newMarsMap.rover.hasCrashed) {
                soundCrashRover();
                alert(" ¡¡ Careful !! You have crashed, try to go a new route..");
            } else {
                cleanRoverMap();
                putRoverMap(newMarsMap);
            }
        }

        function cleanRoverMap() {

            const gridContainer = document.getElementById('grid-container');
            const gridItems = gridContainer.querySelectorAll('.grid-item');
            const roverOldGridItem = gridItems[roverPositionX * size + roverPositionY];

            const imageFondo = `/src/main/resources/static/images/fondo.png`;
            const imageElementRover = roverOldGridItem.querySelector('.grid-image');
            imageElementRover.src = imageFondo;
            imageElementRover.alt = 'Fondo';
            imageElementRover.title = 'Paisaje marciano';

            roverOldGridItem.style.backgroundColor = '#c07244b0';
        }

        function putRoverMap(newMarsMap) {
            const rover = newMarsMap.rover;

            // Actualizo la posicion del rover en las variables globales
            roverPositionX = rover.positionX - 1;
            roverPositionY = rover.positionY - 1;
            roverDirection = rover.direction;

            // accedo a la nueva posicion del rover en la grilla
            const gridContainer = document.getElementById('grid-container');
            const gridItems = gridContainer.querySelectorAll('.grid-item');
            const roverGridItem = gridItems[roverPositionX * size + roverPositionY];

            const roverImage = `/src/main/resources/static/images/ROVER_${roverDirection}.png`;
            const imageElementRover = roverGridItem.querySelector('.grid-image');
            imageElementRover.src = roverImage;
            imageElementRover.alt = `ROVER_${roverDirection}`;
            imageElementRover.title = `Rover looking at ${roverDirection}`;
            roverGridItem.style.backgroundColor = 'rgba(56, 31, 12, 0.79)';
        }

        function isWin(newMarsMap) {
            const rover = newMarsMap.rover;

            if (newMarsMap.isWon) {
                soundWin();

                // Oculto el contenedor de las grillas y botones de jugar
                const mapContainer = document.getElementById('map-container');
                mapContainer.style.display = 'none';

                const mapInit = document.getElementById('init');
                mapInit.style.display = 'none';

                const winnerImage = document.createElement('img');
                winnerImage.src = '/src/main/resources/static/images/WELL_DONE.png';
                winnerImage.alt = "Win";
                winnerImage.title = 'Well done!';
                winnerImage.className = 'winner-image';

                const winOverlay = document.getElementById('win-overlay');

                const playAgainButton = document.createElement('button');
                playAgainButton.textContent = 'Play Again!';
                playAgainButton.id = 'play-again-button';

                winOverlay.appendChild(winnerImage);
                winOverlay.appendChild(playAgainButton);
                winOverlay.style.display = 'flex';

                playAgainButton.addEventListener('click', function () {
                    winOverlay.style.display = 'none';

                    const playAgain = document.getElementById('play-again');
                    playAgain.style.display = 'flex';

                    setTimeout(function () {
                        playAgain.style.display = 'none';
                        window.location.href = 'index.html';
                    }, 5000);

                    /*fetch(`http://localhost:8080/api/mars-map`, {
                       method: 'DELETE',
                       headers: {
                           'Content-Type': 'application/json'
                       }
                   })
                       .then(response => response.json())
                       .then(data => {

                           if (data.success) {
                               setTimeout(function () {
                                   window.location.href = 'index.html';
                               }, 10000);
                               
                           } else {
                               console.error('Error al reiniciar el juego:', data.error);
                           }
                       })
                       .catch(error => {
                           console.error('Error al reiniciar el juego:', error);
                       });*/
                });
            }
        }

        function soundMoveRover() {
            var soundMove = new Audio('/src/main/resources/static/sounds/mover.mp3');
            soundMove.play();
        }

        function soundTurnRover() {
            var soundTurn = new Audio('/src/main/resources/static/sounds/girar.mp3');
            soundTurn.play();
        }

        function soundCrashRover() {
            var soundCrash = new Audio('/src/main/resources/static/sounds/wololo.mp3');
            soundCrash.play();
        }

        function soundCrashedRelicRover() {
            var soundRelicCrash = new Audio('/src/main/resources/static/sounds/reliquia.mp3');
            soundCrashedRelicRover.play();
        }

        function soundWin() {
            var backgroundMusic = document.getElementById('background-music');
            backgroundMusic.pause();
            backgroundMusic.src = '/src/main/resources/static/sounds/ganar.mp3';

            backgroundMusic.play();
            backgroundMusic.volume = '0.8';
        }


    });







});