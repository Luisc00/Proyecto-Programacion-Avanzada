db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );

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
    }
]);
