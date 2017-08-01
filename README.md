## Sistema Web RMSoft ##

La función principal del sistema es gestionar las habitaciones ya sea de un hotel, hostal o similares y poder realizar reservaciones vía online, el sistema maneja 3 roles el de usuario, administrador y recepcionista.

## Iniciar la Sistema

Para poder iniciar el sistema es necesaria una base de datos por defecto está configurado para trabajar con una base MySQL con el nombre `bd_rmsoft` con usuario `root` y password `mysql `.

El sistema crea automática 3 usuario por defecto con cada uno de los roles y 3 habitaciones de prueba.

```
// Administrador:
usuario: admin@admin.com
password: admin

// Recepcionista:
usuario: resep@resep.com
password: resep

// Usuario:
usuario: user@user.com
password: user
```

Puede cambiar los datos de conexión a la base de datos en el archivo ubicación `\src\main\resources\` llamdo `application.yml`.

```
url: jdbc:mysql://localhost:3306/nombre_base?useSSL=false
username: usuario
password: password
```

En caso de que desee utilizar una base de datos Postgres agregar la dependencia al `pom.xml`.

```
<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
</dependency>
```

Y modificar el url en el archivo `application.yml`.

```
url: jdbc:postgresql://localhost:5432/nombre_base
```

## Tecnologías Utilizadas

- [Spring](https://spring.io/)
- [Spring Boot](https://projects.spring.io/spring-boot/)

## Nota

Este proyecto fue desarrollado como trabajo educativo para la universidad por lo cual se omitieron algunos aspectos importantes como seguridad, confiabilidad, pruebas en otros y con los que debería contar un sistema para ponerlo en un ambiente de producción real.

## Licencia

Este proyecto está bajo la licencia MIT. Mira el archivo LICENSE.md para más información.