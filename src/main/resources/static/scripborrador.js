
document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('game-form');
    const playButton = document.getElementById('play-button');
    const resultDiv = document.getElementById('result');
    const mapTable = document.getElementById('map-table');

    let roverPositionX;
    let roverPositionY;
    let roverDirection;

    playButton.addEventListener('click', function () {
        const mapSize = document.querySelector('input[name="map-size"]:checked');
        const difficulty = document.querySelector('input[name="difficulty"]:checked');

        if (!difficulty || !mapSize) {
            alert('Por favor, selecciona una dificultad.');
            return;
        }

        const mapSizeValue = mapSize.value;
        const [rows, cols] = mapSizeValue.split('x').map(str => parseInt(str, 10));

        const data = {
            levelDifficult: difficulty.value,
            heightX: rows,
            wideY: cols
        };

 

        // Enviar solicitud al backend
        fetch('http://localhost:8080/api/mars-map', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(marsMap => generateMap(marsMap))
            .catch(error => {
                console.error('Error al procesar la respuesta del servidor:', error);
            });

        function generateMap(marsMap) {
            const heightX = marsMap.heightX;
            const wideY = marsMap.wideY;

            const [finishX, finishY] = marsMap.finishLine;

            for (let row = 1; row <= heightX; row++) {
                const tr = document.createElement('tr');

                for (let col = 1; col <= wideY; col++) {
                    const td = document.createElement('td');
                    td.textContent = ''; // Limpiar celda
                    tr.appendChild(td);
                }
                mapTable.appendChild(tr);
            }

            if (marsMap.finishLine) {
                const finishLine = marsMap.finishLine;
                const finishLineCell = mapTable.rows[finishLine[0] - 1].cells[finishLine[1] - 1];
                finishLineCell.innerHTML = '<img src="/images/FINISH_LINE.png" alt="FinishLine">';
            } else {
                console.log(" Finish Line empty");
            }

            if (marsMap.rover) {
                const roverMarsMap = marsMap.rover;
                const roverCell = mapTable.rows[roverMarsMap.positionX - 1].cells[roverMarsMap.positionY - 1];

                roverPositionX = roverMarsMap.positionX - 1;
                roverPositionY = roverMarsMap.positionY - 1;
                roverDirection = roverMarsMap.direction;

                const roverImage = document.createElement('img');
                roverImage.alt = "Rover";
                roverImage.src = `/images/ROVER_${roverDirection}.png`;
                roverCell.appendChild(roverImage);

            } else {
                console.log(" Rover empty");
            }

            if (marsMap.martianObjects) {
                marsMap.martianObjects.forEach(obj => {
                    const objCell = mapTable.rows[obj.positionX - 1].cells[obj.positionY - 1];
                    objCell.innerHTML = `<img src="/images/${obj.name}.png" alt="${obj.name}">`;
                });
            } else {
                console.log(" Martian Objects empty.");
            }

            // [{
            //"lockers": [],
            //"finishLine": null,
            //"martianObjects": [],
            // "rover": null,
            //"levelDifficult": "EASY",
            //"heightX": 8, 
            //"wideY": 8
            //}]

            const mapContainer = document.getElementById('map-container');
            const cellWidth = 300 / wideY;
            const cellHeight = 300 / heightX;

            mapContainer.style.gridTemplateColumns = `repeat(${numCols}, ${cellWidth}px)`;
            mapContainer.style.gridTemplateRows = `repeat(${numRows}, ${cellHeight}px)`;
            
            // Que aparezcan los botones para juagr: F, B, L, R
            document.getElementById('controls').style.display = 'block'; 
        }
    });


    const controlButtons = document.getElementById('controls');

    controlButtons.addEventListener('click', function (event) {
        const buttonClicked = event.target;

        if (buttonClicked.id === 'forward-button' || buttonClicked.id === 'backward-button') {

            // Si es F o B debo mover el rover asique -> Elimino el rover viejo
            const currentCell = mapTable.rows[roverPositionX].cells[roverPositionY];
            currentCell.innerHTML = ''; 

            const action = buttonClicked.id === 'forward-button' ? 'F' : 'B';

            fetch('http://localhost:8080/api/move-rover', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ action })
            })
                .then(response => response.json())
                .then(newMarsMap => {
                    // me fijo si gana primero
                    isWin(newMarsMap);

                    // borro el rover viejo y pongo el nuevo
                    cleanRoverMap()
                    putRoverMap(newMarsMap);
                })
                .catch(error => {
                    console.error('Error al mover el rover:', error);
                });

        } else if (buttonClicked.id === 'left-button' || buttonClicked.id === 'right-button') {

            const action = buttonClicked.id === 'left-button' ? 'L' : 'R';

            fetch('http://localhost:8080/api/turn-rover', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ action })
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

        function cleanRoverMap() {
            // Verificar si hay una imagen existente y eliminarla
            const roverCell = mapTable.rows[roverPositionX].cells[roverPositionY];

            const existingRoverImage = roverCell.querySelector('img');

            if (existingRoverImage) {
                roverCell.removeChild(existingRoverImage);
            }
        }

        function putRoverMap(newMarsMap) {
            const rover = newMarsMap.rover;

            // Actualizo la posicion del rover en las variables globales
            roverPositionX = rover.positionX - 1;
            roverPositionY = rover.positionY - 1;
            roverDirection = rover.direction;

            const roverCell = mapTable.rows[roverPositionX].cells[roverPositionY];

            const roverImage = document.createElement('img');
            roverImage.alt = "Rover";
            roverImage.src = `/images/ROVER_${roverDirection}.png`;

            roverCell.appendChild(roverImage);
        }

        function isWin(newMarsMap) {
            const id = newMarsMap.id;
            const rover = newMarsMap.rover;
            const [finishX, finishY] = marsMap.finishLine;

            if (finishX === rover.positionX && finishY === rover.positionY) {

                const mapContainer = document.getElementById('map-container');

                const winnerImage = document.createElement('img');
                winnerImage.src = '/images/WINNER.png';
                winnerImage.alt = "Win";
                winnerImage.className = 'winner-image'; // Agregar una clase CSS para estilizarla
                mapContainer.appendChild(winnerImage);

                mapContainer.appendChild(playAgainButton);

                const playAgainButton = document.createElement('button');
                playAgainButton.textContent = 'Volver a Jugar';
                playAgainButton.id = 'play-again-button';

                playAgainButton.addEventListener('click', function () {

                    const mapContainer = document.getElementById('map-container');
                    while (mapContainer.firstChild) {
                        mapContainer.removeChild(mapContainer.firstChild);
                    }

                    fetch(`http://localhost:8080/api/mars-map/${id}`, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                const winnerImage = document.querySelector('.winner-image');

                                if (winnerImage) {
                                    winnerImage.remove();
                                }
                                playAgainButton.remove();
                            } else {
                                console.error('Error al reiniciar el juego:', data.error);
                            }
                        })
                        .catch(error => {
                            console.error('Error al reiniciar el juego:', error);
                        });
                });
            }
        }


    });







});
