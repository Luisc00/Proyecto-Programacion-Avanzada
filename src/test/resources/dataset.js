db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );
//Estos datos no nos sirven para la pruebas ya que no cifran la contraseña
db.Clientes.insertMany([
    {
        _id: 'Cliente1',
        fotoPerfil: 'mi foto',
        nickname: 'juanito',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        nombre: 'Juan',
        password: 'mipassword',
        email: 'juan@email.com',
        _class: 'co.edu.uniquindio.proyecto.modelo.Cliente'
    },
    {
        _id: 'Cliente2',
        fotoPerfil: 'mi foto',
        nickname: 'maria',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        nombre: 'Maria',
        password: 'mipassword',
        email: 'maria@email.com',
        _class: 'co.edu.uniquindio.proyecto.modelo.Cliente'
    },
    {
        _id: 'Cliente3',
        fotoPerfil: 'mi foto',
        nickname: 'pedrito',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        nombre: 'Pedro',
        password: 'mipassword',
        email: 'pedro@email.com',
        _class: 'co.edu.uniquindio.proyecto.modelo.Cliente'
    },
    {
        _id: 'Cliente4',
        fotoPerfil: 'mi foto',
        nickname: 'carlitos',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        nombre: 'CarlosP',
        password: 'mipassword',
        email: 'carlos@email.com',
        _class: 'co.edu.uniquindio.proyecto.modelo.Cliente'
    },
    {
        _id: 'Cliente5',
        fotoPerfil: 'mi foto',
        nickname: 'oscarin',
        ciudad: 'Armenia',
        estado: 'ACTIVO',
        nombre: 'oscar',
        password: 'mipassword',
        email: 'oscar@email.com',
        _class: 'co.edu.uniquindio.proyecto.modelo.Cliente'
    }

])
//MODERADORES
db.moderadores.insertMany([{
        _id: 'M1',
        nombre: 'Moderador1',
        password: 'Password',
        email: 'moderador1@GMAIL.COM',
        estadoRegistro: 'INACTIVO',
        _class: 'co.edu.uniquindio.proyecto.modelo.Moderador'
},{
    _id: '2',
    nombre: 'Moderador2',
    password: 'Password',
    email: 'Moderador2@GMAIL.COM',
    estadoRegistro: 'ACTIVO',
    _class: 'co.edu.uniquindio.proyecto.modelo.Moderador'
},{
    _id: '3',
    nombre: 'Moderador3',
    password: 'Password',
    email: 'Moderador3@GMAIL.COM',
    estadoRegistro: 'ACTIVO',
    _class: 'co.edu.uniquindio.proyecto.modelo.Moderador'
},{
    _id: '4',
    nombre: 'Moderador4',
    password: 'Password',
    email: 'moderador4@GMAIL.COM',
    estadoRegistro: 'ACTIVO',
    _class: 'co.edu.uniquindio.proyecto.modelo.Moderador'
},{
    _id: '5',
    nombre: 'Moderador5',
    password: 'Password',
    email: 'moderador5@GMAIL.COM',
    estadoRegistro: 'ACTIVO',
    _class: 'co.edu.uniquindio.proyecto.modelo.Moderador'
}])

//NEGOCIOS
db.Negocios.insertMany([{
    _id: 'Negocio1',
    ubicacion: {
        longitud: 213413455346,
        latitud: 1234326
    },
    nombre: 'El palacion del pandono ',
        descripcion: 'El mejor pandeono',
    horarios: [
        {
            horaFin: ISODate('2024-04-17T05:00:00.000Z'),
            horaInicio: ISODate('2024-04-17T17:00:00.000Z'),
            dia: 'Lunes-Sabado'
        }
    ],
    estadoNegocio: 'PENDIENTE',
    imagenes: [
        'imagen1.jpg',
        'imagen2.jpg',
        'imagen3.jpg'
    ],
    codigoCliente: 'Cliente1',
    tipoNegocio: 'PANADERIA',
    telefonos: [
        '7425169',
        '310248424'
    ],
    _class: 'co.edu.uniquindio.proyecto.modelo.Negocio'
   },{
    _id: 'Negocio2',
    ubicacion: {
        longitud: 2134134563,
        latitud: 1234323246
    },
    nombre: 'Discote la floresta',
    descripcion: 'disfruta',
    horarios: [
        {
            horaFin: ISODate('2024-04-17T05:00:00.000Z'),
            horaInicio: ISODate('2024-04-17T17:00:00.000Z'),
            dia: 'Lunes-viernes'
        }
    ],
    estadoNegocio: 'APROBADO',
    imagenes: [
        'imagen1.jpg',
        'imagen2.jpg',
        'imagen3.jpg'
    ],
    codigoCliente: 'Cliente2',
    tipoNegocio: 'DISCOTECA',
    telefonos: [
        '7425169',
        '310248424'
    ],
    _class: 'co.edu.uniquindio.proyecto.modelo.Negocio'
} ,{
    _id: 'Negocio3',
    ubicacion: {
        longitud: 2134134563,
        latitud: 1234323246
    },
    nombre: 'LA CASA DE LA ABUELA',
    descripcion: 'Almuerzos baratos',
    horarios: [
        {
            horaFin: ISODate('2024-04-17T05:00:00.000Z'),
            horaInicio: ISODate('2024-04-17T17:00:00.000Z'),
            dia: 'Lunes-viernes'
        }
    ],
    estadoNegocio: 'RECHAZADO',
    imagenes: [
        'imagen1.jpg',
        'imagen2.jpg',
        'imagen3.jpg'
    ],
    codigoCliente: 'CLIENTE3',
    tipoNegocio: 'RESTAURANTE',
    telefonos: [
        '7425169',
        '310248424'
    ],
    _class: 'co.edu.uniquindio.proyecto.modelo.Negocio'
},{
    _id: 'Negocio4',
    ubicacion: {
        longitud: 213413456233,
        latitud: 1234323233246
    },
    nombre: 'Mercado',
    descripcion: 'encuentra los mejores precios',
    horarios: [
        {
            horaFin: ISODate('2024-04-17T05:00:00.000Z'),
            horaInicio: ISODate('2024-04-17T17:00:00.000Z'),
            dia: 'Lunes-viernes'
        }
    ],
    estadoNegocio: 'PENDIENTE',
    imagenes: [
        'imagen1.jpg',
        'imagen2.jpg',
        'imagen3.jpg'
    ],
    codigoCliente: 'CLIENTE4',
    tipoNegocio: 'SUPERMERCADO',
    telefonos: [
        '7425169',
        '310248424'
    ],
    _class: 'co.edu.uniquindio.proyecto.modelo.Negocio'
},{
    _id: 'Negocio5',
    ubicacion: {
        longitud: 213413657323,
        latitud: 1234326346
    },
    nombre: 'Hotel',
    descripcion: 'La mejor estadia',
    horarios: [
        {
            horaFin: ISODate('2024-04-17T05:00:00.000Z'),
            horaInicio: ISODate('2024-04-17T17:00:00.000Z'),
            dia: 'Lunes-viernes'
        }
    ],
    estadoNegocio: 'PENDIENTE',
    imagenes: [
        'imagen1.jpg',
        'imagen2.jpg',
        'imagen3.jpg'
    ],
    codigoCliente: 'CLIENTE5',
    tipoNegocio: 'OTRO',
    telefonos: [
        '7425467642',
        '315242334'
    ],
    _class: 'co.edu.uniquindio.proyecto.modelo.Negocio'
}])
;
