# Prueba de clean code ruleta



## Iniciar por scrips de docker
* primero hay que compilar la aplicacion
* Copiar la compilacion generada, `roulette-0.0.1-SNAPSHOT.war` a la carpeta `./docker/`
* cargar los valores del archivo `./docker/vars.sh` en las variables de entorno
* Ejecutar el archivo `./docker/build.sh`, este paso descarga la imagen de redis y construlle la de la aplicación
* Ejecutar el archivo `./docker/run.sh`, levanta por docker compose redis y la applicación

