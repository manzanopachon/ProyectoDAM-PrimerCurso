function navigateTo(section) {
    switch(section) {
        case 'pilotos':
            alert('Redirigiendo a Gestionar Pilotos...');
          	window.location.href = 'GestionPiloto.html';
            break;
        case 'aviones':
            alert('Redirigiendo a Gestionar Aviones...');
          	window.location.href = 'GestionAvion.html';
            break;
        case 'incidencias':
            alert('Redirigiendo a Gestionar Incidencias...');
            window.location.href = 'GestionIncidencia.html';
            break;
        case 'rutas':
            alert('Redirigiendo a Gestionar Rutas...');
            window.location.href = 'GestionRuta.html';
            break;
        default:
            alert('Sección no reconocida.');
    }
}
function irA(section,tipo) {
    switch(section) {
        case 'alta':
            alert('Vamos a dar de alta un '+tipo);
          	
            break;
        case 'baja':
            alert('Vamos a dar de baja un '+tipo);
          	
            break;
        case 'modificar':
            alert('Vamos a dar modificar un '+tipo);
            
            break;
        case 'ver':
            alert('Vamos a ver una lista de '+tipo+'s');
          
            break;
        default:
            alert('Sección no reconocida.');
    }
}