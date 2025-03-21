## 📌 Relational Schema Description

This diagram represents the structure of the database with five main tables:

- **USER**: Stores user information.
- **SHELTER**: Represents animal shelters.
- **ANIMAL**: Contains data about each animal and the shelter it belongs to.
- **CONTACT**: Records the contacts initiated by users with shelters.
- **ANSWER**: Stores the responses from shelters to users.

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

    USER ||--|| ANIMAL : "views"
    USER ||--o{ CONTACT : "contacts"
    CONTACT }o--|| SHELTER : "with"
    SHELTER ||--o{ ANSWER : "answers"
    ANSWER }o--|| USER : "to"
    SHELTER ||--o{ ANIMAL : "publishes"
```

---

📌 **Summary of relationships:**  
- A user **can view multiple animals**.  
- A user **can contact multiple shelters** and **vice versa**.  
- A shelter **can respond to multiple users**.  
- A shelter **can publish multiple animals**, but each animal **belong to only one shelter**.  

