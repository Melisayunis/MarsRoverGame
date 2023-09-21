
document.addEventListener('DOMContentLoaded', function () {

    const urlParams = new URLSearchParams(window.location.search);
    
    const levelDifficult = urlParams.get('levelDifficult');
    const heightX = urlParams.get('heightX');
    const wideY = urlParams.get('wideY');



    const mapTable = document.getElementById('map-table');

    let roverPositionX;
    let roverPositionY;
    let roverDirection;

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
/*
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
*/
        const mapContainer = document.getElementById('map-container');

        const cellWidth = 200 / wideY;
        const cellHeight = 200 / heightX;

        mapContainer.style.gridTemplateColumns = `repeat(${wideY}, ${cellWidth}px)`;
        mapContainer.style.gridTemplateRows = `repeat(${heightX}, ${cellHeight}px)`;

        // [{
        //"lockers": [],
        //"finishLine": null,
        //"martianObjects": [],
        // "rover": null,
        //"levelDifficult": "EASY",
        //"heightX": 8, 
        //"wideY": 8
        //}]
    }

});