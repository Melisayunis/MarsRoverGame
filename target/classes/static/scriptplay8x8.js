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

            const size = marsMap.wideY;

            const gridContainer = document.getElementById('grid-container');
            gridContainer.innerHTML = ''; // Limpiar cualquier grilla anterior
        
            gridContainer.style.gridTemplateColumns = `repeat(${size}, 1fr)`;
        
            for (let i = 0; i < size * size; i++) {
                const gridItem = document.createElement('div');
                gridItem.classList.add('grid-item');
                gridContainer.appendChild(gridItem);
            }



        }





});