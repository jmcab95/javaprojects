# javaprojects

El proyecto cumple las restricciones establecidas.
La lectura de los tweets se realiza por medio de TwitterStream.
Se almacenan en una base de datos h2 en memoria. La cual se borra cuando paras el proyecto.
Esta implementado con Spring boot.
Además de los datos especificados para almacenar sobre un tweet, se ha decidido almacenar el idioma del tweet, el número de followers del usuario y los hashtags presentes en el tweet

El funcionamiento y los comandos se ejecutan por medio de llamadas al controller.
En este caso se ha decidido que haya dos: Uno StreamTwitterApi para realizar la lectura de tweets, inserción en la bbdd y cierre manual del stream de twitter.
y otro TweetApi, para las funcionalidades especificadas que debe realizar la API de:

  ● Consultar los tweets. 
  ● Marcar un tweet como validado.
  ● Consultar los tweets validados por usuario. 
  ● Consultar una clasificación de los N hashtags más usados (default 10).
  
  
  
El funcionamiento del proyecto es el siguiente:

1 - Primero habrá que importar este repositorio en SprintToolSuite.
2 - Ejecutamos el proyecto con springBoot

3- En primer lugar accederemos a los métodos presentes en StreamTwitterApi:
  - GET - http://localhost:8080/TwitterOperations/PopulateDB con esta llamada se realiza la conexión a Twitter y comenzamos a leer Tweets con las restricciones especificadas y guardarlos en BBDD
    Por defecto la llamada buscará y grabará tweets cuyos usuarios tengan mas de 1500 followers y en idioma español.
    http://localhost:8080/TwitterOperations/PopulateDB?numberFollowers=12000&language=fr,es,it , aquí vemos como podemos modificar esos valores por defectos para incluir otras restricciones a nivel de número de followers y de idiomas.
    Por defecto, esta llamada se ejecuturá constantemente mientras que accedemos a los métodos expuestos por el otro controller para tratamiento de los tweets en la bbdd
   - GET - http://localhost:8080/TwitterOperations/StopPopulateDB si en cualquier momento queremos detener el stream y guardado de tweets, utilizaremos esta llamada.
   
4- Posteriormente los métodos/funcionalidades de TweetApi que se corresponden con aquellos que utilizamos para manipular la bbdd.
  - GET - http://localhost:8080/TwitterRepository/getAllTweets Recuperaremos todos los tweets presentes en bbdd.
  - GET - http://localhost:8080/TwitterRepository/getAllValidatedTweets Recuperaremos los tweets validados por el usuario
  - GET - http://localhost:8080/TwitterRepository/getTweet/{tweetId} Recuperaremos los datos del tweet especificado por medio del id
  - PATCH - http://localhost:8080/TwitterRepository/validateTweet/{tweetId} Permitiremos al usuario validar los tweets del que queramos por medio del id. Se ha decidido implementar una funcionalidad PATCH y no PUT, porque simplemente modificabamos un campo y no toda la entity
  - GET - http://localhost:8080/TwitterRepository/getNTopHashtag Por defecto recuperaremos los 10 hashtags más utilizados entre los tweets que tengamos almacenados. si quisieramos obtener los N más utilizados bastaría con ejecutar http://localhost:8080/TwitterRepository/getNTopHashtag?nTop=N 
  
  
  
