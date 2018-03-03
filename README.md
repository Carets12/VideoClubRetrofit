# VIDEOCLUB
Este proyecto fué desarrollado para la asignatura de Programación de Servicios y Procesos del Grado Superior de DAM del "IES Virgen del Carmen".

Consta de un App de android para un servicio REST usando Retrofit.
## Resumen
Esta aplicación android esta pensada para la gestión de un videoclub en la compra de peliculas, con ella podemos gestionar el control de las pelis así como actualizarlas, crearlas, borrarlas y buscarlas.

## Backend
Utilizamos node.js, mongoose, express y mongoDB para el servicio REST.<br/>
El esquema del backend:<br/>
  codigo      : {type : Number, unique : true, required : true},<br/>
  foto        : String,<br/>
  titulo      : {type : String, required : true},<br/>
  director    : String,<br/>
  precio      : {type : Number, required : true},<br/>
  anio        : {type : Number, required : true},<br/>
  stock       : {type : Number, required : true},<br/>
  descripcion : String<br/>
En la demostración de dicha aplicación use un servidor montado personalmente en casa con los puertos abiertos. Nota **Ahora el servidor esta apagado.

Endpoints:
<table>
    <tr>
        <th>URL</th>
        <th>HTTP</th>
        <th>BODY</th>
        <th>RESULT</th>
    </tr>
    <tr>
        <td>/peliculas</td>
        <td>GET</td>
        <td>-</td>
        <td>Se obtiene un array json de películas.</td>
    </tr>
    <tr>
        <td>/peliculas/codigo</td>
        <td>GET</td>
        <td>-</td>
        <td>Se obtiene una única película dado un código.</td>
    </tr>
    <tr>
        <td>/peliculas/codigo</td>
        <td>PUT</td>
        <td>JSON</td>
        <td>Actualiza una película existente dado un código.</td>
    </tr>
    <tr>
        <td>/peliculas</td>
        <td>POST</td>
        <td>JSON</td>
        <td>Se añade la película.</td>
    </tr>
    <tr>
        <td>/peliculas/codigo</td>
        <td>DELETE</td>
        <td>-</td>
        <td>Borra una película dado un código.</td>
    </tr>
</table>

