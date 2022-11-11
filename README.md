# Lab2_TBD
Proyecto TBD Grupo 1
Prerequisitos
Para ejecutar el proyecto se debe contar con los siguientes prerequisitos:

-PostgreSQL 12 -Postgis 3.3 -Vue 3.2.38 -pgAdmin4 -IDE (Intellij) o Visual Studio Code -JDK version 17  -NodeJs 16.14

Descarga del proyecto
Se debe descargar el proyecto en su computador desde el repositorio de github con el siguiente link: https://github.com/LeoVergaraS/Lab2_TBD.git

Previo a la ejecución del proyecto se debe tener la base de datos creada efectivamente a traves de pgAdmin4 con el puerto 5432 con un database llamada "voluntarios".

Luego se debe agregar la extensión postgis 3, se puede hacer en pgAdmin4 de la siguiente manera:
![alt text](https://github.com/LeoVergaraS/Lab2_TBD/blob/main/add_ext1.png?raw=true)
![alt text](https://github.com/LeoVergaraS/Lab2_TBD/blob/main/add_ext2.png?raw=true)

Finalmente se debe restaurar la base de datos con un backup de dump data.
![alt text](https://github.com/LeoVergaraS/Lab2_TBD/blob/main/restore.png?raw=true)

Ejecución del proyecto
En Intellij se debe abrir la carpeta voluntarios-be y ejecutar el proyecto dando click en el boton verde de "play" de la parte superior derecha. Otra forma es dar click derecho en el archivo "VoluntariosBEApplication" y seleccionar la opcion run.

A continuación se debe abrir una consola o terminal ubicándose en la carpeta voluntarios-fe y ejecutar el comando:

npm install

Con esto se instalaran las herramientas necesarias para el funcionamiento del proyecto. Finalmente se debe ejeutar el comando:

npm run dev

De esta forma se levanta la pagina web en la url http://localhost:3000/ mostrando una pagina principal. Para mostrar las funcionalidades requeridas se debe seleccionar la seccion Tareas ubicada en la lista obtenida del boton superios izquierdo.
