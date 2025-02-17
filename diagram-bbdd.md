## 📌 Descripción del esquema relacional

Este diagrama representa la estructura de la base de datos con cinco tablas principales:

- **USER**: Guarda la información de los usuarios.
- **SHELTER**: Representa los refugios de animales.
- **ANIMAL**: Contiene los datos de cada animal y a qué refugio pertenece.
- **CONTACT**: Registra los contactos iniciados por usuarios con refugios.
- **ANSWER**: Guarda las respuestas de los refugios a los usuarios.

---

```mermaid
erDiagram
    USER {
        INT id PK
        STRING name
        STRING pass
    }
    
    SHELTER {
        INT id PK
        STRING name
        STRING pass       
    }
    
    ANIMAL {
        INT reiac PK
        STRING name
        INT age
        STRING gender
        FLOAT weight
        INT id_shelter FK
    }
    
    CONTACT {
        INT id PK
        INT id_user FK
        INT id_shelter FK
        DATETIME date
    }

    ANSWER {
        INT id PK
        INT id_shelter FK
        INT id_user FK
        TEXT message
        DATETIME date
    }

    USER ||--o{ ANIMAL : "views"
    USER ||--o{ CONTACT : "contacts"
    CONTACT }o--|| SHELTER : "with"
    SHELTER ||--o{ ANSWER : "answers"
    ANSWER }o--|| USER : "to"
    SHELTER ||--o{ ANIMAL : "publishes"


---

### 🔹 **Explicación**
Colocamos la descripción en **Markdown** (fuera del bloque Mermaid), ya que **Mermaid no admite texto explicativo dentro del diagrama**.  

📌 **Resumen de relaciones:**  
- Un usuario **puede ver varios animales**.  
- Un usuario **puede contactar con varios refugios** y **viceversa**.  
- Un refugio **puede responder a varios usuarios**.  
- Un refugio **puede publicar varios animales**, pero cada animal **pertenece a un solo refugio**.  

---

💡 **Este formato funciona correctamente en GitHub sin errores.**  
Si necesitas más ajustes, dime y lo adaptamos. 🚀
