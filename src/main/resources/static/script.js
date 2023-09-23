document.addEventListener('DOMContentLoaded', function () {

    const playButton = document.getElementById('play-button');

    playButton.addEventListener('click', function () {
        
        const mapSize = document.querySelector('input[name="map-size"]:checked');
        const difficulty = document.querySelector('input[name="difficulty"]:checked');

        if (!difficulty || !mapSize) {
            alert('Por favor, selecciona una dificultad.');
            return;
        }

        const mapSizeValue = mapSize.value;
        const [rows, cols] = mapSizeValue.split('x').map(str => parseInt(str, 10));
        const configuracionURL = `playochos.html?levelDifficult=${encodeURIComponent(difficulty.value)}&heightX=${rows}&wideY=${cols}`;
        window.location.href = configuracionURL;
        
    })

});

