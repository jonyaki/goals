# GOALS
Trate de implementar unos servicios para autenticación con JWT.  
Además explore el tema de "sanitización" y agregué algunos ejemplos.  
Por otro lado, quiero comentar que utilicé Spring Boot para facilitar la implementación de los servicios. Decidí no utilizar Spring Security ni crear configuraciones @Configuration para mantener un enfoque más generico y centrarme en estas bibliotecas.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 
- Auth0 JWT
- OWASP Encoder


## Endpoints de JWT
Para no hacer tedioso el testeo, puse el token en una coockie el cual hice que se inserte en **JWTUtil** esta cookie se llama **jwtToken**
##### POST /auth/login

Endpoint para iniciar sesión y obtener un token JWT.

- **Método:** POST
- **Body:** Para aclarar solo es importante el dato username, el password no se usa lo deje como ilustrativo ya que realmente no inicio una sesion pero este username lo uso para generar el token.
  ```json
  {
    "username": "Cualquier_dato",
    "password": "Cualquier_dato"
  }
  
#

#### GET /auth/token-validate

Endpoint para validar un token JWT.

- **Método:** GET
- **Parámetros:** No se requieren parámetros. El token se extrae automáticamente de la cookie **jwtToken**.
- **Respuesta exitosa:** Devolvera un 200 
- **Respuesta de error:** Va a devolver 401 Unauthorized, si el token no es válido, y un 500 si es que hubo algun error general.
  
  
  
## Endpoints de sanitizer
### Sin librearia:
#### GET /sanitizer/sanitize-string
Este endopoint sanitiza los caracteres dejando solamente ingresar letras y numeros, el resto seran eliminados.  
- **Método:** GET  
- **Ejemplo de parametro:**  ?inputString=file://11.22.11 
- **Respuesta con el ejemplo dado de parametro:** En este caso solo dejaria letras y numeros, tal que asi file112211. 


### Con librearia owasp:
#### GET /sanitizer/sanitize-html  
Este Endpoint utiliza una libreria para encriptar codigo html asi evitar injecciones de este codigo.  
- **Método:** GET  
- **Ejemplo de parametro:** `?htmlInput=<script>alert('Hello')</script>` 
- **Respuesta con el ejemplo dado de parametro:** codificaria el parametro dejandolo asi `&lt;script&gt;alert(&#39;Hello&#39;)&lt;/script&gt;`

#### GET /sanitizer/sanitize-java-script
Endpoint para sanitizar código JavaScript escapando caracteres especiales.
- **Método:** GET
- **Ejemplo de parametro:** ?jsInput=console.log('Hello World!')
- **Respuesta con el ejemplo dado de parametro:**  codificaria el parametro dejandolo asi `console.log(\x27Hello World!\x27)`